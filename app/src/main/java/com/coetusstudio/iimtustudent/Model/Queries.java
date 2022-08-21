package com.coetusstudio.iimtustudent.Model;

public class Queries {
        String queriesName, queriesRollNumber, facultyName, queriesTitle, facultyImage, studentImage;

        public Queries(String queriesName, String queriesRollNumber, String facultyName, String queriesTitle, String facultyImage, String studentImage) {
                this.queriesName = queriesName;
                this.queriesRollNumber = queriesRollNumber;
                this.facultyName = facultyName;
                this.queriesTitle = queriesTitle;
                this.facultyImage = facultyImage;
                this.studentImage = studentImage;
        }

        public Queries() {
        }

        public Queries(String queriesName, String queriesRollNumber, String facultyName, String queriesTitle, String studentImage) {
                this.queriesName = queriesName;
                this.queriesRollNumber = queriesRollNumber;
                this.facultyName = facultyName;
                this.queriesTitle = queriesTitle;
                this.studentImage = studentImage;
        }

        public String getQueriesName() {
                return queriesName;
        }

        public void setQueriesName(String queriesName) {
                this.queriesName = queriesName;
        }

        public String getQueriesRollNumber() {
                return queriesRollNumber;
        }

        public void setQueriesRollNumber(String queriesRollNumber) {
                this.queriesRollNumber = queriesRollNumber;
        }

        public String getFacultyName() {
                return facultyName;
        }

        public void setFacultyName(String facultyName) {
                this.facultyName = facultyName;
        }

        public String getQueriesTitle() {
                return queriesTitle;
        }

        public void setQueriesTitle(String queriesTitle) {
                this.queriesTitle = queriesTitle;
        }

        public String getFacultyImage() {
                return facultyImage;
        }

        public void setFacultyImage(String facultyImage) {
                this.facultyImage = facultyImage;
        }

        public String getStudentImage() {
                return studentImage;
        }

        public void setStudentImage(String studentImage) {
                this.studentImage = studentImage;
        }
}
