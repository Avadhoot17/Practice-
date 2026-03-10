package main

import (
	"fmt"
	"sort"
)

func main() {

	arr := []int{10, 20, 4, 45, 99}

	sort.Ints(arr)

	secondLargest := arr[len(arr)-2]

	fmt.Println("Sorted Array:", arr)
	fmt.Println("Second Largest:", secondLargest)
}
