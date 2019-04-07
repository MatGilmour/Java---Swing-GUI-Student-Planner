import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Planner extends JFrame {

    /*Class Object declarations*/
    Student student = new Student();
    ArrayList<String> studentInfo = new ArrayList<>();
    ArrayList<String> degMinInfo = new ArrayList<>();
    ArrayList<Course> requiredCourses = new ArrayList<>();
    CourseCatalog courseCatalog = new CourseCatalog();
    Transcript transcript = new Transcript();
    ArrayList<String> attemptList = new ArrayList<>();
    ArrayList<Course> courseList = new ArrayList<>();
    double currCredits = 0.0;
    double GPA = 0;


    /*StartUp UI*/
    private JPanel startP = new JPanel();
    private JPanel labelP = new JPanel();
    private JLabel welcomeL = new JLabel("Welcome, Please Sign In");
    private JButton adminB = new JButton("Admin");
    private JButton userB = new JButton("User");

    /*User and Admin screens*/
    private JPanel inputP = new JPanel();
    private JLabel userIDL = new JLabel("Please enter your name and ID");
    private JLabel userL = new JLabel("First Name: ");
    private JLabel IDL = new JLabel("ID#: ");
    private JTextField userText = new JTextField(10);
    private JTextField IDText = new JTextField(10);
    private JButton signInB = new JButton("Sign in");
    private JLabel lastL = new JLabel("Last Name: ");
    private JTextField lastText = new JTextField(10);

    /*Home*/
    private JPanel homeP = new JPanel();
    private JLabel currStuL = new JLabel("Student Information: ");
    private JTextArea currStuArea = new JTextArea("No current Student", 10, 20);
    private JLabel currDegMinL = new JLabel("Student Degree/Major: ");
    private JTextArea currDegMinArea = new JTextArea("No current Degree\nNo current Major", 10, 10);
    private JButton selectDegreeB = new JButton("Select Degree");
    private JButton selectMajorB = new JButton("Select Major");
    private JButton remainPreReqB = new JButton("Remaining Credits");
    private JButton listReqCoursesB = new JButton("List Required Courses");
    private JButton addRemoveCourseB = new JButton(" Add Courses");
    private JButton addRemoveChangeGradeB = new JButton("Add/Remove/Change Grades");
    private JButton saveProgramB = new JButton("SAVE");
    private JButton viewUnknownCoursesB = new JButton("View Unknown Courses");
    private JButton viewIncompleteCoursesB = new JButton("View Incomplete Courses");
    private JButton viewCurrReqCreditsB = new JButton("View Current Required Credits");
    private JButton viewPreReqs4CourseB = new JButton("View Prereqs for courses");
    private JButton viewCreditsB = new JButton("View Credits");
    private JButton viewGpaB = new JButton("View GPA");
    private JButton determineCompletionB = new JButton("Determine Completion");
    private JButton viewCoursePlanB = new JButton("View Course Plan");
    private JLabel currTranscriptL = new JLabel("Transcript for Student: ");
    private JTextArea currTranscript = new JTextArea("No current Courses", 10, 20);

    /*Degree UI*/
    private JLabel degreeL = new JLabel("Degrees: ");
    private String[] degreeList = {"BComp Honours", "BComp General"};
    private JComboBox degreeChoice = new JComboBox(degreeList);
    private JButton submitDegree = new JButton("Submit");
    private JPanel degreeP = new JPanel();

    /*Major UI*/
    private JPanel majorP = new JPanel();
    private JLabel majorL = new JLabel("Major: ");
    private JLabel majorChoiceL = new JLabel("Prerequisites for Major of Choice");
    private JButton majorB = new JButton("Choose Major");
    private JTextArea majorText = new JTextArea(10,30);
    private JComboBox majorChoice = new JComboBox();

    /*Add or Remove Course UI*/
    private JPanel addRemoveP = new JPanel();
    private JPanel addRemoveFinalP = new JPanel();
    private JLabel chooseActionL = new JLabel("Choose an Action: ");
    private JButton addCourseB = new JButton("Add Course");
    private JButton removeCourseB = new JButton("Remove Course");
    private JLabel chooseCourseL = new JLabel("Choose a Course Code: ");
    private JTextField addCourseText = new JTextField(10);
    private JButton addSelectedCourseB = new JButton("Confirm");
    private JLabel currGrade = new JLabel("Input Grade: ");
    private JTextField gradeText = new JTextField(10);
    private JLabel currSemester = new JLabel("Semester of choice: ");
    private JComboBox semesterBox = new JComboBox();
    private JLabel currStatus = new JLabel("Choose Status");
    private JComboBox statusBox = new JComboBox();

    public Planner() {
        super();
        courseCatalog.initializeCatalog("C:/Users/Mathi/Documents/src/courseListA2.txt");
        gui();
    }
    /**@Author Daniel **/

    public void gui(){
        setSize(600,400);
        setTitle("Welcome!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        startUp();
        adminOrUser();
        Home();
        chooseDegree();
        chooseMajor();
        addRemoveCourse();
        pack();
    }
    /**@Author Mathieu **/


    private void startUp(){
        startP.setVisible(false);
        labelP.setVisible(false);

        startP.setLayout(new BoxLayout(startP, BoxLayout.X_AXIS));
        labelP.setLayout(new CardLayout(5,5));

        labelP.add(welcomeL);
        startP.add(adminB);
        startP.add(userB);

        startUpListener start = new startUpListener();
        adminB.addActionListener(start);
        userB.addActionListener(start);

        add(labelP);
        add(startP);
    }
    /**@Author Daniel **/


    private void adminOrUser(){
        inputP.setVisible(false);

        inputP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;

        //  inputP.add(userIDL, gbc);
        // gbc.gridy++;
        inputP.add(userL, gbc);
        gbc.gridx++;
        inputP.add(userText, gbc);
        gbc.gridx++;
        inputP.add(lastL, gbc);
        gbc.gridx++;
        inputP.add(lastText, gbc);
        gbc.gridx++;
        inputP.add(IDL, gbc);
        gbc.gridx++;
        inputP.add(IDText, gbc);
        gbc.gridy++;

        gbc.gridx = 5;
        inputP.add(signInB,gbc);

        startUpListener admin = new startUpListener();
        signInB.addActionListener(admin);

        add(inputP);
    }
    /**@Author Mathieu **/


    private void Home(){
        //homeP.setVisible(false);

        homeP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        currStuArea.setEditable(false);
        currDegMinArea.setEditable(false);
        currTranscript.setEditable(false);

        homeP.add(currStuL, gbc);
        gbc.gridx += 2;
        homeP.add(currDegMinL, gbc);
        gbc.gridx += 2;
        homeP.add(currTranscriptL, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        homeP.add(currStuArea, gbc);
        gbc.gridx += 2;
        homeP.add(currDegMinArea, gbc);
        gbc.gridx += 2;
        homeP.add(currTranscript, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        homeP.add(selectDegreeB, gbc);
        gbc.gridx++;
        selectMajorB.setEnabled(false);
        homeP.add(selectMajorB, gbc);
        gbc.gridx++;
        homeP.add(viewCreditsB,gbc);
        gbc.gridx++;
        homeP.add(viewGpaB,gbc);
        gbc.gridx++;
        remainPreReqB.setEnabled(false);
        homeP.add(remainPreReqB, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        listReqCoursesB.setEnabled(false);
        homeP.add(listReqCoursesB, gbc);
        gbc.gridx++;
        homeP.add(addRemoveCourseB, gbc);
        addRemoveCourseB.setEnabled(false);
        gbc.gridx++;
        homeP.add(determineCompletionB, gbc);
        gbc.gridx++;
        viewCoursePlanB.setEnabled(false);
        homeP.add(viewCoursePlanB, gbc);
        gbc.gridx++;
        addRemoveChangeGradeB.setEnabled(false);
        homeP.add(addRemoveChangeGradeB, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        viewUnknownCoursesB.setEnabled(false);
        homeP.add(viewUnknownCoursesB,gbc);
        gbc.gridx++;
        viewIncompleteCoursesB.setEnabled(false);
        homeP.add(viewIncompleteCoursesB,gbc);
        gbc.gridx++;
        viewCurrReqCreditsB.setEnabled(false);
        homeP.add(viewCurrReqCreditsB,gbc);
        gbc.gridx++;
        viewPreReqs4CourseB.setEnabled(false);
        homeP.add(viewPreReqs4CourseB,gbc);

        homeListener home = new homeListener();
        selectDegreeB.addActionListener(home);
        selectMajorB.addActionListener(home);
        remainPreReqB.addActionListener(home);
        listReqCoursesB.addActionListener(home);
        addRemoveCourseB.addActionListener(home);
        addRemoveChangeGradeB.addActionListener(home);
        saveProgramB.addActionListener(home);
        viewUnknownCoursesB.addActionListener(home);
        viewIncompleteCoursesB.addActionListener(home);
        viewCurrReqCreditsB.addActionListener(home);
        viewPreReqs4CourseB.addActionListener(home);
        viewCreditsB.addActionListener(home);
        viewGpaB.addActionListener(home);
        determineCompletionB.addActionListener(home);
        viewCoursePlanB.addActionListener(home);

        add(homeP);
    }
    /**@Author Mathieu **/


    private void chooseDegree(){
        degreeP.setVisible(false);

        degreeP.setLayout(new BorderLayout());
        degreeP.add(degreeL, BorderLayout.NORTH);
        degreeP.add(degreeChoice, BorderLayout.CENTER);
        degreeP.add(submitDegree, BorderLayout.SOUTH);

        degListener listen = new degListener();
        submitDegree.addActionListener(listen);
        add(degreeP);
    }
    /**@Author Daniel **/


    private void chooseMajor(){
        majorP.setVisible(false);

        majorP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        //gbc.fill = GridBagConstraints.BOTH;

        majorP.add(majorChoiceL, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy++;
        majorP.add(majorL,gbc);
        gbc.gridx++;
        majorP.add(majorChoice, gbc);
        gbc.gridx++;
        majorP.add(majorText,gbc);
        gbc.gridy++;
        majorP.add(majorB,gbc);

        majorListener minor = new majorListener();
        majorChoice.addActionListener(minor);
        majorB.addActionListener(minor);

        add(majorP);
    }
    /**@Author Mathieu **/

    private void addRemoveCourse(){
        addRemoveP.setVisible(false);
        addRemoveFinalP.setVisible(false);

        addRemoveP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;

        addRemoveP.add(chooseCourseL, gbc);
        gbc.gridy++;
        addRemoveP.add(addCourseText, gbc);
        gbc.gridx++;
        addRemoveP.add(addSelectedCourseB, gbc);

        addRemoveFinalP.setLayout(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.fill = GridBagConstraints.BOTH;

        addRemoveFinalP.add(currGrade, gbc2);
        gbc2.gridy++;
        addRemoveFinalP.add(gradeText, gbc2);
        gbc2.gridy = 0;
        gbc2.gridx++;
        addRemoveFinalP.add(currSemester, gbc2);
        gbc2.gridy++;
        addRemoveFinalP.add(semesterBox, gbc2);
        gbc2.gridy = 0;
        gbc2.gridx++;
        addRemoveFinalP.add(currStatus, gbc2);
        gbc2.gridy++;
        addRemoveFinalP.add(statusBox, gbc2);
        gbc2.gridy = 3;
        gbc2.gridx = 1;
        addRemoveFinalP.add(chooseActionL, gbc2);
        gbc2.gridy = 4;
        gbc2.gridx = 1;
        addRemoveFinalP.add(addCourseB, gbc2);

        /*
        private JTextField gradeText = new JTextField(10);
        private JLabel currSemester = new JLabel("Semester of choice: ");
        private JComboBox semesterBox = new JComboBox();
        private JLabel currStatus = new JLabel("Choose Status");
        private JComboBox statusBox = new JComboBox();
        private JButton submitChoice = new JButton("Submit");*/


        addRemoveCourseListener addRemove = new addRemoveCourseListener();
        addCourseB.addActionListener(addRemove);
        addSelectedCourseB.addActionListener(addRemove);

        add(addRemoveP);
        add(addRemoveFinalP);
    }
    /**@Author Daniel **/


    public class startUpListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == adminB){
                startP.setVisible(false);
                labelP.setVisible(false);
                inputP.setVisible(true);
                pack();
            }
            else if(event.getSource() == userB){
                startP.setVisible(false);
                labelP.setVisible(false);
                inputP.setVisible(true);
                pack();
            }
            else if(event.getSource() == signInB){
                String first = null;
                String last = null;
                Integer ID = null;
                String fullInfo;

                try {
                    if(userText.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(inputP,"NO FIRST NAME INPUT");
                        throw new NullException("NO FIRST NAME INPUT");

                    }

                    if(lastText.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(inputP,"NO LAST NAME INPUT");
                        throw new NullException("NO LAST NAME INPUT");

                    }
                    if(IDText.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(inputP,"NO ID INPUT");
                        throw new NullException("NO ID INPUT");

                    }
                    if(IDText.getText().length() != 7)
                    {
                        JOptionPane.showMessageDialog(inputP,"ID MUST BE 7 NUMBERS LONG");
                        throw new NullException("MUST BE 7 NUMBERS LONG");
                    }
                    if(isAlphabetical(IDText.getText()))
                    {
                        JOptionPane.showMessageDialog(inputP,"IDS CAN ONLY CONTAIN LETTERS");
                        throw new NullException("IDS CAN ONLY CONTAIN LETTERS");
                    }
                    if(!(isAlphabetical(userText.getText())))
                    {
                        JOptionPane.showMessageDialog(inputP,"NAMES CAN ONLY CONTAIN LETTERS");
                        throw new NullException("NAMES CAN ONLY CONTAIN LETTERS");
                    }
                    if(!(isAlphabetical(lastText.getText())))
                    {
                        JOptionPane.showMessageDialog(inputP,"NAMES CAN ONLY CONTAIN LETTERS");
                        throw new NullException("NAMES CAN ONLY CONTAIN NUMBERS");
                    }

                    first = userText.getText();
                    last = lastText.getText();
                    ID = Integer.valueOf(IDText.getText());

                }
                catch(NullException e){
                    System.out.println(e.getMessage());
                }
                catch(NullPointerException e){
                    e.printStackTrace();
                }

                student.setFirstName(first);
                student.setLastName(last);
                student.setStudentNumber(ID);
                student.setTranscript(transcript);

                studentInfo.add(first);
                studentInfo.add(last);
                studentInfo.add(IDText.getText());

                fullInfo = toStringList(studentInfo);

                currStuArea.setText(fullInfo);

                homeP.setVisible(true);
                inputP.setVisible(false);
                pack();
            }
        }
    }
    /**@Author Daniel **/


    public class homeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == selectDegreeB){
                homeP.setVisible(false);
                degreeP.setVisible(true);
                pack();
            }
            else if(event.getSource() == selectMajorB){
                homeP.setVisible(false);
                majorP.setVisible(true);
                pack();
            }
            else if(event.getSource() == remainPreReqB){

                String intChange;
                Degree degreeTitle;
                String degree;
                double maxCredits = 0.0;
                intChange = "Current Credits Left: ";

                for(Course c : courseList){
                    currCredits += c.getCourseCredit();
                }

                degreeTitle = student.getDegreeProgram();
                degree = degreeTitle.getDegreeTitle();
                if(degree.equals("BCG")){
                    maxCredits = 15.0;
                }
                else if(degree.equals("CS")){
                    maxCredits = 20.0;
                }
                else if(degree.equals("SEng")){
                    maxCredits = 20.0;
                }

                maxCredits -= currCredits;
                intChange += Double.toString(maxCredits);
                currCredits = 0.0;

                JOptionPane.showMessageDialog(homeP, intChange);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == listReqCoursesB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == addRemoveCourseB){
                remainPreReqB.setEnabled(true);
                homeP.setVisible(false);
                addRemoveP.setVisible(true);
                pack();
            }
            else if(event.getSource() == addRemoveChangeGradeB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == saveProgramB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewUnknownCoursesB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewIncompleteCoursesB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewCurrReqCreditsB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewCreditsB){

                String intChange;
                intChange = "Current Credits: ";

                for(Course c : courseList){
                    currCredits += c.getCourseCredit();
                }

                intChange += Double.toString(currCredits);

                currCredits = 0.0;

                JOptionPane.showMessageDialog(homeP, intChange);

            }
            else if(event.getSource() == viewGpaB){

                String string = "GPA: ";
                Integer counter = 0;
                double fakeGPA = 0.0;

                for(Course c : courseList){
                    counter++;
                }

                fakeGPA = GPA;
                fakeGPA = fakeGPA / counter;

                string += fakeGPA;

                JOptionPane.showMessageDialog(homeP,string);


            }
            else if(event.getSource() == determineCompletionB){
                JOptionPane.showMessageDialog(homeP,"I'ts not");
            }
            else if(event.getSource() == viewCoursePlanB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            /*selectDegreeB
                    selectMajorB
            remainPreReqB
                    listReqCoursesB
            addRemoveCourseB
                    addRemoveChangeGradeB
            saveProgramB
                    viewUnknownCoursesB
            viewIncompleteCoursesB
                    viewCurrReqCreditsB
            viewPreReqs4CourseB
                    viewCreditsB
            viewGpaB
                    determineCompletionB
            viewCoursePlanB*/
        }
    }
    /**@Author Mathieu **/


    public class degListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == submitDegree)
            {
                String degreeTitle;
                String fullDegreeTitle;
                String softEng = "SEng";
                String compSci = "CS";
                String genComp = "BCG";
                String major = "No current major";
                String honours = "BComp Honours";
                String general = "BComp General";
                ArrayList<String> majorList = new ArrayList<>();
                degreeTitle = degreeChoice.getSelectedItem().toString();
                degMinInfo.add(degreeTitle);
                degMinInfo.add(major);
                fullDegreeTitle = toStringList(degMinInfo);
                currDegMinArea.setText(fullDegreeTitle);
                if(degreeTitle.equals(honours)){
                    majorList.add(softEng);
                    majorList.add(compSci);
                }
                else if(degreeTitle.equals(general)){
                    majorList.add(genComp);
                }
                majorChoice.setModel(new DefaultComboBoxModel(majorList.toArray()));

                selectDegreeB.setEnabled(false);
                selectMajorB.setEnabled(true);
                homeP.setVisible(true);
                degreeP.setVisible(false);
                pack();
            }
        }
    }
    /** @author: Daniel**/

    public class majorListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == majorChoice){
                String string;
                String courses = null;
                String softEng = "SEng";
                String compSci = "CS";
                String genComp = "BCG";

                string = majorChoice.getSelectedItem().toString();

                if(string == softEng)
                    courses = "CIS*1250,CIS*1500,CIS*1910,CIS*2250,CIS*2500,\nCIS*2030," +
                            "CIS*2430,CIS*2520,CIS*3250,CIS*2750,\nCIS*3110,CIS*3490," +
                            "CIS*3750,CIS*2460,STAT*2040,\nCIS*3760,CIS*3260,CIS*4150," +
                            "CIS*4300,CIS*4250";
                else if(string == compSci)
                    courses = "MATH*1200,CIS*1500,CIS*1910,CIS*2500,CIS*2030,\n" +
                            "CIS*2430,CIS*2520,CIS*2910,CIS*2750,CIS*3110,\nCIS*3490," +
                            "CIS*3150,CIS*3750,STAT*2040,\nCIS*3760,CIS*4650";
                else if(string == genComp)
                    courses = "CIS*1500,CIS*1910,CIS*2430,CIS*2500,CIS*2520,\nCIS*2750," +
                            "CIS*2910,CIS*3530";

                majorText.setText(courses);
            }
            else if(event.getSource() == majorB){
                String string;
                String fullDegMin;
                String stringList;
                String[] reqCourses;
                String major = "No current major";
                String softEng = "SEng";
                String compSci = "CS";
                String genComp = "BCG";

                string = majorChoice.getSelectedItem().toString();
                degMinInfo.add(string);
                degMinInfo.remove(major);
                fullDegMin = toStringList(degMinInfo);
                currDegMinArea.setText(fullDegMin);

                if(string == softEng) {
                    SEng seng = new SEng();
                    seng.setDegreeTitle(softEng);
                    seng.setCatalog(courseCatalog);
                    requiredCourses = seng.getRequiredCourses();
                    student.setDegreeProgram(seng);
                }
                else if(string == compSci) {
                    CS cs = new CS();
                    cs.setDegreeTitle(compSci);
                    cs.setCatalog(courseCatalog);
                    requiredCourses = cs.getRequiredCourses();
                    student.setDegreeProgram(cs);
                }
                else if(string == genComp) {
                    BCG bcg = new BCG();
                    bcg.setDegreeTitle(genComp);
                    bcg.setCatalog(courseCatalog);
                    requiredCourses = bcg.getRequiredCourses();
                    student.setDegreeProgram(bcg);
                }

                stringList = toStringCourses(requiredCourses);
                System.out.println(stringList);
                selectMajorB.setEnabled(false);
                addRemoveCourseB.setEnabled(true);
                majorP.setVisible(false);
                homeP.setVisible(true);
                pack();
            }
        }
    }
    /**@Author Mathieu **/


    public class addRemoveCourseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event){

            if(event.getSource() == addSelectedCourseB){

                String string;
                String semString = null;

                string = addCourseText.getText();

                if(courseCatalog.findCourse(string) != null){
                    Course course = courseCatalog.findCourse(string);

                    semString = course.getSemesterOffered();

                    if(semString.equals("F")){
                        semesterBox.addItem("F");
                    }
                    else if(semString.equals("W")){
                        semesterBox.addItem("W");
                    }
                    else if(semString.equals("B")){
                        semesterBox.addItem("F");
                        semesterBox.addItem("W");
                    }
                    else{
                        JOptionPane.showMessageDialog(addRemoveP,"No Semester to Choose From");
                    }

                    statusBox.addItem("Incomplete");
                    statusBox.addItem("InProgress");
                    statusBox.addItem("Complete");

                    addRemoveP.setVisible(false);
                    addRemoveFinalP.setVisible(true);
                    pack();
                }
                else {
                    JOptionPane.showMessageDialog(addRemoveP,"Incorrect Course Code");
                }

            }
            else if(event.getSource() == addCourseB){

                String CourseCode;
                String Grade;
                String Semester;
                String Status;
                String Attempt;

                CourseCode = addCourseText.getText();
                System.out.println(CourseCode);
                GPA += Integer.valueOf(gradeText.getText());
                Grade = gradeText.getText();
                System.out.println(Grade);
                Semester = semesterBox.getSelectedItem().toString();
                System.out.println(Semester);
                Status = statusBox.getSelectedItem().toString();
                System.out.println(Status);

                courseList.add(courseCatalog.findCourse(CourseCode));

                if(CourseCode == null || Grade == null || Semester == null || Status == null) {
                    JOptionPane.showMessageDialog(addRemoveP,"Incorrect Course Code");
                }
                else {
                    Attempt = CourseCode + "," + Grade + "," + Semester + "," + Status;
                    attemptList.add(Attempt);
                }
                currTranscript.setText(toStringAttemptList(attemptList));

                addCourseText.setText("");
                gradeText.setText("");

                semesterBox.removeAllItems();
                statusBox.removeAllItems();

                homeP.setVisible(true);
                addRemoveFinalP.setVisible(false);
                pack();
            }
        }
    }
    /**@Author Daniel
     * @Functionality Adds or removes a course
     * from the transcript**/


    public String toStringAttemptList(ArrayList<String> attemptList){

        String string = null;

        for(String s : attemptList){
            string += s + "\n";
        }

        return string;
    }
    /**@Author Mathieu **/

    public String toStringCourses(ArrayList<Course> courseList){

        String newString = null;

        for(Course c : courseList){
            newString += c.toString() + "\n";
        }

        return newString;
    }
    /**@Author Daniel **/

    public boolean isAlphabetical(String input) {
        return input.matches("[a-zA-Z]+");
    }

    /**@Author Mathieu **/


    public String toStringList(ArrayList<String> list){

        String newString = "";

        for(String s : list){

            newString += s + "\n";
        }
        return newString;
    }
    /**@Author Daniel **/


    public class NullException extends Exception {
        public NullException(String message) { super(message); }
    }
    /**@Author Mathieu **/


    public static void main(String[] args){
        Planner planner = new Planner();
        planner.setVisible(true);
    }
    /**@Author Daniel **/

}
