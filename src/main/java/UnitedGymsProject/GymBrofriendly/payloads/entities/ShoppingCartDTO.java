package UnitedGymsProject.GymBrofriendly.payloads.entities;

import UnitedGymsProject.GymBrofriendly.entities.Product;
import UnitedGymsProject.GymBrofriendly.entities.ShoppingCartItem;
import UnitedGymsProject.GymBrofriendly.entities.User;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

public record ShoppingCartDTO(
        @NotEmpty(message = "Collegare il carrello a un utente")
        User user,
        @NotEmpty(message = "Inserire articoli nel carrello")
        Set<ShoppingCartItem> shoppingCartItems,
        double amountToBePaid
) {
}
