package rw.solution.easy.dental.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.Anamnese;
import rw.solution.easy.dental.model.Paciente;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.record.DadosAnamnese;
import rw.solution.easy.dental.model.repository.AnamneseRepository;
import rw.solution.easy.dental.model.repository.PacienteRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class AnamneseService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5592610375266071463L;
	
	private static Logger log = Logger.getLogger(AnamneseService.class);
	
	@Autowired
	private AnamneseRepository repository;
	
	@Autowired
	private PacienteRepository pacienteRepository;

	public DadosAnamnese getAnamneseByPacienteID(Long pacienteId) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "getAnamneseByPacienteID", "paciente", " Buscando a anamnese do paciente: "+pacienteId));
		Anamnese anamnese = this.repository.getAnamneseByPacienteID(pacienteId);
		return new DadosAnamnese(anamnese);
	}
	
	public Response updateAnamnese(Long customerID, Long pacienteId, Anamnese anamnese) throws Exception{
		
		Anamnese anamneseID = this.repository.getAnamneseByPacienteID(pacienteId);
		
		Paciente paciente = this.pacienteRepository.getPacienteByID(pacienteId);
		
		Anamnese updateA = new Anamnese(anamneseID.getId(), 
										paciente, 
										anamnese.isAlergicoMedicamento(), 
										anamnese.getAlergicoMedicamentoQual(), 
										anamnese.isUsaMedicamento(), 
										anamnese.getUsaMedicamentoQual(), 
										anamnese.isAlergiaAnestesia(), 
										anamnese.getAlergiaAnestesiaQual(), 
										anamnese.isGravidaOuAmamentando(), 
										anamnese.isDoencaCardioRespiratoria(), 
										anamnese.getDoencaCardioRespiratoriaQual(), 
										anamnese.isDoencaoTransmissivel(), 
										anamnese.getDoencaoTransmissivelQual(), 
										anamnese.isDiabetico(), 
										anamnese.isHipertencaoArterial(), 
										anamnese.isHemorragico(), 
										anamnese.isCirurgia(), 
										anamnese.getCirurgiaQual(), 
										anamnese.getInformacaoAdicional());
		
		this.repository.save(updateA);
	
		return new Response(true, "Anamnese Atualizada com sucesso");
	}

}
