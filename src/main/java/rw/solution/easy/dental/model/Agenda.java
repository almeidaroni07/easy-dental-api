package rw.solution.easy.dental.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.solution.easy.dental.model.convert.LocalDateConverter;
import rw.solution.easy.dental.model.enums.StatusConsulta;
import rw.solution.easy.dental.model.record.DadosAgendamentoCor;
import rw.solution.easy.dental.model.record.DadosProcedimento;
import rw.solution.easy.dental.model.record.DadosStatusAgendamento;
import rw.solution.easy.dental.util.LogUtil;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6069448562069599524L;

	private static Logger log = Logger.getLogger(Agenda.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	private Paciente paciente;
	
	@Column(name = "tratamento_id")
	private Long tratamentoID;

	@Column(name = "data_consulta")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate dataConsulta;
	
	@Column(name = "hora_inicio_consulta")
	private String horaInicioConsulta;
	
	@Column(name = "hora_fim_consulta")
	private String horaFimConsulta;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusConsulta status;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "procedimento_id", referencedColumnName = "id")
	private Procedimento procedimento;
	
	
	
	public String getStringInicio() {
		try {
			String stringInicio = this.dataConsulta.toString();
			stringInicio = stringInicio + ' ' +this.horaInicioConsulta;
			log.info(String.format(LogUtil.FORMATLOG, "getAgendamentoDTO", "paciente", "stringInicio: "+stringInicio));
			return stringInicio;
	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	public String getStringFim() {
		try {
			String stringFim = this.dataConsulta.toString();
			stringFim = stringFim + ' ' +this.horaFimConsulta;
			log.info(String.format(LogUtil.FORMATLOG, "getAgendamentoDTO", "paciente", "stringFim: "+stringFim));
			return stringFim;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	public DadosAgendamentoCor getDadosAgendamentoCor() {
		return new DadosAgendamentoCor(this.getProcedimento().getCor(), this.getProcedimento().getCor());
	}
	
	
	public Agenda(Paciente paciente, DadosProcedimento dados, Procedimento procedimento, Tratamento tratamento) {
		this.paciente = paciente;
		this.tratamentoID = tratamento.getId();
		this.dataConsulta = getDataFormatada(dados.data());
		this.horaInicioConsulta = dados.inicio();
		this.horaFimConsulta = dados.fim();
		this.status = StatusConsulta.MARCADO;
		this.procedimento = procedimento;
	}
	
	public void atualizaStatusConsulta(DadosStatusAgendamento dados) {
		this.status = Enum.valueOf(StatusConsulta.class, dados.id());
	}
	
	
	private LocalDate getDataFormatada(LocalDate data) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataStr = data.format(formatter);
			return LocalDate.parse(dataStr, formatter);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return data;
	}
	
}
