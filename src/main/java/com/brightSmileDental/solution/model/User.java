package com.brightSmileDental.solution.model;

import com.brightSmileDental.solution.enums.ROLE;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "users")
public class User extends AuditBaseEntity{
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ROLE role;
}
