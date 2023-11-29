package UnitedGymsProject.GymBrofriendly.payloads.entities;

import UnitedGymsProject.GymBrofriendly.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record HealthCertificateDTO(
        @NotEmpty(message = "Caricare un certificato medico valido")
        String url,
        @NotEmpty(message = "Certificato da abbinare obbligatoriamente a un utente")
        User user,
        LocalDate newCertificate,
        LocalDate expiredCertificate
) {
}
