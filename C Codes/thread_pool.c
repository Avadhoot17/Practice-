#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

// -------- Task --------
typedef struct {
    void (*function)(void *);
    void *arg;
} Task;

// -------- ThreadPool --------
typedef struct {

    pthread_t *threads;
    Task *queue;

    int thread_count;
    int queue_size;

    int front;
    int rear;
    int count;

    int shutdown;

    pthread_mutex_t mutex;
    pthread_cond_t not_empty;
    pthread_cond_t not_full;

} ThreadPool;

// -------- Worker --------
void *worker(void *arg)
{
    ThreadPool *pool = (ThreadPool *)arg;

    while (1)
    {
        pthread_mutex_lock(&pool->mutex);

        while (pool->count == 0 && !pool->shutdown)
            pthread_cond_wait(&pool->not_empty, &pool->mutex);

        if (pool->shutdown && pool->count == 0)
        {
            pthread_mutex_unlock(&pool->mutex);
            pthread_exit(NULL);
        }

        Task task = pool->queue[pool->front];

        pool->front = (pool->front + 1) % pool->queue_size;
        pool->count--;

        pthread_cond_signal(&pool->not_full);
        pthread_mutex_unlock(&pool->mutex);

        task.function(task.arg);
    }
}

// -------- Create Pool --------
ThreadPool *thread_pool_create(int thread_count, int queue_size)
{
    ThreadPool *pool = malloc(sizeof(ThreadPool));

    pool->thread_count = thread_count;
    pool->queue_size = queue_size;

    pool->threads = malloc(sizeof(pthread_t) * thread_count);
    pool->queue = malloc(sizeof(Task) * queue_size);

    pool->front = pool->rear = pool->count = 0;
    pool->shutdown = 0;

    pthread_mutex_init(&pool->mutex, NULL);
    pthread_cond_init(&pool->not_empty, NULL);
    pthread_cond_init(&pool->not_full, NULL);

    for (int i = 0; i < thread_count; i++)
        pthread_create(&pool->threads[i], NULL, worker, pool);

    return pool;
}

// -------- Submit Task --------
void thread_pool_submit(ThreadPool *pool, void (*function)(void *), void *arg)
{
    pthread_mutex_lock(&pool->mutex);

    while (pool->count == pool->queue_size)
        pthread_cond_wait(&pool->not_full, &pool->mutex);

    pool->queue[pool->rear].function = function;
    pool->queue[pool->rear].arg = arg;

    pool->rear = (pool->rear + 1) % pool->queue_size;
    pool->count++;

    pthread_cond_signal(&pool->not_empty);
    pthread_mutex_unlock(&pool->mutex);
}

// -------- Destroy Pool --------
void thread_pool_destroy(ThreadPool *pool)
{
    pthread_mutex_lock(&pool->mutex);

    pool->shutdown = 1;
    pthread_cond_broadcast(&pool->not_empty);

    pthread_mutex_unlock(&pool->mutex);

    for (int i = 0; i < pool->thread_count; i++)
        pthread_join(pool->threads[i], NULL);

    pthread_mutex_destroy(&pool->mutex);
    pthread_cond_destroy(&pool->not_empty);
    pthread_cond_destroy(&pool->not_full);

    free(pool->threads);
    free(pool->queue);
    free(pool);
}

// -------- Example Task --------
void example_task(void *arg)
{
    int num = *(int *)arg;

    printf("Task %d executed by thread %lu\n", num, pthread_self());

    sleep(1);

    free(arg);
}

// -------- Main --------
int main()
{
    ThreadPool *pool = thread_pool_create(4, 10);

    for (int i = 1; i <= 20; i++)
    {
        int *num = malloc(sizeof(int));
        *num = i;

        thread_pool_submit(pool, example_task, num);
    }

    sleep(6);

    thread_pool_destroy(pool);

    return 0;
}
