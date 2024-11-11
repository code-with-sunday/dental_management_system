package com.brightSmileDental.solution.service;

import com.brightSmileDental.solution.DTO.request.PatientRequest;
import com.brightSmileDental.solution.DTO.response.PatientResponse;

public interface PatientService {
    PatientResponse addPatient(PatientRequest patientRequest);
}