package main

import (
	"fmt"
	"sort"
)

func main() {

	arr := []int{5, 2, 9, 1, 7}

	fmt.Println("Original Array:", arr)

	sort.Sort(sort.Reverse(sort.IntSlice(arr)))

	fmt.Println("Descending Order:", arr)
}
