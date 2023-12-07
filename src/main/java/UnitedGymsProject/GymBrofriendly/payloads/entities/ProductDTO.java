package UnitedGymsProject.GymBrofriendly.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;



public record ProductDTO(
        @NotEmpty(message = "Devi inserire il titolo del prodotto ")
        String title,
        @NotEmpty(message = "Descrivi il prodotto")
        String description,
        @NotEmpty(message = "Inserisci il prezzo del prodotto")
        double price,

        @NotEmpty(message = "Inserisci un immagine del prodotto")
        String productImage,

        @NotEmpty(message = "Inserisci la quantità minima di prodotto vendibile")
        int amount,
        double weight,
        Size size

) {
}
