package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.Orcamento;
import rw.solution.easy.dental.model.OrcamentoProcedimento;
import rw.solution.easy.dental.model.repository.OrcamentoProcedimentoRepository;
import rw.solution.easy.dental.model.repository.OrcamentoRepository;

@Service
public class OrcamentoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5803854306455361300L;
	
	@Autowired
	private OrcamentoRepository repository;
	
	@Autowired
	private OrcamentoProcedimentoRepository opRepository;

	public List<Orcamento> getOrcamentos(Long customer) throws Exception {
		return this.repository.getOrcamentos(customer);
	}

	public List<OrcamentoProcedimento> getProcedimentosOrcamento(Long customer, Long orcamentoID) throws Exception {
		return this.opRepository.getProcedimentosOrcamento(orcamentoID);
	}

}
