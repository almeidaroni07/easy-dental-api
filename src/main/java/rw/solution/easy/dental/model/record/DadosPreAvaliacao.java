package rw.solution.easy.dental.model.record;

import rw.solution.easy.dental.model.PreAvaliacao;

public record DadosPreAvaliacao(boolean corDentesIncomoda,
								boolean formatoDentesIncomoda,
								boolean fumante,
								boolean umAnoTratamento,
								boolean escovaDuasVezesDia,
								boolean usaFioDental,
								String queixa) {


	public DadosPreAvaliacao(PreAvaliacao pre) {
		this(pre.isCorDentesIncomoda(), 
			 pre.isFormatoDentesIncomoda(), 
			 pre.isFumante(), 
			 pre.isUmAnoTratamento(), 
			 pre.isEscovaDuasVezesDia(), 
			 pre.isUsaFioDental(), 
			 pre.getQueixa());
	}
}
