package UnitedGymsProject.GymBrofriendly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "eventi")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private long id;
    private String place;
    private LocalDate eventDate;
    private String title;
    private int maxAttendance;
   @ManyToMany(mappedBy = "eventList")
    private List<User> userList;
}
