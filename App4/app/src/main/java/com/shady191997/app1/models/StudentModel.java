package com.shady191997.app1.models;

public class StudentModel {

    private String mStudentName;
    private String mStudentAge;

    public void setmStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public void setmStudentAge(String mStudentAge) {
        this.mStudentAge = mStudentAge;
    }

    public  StudentModel(String mStudentName, String mStudentAge) {
        this.mStudentName = mStudentName;
        this.mStudentAge = mStudentAge;

    }
public String getmStudentAge() {
        return mStudentAge;
    }

    public String getmStudentName() {
        return mStudentName;
    }


}
