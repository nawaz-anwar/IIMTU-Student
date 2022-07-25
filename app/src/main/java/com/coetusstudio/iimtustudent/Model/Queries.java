package com.coetusstudio.iimtustudent.Model;

public class Queries {
        String queriesName, queriesRollNumber, facultyName, queriesTitle, facultyId, studentId;

        public Queries(String queriesName, String queriesRollNumber, String facultyName, String queriesTitle) {
                this.queriesName = queriesName;
                this.queriesRollNumber = queriesRollNumber;
                this.facultyName = facultyName;
                this.queriesTitle = queriesTitle;
        }

        public Queries(String queriesName, String queriesRollNumber, String facultyName, String queriesTitle, String facultyId, String studentId) {
                this.queriesName = queriesName;
                this.queriesRollNumber = queriesRollNumber;
                this.facultyName = facultyName;
                this.queriesTitle = queriesTitle;
                this.facultyId = facultyId;
                this.studentId = studentId;
        }

        public Queries() {
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

        public String getFacultyId() {
                return facultyId;
        }

        public void setFacultyId(String facultyId) {
                this.facultyId = facultyId;
        }

        public String getStudentId() {
                return studentId;
        }

        public void setStudentId(String studentId) {
                this.studentId = studentId;
        }
}
