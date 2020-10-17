package models;

public class StudentsModel {
    private int id;
    private String dept;
    private String versity;

    public StudentsModel() {
    }


    public StudentsModel(int id, String dept, String versity) {
        this.id = id;
        this.dept = dept;
        this.versity = versity;
    }


    public int getId() {
        return id;
    }

    public String getDept() {
        return dept;
    }

    public String getVersity() {
        return versity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setVersity(String versity) {
        this.versity = versity;
    }
}
