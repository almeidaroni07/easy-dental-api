package rw.solution.easy.dental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.solution.easy.dental.model.User;
import rw.solution.easy.dental.model.UserStatus;

public interface UsuarioRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT user FROM User user WHERE user.username = :username and user.status = :status")
	User getUsuarioByUsername(@Param("username") String username, @Param("status") UserStatus status);
	
	

}
