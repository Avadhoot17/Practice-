record Student(String name, int marks) {}

public class StudentRecordDemo {
    public static void main(String[] args) {
        Student s = new Student("Avadhoot", 90);

        System.out.println(s.name());
        System.out.println(s.marks());
        System.out.println(s);
    }
}
