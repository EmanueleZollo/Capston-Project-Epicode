package UnitedGymsProject.GymBrofriendly.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "step")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private String additionalInfo;
    private String exerciseImageUrl;
    private String exerciseSet;
    private String restTime;
    private double weight;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id")
    private Plan plan;

}
