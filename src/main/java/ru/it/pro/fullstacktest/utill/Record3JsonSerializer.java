package ru.it.pro.fullstacktest.utill;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.jooq.Record3;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class Record3JsonSerializer extends JsonSerializer<Record3<Integer, String, Integer>> {

    @Override
    public void serialize(Record3<Integer, String, Integer> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField(value.field1().getName(), value.value1());
        gen.writeStringField(value.field2().getName(), value.value2());
        gen.writeNumberField(value.field3().getName(), value.value3());
        gen.writeEndObject();
    }
}
