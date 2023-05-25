package rw.solution.easy.dental.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.PreAvaliacao;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.record.DadosPreAvaliacao;
import rw.solution.easy.dental.model.repository.PreAvaliacaoRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class PreAvaliacaoService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 724475676658894665L;
	
	private static Logger log = Logger.getLogger(PreAvaliacaoService.class);
	
	@Autowired
	private PreAvaliacaoRepository repository;
	
	public DadosPreAvaliacao getPreAvaliacaoByPacienteID(Long pacienteId) {
		log.info(String.format(LogUtil.FORMATLOG, "paciente", "getPreAvaliacaoByPacienteID", " Buscando a pre avaliacao do paciente: "+pacienteId));
		PreAvaliacao preAvaliacao = this.repository.getPreAvaliacaoByPacienteID(pacienteId);
		return new DadosPreAvaliacao(preAvaliacao);
	}
	
	public Response updatePreAvaliacao(Long customerID, Long pacienteId, DadosPreAvaliacao dados){
		
		log.info(String.format(LogUtil.FORMATLOG, "paciente", "updatePreAvaliacao", " Buscando a pre avaliacao do paciente: "+pacienteId));
		PreAvaliacao pre = this.repository.getPreAvaliacaoByPacienteID(pacienteId);
		pre.atualizaInformacoes(dados);
		
		this.repository.saveAndFlush(pre);
		return new Response(true, "Pre avaliacao Atualizada com sucesso");
	}
	
}
