package rw.solution.easy.dental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.security.model.User;

@Repository
public interface AuthenticationRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT user FROM User user WHERE user.id = :userID ")
	User getUserAuthByUserID(@Param("userID")Long userID);
	
	@Query("SELECT user FROM User user WHERE user.username = :username ")
	User getUserByUsername(@Param("username")String username);

}
