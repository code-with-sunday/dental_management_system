package com.brightSmileDental.solution.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "clinic")
public class Clinic extends AuditBaseEntity{
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String city;
    private String state;
    @OneToMany(mappedBy = "affiliatedClinic")
    private Set<Patient> patients = new HashSet<>();

    @OneToMany(mappedBy = "affiliatedClinic")
    private Set<Doctor> doctors = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "clinic_opening_hours", joinColumns = @JoinColumn(name = "clinic_id"))
    @MapKeyColumn(name = "day_of_week")
    @Column(name = "hours")
    private Map<String, String> openingHours = getDefaultOpeningHours();

    public Map<String, String> getDefaultOpeningHours() {
        Map<String, String> hours = new HashMap<>();
        hours.put("monday", "9:00 AM - 5:00 PM");
        hours.put("tuesday", "9:00 AM - 5:00 PM");
        hours.put("wednesday", "9:00 AM - 5:00 PM");
        hours.put("thursday", "9:00 AM - 5:00 PM");
        hours.put("friday", "9:00 AM - 5:00 PM");
        hours.put("saturday", "9:00 AM - 2:00 PM");
        hours.put("sunday", "Closed");
        return hours;
    }

    public Clinic() {
        this.openingHours = getDefaultOpeningHours();
    }
}
