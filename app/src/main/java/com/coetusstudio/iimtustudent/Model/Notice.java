package com.coetusstudio.iimtustudent.Model;

public class Notice {

    String notificationTitle, notificationImage, notificationDate, notificationTiming;

    public Notice(String notificationTitle, String notificationImage, String notificationDate, String notificationTiming) {
        this.notificationTitle = notificationTitle;
        this.notificationImage = notificationImage;
        this.notificationDate = notificationDate;
        this.notificationTiming = notificationTiming;
    }

    public Notice() {
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationTiming() {
        return notificationTiming;
    }

    public void setNotificationTiming(String notificationTiming) {
        this.notificationTiming = notificationTiming;
    }
}
