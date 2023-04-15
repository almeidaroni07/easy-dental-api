package rw.solution.easy.dental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.model.Tratamento;

@Repository
public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
	
	@Query("SELECT tratamento FROM Tratamento tratamento WHERE tratamento.paciente.id = :pacienteID")
	Tratamento getTratamentoByPacienteID(@Param("pacienteID") Long pacienteID);
	
	@Query("SELECT tratamento FROM Tratamento tratamento WHERE tratamento.id = :tratamentoID")
	Tratamento getTratamentoByID(@Param("tratamentoID") Long tratamentoID);
	
	@Query("SELECT tratamento.assinatura FROM Tratamento tratamento WHERE tratamento.id = :tratamentoID")
	byte[] getAssinaturaByID(@Param("tratamentoID") Long tratamentoID);

}
