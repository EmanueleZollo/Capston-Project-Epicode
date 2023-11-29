package UnitedGymsProject.GymBrofriendly.entities;

import UnitedGymsProject.GymBrofriendly.enums.PlanType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "schede")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Plan {
    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private PlanType planType;
   @ManyToMany(mappedBy = "planList")
    private List<User> userList;
   @OneToMany(fetch = FetchType.LAZY, mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Step> stepList;
}
