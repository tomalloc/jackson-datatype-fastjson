package com.fasterxml.jackson.datatype.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class FastJsonModule extends SimpleModule
{
    private static final long serialVersionUID = 1;

    private final static String NAME = "FastJsonModule";
    
    /*
    /**********************************************************
    /* Life-cycle
    /**********************************************************
     */
    
    public FastJsonModule()
    {
        super(NAME, PackageVersion.VERSION);
        addDeserializer(JSONArray.class, JSONArrayDeserializer.instance);
        addDeserializer(JSONObject.class, JSONObjectDeserializer.instance);
        addSerializer(JSONArraySerializer.instance);
        addSerializer(JSONObjectSerializer.instance);
    }
}