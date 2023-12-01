package UnitedGymsProject.GymBrofriendly.repositories;

import UnitedGymsProject.GymBrofriendly.entities.Event;
import UnitedGymsProject.GymBrofriendly.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Page<Event>> findByUserList(User user, Pageable pageable);

    Optional<Page<Event>> findByEventDate(LocalDate eventDate, Pageable pageable);

    Optional<Event> findByTitle(String title);
}
