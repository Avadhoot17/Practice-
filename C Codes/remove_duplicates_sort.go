package main

import (
	"fmt"
	"sort"
)

func removeDuplicates(arr []int) []int {

	sort.Ints(arr)

	result := []int{arr[0]}

	for i := 1; i < len(arr); i++ {

		if arr[i] != arr[i-1] {
			result = append(result, arr[i])
		}
	}

	return result
}

func main() {

	arr := []int{4, 5, 2, 2, 3, 3, 1}

	fmt.Println("Original:", arr)

	res := removeDuplicates(arr)

	fmt.Println("Sorted without duplicates:", res)
}
