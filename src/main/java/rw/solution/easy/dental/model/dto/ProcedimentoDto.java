package rw.solution.easy.dental.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProcedimentoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6803592280239134338L;
	
	private Long procedimentoID;
	private LocalDate data; 
	private String inicio;
	private String fim;
	private String procedimentoNome;
	
	
	public Long getProcedimentoID() {
		return procedimentoID;
	}
	public void setProcedimentoID(Long procedimentoID) {
		this.procedimentoID = procedimentoID;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
	public String getProcedimentoNome() {
		return procedimentoNome;
	}
	public void setProcedimentoNome(String procedimentoNome) {
		this.procedimentoNome = procedimentoNome;
	}
	
	public LocalDate getDataFormatada() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataStr = this.data.format(formatter);
			return LocalDate.parse(dataStr, formatter);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this.data;
	}
}
