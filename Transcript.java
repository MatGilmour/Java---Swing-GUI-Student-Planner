import java.util.ArrayList;
import java.util.Objects;

public class Transcript {
    private ArrayList<Course> courses;
    private CourseCatalog catalogCopy;
    private Attempt attempt;

    public Transcript(){
        this.courses = new ArrayList<>();
        this.catalogCopy = new CourseCatalog();
        this.attempt = new Attempt();
    }
    /**@Author Mathieu **/


    public void setCourseStatus(String courseCode, String semester, String courseStatus, Transcript transcript) {
        for (Course c : this.courses) {
            if (c.getCourseCode() != null && attempt.getSemesterTaken() != null && c.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester)) {
                attempt.setCourseStatus(courseStatus);
            }
        }
    }
    /**@Author Mathieu **/

    public void setCourseGrade(String courseCode, String semester, String grade, Transcript transcript) {
        for (Course c : this.courses) {
            if (c.getCourseCode() != null && attempt.getSemesterTaken() != null && c.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester)) {
                attempt.setCourseGrade(grade);
                System.out.println("Grade updated.");
                return;
            }
        }
        System.out.println("Grade could not be updated.");
    }
    /**@Author Mathieu **/

    public void setCourses(ArrayList<Course> courses){
        this.courses = courses;
    }
    /**@Author Daniel **/

    public void setCatalog(CourseCatalog catalog) {
        this.catalogCopy = catalog;
    }
    /**@Author Daniel **/

    public ArrayList<Course> getCourses() { return this.courses; }
    /**@Author Daniel **/

    public CourseCatalog getCatalog() {
        return this.catalogCopy;
    }
    /**@Author Daniel **/

    public Course getCourse(String courseCode, String semester, Transcript transcript) {
        for (Course c : transcript.courses) {
            if (c.getCourseCode() != null && attempt.getSemesterTaken() != null && c.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester)) {
                return c;
            }
        }
        return null;
    }
    /**@Author Mathieu **/


    public Course findCourse(String courseCode) {
        Course found;
        if ((found = this.catalogCopy.findCourse(courseCode)) != null) {
            return found;
        }
        return null;
    }
    /**@Author Mathieu **/

    public void addCourse(String courseCode, String semester, Transcript transcript) {
        boolean alreadyAdded = false;
        for (Course code : transcript.catalogCopy.getCourseCatalog()) {
            if (courseCode.equals(code.getCourseCode())) {
                for (Course c : this.courses) {
                    if (c.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester)) {
                        alreadyAdded = true;
                        System.out.println("Already in the Plan of Study.");
                    }
                }
            }
        }
        if (!alreadyAdded) {
            Course found = isValidCourse(courseCode);
            if (found != null) {
                Course toAdd = new Course(found);
                attempt.setSemesterTaken(semester);
                attempt.setCourseStatus("Planned");
                System.out.println(toAdd.toString());
                this.courses.add(toAdd);
                System.out.println("Course added.");
            } else {
                System.out.println("No such course in the catalog.");
            }
        }
    }
    /**@Author Daniel **/

    private Course isValidCourse(String courseCode) {
        Course found = this.catalogCopy.findCourse(courseCode);
        if (found != null) {
            return found;
        }
        return null;
    }
    /**@Author Mathieu **/


    public void removeCourse(String courseCode, String semester,Transcript transcript) {
        for (Course c : transcript.courses) {
            if (c.getCourseCode() != null && attempt.getSemesterTaken() != null && c.getCourseCode().equals(courseCode) && attempt.getSemesterTaken().equals(semester)) {
                this.courses.remove(c);
                return;
            }
        }
    }
    /**@Author Daniel **/


    public double totalCredits(Transcript transcript) {
        double totalCredits = 0.0;
        for (Course c : transcript.courses) {
            if (attempt.getCourseStatus() != null && attempt.getCourseStatus().equals("Completed")) {
                totalCredits += c.getCourseCredit();
            }
        }
        return totalCredits;
    }
}
/**@Author Daniel **/
