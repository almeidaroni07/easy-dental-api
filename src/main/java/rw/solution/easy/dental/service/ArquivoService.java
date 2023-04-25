package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rw.solution.easy.dental.model.Arquivo;
import rw.solution.easy.dental.model.Customer;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.repository.ArquivoRepository;
import rw.solution.easy.dental.model.repository.CustomerRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class ArquivoService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7512799932343718948L;
	
	private static Logger log = Logger.getLogger(ArquivoService.class);
	
	@Autowired
	private ArquivoRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;

	public List<Arquivo> getArquivos(Long customer) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "getArquivos", "arquivo", "Customer"+customer));
		return this.repository.getArquivosByCustomer(customer);
	}

	public Response save(Long customerID, Arquivo arquivo) throws Exception {

		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "save", "customer: "+customerID));
		Customer customer = this.customerRepository.getCustomerById(customerID);
		
		arquivo.setCustomer(customer);
		Arquivo save = this.repository.save(arquivo);
		
		return new Response(true, "Arquivo adicionado com sucesso", save.getId());
	}

	public Response updateArquivo(Long customer, Long arquivoID, MultipartFile blob) throws Exception {
		
		
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "updateArquivo", "arquivoID: "+arquivoID));
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "updateArquivo", "tipo arquivo: "+blob.getContentType()));
		Arquivo arquivo = this.repository.getArquivoByID(arquivoID);
		byte[] arquivoBytes = blob.getBytes();
		arquivo.setArquivo(arquivoBytes);
		arquivo.setTipo(blob.getContentType());
		
		this.repository.save(arquivo);
		
		return new Response(true, "Arquivo atualizado com sucesso.");
	}

	public byte[] getArquivo(Long customer, Long arquivoID) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivo", "arquivoID: "+arquivoID));
		return this.repository.getArquivo(arquivoID);
	}

	public Response update(Long customerID, Long arquivoID, Arquivo arquivo) throws Exception {
		
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "update", "arquivoID: "+arquivoID));
		Arquivo arquivoUpdate = this.repository.getArquivoByID(arquivoID);
		arquivoUpdate.setNome(arquivo.getNome());
		
		this.repository.save(arquivoUpdate);
		
		return new Response(true, "Arquivo atualizado com sucesso", arquivoID);
	}

	public Response delete(Long customer, Long arquivoID) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "delete", "arquivoID: "+arquivoID));
		 Arquivo arquivoByID = this.repository.getArquivoByID(arquivoID);
		 this.repository.delete(arquivoByID);
		return new Response(true, "Arquivo deletado com sucesso", arquivoID);
	}

	public Arquivo getArquivoPorID(Long arquivoID) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivoPorID", "arquivoID: "+arquivoID));
		return this.repository.getArquivoByID(arquivoID);
	}


}
