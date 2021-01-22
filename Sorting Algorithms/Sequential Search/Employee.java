public class Employee {
    String[] name;
    int[] id;
    int[] age;
    String[] job;
    int[] hiredDate;

    public Employee (int n) {
        name = new String[n];
        id = new int[n];
        age = new int[n];
        job = new String[n];
        hiredDate = new int[n];
    }
}
