package rw.solution.easy.dental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.model.Anamnese;

@Repository
public interface AnamneseRepository extends JpaRepository<Anamnese, Long>  {
	
	@Query("SELECT anamnese FROM Anamnese anamnese WHERE anamnese.paciente.id = :pacienteId")
	Anamnese getAnamneseByPacienteID(@Param("pacienteId") Long pacienteId);

}
