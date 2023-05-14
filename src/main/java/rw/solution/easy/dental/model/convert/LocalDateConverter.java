package rw.solution.easy.dental.model.convert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.AttributeConverter;

public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
