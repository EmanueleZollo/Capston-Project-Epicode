package UnitedGymsProject.GymBrofriendly.payloads.entities;

import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.enums.SubscriptionType;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record SubscriptionDTO(
        @NotEmpty(message = "La data di attivazione deve essere inserita")
        LocalDate activationDate,
        @NotEmpty(message = "La tipologia di abbonamento va dichiarata sempre")
        SubscriptionType subscriptionType,
        @NotEmpty(message = "L'abbonamento deve essere legato obbligatoriamente a un utente")
        User user) {}
