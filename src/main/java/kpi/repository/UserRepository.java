package kpi.repository;

import kpi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT l FROM User l LEFT JOIN FETCH l.roles Role where l.email = :email")
    User findByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles Role where u.lastname like %:keyword%")
    List<User> findAllByLastnameContaining(String keyword);
}
