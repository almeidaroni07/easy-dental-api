package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.Agenda;
import rw.solution.easy.dental.model.Customer;
import rw.solution.easy.dental.model.Orcamento;
import rw.solution.easy.dental.model.OrcamentoProcedimento;
import rw.solution.easy.dental.model.Paciente;
import rw.solution.easy.dental.model.Procedimento;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.Tratamento;
import rw.solution.easy.dental.model.TratamentoProcedimento;
import rw.solution.easy.dental.model.TratamentoProcedimentoPK;
import rw.solution.easy.dental.model.dto.ProcedimentoDto;
import rw.solution.easy.dental.model.enums.StatusConsulta;
import rw.solution.easy.dental.model.repository.AgendaRepository;
import rw.solution.easy.dental.model.repository.CustomerRepository;
import rw.solution.easy.dental.model.repository.OrcamentoProcedimentoRepository;
import rw.solution.easy.dental.model.repository.OrcamentoRepository;
import rw.solution.easy.dental.model.repository.PacienteRepository;
import rw.solution.easy.dental.model.repository.ProcedimentoRepository;
import rw.solution.easy.dental.model.repository.TratamentoProcedimentoRepository;
import rw.solution.easy.dental.model.repository.TratamentoRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class TratamentoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5515506776802334819L;
	
	private static Logger log = Logger.getLogger(TratamentoService.class);
	
	@Autowired
	private TratamentoRepository repository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private TratamentoProcedimentoRepository tpRepository;
	
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private OrcamentoProcedimentoRepository opRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Response save(Long customerID, Long pacienteID, Tratamento tratamento) throws Exception {
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", "customer: "+customerID));
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", "pacienteID: "+pacienteID));
		Customer customer = this.customerRepository.getCustomerById(customerID);
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", "Buscando o Paciente."));
		Paciente pacienteByID = this.pacienteRepository.getPacienteByID(pacienteID);
		
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", "Salvando o tratamento"));
		tratamento.setPaciente(pacienteByID);
		tratamento.setDataTratamento(LocalDate.now());
		Tratamento save = this.repository.save(tratamento);
		
		Orcamento orcamento = this.orcamentoRepository.save(new Orcamento(pacienteID, LocalDate.now(), customer, pacienteByID.getNome()));
		List<OrcamentoProcedimento> listOrcamento = new ArrayList<OrcamentoProcedimento>();
		Double valorTotal = 0.0;
		
		log.info(String.format(LogUtil.FORMATLOG, "getAvaliacaoAnamnese", "paciente", "Salvando os agendamentos"));
		for(ProcedimentoDto procedimento : tratamento.getProcedimentos()) {
			
			
			Procedimento procedimentoByID = this.procedimentoRepository.getProcedimentoByID(procedimento.getProcedimentoID());
			this.agendaRepository.save(new Agenda(pacienteByID, procedimento.getData(), procedimento.getInicio(), procedimento.getFim(), StatusConsulta.MARCADO, procedimentoByID));
			
			this.tpRepository.save(new TratamentoProcedimento(new TratamentoProcedimentoPK(save.getId(), pacienteByID.getId()), procedimento.getProcedimentoNome()));
			
			listOrcamento.add(new OrcamentoProcedimento(orcamento, procedimentoByID.getNome(), procedimentoByID.getValor()));
			valorTotal = valorTotal + procedimentoByID.getValor();
		}
		
		orcamento.setValorTotal(valorTotal);
		this.orcamentoRepository.save(orcamento);
		
		this.opRepository.saveAll(listOrcamento);
		
		return new Response(true, "Tratamento salvo com sucesso.");
	}

}
