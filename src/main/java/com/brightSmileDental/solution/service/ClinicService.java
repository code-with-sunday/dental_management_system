package com.brightSmileDental.solution.service;


import com.brightSmileDental.solution.DTO.request.ClinicRequest;
import com.brightSmileDental.solution.DTO.response.ClinicResponse;

public interface ClinicService {
    ClinicResponse addClinic(ClinicRequest clinicRequest);
    ClinicResponse getClinicInfo(Long clinicId);
}
