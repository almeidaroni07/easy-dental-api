package rw.solution.easy.dental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.solution.easy.dental.model.PreAvaliacao;

public interface PreAvaliacaoRepository extends JpaRepository<PreAvaliacao, Long>  {
	
	@Query("SELECT preAvaliacao FROM PreAvaliacao preAvaliacao WHERE preAvaliacao.paciente.id = :pacienteID")
	PreAvaliacao getPreAvaliacaoByPacienteID(@Param("pacienteID") Long pacienteID);

}
