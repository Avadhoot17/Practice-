#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <sys/epoll.h>

#define PORT 8080
#define MAX_EVENTS 1024
#define BUFFER_SIZE 4096

// -------- Set socket non-blocking --------
int set_nonblocking(int fd) {
    int flags = fcntl(fd, F_GETFL, 0);
    return fcntl(fd, F_SETFL, flags | O_NONBLOCK);
}

// -------- Send simple HTTP response --------
void send_http_response(int client_fd) {
    const char *response =
        "HTTP/1.1 200 OK\r\n"
        "Content-Type: text/plain\r\n"
        "Content-Length: 13\r\n"
        "\r\n"
        "Hello, World!";

    send(client_fd, response, strlen(response), 0);
}

// -------- Main --------
int main() {
    int server_fd, epoll_fd;
    struct sockaddr_in address;
    struct epoll_event ev, events[MAX_EVENTS];

    // Create socket
    server_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (server_fd < 0) {
        perror("socket");
        exit(EXIT_FAILURE);
    }

    set_nonblocking(server_fd);

    // Bind
    address.sin_family = AF_INET;
    address.sin_addr.s_addr = INADDR_ANY;
    address.sin_port = htons(PORT);

    if (bind(server_fd, (struct sockaddr *)&address, sizeof(address)) < 0) {
        perror("bind");
        exit(EXIT_FAILURE);
    }

    // Listen
    if (listen(server_fd, SOMAXCONN) < 0) {
        perror("listen");
        exit(EXIT_FAILURE);
    }

    // Create epoll instance
    epoll_fd = epoll_create1(0);
    if (epoll_fd < 0) {
        perror("epoll_create1");
        exit(EXIT_FAILURE);
    }

    // Add server socket to epoll
    ev.events = EPOLLIN;
    ev.data.fd = server_fd;
    epoll_ctl(epoll_fd, EPOLL_CTL_ADD, server_fd, &ev);

    printf("High-performance HTTP server running on port %d...\n", PORT);

    while (1) {
        int n = epoll_wait(epoll_fd, events, MAX_EVENTS, -1);

        for (int i = 0; i < n; i++) {

            // -------- New connection --------
            if (events[i].data.fd == server_fd) {
                int client_fd = accept(server_fd, NULL, NULL);
                if (client_fd >= 0) {
                    set_nonblocking(client_fd);

                    ev.events = EPOLLIN | EPOLLET;
                    ev.data.fd = client_fd;
                    epoll_ctl(epoll_fd, EPOLL_CTL_ADD, client_fd, &ev);
                }
            }

            // -------- Client request --------
            else {
                int client_fd = events[i].data.fd;
                char buffer[BUFFER_SIZE];

                int bytes = read(client_fd, buffer, BUFFER_SIZE - 1);

                if (bytes <= 0) {
                    close(client_fd);
                } else {
                    buffer[bytes] = '\0';
                    printf("Request:\n%s\n", buffer);

                    send_http_response(client_fd);
                    close(client_fd);
                }
            }
        }
    }

    close(server_fd);
    return 0;
}
