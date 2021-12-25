package com.fasterxml.jackson.datatype.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SimpleWriteTest {
    @Test
    public void testWriteObject() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new FastJsonModule());

        String JSON = "{\"a\":{\"b\":3}}";
        JSONObject ob= JSONObject.parseObject(JSON);
        assertEquals(JSON, mapper.writeValueAsString(ob));

        // And for [Issue#2], with null(s):
        JSON = "{\"a\":null}";
        ob=new JSONObject();
        ob.put("a",null);
        assertEquals(JSON, mapper.writeValueAsString(ob));
    }

    @Test
    public void testWriteArray() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new FastJsonModule());

        // Ok: let's create JSONObject from JSON text
        String JSON = "[1,true,\"text\",[null,3],{\"a\":[1.25]}]";
        JSONArray ob= JSONObject.parseArray(JSON);
        assertEquals(JSON, mapper.writeValueAsString(ob));
    }
}
