package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.battery.rental.persistence.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    
    User findById(Integer id);

    @Override
    void delete(User user);

}
