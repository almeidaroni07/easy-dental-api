package rw.solution.easy.dental.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.solution.easy.dental.model.TratamentoProcedimento;
import rw.solution.easy.dental.model.TratamentoProcedimentoPK;

@Repository
public interface TratamentoProcedimentoRepository extends JpaRepository<TratamentoProcedimento, TratamentoProcedimentoPK> {

}
