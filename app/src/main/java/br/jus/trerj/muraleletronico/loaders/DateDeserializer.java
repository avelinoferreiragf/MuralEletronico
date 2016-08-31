package br.jus.trerj.muraleletronico.loaders;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by avelinoferreiragf on 30/08/16.
 */
public class DateDeserializer implements JsonDeserializer<Date> {

    private static final SimpleDateFormat FORMAT_SIMPLE_DATE = new SimpleDateFormat("yyyy-MM-dd");

    static {
        FORMAT_SIMPLE_DATE.setTimeZone(TimeZone.getTimeZone("GMT-3"));
    }
    @Override
    public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        String possibleDate = element.getAsString();

        if (possibleDate == null || possibleDate.trim().equals("")) {
            return null;
        }
        try {
            if (!possibleDate.contains("-")) {
                return new Date(Long.valueOf(possibleDate.trim()));
            }

            return FORMAT_SIMPLE_DATE.parse(possibleDate);
        } catch (ParseException exp) {
            throw new JsonParseException(exp);
        }
    }
}