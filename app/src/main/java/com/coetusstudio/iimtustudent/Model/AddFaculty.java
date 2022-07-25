package com.coetusstudio.iimtustudent.Model;

public class AddFaculty {

    String facultyImage, facultyName, facultyEmail, facultyId, facultySubject, facultySubjectCode, facultyBranch, facultySemester, facultySection,facultyPassword, facultyUid;

    public AddFaculty(String facultyImage, String facultyName, String facultyEmail, String facultyId, String facultySubject, String facultySubjectCode, String facultyBranch, String facultySemester, String facultySection, String facultyPassword, String facultyUid) {
        this.facultyImage = facultyImage;
        this.facultyName = facultyName;
        this.facultyEmail = facultyEmail;
        this.facultyId = facultyId;
        this.facultySubject = facultySubject;
        this.facultySubjectCode = facultySubjectCode;
        this.facultyBranch = facultyBranch;
        this.facultySemester = facultySemester;
        this.facultySection = facultySection;
        this.facultyPassword = facultyPassword;
        this.facultyUid = facultyUid;
    }

    public AddFaculty() {
    }

    public AddFaculty(String facultyImage, String facultyName, String facultyEmail, String facultyId, String facultySubject, String facultySubjectCode, String facultyBranch, String facultySemester, String facultySection, String facultyPassword) {
        this.facultyImage = facultyImage;
        this.facultyName = facultyName;
        this.facultyEmail = facultyEmail;
        this.facultyId = facultyId;
        this.facultySubject = facultySubject;
        this.facultySubjectCode = facultySubjectCode;
        this.facultyBranch = facultyBranch;
        this.facultySemester = facultySemester;
        this.facultySection = facultySection;
        this.facultyPassword = facultyPassword;
    }

    public String getFacultyImage() {
        return facultyImage;
    }

    public void setFacultyImage(String facultyImage) {
        this.facultyImage = facultyImage;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultySubject() {
        return facultySubject;
    }

    public void setFacultySubject(String facultySubject) {
        this.facultySubject = facultySubject;
    }

    public String getFacultySubjectCode() {
        return facultySubjectCode;
    }

    public void setFacultySubjectCode(String facultySubjectCode) {
        this.facultySubjectCode = facultySubjectCode;
    }

    public String getFacultyPassword() {
        return facultyPassword;
    }

    public void setFacultyPassword(String facultyPassword) {
        this.facultyPassword = facultyPassword;
    }

    public String getFacultyMessage() {
        return facultyUid;
    }

    public void setFacultyMessage(String facultyMessage) {
        this.facultyUid = facultyMessage;
    }

    public String getFacultyBranch() {
        return facultyBranch;
    }

    public void setFacultyBranch(String facultyBranch) {
        this.facultyBranch = facultyBranch;
    }

    public String getFacultySemester() {
        return facultySemester;
    }

    public void setFacultySemester(String facultySemester) {
        this.facultySemester = facultySemester;
    }

    public String getFacultySection() {
        return facultySection;
    }

    public void setFacultySection(String facultySection) {
        this.facultySection = facultySection;
    }
}
