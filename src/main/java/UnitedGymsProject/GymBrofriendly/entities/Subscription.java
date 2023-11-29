package UnitedGymsProject.GymBrofriendly.entities;

import UnitedGymsProject.GymBrofriendly.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate activationDate;
    private LocalDate expirationDate;
    @OneToOne(mappedBy = "subscription")
    private User user;
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;

}
