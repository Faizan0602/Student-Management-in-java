package Assignment;

public class Student {
    private String name;
    private String email;
    private String course;
    private int score;

    public Student(String name, String email, String course, int score) {
        this.name = name;
        this.email = email;
        this.course = course;
        this.score = score;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public int getScore() { return score; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCourse(String course) { this.course = course; }
    public void setScore(int score) { this.score = score; }

    @Override
    public String toString() {
        String cleanedName = name.replace(",", ";");
        String cleanedEmail = email.replace(",", ";");
        String cleanedCourse = course.replace(",", ";");
        return String.format("%s,%s,%s,%d", cleanedName, cleanedEmail, cleanedCourse, score);
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid student data format: " + line);
        }
        String name = parts[0];
        String email = parts[1];
        String course = parts[2];
        int score;
        try {
            score = Integer.parseInt(parts[3]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid score format: " + parts[3], e);
        }
        return new Student(name, email, course, score);
    }
}
