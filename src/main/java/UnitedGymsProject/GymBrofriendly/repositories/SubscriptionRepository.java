package UnitedGymsProject.GymBrofriendly.repositories;

import UnitedGymsProject.GymBrofriendly.entities.Subscription;
import UnitedGymsProject.GymBrofriendly.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByUser(User user);
}
