package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.battery.rental.persistence.model.User;
import au.com.battery.rental.persistence.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}
