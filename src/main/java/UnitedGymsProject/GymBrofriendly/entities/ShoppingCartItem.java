package UnitedGymsProject.GymBrofriendly.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Formula;

@Entity
@Getter
@ToString
@Setter
public class ShoppingCartItem {
    @Id
    @GeneratedValue
    long id;
    int quantity;
    double price;
   @Formula("price*quantity")
    double totalPrice;
    @OneToOne
    Product product;
    @ManyToOne
    @JoinColumn(name="shoppingCart_id")
    ShoppingCart shoppingCart;
}
