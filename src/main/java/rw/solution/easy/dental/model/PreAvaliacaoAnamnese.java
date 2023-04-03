package rw.solution.easy.dental.model;

import java.io.Serializable;

public class PreAvaliacaoAnamnese implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1151885387468929602L;
	
	private Anamnese anamnese;
	private PreAvaliacao preAvaliacao;
	
	public PreAvaliacaoAnamnese() {
		// TODO Auto-generated constructor stub
	}
	
	public PreAvaliacaoAnamnese(Anamnese anamnese, PreAvaliacao preAvaliacao) {
		super();
		this.anamnese = anamnese;
		this.preAvaliacao = preAvaliacao;
	}

	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}

	public PreAvaliacao getPreAvaliacao() {
		return preAvaliacao;
	}

	public void setPreAvaliacao(PreAvaliacao preAvaliacao) {
		this.preAvaliacao = preAvaliacao;
	}
}
