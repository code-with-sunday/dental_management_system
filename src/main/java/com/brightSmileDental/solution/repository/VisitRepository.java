package com.brightSmileDental.solution.repository;

import com.brightSmileDental.solution.model.Patient;
import com.brightSmileDental.solution.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPatientId(Long patientId);
    List<Visit> findByDoctorId(Long doctorId);
    List<Visit> findByClinicId(Long clinicId);
    Optional<Visit> findFirstByPatientAndVisitDateAfterOrderByVisitDateAsc(Patient patient, LocalDateTime date);
    List<Visit> findByPatientIdOrderByVisitDateDesc(Long patientId);

}
