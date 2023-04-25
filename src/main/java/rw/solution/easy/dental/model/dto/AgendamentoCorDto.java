package rw.solution.easy.dental.model.dto;

import java.io.Serializable;

public class AgendamentoCorDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8330644413386714486L;
	
	private String primary;
	private String secondary;
	
	public AgendamentoCorDto() {
		// TODO Auto-generated constructor stub
	}

	public AgendamentoCorDto(String primary, String secondary) {
		super();
		this.primary = primary;
		this.secondary = secondary;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getSecondary() {
		return secondary;
	}

	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

}
