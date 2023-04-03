package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rw.solution.easy.dental.model.enums.StatusPaciente;


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
	
	
	@Transient
	private String endereco;
	@Transient
	private String ultimaConsultaFmt;
	
	public Paciente() {
		// TODO Auto-generated constructor stub
	}
	
	public Paciente(Long id, String nome, LocalDate dataNascimento, String email, String cpf, String rg,
			StatusPaciente status, LocalDate ultimaConsulta, String rua, String bairro, String cidade, String estado, Customer customer) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.cpf = cpf;
		this.rg = rg;
		this.status = status;
		this.ultimaConsulta = ultimaConsulta;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.customer = customer;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public StatusPaciente getStatus() {
		return status;
	}
	public void setStatus(StatusPaciente status) {
		this.status = status;
	}
	public LocalDate getUltimaConsulta() {
		return ultimaConsulta;
	}
	public void setUltimaConsulta(LocalDate ultimaConsulta) {
		this.ultimaConsulta = ultimaConsulta;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Anamnese getAnamnese() {
		return anamnese;
	}

	public void setAnamnese(Anamnese anamnese) {
		this.anamnese = anamnese;
	}

	public PreAvaliacao getPreAvaliacao() {
		return preAvaliacao;
	}

	public void setPreAvaliacao(PreAvaliacao preAvaliacao) {
		this.preAvaliacao = preAvaliacao;
	}

	public Tratamento getTratamento() {
		return tratamento;
	}

	public void setTratamento(Tratamento tratamento) {
		this.tratamento = tratamento;
	}

	public String getUltimaConsultaFmt() {
		try {
			return this.ultimaConsulta.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ultimaConsultaFmt;
	}
	public void setUltimaConsultaFmt(String ultimaConsultaFmt) {
		this.ultimaConsultaFmt = ultimaConsultaFmt;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, email, endereco, id, nome, rg, status, ultimaConsulta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(email, other.email) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome) && Objects.equals(rg, other.rg)
				&& status == other.status && Objects.equals(ultimaConsulta, other.ultimaConsulta);
	}
	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", endereco=" + endereco
				+ ", email=" + email + ", cpf=" + cpf + ", rg=" + rg + ", status=" + status + ", ultimaConsulta="
				+ ultimaConsulta + "]";
	}

}
