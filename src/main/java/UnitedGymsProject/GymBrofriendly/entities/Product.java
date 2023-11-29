package UnitedGymsProject.GymBrofriendly.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "prodotti")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private double price;
    private double weight;
    private int amount;
    private String productImage;
    @Enumerated(EnumType.STRING)
    private Size size;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="shoppingCart_id")
    private ShoppingCart shoppingCart;

}
