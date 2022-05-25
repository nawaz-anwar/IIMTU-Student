package com.coetusstudio.iimtustudent.Model;

public class Notes {

    String filename, fileurl;


    public Notes(String filename, String fileurl) {
        this.filename = filename;
        this.fileurl = fileurl;
    }

    public Notes() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }
}
