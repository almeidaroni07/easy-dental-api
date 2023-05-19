package rw.solution.easy.dental.model.record;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import rw.solution.easy.dental.model.Paciente;

public record DadosPaciente(@NotBlank String nome,
							@NotBlank String email,
							@NotBlank String cpf,
							@NotBlank String rg,
							@NotBlank String rua,
							@NotBlank String bairro,
							@NotBlank String cidade,
							@NotBlank String estado,
							@NotNull LocalDate dataNascimento,
							String ultimaConsulta,
							String status) {
	
	public DadosPaciente(Paciente paciente) {
		this(paciente.getNome(), 
			 paciente.getEmail(), 
			 paciente.getCpf(), 
			 paciente.getRg(), 
			 paciente.getRua(), 
			 paciente.getBairro(), 
			 paciente.getCidade(), 
			 paciente.getEstado(), 
			 paciente.getDataNascimento(),
			 paciente.getUltimaConsultaFmt(),
			 paciente.getStatus().toString());
	}

}
