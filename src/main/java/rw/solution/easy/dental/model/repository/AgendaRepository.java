package rw.solution.easy.dental.model.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
	
	@Query("SELECT agenda FROM Agenda agenda WHERE agenda.paciente.customer.id = :customerID")
	List<Agenda> getAgendamentos(@Param("customerID") Long customer);
	
	@Query("SELECT agenda FROM Agenda agenda WHERE agenda.paciente.customer.id = :customerID and agenda.dataConsulta = :hoje")
	List<Agenda> getAgendamentosHoje(@Param("customerID") Long customer, @Param("hoje") LocalDate hoje);
}
