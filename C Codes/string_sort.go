package main

import (
	"fmt"
	"sort"
)

func main() {

	names := []string{"Rahul", "Amit", "Sita", "John", "Neha"}

	fmt.Println("Original:", names)

	sort.Strings(names)

	fmt.Println("Sorted:", names)
}
