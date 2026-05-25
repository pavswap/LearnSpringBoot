package com.Valid.ValidExcep.Controller;

import com.Valid.ValidExcep.DAO.AttendanceDAO;
import com.Valid.ValidExcep.Service.AttendanceService;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.web.bind.annotation.*;


import java.util.LinkedList;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    private final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/log")
    public LinkedList<AttendanceDAO> log() {

        logger.info("Granted Attendance Log Request");
        return attendanceService.log();
    }

    @PostMapping
    public void add(@RequestBody AttendanceDAO dao) {

        logger.info("Added Student {}", dao.getName());
        attendanceService.add(dao);
    }

    @DeleteMapping("/{roll}")
    public void remove(@PathVariable int roll) {

        logger.info("Removed Student with roll {}", roll);
        attendanceService.remove(roll);
    }

    @GetMapping("/student/{name}")
    public int getRoll(@PathVariable String name) {

        logger.info("Granted Roll for Student {}", name);
        return attendanceService.getRoll(name);
    }
}
