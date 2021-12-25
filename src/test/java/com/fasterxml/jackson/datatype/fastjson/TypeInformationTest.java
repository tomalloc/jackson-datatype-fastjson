package com.fasterxml.jackson.datatype.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests to verify that we can also use JSONObject and JSONArray
 * with polymorphic type information.
 */
public class TypeInformationTest
{
    static class ObjectWrapper {
        public Object value;

        public ObjectWrapper(Object v) { value = v; }
        public ObjectWrapper() { }
    }

    @Test
    public void testWrappedArray() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new FastJsonModule());
        mapper.enableDefaultTyping();
        JSONArray array = JSONArray.parseArray("[13]");

        String json = mapper.writeValueAsString(new ObjectWrapper(array));
        assertEquals("{\"value\":[\"com.alibaba.fastjson.JSONArray\",[13]]}", json);

        ObjectWrapper result = mapper.readValue(json, ObjectWrapper.class);
        assertEquals(JSONArray.class, result.value.getClass());
        JSONArray resultArray = (JSONArray) result.value;
        assertEquals(1, resultArray.size());
        assertEquals(13, resultArray.getInteger(0).intValue());
    }

    @Test
    public void testWrappedObject() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new FastJsonModule());
        mapper.enableDefaultTyping();
        JSONObject array = JSONObject.parseObject("{\"a\":true}");

        String json = mapper.writeValueAsString(new ObjectWrapper(array));
        assertEquals("{\"value\":[\"com.alibaba.fastjson.JSONObject\",{\"a\":true}]}", json);

        ObjectWrapper result = mapper.readValue(json, ObjectWrapper.class);
        assertEquals(JSONObject.class, result.value.getClass());
        JSONObject resultOb = (JSONObject) result.value;
        assertEquals(1, resultOb.size());
        assertTrue(resultOb.getBoolean("a"));
    }
}
