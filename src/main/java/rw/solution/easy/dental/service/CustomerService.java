package rw.solution.easy.dental.service;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rw.solution.easy.dental.model.Customer;
import rw.solution.easy.dental.model.Response;
import rw.solution.easy.dental.model.repository.CustomerRepository;
import rw.solution.easy.dental.util.LogUtil;

@Service
public class CustomerService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6991797431185639674L;
	
	private static Logger log = Logger.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository repository;

	public Response updateLogo(Long customerID, MultipartFile arquivo) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "CustomerService", "updateLogo", "customer: "+customerID));
		Customer customer = this.repository.getCustomerById(customerID);
		customer.setLogo(arquivo.getBytes());
		this.repository.save(customer);
		
		return new Response(true, "Customer atualizado com sucesso");
	}

	public byte[] getLogo(Long customer) throws Exception {
		log.info(String.format(LogUtil.FORMATLOG, "CustomerService", "getLogo", "customer: "+customer));
		return this.repository.getLoogByCustomerID(customer);
	}

}
