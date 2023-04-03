package rw.solution.easy.dental.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.model.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long>  {

	@Query("SELECT procedimento FROM Procedimento procedimento WHERE procedimento.customer.id = :customer")
	List<Procedimento> getProcedimentosByCustomer(@Param("customer") Long customer);
	
	@Query("SELECT procedimento FROM Procedimento procedimento WHERE procedimento.id = :procedimentoID")
	Procedimento getProcedimentoByID(@Param("procedimentoID") Long procedimentoID);

}
