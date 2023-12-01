package UnitedGymsProject.GymBrofriendly.payloads.entities;

import UnitedGymsProject.GymBrofriendly.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record NewEventDTO(
        @NotEmpty(message = "L'evento deve avere un titolo")
        String title,
        @NotEmpty(message = "L'evento deve avere una data e un'orario di inizio")
        LocalDate eventDate,
        @NotEmpty(message = "L'evento deve avere almeno due partecipanti")
        List<User> userList,
        String place,
        int maxAttendance
) {
}
