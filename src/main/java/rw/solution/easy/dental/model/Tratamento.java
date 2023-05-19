package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.time.LocalDate;

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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.solution.easy.dental.model.convert.LocalDateConverter;
import rw.solution.easy.dental.model.enums.FormaPamento;
import rw.solution.easy.dental.model.record.DadosTratamento;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
	
	
	public Tratamento(DadosTratamento dados, Paciente paciente) {
		this.paciente = paciente;
		this.formaPagamento = dados.formaPagamento();
		this.dataTratamento = LocalDate.now();
	}



}
