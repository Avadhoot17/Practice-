class Library:
    def __init__(self):
        self.books = []

    def add_book(self, book):
        self.books.append(book)
        print(f"{book} added successfully!")

    def show_books(self):
        print("\nAvailable Books:")
        for book in self.books:
            print(book)

library = Library()

while True:
    print("\n1. Add Book")
    print("2. Show Books")
    print("3. Exit")

    choice = input("Enter choice: ")

    if choice == "1":
        book = input("Enter book name: ")
        library.add_book(book)

    elif choice == "2":
        library.show_books()

    elif choice == "3":
        break

    else:
        print("Invalid choice")
