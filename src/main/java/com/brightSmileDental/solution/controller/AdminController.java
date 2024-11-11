package com.brightSmileDental.solution.controller;

import com.brightSmileDental.solution.DTO.request.ClinicRequest;
import com.brightSmileDental.solution.DTO.request.DoctorRequest;
import com.brightSmileDental.solution.DTO.request.PatientRequest;
import com.brightSmileDental.solution.DTO.response.ClinicResponse;
import com.brightSmileDental.solution.DTO.response.DoctorResponse;
import com.brightSmileDental.solution.DTO.response.PatientResponse;
import com.brightSmileDental.solution.service.ClinicService;
import com.brightSmileDental.solution.service.DoctorService;
import com.brightSmileDental.solution.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final ClinicService clinicService;

    @PostMapping("/doctor")
    public ResponseEntity<DoctorResponse> addDoctor(@RequestBody DoctorRequest doctorRequest) {
        DoctorResponse doctorResponse = doctorService.addDoctor(doctorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorResponse);
    }

    @PostMapping("/clinic")
    public ResponseEntity<ClinicResponse> addClinic(@RequestBody ClinicRequest clinicRequest) {
        ClinicResponse clinicResponse = clinicService.addClinic(clinicRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(clinicResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicResponse> getClinicInfo(@PathVariable Long id) {
        ClinicResponse clinicResponse = clinicService.getClinicInfo(id);
        return ResponseEntity.ok(clinicResponse);
    }

    @PostMapping("/patient")
    public ResponseEntity<PatientResponse> addPatient(@RequestBody PatientRequest patientRequest) {
        PatientResponse patientResponse = patientService.addPatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }
}
