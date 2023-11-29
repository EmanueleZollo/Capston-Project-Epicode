package UnitedGymsProject.GymBrofriendly.repositories;

import UnitedGymsProject.GymBrofriendly.entities.HealthCertificate;
import UnitedGymsProject.GymBrofriendly.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HealthCertificateRepository extends JpaRepository<HealthCertificate, UUID> {
    Optional<HealthCertificate> findByUser(User user);
}
