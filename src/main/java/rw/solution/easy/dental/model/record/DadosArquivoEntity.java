package rw.solution.easy.dental.model.record;

import rw.solution.easy.dental.model.Arquivo;

public record DadosArquivoEntity( Long id, String nome,String tipo) {
	
	public DadosArquivoEntity(Arquivo arquivo) {
		this(arquivo.getId(), arquivo.getNome(), arquivo.getTipo());
	}
	
}
