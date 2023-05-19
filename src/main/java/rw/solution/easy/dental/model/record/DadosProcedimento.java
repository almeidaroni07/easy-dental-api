package rw.solution.easy.dental.model.record;

import java.time.LocalDate;

public record DadosProcedimento(Long procedimentoID,
								LocalDate data,
								String inicio,
								String fim,
								String procedimentoNome) {

}
