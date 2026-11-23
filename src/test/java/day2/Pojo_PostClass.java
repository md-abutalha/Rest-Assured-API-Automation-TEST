package day2;

public class Pojo_PostClass {
    String name;
    String location;
    String phone;
    String courses[];

    // Getter & setter created
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getCourses() {
        return courses;
    }

    // FIXED: Accepts a String array directly
    public void setCourses(String[] courses) {
        this.courses = courses;
    }
}