package ru.sfedu.bookticket.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import ru.sfedu.bookticket.models.Place;
import ru.sfedu.bookticket.Constants;
import ru.sfedu.bookticket.models.Event;

import java.util.List;

public class CsvConverterEvent extends AbstractBeanField {
    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        String params = value
                .substring(
                        value.indexOf(Constants.OBJECT_LEFT_SEPARATOR) + 1,
                        value.lastIndexOf(Constants.OBJECT_RIGHT_SEPARATOR)
                );
        String simpleParamsString = params
                .substring(0, params.indexOf(Constants.PHOTOS) - 2);
        List<String> simpleParams = BaseUtil
                .parseCsvObject(
                        Constants.OBJECT_LEFT_SEPARATOR +
                            simpleParamsString +
                            Constants.OBJECT_RIGHT_SEPARATOR
                );

        Event event = new Event();
        event.setId(Long.parseLong(simpleParams.get(0)));
        event.setName(simpleParams.get(1));
        event.setDate(simpleParams.get(2));
        event.setTime(simpleParams.get(3));
        event.setPrice(simpleParams.get(4));

        String photosParamsString = value.substring(
                value.indexOf(Constants.PHOTOS),
                value.indexOf(Constants.ARRAY_RIGHT_SEPARATOR) + 1
        );
        List<String> photosParams = (List<String>) new CsvConverterIds().convert(photosParamsString);
        event.setPhotos(photosParams);

        String placeParamsString = params.substring(
                params.indexOf(Constants.PLACE),
                params.indexOf(Constants.OBJECT_RIGHT_SEPARATOR) + 1
        );
        Place place = (Place) new CsvConverterPlace()
                .convert(placeParamsString + Constants.OBJECT_RIGHT_SEPARATOR);
        event.setPlace(place);

        return event;
    }
}
