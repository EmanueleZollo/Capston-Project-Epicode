package UnitedGymsProject.GymBrofriendly.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    // collegamento many-to-many con List<Plan>

}
