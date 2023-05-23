package rw.solution.easy.dental.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import rw.solution.easy.dental.model.Arquivo;
import rw.solution.easy.dental.model.Customer;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.record.DadosArquivo;
import rw.solution.easy.dental.model.record.DadosArquivoEntity;
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

	public List<DadosArquivoEntity> getArquivos(Long customer) {
		log.info(String.format(LogUtil.FORMATLOG, "getArquivos", "arquivo", "Customer"+customer));
		List<Arquivo> listArquivo = this.repository.getArquivosByCustomer(customer);
		return listArquivo.stream().map(arquivo -> new DadosArquivoEntity(arquivo)).toList();
	}
	
	public DadosArquivoEntity getArquivoPorID(Long arquivoID) {
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivoPorID", "arquivoID: "+arquivoID));
		Arquivo arquivo = this.repository.getArquivoByID(arquivoID);
		return new DadosArquivoEntity(arquivo);
	}

	public Response save(Long customerID, DadosArquivoEntity dados) {

		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "save", "customer: "+customerID));
		Customer customer = this.customerRepository.getCustomerById(customerID);
		
		Arquivo arquivo = new Arquivo(dados, customer);
		Arquivo save = this.repository.save(arquivo);
		
		return new Response(true, "Arquivo adicionado com sucesso", save.getId());
	}

	public Response update(Long customerID, Long arquivoID, DadosArquivoEntity dados) {
		
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "update", "arquivoID: "+arquivoID));
		Arquivo arquivo = this.repository.getArquivoByID(arquivoID);
		arquivo.atualizarInformacoes(dados);
		
		this.repository.save(arquivo);
		
		return new Response(true, "Arquivo atualizado com sucesso", arquivoID);
	}

	public Response delete(Long customer, Long arquivoID) {
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "delete", "arquivoID: "+arquivoID));
		 Arquivo arquivoByID = this.repository.getArquivoByID(arquivoID);
		 this.repository.delete(arquivoByID);
		return new Response(true, "Arquivo deletado com sucesso", arquivoID);
	}
	
	
	public Response updateArquivo(Long customer, Long arquivoID, MultipartFile blob) {
		
		
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "updateArquivo", "arquivoID: "+arquivoID));
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "updateArquivo", "tipo arquivo: "+blob.getContentType()));
		Arquivo arquivo = this.repository.getArquivoByID(arquivoID);
		arquivo.atualizarInformacoesArquivo(blob);
		
		this.repository.save(arquivo);
		
		return new Response(true, "Arquivo atualizado com sucesso.");
	}

	
	public byte[] getArquivo(Long customer, Long arquivoID) {
		log.info(String.format(LogUtil.FORMATLOG, "arquivo", "getArquivo", "arquivoID: "+arquivoID));
		return this.repository.getArquivo(arquivoID);
	}


	public DadosArquivo downloadArquivo(Long customer, Long arquivoID, HttpServletRequest request) {
		byte[] response = this.getArquivo(customer, arquivoID);
		log.info(String.format(LogUtil.FORMATLOG, "getArquivo", "arquivo", "Blob: "+response));
		
		if(null != response) {
			
			Resource resource = new ByteArrayResource(response);
			
			Arquivo arquivo = this.repository.getArquivoByID(arquivoID);
			String contentType = arquivo.getTipo();
			
			log.info(String.format(LogUtil.FORMATLOG, "getArquivo", "arquivo", "contentType: "+contentType));
			return new DadosArquivo(resource, contentType);
		}
		
		return null;
	}


}
