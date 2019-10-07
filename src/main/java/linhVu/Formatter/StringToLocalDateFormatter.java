package linhVu.Formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class StringToLocalDateFormatter implements Formatter<LocalDate> {
    private String partten;

    public StringToLocalDateFormatter(String partten) {
        this.partten = partten;
    }

    @Override
    public LocalDate parse(String date, Locale locale) throws ParseException {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(partten));
    }

    @Override
    public String print(LocalDate object, Locale locale) {
        return object.toString();
    }
}
