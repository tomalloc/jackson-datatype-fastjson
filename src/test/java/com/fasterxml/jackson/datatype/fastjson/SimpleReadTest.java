package com.fasterxml.jackson.datatype.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.*;
import org.junit.Test;

import static org.junit.Assert.*;


public class SimpleReadTest
{
    @Test
    public void testReadObject() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new FastJsonModule());

        JSONObject ob = mapper.readValue("{\"a\":{\"b\":3}, \"c\":[9, -4], \"d\":null, \"e\":true}",
                JSONObject.class);
        assertEquals(4, ob.size());
        JSONObject ob2 = ob.getJSONObject("a");
        assertEquals(1, ob2.size());
        assertEquals(3, ob2.getInteger("b").intValue());
        JSONArray array = ob.getJSONArray("c");
        assertEquals(2, array.size());
        assertEquals(9, array.getInteger(0).intValue());
        assertEquals(-4, array.getInteger(1).intValue());
        assertEquals(ob.get("d"),null);
        assertTrue(ob.getBoolean("e"));
    }

    @Test
    public void testReadArray() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new FastJsonModule());

        JSONArray array = mapper.readValue("[null, 13, false, 1.25, \"abc\", {\"a\":13}, [ ] ]",
                JSONArray.class);
        assertEquals(7, array.size());
        assertEquals(array.get(0),null);
        assertEquals(13, array.getInteger(1).intValue());
        assertFalse(array.getBoolean(2));
        assertEquals(Double.valueOf(1.25), array.getDouble(3));
        assertEquals("abc", array.getString(4));
        JSONObject ob = array.getJSONObject(5);
        assertEquals(1, ob.size());
        assertEquals(13, ob.getInteger("a").intValue());
        JSONArray array2 = array.getJSONArray(6);
        assertEquals(0, array2.size());
    }
}
