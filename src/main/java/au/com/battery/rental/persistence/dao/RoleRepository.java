package au.com.battery.rental.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.battery.rental.persistence.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
