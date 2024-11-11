package com.brightSmileDental.solution.repository;

import com.brightSmileDental.solution.model.DoctorAffiliation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAffiliationRepository extends JpaRepository<DoctorAffiliation, Long> {
    List<DoctorAffiliation> findByClinicId(Long clinicId);
    List<DoctorAffiliation> findByDoctorId(Long doctorId);
}
