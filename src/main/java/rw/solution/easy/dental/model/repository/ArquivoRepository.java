package rw.solution.easy.dental.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rw.solution.easy.dental.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {
	
	@Query("SELECT arquivo FROM Arquivo arquivo WHERE arquivo.customer.id = :customerID")
	List<Arquivo> getArquivosByCustomer(@Param("customerID") Long customer);
	
	@Query("SELECT arquivo FROM Arquivo arquivo WHERE arquivo.id = :arquivoID")
	Arquivo getArquivoByID(@Param("arquivoID") Long arquivoID);
	
	@Query("SELECT arquivo.arquivo FROM Arquivo arquivo WHERE arquivo.id = :arquivoID")
	byte[] getArquivo(@Param("arquivoID") Long arquivoID);
	
}
