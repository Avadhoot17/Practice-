#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

#define QUEUE_SIZE 10
#define THREAD_COUNT 4

// -------- Task structure --------
typedef struct {
    void (*function)(void *);
    void *arg;
} Task;

// -------- Thread pool structure --------
typedef struct {
    pthread_t threads[THREAD_COUNT];
    Task queue[QUEUE_SIZE];

    int front;
    int rear;
    int count;

    pthread_mutex_t mutex;
    pthread_cond_t not_empty;
    pthread_cond_t not_full;

    int shutdown;
} ThreadPool;

ThreadPool pool;

// -------- Worker function --------
void *worker(void *arg) {
    while (1) {
        pthread_mutex_lock(&pool.mutex);

        while (pool.count == 0 && !pool.shutdown)
            pthread_cond_wait(&pool.not_empty, &pool.mutex);

        if (pool.shutdown) {
            pthread_mutex_unlock(&pool.mutex);
            pthread_exit(NULL);
        }

        Task task = pool.queue[pool.front];
        pool.front = (pool.front + 1) % QUEUE_SIZE;
        pool.count--;

        pthread_cond_signal(&pool.not_full);
        pthread_mutex_unlock(&pool.mutex);

        task.function(task.arg);
    }
}

// -------- Initialize thread pool --------
void thread_pool_init() {
    pool.front = pool.rear = pool.count = 0;
    pool.shutdown = 0;

    pthread_mutex_init(&pool.mutex, NULL);
    pthread_cond_init(&pool.not_empty, NULL);
    pthread_cond_init(&pool.not_full, NULL);

    for (int i = 0; i < THREAD_COUNT; i++)
        pthread_create(&pool.threads[i], NULL, worker, NULL);
}

// -------- Add task to queue --------
void thread_pool_submit(void (*function)(void *), void *arg) {
    pthread_mutex_lock(&pool.mutex);

    while (pool.count == QUEUE_SIZE)
        pthread_cond_wait(&pool.not_full, &pool.mutex);

    pool.queue[pool.rear].function = function;
    pool.queue[pool.rear].arg = arg;
    pool.rear = (pool.rear + 1) % QUEUE_SIZE;
    pool.count++;

    pthread_cond_signal(&pool.not_empty);
    pthread_mutex_unlock(&pool.mutex);
}

// -------- Shutdown pool --------
void thread_pool_shutdown() {
    pthread_mutex_lock(&pool.mutex);
    pool.shutdown = 1;
    pthread_cond_broadcast(&pool.not_empty);
    pthread_mutex_unlock(&pool.mutex);

    for (int i = 0; i < THREAD_COUNT; i++)
        pthread_join(pool.threads[i], NULL);

    pthread_mutex_destroy(&pool.mutex);
    pthread_cond_destroy(&pool.not_empty);
    pthread_cond_destroy(&pool.not_full);
}

// -------- Example task --------
void print_task(void *arg) {
    int num = *(int *)arg;
    printf("Task %d executed by thread %lu\n", num, pthread_self());
    sleep(1);
    free(arg);
}

// -------- Main --------
int main() {
    thread_pool_init();

    for (int i = 1; i <= 20; i++) {
        int *num = malloc(sizeof(int));
        *num = i;
        thread_pool_submit(print_task, num);
    }

    sleep(5); // allow tasks to finish
    thread_pool_shutdown();

    return 0;
}
