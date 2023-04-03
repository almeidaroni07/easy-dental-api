package rw.solution.easy.dental.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.model.Paciente;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, Long> {
	
	@Query("SELECT paciente FROM Paciente paciente WHERE paciente.customer.id = :customerID")
	List<Paciente> getPacientesByCustomer(@Param("customerID") Long customer);
	
	@Query("SELECT paciente FROM Paciente paciente WHERE paciente.id = :id")
	Paciente getPacienteByID(@Param("id") Long id);

}
