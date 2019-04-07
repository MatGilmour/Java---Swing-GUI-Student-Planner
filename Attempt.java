import java.util.ArrayList;
import java.util.Objects;

public class Attempt {

    private Course courses;
    private String grade;
    private String semester;
    private String courseStatus;

    public Attempt(){
        this.courseStatus = null;
        this.grade = null;
        this.semester = null;
        this.courses = new Course();
    }
    /**@Author Mathieu **/



    public Attempt(Attempt attempt) {
        this.courseStatus = attempt.getCourseStatus();
        this.grade = attempt.getCourseGrade();
        this.semester = attempt.getSemesterTaken();
    }
    /**@Author Daniel **/


    public void setCourseStatus(String courseStatus) {
        if (courseStatus != null && !courseStatus.isEmpty()) {
            this.courseStatus = courseStatus;
        }
    }
    /**@Author Mathieu **/

    public void setCourseGrade(String grade) {
        if (grade == null) {
            this.grade = null;
            return;
        }

        int gradeNum;
        try {
            gradeNum = Integer.parseInt(grade);
            if (gradeNum <= 100 && gradeNum >= 0) {
                this.grade = grade;
            }
        } catch (Exception ignored) {
            System.out.println("Grades must be between 0 and 100.");
        }
    }
    /**@Author Daniel **/

    public void setSemesterTaken(String semester) {
        if (semester != null && !semester.isEmpty()) {
            this.semester = semester;
        }
    }
    /**@Author Mathieu **/

    public void setCourses(Course courses){
        this.courses = courses;
    }

    public String getCourseStatus() { return this.courseStatus; }
    /**@Author Daniel **/


    public String getCourseGrade() { return this.grade; }
    /**@Author Daniel **/

    public String getSemesterTaken() { return this.semester; }
    /**@Author Mathieu **/

    public Course getCourses() { return this.courses; }
    /**@Author Mathieu **/


    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.courseStatus != null) {
            toString.append("Status: ").append(this.courseStatus).append(System.getProperty("line.separator"));
        }
        if (this.grade != null) {
            toString.append("Grade: ").append(this.grade).append("\n");
        }
        if (this.semester != null) {
            toString.append("Semester Taken: ").append(this.semester).append(System.getProperty("line.separator"));
        }
        return toString.toString();
    }
    /**@Author Mathieu **/


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Course)) {
            return false;
        }

        if (courseStatus == null || !(this.courseStatus.equals(courseStatus))) {
            return false;
        }
        if (this.grade != null && grade != null) {
            if (!(this.grade.equals(grade))) {
                return false;
            }
        }
        if (this.semester != null && semester != null) {
            if (!(this.semester.equals(semester))) {
                return false;
            }
        }
        return false;
    }
    /**@Author Daniel **/

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.courseStatus);
        hash = 53 * hash + Objects.hashCode(this.grade);
        hash = 53 * hash + Objects.hashCode(this.semester);
        return hash;
    }
    /**@Author Mathieu **/

}
