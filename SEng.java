import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class SEng extends HonoursDegree {
    private static final double maxOneSubjectCredits = 11.25;
    private static final double max1000LvlCredits = 6.00;
    private static final double rqrd3000orHigherCredits = 6.00;
    private static final double rqrdCisStat2000orHigherCredits = 0.5;
    private static final double maxAreaOfApplicationOrElective = 8.75;
    private Transcript transcript = new Transcript();

    public SEng() {
        super();
    }

    /**
     * @Author Mathieu
     **/


    public boolean meetsRequirements(Attempt attempt) {
        double totalCredits = 0.0, credits3000 = 0.0, credits1000 = 0.0, creditsSubject = 0.0, creditsCisStat2000 = 0.0;
        String[] courseCodeParts;
        for (Course c : transcript.getCourses()) {
            if (attempt.getCourseStatus().equals("Completed")) {
                courseCodeParts = c.getCourseCode().split("\\*", 2);
                if (courseCodeParts[0].equals("CIS")) {
                    creditsSubject += c.getCourseCredit();
                }
                if (Double.parseDouble(courseCodeParts[1]) < 2000) {
                    credits1000 += c.getCourseCredit();
                }
                if (Double.parseDouble(courseCodeParts[1]) >= 3000) {
                    credits3000 += c.getCourseCredit();
                }
                if ((courseCodeParts[0].equals("CIS") || courseCodeParts[0].equals("STAT")) && Double.parseDouble(courseCodeParts[1]) >= 2000) {
                    creditsCisStat2000 += c.getCourseCredit();
                }
                if (creditsSubject < maxOneSubjectCredits && credits1000 < max1000LvlCredits) {
                    totalCredits += c.getCourseCredit();
                }
            }
        }
        return totalCredits >= rqrdNumberOfCredits && credits3000 >= rqrd3000orHigherCredits && creditsCisStat2000 >= rqrdCisStat2000orHigherCredits;
    }

    /**
     * @Author Mathieu
     **/


    public double numberOfCreditsRemaining(Attempt attempt) {
        double remainingCredits = 0;
        boolean completed = false;
        CourseCatalog catalog = transcript.getCatalog();
        ArrayList<Course> courses = transcript.getCourses();
        for (Course c : courses) {
            if (!attempt.getCourseStatus().equals("Completed")) {
                if (!completed) {
                    remainingCredits += c.getCourseCredit();
                }
            }
        }
        return remainingCredits;
    }

    /**
     * @Author Daniel
     **/


    public ArrayList<Course> remainingRequiredCourses(Attempt attempt) {
        boolean completed = false;
        CourseCatalog catalog = transcript.getCatalog();
        ArrayList<Course> remainingRequiredCourses = new ArrayList<>();
        ArrayList<Course> courses = transcript.getCourses();
        for (Course needed : this.courseList) {
            for (Course c : courses) {
                if ((c.getCourseCode() != null && c.getCourseCode().equals(needed)) && (attempt.getCourseStatus() != null && attempt.getCourseStatus().equals("Completed"))) {
                    completed = true;
                    break;
                }
            }
            if (!completed) {
                if (catalog.findCourse(needed.getCourseCode()) != null) {
                    remainingRequiredCourses.add(catalog.findCourse(needed.getCourseCode()));
                } else {
                    System.out.println("Course not in catalog: " + needed);
                }
            }
            completed = false;
        }
        return remainingRequiredCourses;
    }

    /**
     * @Author Daniel
     **/


    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.title != null) {
            toString = new StringBuilder(("Code: " + this.title + System.getProperty("line.separator")));
        }
        if (this.courseList != null) {
            toString.append("Required Course Codes: ");
            for (Course s : courseList) {
                toString.append(s.getCourseCode()).append(" ");
            }
            toString.append(System.getProperty("line.separator"));
        }
        return toString.toString();
    }

    /**
     * @Author Mathieu
     **/


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Degree)) {
            return false;
        }

        BCG bcg = (BCG) o;
        if (!(this.title.equals(bcg.title))) {
            return false;
        }
        return this.courseList.equals(bcg.courseList);
    }

    /**
     * @Author Mathieu
     **/


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(getDegreeTitle());
        hash = 41 * hash + Objects.hashCode(this.courseList);
        return hash;
    }/**@Author Mathieu **/
}