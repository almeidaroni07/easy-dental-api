package rw.solution.easy.dental.model;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.solution.easy.dental.model.record.DadosArquivoEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "arquivo")
public class Arquivo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1396123409477002346L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	private Customer customer;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "tipo_arquivo")
	private String tipo;
	
	@JsonIgnore
	@Lob
	@Column(name = "bl_arquivo", nullable = true)
	private byte[] arquivo;
	
	public Arquivo(DadosArquivoEntity dados, Customer customer) {
		this.customer = customer;
		this.nome = dados.nome();
	}
	
	public void atualizarInformacoes(@Valid DadosArquivoEntity dados) {
		this.nome = dados.nome();
	}

	public void atualizarInformacoesArquivo(MultipartFile blob) {
		try {
			byte[] arquivoBytes = blob.getBytes();
			this.arquivo = arquivoBytes;
			this.tipo = blob.getContentType();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
