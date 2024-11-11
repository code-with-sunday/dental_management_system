package com.brightSmileDental.solution.model;

import com.brightSmileDental.solution.enums.Speciality;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "doctor")
public class Doctor extends AuditBaseEntity{
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;

    @ElementCollection(targetClass = Speciality.class)
    @Enumerated(EnumType.STRING)
    private Set<Speciality> specialties;

    @ManyToMany
    @JoinTable(
            name = "doctor_affiliation",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id")
    )
    private Set<Clinic> clinics = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "affiliated_clinic_id")
    private Clinic affiliatedClinic;
}
