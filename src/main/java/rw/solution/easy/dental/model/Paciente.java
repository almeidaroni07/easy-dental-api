package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.solution.easy.dental.model.enums.StatusPaciente;
import rw.solution.easy.dental.model.record.DadosPaciente;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7450595748949811564L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	@JsonIgnore
	private Customer customer;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "data_nascimento")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate dataNascimento;
	
	@Column(name = "email")
	private String email;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "rg")
	private String rg;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusPaciente status;
	
	@Column(name = "data_ultima_consulta")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate ultimaConsulta;
	
	@Column(name = "rua")
	private String rua;
	@Column(name = "bairro")
	private String bairro;
	@Column(name = "cidade")
	private String cidade;
	@Column(name = "estado")
	private String estado;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "anamnese_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Anamnese anamnese;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "preAvaliacao_id", referencedColumnName = "id", insertable = false, updatable = false)
	private PreAvaliacao preAvaliacao;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "tratamento_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Tratamento tratamento;
	

	public Paciente(DadosPaciente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.cpf = dados.cpf();
		this.rg = dados.rg();
		this.rua = dados.rua();
		this.bairro = dados.bairro();
		this.cidade = dados.cidade();
		this.estado = dados.estado();
		this.dataNascimento = dados.dataNascimento();
	}
	
	public void atualizarInformacoes(@Valid DadosPaciente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.cpf = dados.cpf();
		this.rg = dados.rg();
		this.rua = dados.rua();
		this.bairro = dados.bairro();
		this.cidade = dados.cidade();
		this.estado = dados.estado();
		this.dataNascimento = dados.dataNascimento();
	}
	
	
	public String getUltimaConsultaFmt() {
		try {
			return this.ultimaConsulta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	
}
