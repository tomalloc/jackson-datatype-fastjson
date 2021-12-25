package com.fasterxml.jackson.datatype.fastjson;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


public class JSONArrayDeserializer extends StdDeserializer<JSONArray>
{
    private static final long serialVersionUID = 1L;

    public final static JSONArrayDeserializer instance = new JSONArrayDeserializer();

    public JSONArrayDeserializer()
    {
        super(JSONArray.class);
    }
    
    @Override
    public JSONArray deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException
    {
        JSONArray array = new JSONArray();
        JsonToken t;
        while ((t = p.nextToken()) != JsonToken.END_ARRAY) {
            switch (t) {
            case START_ARRAY:
                array.add(deserialize(p, ctxt));
                continue;
            case START_OBJECT:
                array.add(JSONObjectDeserializer.instance.deserialize(p, ctxt));
                continue;
            case VALUE_STRING:
                array.add(p.getText());
                continue;
            case VALUE_NULL:
                array.add(null);
                continue;
            case VALUE_TRUE:
                array.add(Boolean.TRUE);
                continue;
            case VALUE_FALSE:
                array.add(Boolean.FALSE);
                continue;
            case VALUE_NUMBER_INT:
                array.add(p.getNumberValue());
                continue;
            case VALUE_NUMBER_FLOAT:
                array.add(p.getNumberValue());
                continue;
            case VALUE_EMBEDDED_OBJECT:
                array.add(p.getEmbeddedObject());
                continue;
            default:
                return (JSONArray) ctxt.handleUnexpectedToken(handledType(), p);
            }
        }
        return array;
    }
}
