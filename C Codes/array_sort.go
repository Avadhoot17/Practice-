// File: array_sort.go
package main

import (
	"fmt"
	"sort"
)

func main() {

	// Declare an array (slice in Go)
	arr := []int{45, 12, 89, 3, 67, 24, 9}

	fmt.Println("Original Array:")
	fmt.Println(arr)

	// Sorting the array
	sort.Ints(arr)

	fmt.Println("Sorted Array (Ascending):")
	fmt.Println(arr)
}
