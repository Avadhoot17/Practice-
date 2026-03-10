package main

import (
	"fmt"
	"sort"
	"sync"
)

func sortArray(arr []int, wg *sync.WaitGroup) {
	defer wg.Done()
	sort.Ints(arr)
}

func main() {

	arr1 := []int{5, 3, 8, 1}
	arr2 := []int{9, 7, 2, 6}

	var wg sync.WaitGroup

	wg.Add(2)

	go sortArray(arr1, &wg)
	go sortArray(arr2, &wg)

	wg.Wait()

	fmt.Println("Sorted Array 1:", arr1)
	fmt.Println("Sorted Array 2:", arr2)
}
