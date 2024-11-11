package com.brightSmileDental.solution.service;

import com.brightSmileDental.solution.DTO.request.DoctorRequest;
import com.brightSmileDental.solution.DTO.response.DoctorResponse;

public interface DoctorService {
    DoctorResponse addDoctor(DoctorRequest doctorRequest);
}
