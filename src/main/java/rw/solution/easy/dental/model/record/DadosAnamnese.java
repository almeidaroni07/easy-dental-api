package rw.solution.easy.dental.model.record;

import rw.solution.easy.dental.model.Anamnese;

public record DadosAnamnese(Long id,
						    boolean alergicoMedicamento,
							String alergicoMedicamentoQual,
							boolean usaMedicamento,
							String usaMedicamentoQual,
							boolean alergiaAnestesia,
							String alergiaAnestesiaQual,
							boolean gravidaOuAmamentando,
							boolean doencaCardioRespiratoria,
							String doencaCardioRespiratoriaQual,
							boolean doencaoTransmissivel,
							String doencaoTransmissivelQual,
							boolean diabetico,
							boolean hipertencaoArterial,
							boolean hemorragico,
							boolean cirurgia,
							String cirurgiaQual,
							String informacaoAdicional) {
	
	public DadosAnamnese(Anamnese anamnese) {
		this(anamnese.getId(),
			 anamnese.isAlergicoMedicamento(), 
			 anamnese.getAlergicoMedicamentoQual(), 
			 anamnese.isUsaMedicamento(), 
			 anamnese.getUsaMedicamentoQual(), 
			 anamnese.isAlergiaAnestesia(), 
			 anamnese.getAlergiaAnestesiaQual(), 
			 anamnese.isGravidaOuAmamentando(), 
			 anamnese.isDoencaCardioRespiratoria(), 
			 anamnese.getDoencaCardioRespiratoriaQual(), 
			 anamnese.isDoencaoTransmissivel(), 
			 anamnese.getDoencaoTransmissivelQual(), 
			 anamnese.isDiabetico(), 
			 anamnese.isHipertencaoArterial(), 
			 anamnese.isHemorragico(), 
			 anamnese.isCirurgia(), 
			 anamnese.getCirurgiaQual(), 
			 anamnese.getInformacaoAdicional());	
	}
	

}
