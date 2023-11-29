package UnitedGymsProject.GymBrofriendly.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

public record UserSignUpDTO(
@NotEmpty(message = "Il nome è un campo obbligatorio!")
String name,
@NotEmpty(message = "Il cognome è un campo obbligatorio!")
String surname,
@NotEmpty(message = "La mail è un campo obbligatorio!")
String email,
@NotEmpty(message = "La password è un campo obbligatorio!")
String password) {}
