import java.io.FileWriter;
import java.util.ArrayList;

public abstract class Degree {
    String title;
    ArrayList<Course> courseList = new ArrayList<>();
    CourseCatalog courseCatalog;

    public Degree() {
        this.title = null;
        ArrayList<Course> courseList = new ArrayList<>();
        this.courseCatalog = new CourseCatalog();
    }
    /**@Author Mathieu **/


    public void saveState() {
        try (FileWriter PoSData = new FileWriter("BootstrapDegrees.txt", true)) {
            String fileLine = this.getDegreeTitle();
            fileLine += ", ";
            for (Course reqCourse : this.getRequiredCourses()) {
                fileLine += reqCourse.getCourseCode();
                fileLine += ",";
            }
            if (fileLine.substring(fileLine.length() - 1).equals(",")) {
                fileLine = fileLine.substring(0, fileLine.length() - 1);
            }
            fileLine += "?";
            PoSData.write(fileLine);
            PoSData.flush();
            PoSData.close();
            System.out.println("Saved Degree information.");
        } catch (Exception e) {
            System.out.println("Failed to successfully save state for Plan Of Study.");
        }
    }
    /**@Author Daniel **/

    protected void setDegreeTitle(String title) {
        if (title != null && !title.isEmpty())
            this.title = title;
    }
    /**@Author Daniel **/


    public String getDegreeTitle() { return this.title; }
    /**@Author Daniel **/

    protected void setRequiredCourses(ArrayList<Course> courseList) {
        if (courseList != null && !courseList.isEmpty())
            this.courseList = courseList;
    }
    /**@Author Mathieu **/

        public ArrayList<Course> getRequiredCourses()
        {
        	String string = null;
        	String[] stringList;
        	ArrayList<Course> courseList = new ArrayList<>();

        	if(getDegreeTitle() == "SEng") {
                string = "CIS*1250,CIS*1500,CIS*1910,CIS*2250,CIS*2500,CIS*2030," +
                        "CIS*2430,CIS*2520,CIS*3250,CIS*2750,CIS*3110," +
                        "CIS*3750,CIS*2460,STAT*2040,CIS*3760,CIS*3260,CIS*4150,CIS*4300,CIS*4250";
            }
            else if(getDegreeTitle() == "CS"){
                string = "MATH*1200,CIS*1500,CIS*1910,CIS*2500,CIS*2030," +
                        "CIS*2430,CIS*2520,CIS*2910,CIS*2750,CIS*3110,CIS*3490," +
                        "CIS*3150,CIS*3750,CIS*2460,STAT*2040,CIS*3760,CIS*4650";
            }
            else if(getDegreeTitle() == "BCG"){
                string = "CIS*1500,CIS*1910,CIS*2430,CIS*2500,CIS*2520,CIS*2750," +
                        "CIS*2910,CIS*3530";
            }
            stringList = string.split(",");
            for(String s : stringList){
                //System.out.println(s);
                Course currCourse = courseCatalog.findCourse(s);
                if(currCourse != null){
                    courseList.add(currCourse);
                }
            }
            return courseList;
        }
    /**@Author Daniel **/


    protected void setCatalog(CourseCatalog catalog) {
        this.courseCatalog = catalog;
    }
    /**@Author Mathieu **/


    public CourseCatalog getCatalog() {
        return this.courseCatalog;
    }
    /**@Author Mathieu **/


    public Course findDegreeCourse(String courseCode){
        for (Course c : courseList) {
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        return null;
    }
    /**@Author Daniel **/


    public abstract boolean meetsRequirements(Attempt attempt);
    /**@Author Daniel **/

    public abstract double numberOfCreditsRemaining(Attempt attempt);
    /**@Author Mathieu **/

    public abstract ArrayList<Course> remainingRequiredCourses(Attempt attempt);
    /**@Author Daniel **/

    @Override
    public abstract String toString();
    @Override
    public abstract boolean equals(Object o);
    @Override
    public abstract int hashCode();

}
