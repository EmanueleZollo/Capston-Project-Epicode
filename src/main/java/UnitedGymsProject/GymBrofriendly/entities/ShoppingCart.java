package UnitedGymsProject.GymBrofriendly.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingCart", cascade = CascadeType.ALL)
    private List<Product> products;
}
