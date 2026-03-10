package main

import "fmt"

func countingSort(arr []int) []int {

	max := arr[0]

	for _, v := range arr {
		if v > max {
			max = v
		}
	}

	count := make([]int, max+1)

	for _, v := range arr {
		count[v]++
	}

	sorted := []int{}

	for i := 0; i <= max; i++ {
		for count[i] > 0 {
			sorted = append(sorted, i)
			count[i]--
		}
	}

	return sorted
}

func main() {

	arr := []int{4, 2, 2, 8, 3, 3, 1}

	fmt.Println("Original:", arr)

	result := countingSort(arr)

	fmt.Println("Sorted:", result)
}
