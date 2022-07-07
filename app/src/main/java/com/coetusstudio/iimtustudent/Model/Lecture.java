package com.coetusstudio.iimtustudent.Model;

public class Lecture {
    String lectureName, lectureTiming, lectureLink, lectureDate, lectureTime;

    public Lecture(String lectureName, String lectureTiming, String lectureLink, String lectureDate, String lectureTime) {
        this.lectureName = lectureName;
        this.lectureTiming = lectureTiming;
        this.lectureLink = lectureLink;
        this.lectureDate = lectureDate;
        this.lectureTime = lectureTime;
    }

    public Lecture() {
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getLectureTiming() {
        return lectureTiming;
    }

    public void setLectureTiming(String lectureTiming) {
        this.lectureTiming = lectureTiming;
    }

    public String getLectureLink() {
        return lectureLink;
    }

    public void setLectureLink(String lectureLink) {
        this.lectureLink = lectureLink;
    }

    public String getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(String lectureDate) {
        this.lectureDate = lectureDate;
    }

    public String getLectureTime() {
        return lectureTime;
    }

    public void setLectureTime(String lectureTime) {
        this.lectureTime = lectureTime;
    }
}
