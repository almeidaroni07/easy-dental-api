package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
import rw.solution.easy.dental.model.record.DadosProcedimento;
import rw.solution.easy.dental.model.record.DadosTratamento;
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
	
	public Response save(Long customerID, Long pacienteID, DadosTratamento dados) throws Exception {
		
		log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", "customer: "+customerID));
		log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", "pacienteID: "+pacienteID));
		Customer customer = this.customerRepository.getCustomerById(customerID);
		
		log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", "Buscando o Paciente."));
		Paciente paciente = this.pacienteRepository.getPacienteByID(pacienteID);
		
		
		log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", "Salvando o tratamento"));
		Tratamento tratamento = this.repository.save(new Tratamento(dados, paciente));
		
		Orcamento orcamento = this.orcamentoRepository.save(new Orcamento(customer, paciente));
		List<OrcamentoProcedimento> listOrcamento = new ArrayList<OrcamentoProcedimento>();
		Double valorTotal = 0.0;
		
		log.info(String.format(LogUtil.FORMATLOG, "save", "tratamento", "Salvando os agendamentos"));
		for(DadosProcedimento dadosProcedimento : dados.procedimentos()) {
			
			Procedimento procedimentoByID = this.procedimentoRepository.getProcedimentoByID(dadosProcedimento.procedimentoID());
			this.agendaRepository.save(new Agenda(paciente, dadosProcedimento, procedimentoByID, tratamento));
			
			this.tpRepository.save(new TratamentoProcedimento(new TratamentoProcedimentoPK(tratamento.getId(), paciente.getId()), dadosProcedimento.procedimentoNome()));
			
			listOrcamento.add(new OrcamentoProcedimento(orcamento, procedimentoByID));
			valorTotal = valorTotal + procedimentoByID.getValor();
		}
		
		orcamento.setValorTotal(valorTotal);
		this.orcamentoRepository.save(orcamento);
		
		this.opRepository.saveAll(listOrcamento);
		
		return new Response(true, "Tratamento salvo com sucesso.", tratamento.getId());
	}

	public Response updateAssinatura(Long customer, Long tratamentoID, MultipartFile assinatura) throws Exception  {
		
		log.info(String.format(LogUtil.FORMATLOG, "updateAssinatura", "tratamento", "assinatura: "+assinatura));
		
		log.info(String.format(LogUtil.FORMATLOG, "updateAssinatura", "tratamento", "tratamentoID: "+tratamentoID));
		Tratamento tratamento = this.repository.getTratamentoByID(tratamentoID);
		byte[] assinaturaBytes = assinatura.getBytes();
		log.info(String.format(LogUtil.FORMATLOG, "updateAssinatura", "tratamento", "assinaturaBytes: "+assinaturaBytes));
		tratamento.setAssinatura(assinaturaBytes);
		
		this.repository.save(tratamento);
		
		return new Response(true, "Assinatura atualizada com sucesso.");
	}

	public byte[] getArquivoAssinatura(Long customer, Long tratamentoID) {
		return this.repository.getAssinaturaByID(tratamentoID);
	}

}
