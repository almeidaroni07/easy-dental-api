package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rw.solution.easy.dental.model.Anamnese;
import rw.solution.easy.dental.model.Customer;
import rw.solution.easy.dental.model.Paciente;
import rw.solution.easy.dental.model.PreAvaliacao;
import rw.solution.easy.dental.model.PreAvaliacaoAnamnese;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.enums.StatusPaciente;
import rw.solution.easy.dental.model.repository.AnamneseRepository;
import rw.solution.easy.dental.model.repository.CustomerRepository;
import rw.solution.easy.dental.model.repository.PacienteRepository;
import rw.solution.easy.dental.model.repository.PreAvaliacaoRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class PacienteService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2185274976074100296L;
	
	private static Logger log = Logger.getLogger(PacienteService.class);

	@Autowired
	private PacienteRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AnamneseRepository anamneseRepository;
	
	@Autowired
	private PreAvaliacaoRepository preAvaliacaoRepository;

	public List<Paciente> getPacientesByCustomer(Long customer) throws Exception {
		return this.repository.getPacientesByCustomer(customer);
	}
	
	public Paciente getPacienteByID(Long id) throws Exception {
		return this.repository.getPacienteByID(id);
	}

	@Transactional
	public Response addPaciente(Long customerID, Paciente paciente) throws Exception{
		
		Customer customer = this.customerRepository.getCustomerById(customerID);
		paciente.setCustomer(customer);
		paciente.setUltimaConsulta(LocalDate.now());
		paciente.setStatus(StatusPaciente.ATIVO);
	
		Paciente save = this.repository.save(paciente);
		
		Anamnese anamnese = new Anamnese(customerID, save, true,  "", false, "", false, "",  false, false, "", false, "", false, false, false, false, "", "teste teste");
		this.anamneseRepository.save(anamnese);
		
		PreAvaliacao preAvaliacao = new PreAvaliacao(paciente, false, false, false, true, false, false, " teste teste ");
		this.preAvaliacaoRepository.save(preAvaliacao);
		
		return new Response(true, "Paciente adicionado com sucesso");
	}
	
	@Transactional
	public Response updatePaciente(Long customerID, Long id, Paciente paciente) throws Exception{
		
		Paciente paciente2 = this.repository.getPacienteByID(id);
		
		Paciente updateP = new Paciente(id, 
										paciente.getNome(), 
										paciente.getDataNascimento(), 
										paciente.getEmail(), 
										paciente.getCpf(), 
										paciente.getRg(), 
										StatusPaciente.ATIVO, 
										paciente.getUltimaConsulta(), 
										paciente.getRua(), 
										paciente.getBairro(), 
										paciente.getCidade(), 
										paciente.getEstado(),
										paciente2.getCustomer());	
		
		
		this.repository.save(updateP);
		
		return new Response(true, "Paciente adicionado com sucesso");
	}
	
	
	@Transactional
	public Response delete(Long customerID, Long pacienteID) throws Exception{
		
		Anamnese anamnese = this.anamneseRepository.getAnamneseByPacienteID(pacienteID);
		this.anamneseRepository.delete(anamnese);
		
		PreAvaliacao pre = this.preAvaliacaoRepository.getPreAvaliacaoByPacienteID(pacienteID);
		this.preAvaliacaoRepository.delete(pre);
		
		Paciente paciente = this.repository.getPacienteByID(pacienteID);
		this.repository.delete(paciente);
		
		return new Response(true, "Paciente adicionado com sucesso");
	}

	public PreAvaliacaoAnamnese getAvaliacaoAnamnese(Long customer, Long pacienteId) throws Exception {
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", "pacienteId: "+pacienteId));
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", " Buscando a anamnese"));
		Anamnese anamnese = this.anamneseRepository.getAnamneseByPacienteID(pacienteId);
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", " Buscando a pre avaliacao"));
		PreAvaliacao preAvaliacao = this.preAvaliacaoRepository.getPreAvaliacaoByPacienteID(pacienteId);
		
		return new PreAvaliacaoAnamnese(anamnese, preAvaliacao);
	}

	public Response updatePreAnamnese(Long customer, Long pacienteId, PreAvaliacaoAnamnese parameter) throws Exception {
		
		Paciente pacienteByID = this.repository.getPacienteByID(pacienteId);
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", " Atualizado a anamnese"));
		Anamnese anamnese = parameter.getAnamnese();
		anamnese.setPaciente(pacienteByID);
		this.anamneseRepository.save(anamnese);
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", " Atualizando a pre avaliacao"));
		PreAvaliacao pre = parameter.getPreAvaliacao();
		pre.setPaciente(pacienteByID);
		this.preAvaliacaoRepository.save(pre);
		
		return new Response(true, "Pre avaliacao e anamnese atualizada com sucesso.");
	}
	
	

}
