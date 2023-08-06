package rw.solution.easy.dental.model.dto;

import java.util.List;

public record DadosTokenJWT(String token, String tipo, List<Long> idsClinicas ) {

}
