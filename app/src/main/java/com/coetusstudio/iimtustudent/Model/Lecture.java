package com.coetusstudio.iimtustudent.Model;

public class Lecture {
    String lectureName, lectureTiming, lectureLink;


    public Lecture(String lectureName, String lectureTiming, String lectureLink) {
        this.lectureName = lectureName;
        this.lectureTiming = lectureTiming;
        this.lectureLink = lectureLink;
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
}
