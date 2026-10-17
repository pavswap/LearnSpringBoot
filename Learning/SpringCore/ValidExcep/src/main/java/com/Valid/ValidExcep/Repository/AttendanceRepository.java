package com.Valid.ValidExcep.Repository;

import com.Valid.ValidExcep.DAO.AttendanceDAO;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class AttendanceRepository {

    private LinkedList<AttendanceDAO> list = new LinkedList<>(
            List.of(new AttendanceDAO("Pav"), new AttendanceDAO("swap")));

    public void add(AttendanceDAO dao) {
        list.add(dao);
    }

    public void remove(int roll) {
        list.remove(roll);
    }

    public int getRoll(String name) {

        var dao = new AttendanceDAO(name);
        return list.indexOf(dao);
    }

    public LinkedList<AttendanceDAO> log() {
        return list;
    }

    public int strength() {
        return list.size();
    }
}
