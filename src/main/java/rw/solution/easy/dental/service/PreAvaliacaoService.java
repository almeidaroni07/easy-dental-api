package rw.solution.easy.dental.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.PreAvaliacao;
import rw.solution.easy.dental.model.Response;
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
	
	public PreAvaliacao getPreAvaliacaoByPacienteID(Long pacienteId) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "getPreAvaliacaoByPacienteID", "paciente", " Buscando a pre avaliacao do paciente: "+pacienteId));
		return this.repository.getPreAvaliacaoByPacienteID(pacienteId);
	}
	
	public Response updatePreAvaliacao(Long customerID, Long pacienteId, PreAvaliacao pre) throws Exception{
		this.repository.save(pre);
		return new Response(true, "Pre avaliacao Atualizada com sucesso");
	}
	
}
