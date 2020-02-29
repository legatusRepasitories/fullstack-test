package ru.it.pro.fullstacktest.utill;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.jooq.Record4;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

//@JsonComponent(value = "Record4", type = Record4.class)
public class Record4JsonSerializer extends JsonSerializer<Record4<Integer, String, String, String>> {

    @Override
    public void serialize(Record4<Integer, String, String, String> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeStartObject();
        gen.writeNumberField(value.field1().getName(), value.value1());
        gen.writeStringField(value.field2().getName(), value.value2());
        gen.writeStringField(value.field3().getName(), value.value3());
        gen.writeStringField(value.field4().getName(), value.value4());
        gen.writeEndObject();
    }
}
