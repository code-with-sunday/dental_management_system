package com.brightSmileDental.solution.repository;

import com.brightSmileDental.solution.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByAffiliatedClinicId(Long clinicId);
    List<Patient> findByAffiliatedDoctorId(Long doctorId);
}
