package ru.it.pro.fullstacktest.utill;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.jooq.Record;
import org.jooq.Record3;
import org.jooq.Record4;
import org.jooq.Row;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class JsonSer extends JsonSerializer<Record> {

    @Override
    public void serialize(Record value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        

        if (value.fields().length > 3) {

            Record4<Integer, String, String, String> r4 = (Record4<Integer, String, String, String>) value;

            gen.writeStartObject();
            gen.writeNumberField(r4.field1().getName(), r4.value1());
            gen.writeStringField(r4.field2().getName(), r4.value2());
            gen.writeStringField(r4.field3().getName(), r4.value3());
            gen.writeStringField(r4.field4().getName(), r4.value4());
            gen.writeEndObject();
        } else {
            Record3<Integer, String, Integer> r3 = (Record3<Integer, String, Integer>) value;
            gen.writeStartObject();
            gen.writeNumberField(r3.field1().getName(), r3.value1());
            gen.writeStringField(r3.field2().getName(), r3.value2());
            gen.writeNumberField(r3.field3().getName(), r3.value3());
            gen.writeEndObject();
        }
    }
}
