package com.Valid.ValidExcep.DAO;

public class AttendanceDAO {

    private String name;

    public AttendanceDAO() {
    }

    public AttendanceDAO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AttendanceDAO other = (AttendanceDAO) obj;

        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}