package rw.solution.easy.dental.model.record;

import org.springframework.core.io.Resource;

public record DadosArquivo(Resource resource, String contentType) {
	
	public DadosArquivo(Resource resource, String contentType) {
		this.resource = resource;
		this.contentType = contentType;
	}
	
}
