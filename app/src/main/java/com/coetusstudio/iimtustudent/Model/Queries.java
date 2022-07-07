package com.coetusstudio.iimtustudent.Model;

public class Queries {
        String queriesName, queriesRollNumber, queriesTitle;

        public Queries(String queriesName, String queriesRollNumber, String queriesTitle) {
                this.queriesName = queriesName;
                this.queriesRollNumber = queriesRollNumber;
                this.queriesTitle = queriesTitle;
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

        public String getQueriesTitle() {
                return queriesTitle;
        }

        public void setQueriesTitle(String queriesTitle) {
                this.queriesTitle = queriesTitle;
        }
}
