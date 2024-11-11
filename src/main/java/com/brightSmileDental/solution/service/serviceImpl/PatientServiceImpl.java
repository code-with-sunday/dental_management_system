package com.brightSmileDental.solution.service.serviceImpl;

import com.brightSmileDental.solution.DTO.request.PatientRequest;
import com.brightSmileDental.solution.DTO.response.PatientResponse;
import com.brightSmileDental.solution.DTO.response.VisitResponse;
import com.brightSmileDental.solution.exception.ClinicNotFoundException;
import com.brightSmileDental.solution.exception.DoctorNotFoundException;
import com.brightSmileDental.solution.exception.UnAuthorizedException;
import com.brightSmileDental.solution.mapper.PatientMapper;
import com.brightSmileDental.solution.mapper.VisitMapper;
import com.brightSmileDental.solution.model.Clinic;
import com.brightSmileDental.solution.model.Doctor;
import com.brightSmileDental.solution.model.Patient;
import com.brightSmileDental.solution.model.Visit;
import com.brightSmileDental.solution.repository.ClinicRepository;
import com.brightSmileDental.solution.repository.DoctorRepository;
import com.brightSmileDental.solution.repository.PatientRepository;
import com.brightSmileDental.solution.repository.VisitRepository;
import com.brightSmileDental.solution.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ClinicRepository clinicRepository;
    private final DoctorRepository doctorRepository;
    private final PatientMapper patientMapper;
    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    @Override
    public PatientResponse addPatient(PatientRequest patientRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UnAuthorizedException("Session timed out, please Login first");
        }

        if (!authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            throw new UnAuthorizedException("You do not have permission to add a doctor");
        }

        Clinic affiliatedClinic = clinicRepository.findById(patientRequest.getAffiliatedClinicId())
                .orElseThrow(() -> new ClinicNotFoundException("Clinic not found with ID: " + patientRequest.getAffiliatedClinicId()));

        Doctor affiliatedDoctor = doctorRepository.findById(patientRequest.getAffiliatedDoctorId())
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with ID: " + patientRequest.getAffiliatedDoctorId()));

        Patient patient = patientMapper.toEntity(patientRequest, affiliatedClinic, affiliatedDoctor);
        Patient savedPatient = patientRepository.save(patient);

        VisitResponse nextAppointment = findOrCreateNextAppointmentForPatient(savedPatient);

        return patientMapper.toResponse(savedPatient, nextAppointment, new ArrayList<>());
    }

    private VisitResponse findOrCreateNextAppointmentForPatient(Patient patient) {
        Optional<Visit> nextVisit = visitRepository.findFirstByPatientAndVisitDateAfterOrderByVisitDateAsc(patient, LocalDateTime.now());

        if (nextVisit.isPresent()) {
            return visitMapper.toResponse(nextVisit.get());
        } else {
            List<Visit> pastVisits = visitRepository.findByPatientIdOrderByVisitDateDesc(patient.getId());

            if (!pastVisits.isEmpty()) {
                LocalDateTime lastVisitDate = pastVisits.get(0).getVisitDate();
                LocalDateTime suggestedNextDate = lastVisitDate.plusMonths(6);

                Visit nextVisitSuggestion = Visit.builder()
                        .patient(patient)
                        .doctor(patient.getAffiliatedDoctor())
                        .clinic(patient.getAffiliatedClinic())
                        .visitDate(suggestedNextDate)
                        .proceduresDone("Scheduled Check-up")
                        .doctorNotes("Suggested follow-up")
                        .build();

                visitRepository.save(nextVisitSuggestion);
                return visitMapper.toResponse(nextVisitSuggestion);
            } else {
                LocalDateTime firstAppointmentDate = LocalDateTime.now().plusDays(3);

                Visit firstVisit = Visit.builder()
                        .patient(patient)
                        .doctor(patient.getAffiliatedDoctor())
                        .clinic(patient.getAffiliatedClinic())
                        .visitDate(firstAppointmentDate)
                        .proceduresDone("Initial Consultation")
                        .doctorNotes("First appointment scheduled automatically for : "+ firstAppointmentDate )
                        .build();

                visitRepository.save(firstVisit);
                return visitMapper.toResponse(firstVisit);
            }
        }
    }

}
