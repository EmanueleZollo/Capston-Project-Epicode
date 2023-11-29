package UnitedGymsProject.GymBrofriendly.entities;

import UnitedGymsProject.GymBrofriendly.enums.Role;
import jakarta.persistence.*;
import jdk.jshell.JShell;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "utenti")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
    @OneToOne
    @JoinColumn(name = "shoppingCart_id")
    private ShoppingCart shoppingCart;
    @ManyToMany
    @JoinTable(
            name = "schede workout e alimentari",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "plan_id"))
    private List<Plan> planList;
    @ManyToMany
    @JoinTable(
            name = "event_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> eventList;
    private String avatarUrl;
    private boolean healthCertificate;

}
