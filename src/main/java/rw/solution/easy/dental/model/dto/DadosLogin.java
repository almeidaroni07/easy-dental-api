package rw.solution.easy.dental.model.dto;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(@NotBlank
						 String username,
						 @NotBlank
						 String password) {

}
