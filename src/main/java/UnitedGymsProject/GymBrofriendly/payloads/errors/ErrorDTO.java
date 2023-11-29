package UnitedGymsProject.GymBrofriendly.payloads.errors;

import java.time.LocalDate;

public record ErrorDTO(String message, LocalDate timestamp) {
}
