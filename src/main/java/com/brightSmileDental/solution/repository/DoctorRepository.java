package com.brightSmileDental.solution.repository;

import com.brightSmileDental.solution.enums.Speciality;
import com.brightSmileDental.solution.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialties(Speciality specialty);
    List<Doctor> findByClinicsId(Long clinicId);
}
