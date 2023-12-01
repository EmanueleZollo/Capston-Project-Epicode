package UnitedGymsProject.GymBrofriendly.payloads.entities;

import UnitedGymsProject.GymBrofriendly.entities.Step;
import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.enums.PlanType;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PlanDTO(
        @NotEmpty(message = "Indica la tipologia di piano")
        PlanType planType,
        @NotEmpty(message = "Necessario indicare il professionista che l'ha fatto e indicare, se c'è, l'utente a cui è rivolto")
        List<User> userList,
        @NotEmpty(message = "Necessario inserire i passi da seguire per il piano")
        List<Step> stepList
) {
}
