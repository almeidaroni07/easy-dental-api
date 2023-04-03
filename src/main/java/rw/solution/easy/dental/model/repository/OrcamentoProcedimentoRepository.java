package rw.solution.easy.dental.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.model.OrcamentoProcedimento;

@Repository
public interface OrcamentoProcedimentoRepository extends JpaRepository<OrcamentoProcedimento, Long> {
	
	@Query("SELECT op FROM OrcamentoProcedimento op WHERE op.orcamento.id = :orcamentoID")
	List<OrcamentoProcedimento> getProcedimentosOrcamento(@Param("orcamentoID") Long orcamentoID);

}
