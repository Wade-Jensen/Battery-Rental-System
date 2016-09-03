package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.battery.rental.persistence.model.PasswordResetToken;
import au.com.battery.rental.persistence.model.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

}
