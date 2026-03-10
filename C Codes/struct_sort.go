package main

import (
	"fmt"
	"sort"
)

type Student struct {
	Name  string
	Marks int
}

func main() {

	students := []Student{
		{"Amit", 85},
		{"Rahul", 72},
		{"Neha", 90},
	}

	sort.Slice(students, func(i, j int) bool {
		return students[i].Marks < students[j].Marks
	})

	fmt.Println("Sorted by Marks:")

	for _, s := range students {
		fmt.Println(s.Name, s.Marks)
	}
}
