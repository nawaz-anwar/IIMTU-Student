package com.coetusstudio.iimtustudent.Model;

public class StudentDetails {

    String studentImage, studentName, studentEmail, studentAdmissionNumber, studentEnrollmentNumber, studentRollNumber, studentFees, studentGrade, studentBranch, studentSemester, studentSection,studentAttendance, studentPassword;

    public StudentDetails(String studentImage, String studentName, String studentEmail, String studentAdmissionNumber, String studentEnrollmentNumber, String studentRollNumber, String studentBranch, String studentSemester, String studentSection, String studentGrade, String studentAttendance, String studentPassword) {
        this.studentImage = studentImage;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentAdmissionNumber = studentAdmissionNumber;
        this.studentEnrollmentNumber = studentEnrollmentNumber;
        this.studentRollNumber = studentRollNumber;
        this.studentBranch = studentBranch;
        this.studentSemester = studentSemester;
        this.studentSection = studentSection;
        this.studentGrade = studentGrade;
        this.studentAttendance = studentAttendance;
        this.studentPassword = studentPassword;
    }

    public StudentDetails() {
    }

    public StudentDetails(String studentImage, String studentName, String studentEmail, String studentAdmissionNumber, String studentEnrollmentNumber, String studentRollNumber, String studentBranch, String studentSemester, String studentSection, String studentGrade, String studentAttendance) {
        this.studentImage = studentImage;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentAdmissionNumber = studentAdmissionNumber;
        this.studentEnrollmentNumber = studentEnrollmentNumber;
        this.studentRollNumber = studentRollNumber;
        this.studentBranch = studentBranch;
        this.studentSemester = studentSemester;
        this.studentSection = studentSection;
        this.studentGrade = studentGrade;
        this.studentAttendance = studentAttendance;
    }

    public StudentDetails(String s, String toString, String string, String s1, String toString1, String string1) {
    }

    public String getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(String studentImage) {
        this.studentImage = studentImage;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentAdmissionNumber() {
        return studentAdmissionNumber;
    }

    public void setStudentAdmissionNumber(String studentAdmissionNumber) {
        this.studentAdmissionNumber = studentAdmissionNumber;
    }

    public String getStudentEnrollmentNumber() {
        return studentEnrollmentNumber;
    }

    public void setStudentEnrollmentNumber(String studentEnrollmentNumber) {
        this.studentEnrollmentNumber = studentEnrollmentNumber;
    }

    public String getStudentRollNumber() {
        return studentRollNumber;
    }

    public void setStudentRollNumber(String studentRollNumber) {
        this.studentRollNumber = studentRollNumber;
    }

    public String getStudentBranch() {
        return studentBranch;
    }

    public void setStudentBranch(String studentBranch) {
        this.studentBranch = studentBranch;
    }

    public String getStudentFees() {
        return studentFees;
    }

    public void setStudentFees(String studentFees) {
        this.studentFees = studentFees;
    }

    public String getStudentSemester() {
        return studentSemester;
    }

    public void setStudentSemester(String studentSemester) {
        this.studentSemester = studentSemester;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }


    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentSection() {
        return studentSection;
    }

    public void setStudentSection(String studentSection) {
        this.studentSection = studentSection;
    }
}
