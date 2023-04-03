package rw.solution.easy.dental.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.model.Orcamento;

@Repository
public interface OrcamentoRepository  extends JpaRepository<Orcamento, Long>{
	
	@Query("SELECT orcamento FROM Orcamento orcamento WHERE orcamento.customer.id = :customerID")
	List<Orcamento> getOrcamentos(@Param("customerID") Long customer);

}
