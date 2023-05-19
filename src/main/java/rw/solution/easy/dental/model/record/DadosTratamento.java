package rw.solution.easy.dental.model.record;

import java.util.List;

import rw.solution.easy.dental.model.enums.FormaPamento;

public record DadosTratamento(FormaPamento formaPagamento,
							  List<DadosProcedimento> procedimentos) {

}
