package UnitedGymsProject.GymBrofriendly.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="carrello")
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue
    private long id;
    private double amountToBePaid;
    @OneToOne(mappedBy = "shoppingCart")
    private User user;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, mappedBy = "shoppingCart")
    private Set<ShoppingCartItem> shoppingCartItems;
}
