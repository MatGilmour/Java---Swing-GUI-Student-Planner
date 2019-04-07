
import java.util.Objects;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Student {

    private String first;
    private String last;
    private int studentNum;
    private Degree deg;
    Transcript transcript;
    private Attempt attempt;

    public Student() {
        this.first = null;
        this.last = null;
        this.studentNum = 0;
        this.deg = null;
        this.attempt = null;
    }
    /**@Author Mathieu **/


    public void setFirstName(String first) {
        if (first != null && !first.isEmpty())
            this.first = first;
    }
    /**@Author Daniel **/


    public void setLastName(String last) {
        if (last != null && !last.isEmpty())
            this.last = last;
    }
    /**@Author Mathieu **/


    public void setStudentNumber(Integer studentNum) { this.studentNum = studentNum; }
    /**@Author Daniel **/

    public String getFullName() {
        String fullName;
        if (this.first == null && this.last == null) {
            return null;
        } else if (this.first == null) {
            fullName = this.last;
        } else if (this.last == null) {
            fullName = this.first;
        } else {
            fullName = this.first + " " + this.last;
        }
        return fullName;
    }
    /**@Author Mathieu **/


    public void setDegreeProgram(Degree deg) {
        this.deg = deg;
    }
    /**@Author Daniel **/


    public void setTranscript(Transcript transcript){
        this.transcript = transcript;
    }
    /**@Author Mathieu **/


    public void setAttempt(String grade, String semester, String courseStatus){
        attempt.setCourseGrade(grade);
        attempt.setSemesterTaken(semester);
        attempt.setCourseStatus(courseStatus);
    }
    /**@Author Daniel **/


    public String getFirstName() { return this.first; }
    /**@Author Mathieu **/

    public String getLastName() { return this.last; }
    /**@Author Daniel **/

    public Integer getStudentNumber() { return this.studentNum; }
    /**@Author Mathieu **/

    public Degree getDegreeProgram() {
        return this.deg;
    }
    /**@Author Daniel **/

    public Attempt getAttempt(){
        attempt.getCourseGrade();
        attempt.getSemesterTaken();
        attempt.getCourseStatus();
        return attempt;
    }
    /**@Author Mathieu **/


    public Transcript getTranscript(){
        return this.transcript;
    }
    /**@Author Daniel**/

    @Override
    public String toString() {
        String toString = "";
        if (this.first != null) {
            toString = ("First name: " + this.first + System.getProperty( "line.separator" ));
        }
        if (this.last != null) {
            toString += ("Last name: " + this.last + System.getProperty( "line.separator" ));
        }
        toString += ("Student number: " + this.studentNum + System.getProperty( "line.separator" ));

        return toString;
    }
    /**@Author Mathieu **/


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Student)) {
            return false;
        }

        Student student = (Student) o;
        if (!(this.first.equals(student.first))){
            return false;
        }
        if (!(this.last.equals(student.last))){
            return false;
        }
        return this.studentNum == student.studentNum;
    }
    /**@Author Daniel **/


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.first);
        hash = 37 * hash + Objects.hashCode(this.last);
        hash = 37 * hash + Objects.hashCode(this.studentNum);
        return hash;
    }
    /**@Author Mathieu **/

}
