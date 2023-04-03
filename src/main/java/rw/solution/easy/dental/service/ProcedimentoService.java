package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.solution.easy.dental.model.Customer;
import rw.solution.easy.dental.model.Procedimento;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.repository.CustomerRepository;
import rw.solution.easy.dental.model.repository.ProcedimentoRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class ProcedimentoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3519498123495379773L;
	
	private static Logger log = Logger.getLogger(ProcedimentoService.class);
	
	@Autowired
	private ProcedimentoRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Procedimento> getProcedimentos(Long customer) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", "customer: "+customer));
		return this.repository.getProcedimentosByCustomer(customer);
	}
	

	public Response save(Long customerID, Procedimento procedimento) throws Exception {
		
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", "customer: "+customerID));
		Customer customer = this.customerRepository.getCustomerById(customerID);
		procedimento.setCustomer(customer);
		
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", "Salvando o  procedimento"));
		this.repository.save(procedimento);
		
		return new Response(true, "Procedimento Salvo com sucesso.");
	}


	public Response update(Long customerID, Long procedimentoID, Procedimento procedimento) throws Exception  {
		
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", "Atualizando o procedimento: "+procedimentoID));
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", "customer: "+customerID));
		Customer customer = this.customerRepository.getCustomerById(customerID);
		procedimento.setCustomer(customer);
		procedimento.setId(procedimentoID);
		this.repository.save(procedimento);
		
		return new Response(true, "Procedimento Atualizado com sucesso.");
	}


	public Response delete(Long customer, Long procedimentoID) throws Exception  {
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", "Deletando o procedimento: "+procedimentoID));
		Procedimento procedimento = this.repository.getProcedimentoByID(procedimentoID);
		this.repository.delete(procedimento);
		return new Response(true, "Procedimento deletado com sucesso.");
	}


	public Procedimento getPacienteByID(Long procedimentoID) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "getProcedimentos", "procedimento", "Deletando o procedimento: "+procedimentoID));
		return this.repository.getProcedimentoByID(procedimentoID);
	}

	

}
