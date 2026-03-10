package main

import (
	"fmt"
	"sort"
)

func main() {

	var n int

	fmt.Print("Enter number of elements: ")
	fmt.Scan(&n)

	arr := make([]int, n)

	fmt.Println("Enter elements:")

	for i := 0; i < n; i++ {
		fmt.Scan(&arr[i])
	}

	sort.Ints(arr)

	fmt.Println("Sorted Array:", arr)
}
