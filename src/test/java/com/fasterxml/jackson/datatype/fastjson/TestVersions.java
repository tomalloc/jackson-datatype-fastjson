package com.fasterxml.jackson.datatype.fastjson;

import java.io.*;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.json.PackageVersion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVersions
{
    @Test
    public void testMapperVersions() throws IOException
    {
        FastJsonModule module = new FastJsonModule();
        assertVersion(module);
    }

    /*
    /**********************************************************
    /* Helper methods
    /**********************************************************
     */
    
    private void assertVersion(Versioned vers)
    {
        final Version v = vers.version();
        assertEquals(PackageVersion.VERSION, v);
    }
}

