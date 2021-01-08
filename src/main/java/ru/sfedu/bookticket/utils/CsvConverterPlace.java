package ru.sfedu.bookticket.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import ru.sfedu.bookticket.models.Place;
import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.models.City;

import java.util.Arrays;
import java.util.List;

public class CsvConverterPlace extends AbstractBeanField {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        String params = value.substring(
                value.indexOf(Constants.OBJECT_LEFT_SEPARATOR) + 1,
                value.lastIndexOf(Constants.OBJECT_RIGHT_SEPARATOR)
        );
        int firstSeparator = params.indexOf(Constants.OBJECT_FIELD_SEPARATOR);
        int secondSeparator = params.indexOf(Constants.OBJECT_FIELD_SEPARATOR, firstSeparator + 2);
        List<String> args = Arrays.asList(
                params.substring(0, firstSeparator),
                params.substring(firstSeparator + 2, secondSeparator),
                params.substring(secondSeparator + 2)
        );

        Place place = new Place();
        place.setId(Long.parseLong(args.get(0).split(Constants.OBJECT_VALUE_SEPARATOR)[1]));
        place.setAddress(args.get(1)
                .split(Constants.OBJECT_VALUE_SEPARATOR)[1]
                .replaceAll(Constants.STRING_SEPARATOR, Constants.EMPTY));
        City city = (City) new CsvConverterCity().convert(args.get(2));
        place.setCity(city);
        return place;
    }
}
