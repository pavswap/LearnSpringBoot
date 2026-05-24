package com.Valid.ValidExcep.Service;

import com.Valid.ValidExcep.DAO.AttendanceDAO;
import com.Valid.ValidExcep.Exception.IllegalRollException;
import com.Valid.ValidExcep.Repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class AttendanceService {

    AttendanceRepository attendanceRepository;

    AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void add(AttendanceDAO attendanceDAO) {
        attendanceRepository.add(attendanceDAO);
    }

    public void remove(int roll) {

        if(roll >= 0 && roll < attendanceRepository.strength()) {
            attendanceRepository.remove(roll);
        }
        else {
            throw new IllegalRollException("Invalid Roll");
        }
    }

    public LinkedList<AttendanceDAO> log() {
        return attendanceRepository.log();
    }

    public int getRoll(String name) {
        return attendanceRepository.getRoll(name);
    }
}
