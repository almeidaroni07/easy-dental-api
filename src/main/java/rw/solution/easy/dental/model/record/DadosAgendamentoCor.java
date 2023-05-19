package rw.solution.easy.dental.model.record;

public record DadosAgendamentoCor(String primary,
								  String secondary) {
	
	public DadosAgendamentoCor(String primary, String secondary) {
		this.primary = primary;
		this.secondary = secondary;
	}
	
}
