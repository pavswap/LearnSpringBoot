package com.Valid.ValidExcep.Controller;

import com.Valid.ValidExcep.DAO.AttendanceDAO;
import com.Valid.ValidExcep.Repository.AttendanceRepository;
import com.Valid.ValidExcep.Service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/log")
    public LinkedList<AttendanceDAO> log() {
        return attendanceService.log();
    }

    @PostMapping
    public void add(@RequestBody AttendanceDAO dao) {
        attendanceService.add(dao);
    }

    @DeleteMapping("/{roll}")
    public void remove(@PathVariable int roll) {
        attendanceService.remove(roll);
    }

    @GetMapping("/student/{name}")
    public int getRoll(@PathVariable String name) {
        return attendanceService.getRoll(name);
    }
}
