package UnitedGymsProject.GymBrofriendly.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "certificato_medico")
@ToString
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class HealthCertificate {
    @Id
    @GeneratedValue
    private UUID id;
    private String url;
    private LocalDate newCertificate;
    private LocalDate expiredCertificate;
    @OneToOne(mappedBy = "healthCertificate")
    private User user;
}
