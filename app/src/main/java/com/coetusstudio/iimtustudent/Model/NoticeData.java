package com.coetusstudio.iimtustudent.Model;

public class NoticeData {

    String title, image, date, time, facultyImage;

    public NoticeData(String title, String image, String date, String time, String facultyImage) {
        this.title = title;
        this.image = image;
        this.date = date;
        this.time = time;
        this.facultyImage = facultyImage;
    }

    public NoticeData() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFacultyImage() {
        return facultyImage;
    }

    public void setFacultyImage(String facultyImage) {
        this.facultyImage = facultyImage;
    }
}
