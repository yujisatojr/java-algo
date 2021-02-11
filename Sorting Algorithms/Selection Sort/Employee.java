public class Employee {
    private String name;
    public int id;
    private int age;
    private String jobTitle;
    private int year;

    public Employee(String line) {
        //System.out.println("DEBUGGING: " +line);
        String[] fields = line.split("\\|");
        name = fields[0];
        id = Integer.parseInt(fields[1]);
        age = Integer.parseInt(fields[2]);
        jobTitle = fields[3];
        year = Integer.parseInt(fields[4]);
    }

    @Override
    public String toString() {
        return name + "|" + id + "|" + age + "|" + jobTitle + "|" + year;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Employee) {
            Employee other = (Employee) o;
            return (this.id == other.id);
        }
        return false;
    }

    public int getID() {
        return id;
    }
}
