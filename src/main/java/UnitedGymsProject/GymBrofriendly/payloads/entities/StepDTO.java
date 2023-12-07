package UnitedGymsProject.GymBrofriendly.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

public record StepDTO(
        @NotEmpty(message = "Il titolo è necessario")
        String title,
        @NotEmpty(message = "In caso di assenza di immagine, inserire descrizione esercizio")
        String description,
        String exerciseImageUrl,
        @NotEmpty(message = "Inserire numero di ripetizioni da fare")
        String exerciseSet) {
}
