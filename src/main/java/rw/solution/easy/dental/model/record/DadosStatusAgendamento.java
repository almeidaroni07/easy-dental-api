package rw.solution.easy.dental.model.record;

public record DadosStatusAgendamento(String id, String status) {
	
	public DadosStatusAgendamento(String id, String status) {
		this.id = id;
		this.status = status;
	}

}
