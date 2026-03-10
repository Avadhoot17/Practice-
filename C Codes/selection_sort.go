package main

import "fmt"

func selectionSort(arr []int) {

	n := len(arr)

	for i := 0; i < n-1; i++ {

		min := i

		for j := i + 1; j < n; j++ {
			if arr[j] < arr[min] {
				min = j
			}
		}

		arr[i], arr[min] = arr[min], arr[i]
	}
}

func main() {

	arr := []int{29, 10, 14, 37, 13}

	fmt.Println("Original Array:", arr)

	selectionSort(arr)

	fmt.Println("Sorted Array:", arr)
}
