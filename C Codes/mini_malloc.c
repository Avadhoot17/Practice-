#include <stdio.h>
#include <unistd.h>
#include <string.h>

// -------- Block metadata --------
typedef struct block {
    size_t size;
    int free;
    struct block *next;
} block_t;

#define BLOCK_SIZE sizeof(block_t)

static block_t *free_list = NULL;

// -------- Find free block --------
block_t *find_free_block(size_t size) {
    block_t *curr = free_list;

    while (curr) {
        if (curr->free && curr->size >= size)
            return curr;
        curr = curr->next;
    }
    return NULL;
}

// -------- Request space from OS --------
block_t *request_space(size_t size) {
    block_t *block = sbrk(0);
    void *request = sbrk(size + BLOCK_SIZE);

    if (request == (void *)-1)
        return NULL;

    block->size = size;
    block->free = 0;
    block->next = NULL;

    return block;
}

// -------- Custom malloc --------
void *mini_malloc(size_t size) {
    if (size <= 0)
        return NULL;

    block_t *block;

    if (!free_list) {
        block = request_space(size);
        if (!block) return NULL;
        free_list = block;
    } else {
        block = find_free_block(size);

        if (!block) {
            block_t *last = free_list;
            while (last->next)
                last = last->next;

            block = request_space(size);
            if (!block) return NULL;

            last->next = block;
        } else {
            block->free = 0;
        }
    }

    return (block + 1); // memory after metadata
}

// -------- Custom free --------
void mini_free(void *ptr) {
    if (!ptr) return;

    block_t *block = (block_t *)ptr - 1;
    block->free = 1;
}

// -------- Demo --------
int main() {
    printf("Allocating memory...\n");

    int *arr = (int *)mini_malloc(5 * sizeof(int));

    for (int i = 0; i < 5; i++)
        arr[i] = i * 10;

    for (int i = 0; i < 5; i++)
        printf("%d ", arr[i]);

    printf("\nFreeing memory...\n");
    mini_free(arr);

    // Reuse freed memory
    char *msg = (char *)mini_malloc(20);
    strcpy(msg, "Hello MiniMalloc");
    printf("%s\n", msg);

    return 0;
}
