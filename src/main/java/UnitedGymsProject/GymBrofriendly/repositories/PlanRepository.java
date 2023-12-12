package UnitedGymsProject.GymBrofriendly.repositories;

import UnitedGymsProject.GymBrofriendly.entities.Plan;
import UnitedGymsProject.GymBrofriendly.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    Optional<Page<Plan>> findByUserList(User user, Pageable pageable);
}
