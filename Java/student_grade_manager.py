students = {}

while True:
    print("\n1. Add Student")
    print("2. View Students")
    print("3. Exit")

    choice = input("Enter choice: ")

    if choice == "1":
        name = input("Enter student name: ")
        marks = int(input("Enter marks: "))
        students[name] = marks
        print("Student added successfully!")

    elif choice == "2":
        print("\nStudent Records:")
        for name, marks in students.items():
            print(f"{name} : {marks}")

    elif choice == "3":
        break

    else:
        print("Invalid choice")
