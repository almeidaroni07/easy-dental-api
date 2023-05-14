package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import rw.solution.easy.dental.model.convert.LocalDateConverter;
import rw.solution.easy.dental.model.dto.ProcedimentoDto;
import rw.solution.easy.dental.model.enums.FormaPamento;

@Entity
@Table(name = "tratamento")
public class Tratamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7596010620413465494L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	private Paciente paciente;
	
	@Column(name = "data_tratamento")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate dataTratamento;

	@Lob
	@Column(name = "bl_assinatura", nullable = true)
	private byte[] assinatura;
	
	@Column(name = "forma_pagamento")
	@Enumerated(EnumType.STRING)
	private FormaPamento formaPagamento;
	
	@Transient
	private Long pacienteID;
	
	@Transient
	private List<ProcedimentoDto> procedimentos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public LocalDate getDataTratamento() {
		return dataTratamento;
	}

	public void setDataTratamento(LocalDate dataTratamento) {
		this.dataTratamento = dataTratamento;
	}

	public byte[] getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(byte[] assinatura) {
		this.assinatura = assinatura;
	}

	public FormaPamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Long getPacienteID() {
		return pacienteID;
	}

	public void setPacienteID(Long pacienteID) {
		this.pacienteID = pacienteID;
	}

	public List<ProcedimentoDto> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<ProcedimentoDto> procedimentos) {
		this.procedimentos = procedimentos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(assinatura);
		result = prime * result + Objects.hash(dataTratamento, id, paciente);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tratamento other = (Tratamento) obj;
		return Arrays.equals(assinatura, other.assinatura) && Objects.equals(dataTratamento, other.dataTratamento)
				&& Objects.equals(id, other.id) && Objects.equals(paciente, other.paciente);
	}

	@Override
	public String toString() {
		return "Tratamento [id=" + id + ", paciente=" + paciente + ", dataTratamento=" + dataTratamento
				+ ", assinatura=" + Arrays.toString(assinatura) + "]";
	}

}
