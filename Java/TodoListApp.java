import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {

    static class Task {
        String title;
        boolean isDone;

        Task(String title) {
            this.title = title;
            this.isDone = false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        int choice;

        do {
            System.out.println("\n--- TODO MENU ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Done");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter task title: ");
                    tasks.add(new Task(sc.nextLine()));
                    System.out.println("Task added");
                    break;

                case 2:
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks available");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            Task t = tasks.get(i);
                            System.out.println(
                                (i + 1) + ". " + t.title +
                                " [" + (t.isDone ? "Done" : "Pending") + "]"
                            );
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter task number: ");
                    int done = sc.nextInt() - 1;
                    if (done >= 0 && done < tasks.size()) {
                        tasks.get(done).isDone = true;
                        System.out.println("Marked as done");
                    } else {
                        System.out.println("Invalid task number");
                    }
                    break;

                case 4:
                    System.out.print("Enter task number: ");
                    int del = sc.nextInt() - 1;
                    if (del >= 0 && del < tasks.size()) {
                        tasks.remove(del);
                        System.out.println("Task deleted");
                    } else {
                        System.out.println("Invalid task number");
                    }
                    break;

                case 5:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        sc.close();
    }
}
