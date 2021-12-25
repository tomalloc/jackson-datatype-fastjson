package com.fasterxml.jackson.datatype.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class JSONObjectTest {

    static class Foo {
        private JSONObject jsonObject;

        public JSONObject getJsonObject() {
            return jsonObject;
        }

        public void setJsonObject(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
        }
    }

    @Test
    public void test() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","su");
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<10;i++){
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("a","a"+i);
            jsonArray.add(jsonObject1);
        }
        jsonObject.put("aa",jsonArray);
        Foo foo = new Foo();
        foo.setJsonObject(jsonObject);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.registerModule(new FastJsonModule());

        String content = objectMapper.writeValueAsString(foo);
        System.out.println(content);
        String expectJson = "{\"jsonObject\":{\"aa\":[{\"a\":\"a0\"},{\"a\":\"a1\"},{\"a\":\"a2\"},{\"a\":\"a3\"},{\"a\":\"a4\"},{\"a\":\"a5\"},{\"a\":\"a6\"},{\"a\":\"a7\"},{\"a\":\"a8\"},{\"a\":\"a9\"}],\"name\":\"su\"}}";
        assertThat(content).isEqualTo(expectJson);
        Foo newFoo = objectMapper.readValue(expectJson,Foo.class);
        assertThat(newFoo.jsonObject).isNotNull();
        assertThat(newFoo.jsonObject.get("aa")).isInstanceOf(JSONArray.class);
        JSONArray jsonArray1 = (JSONArray) newFoo.jsonObject.get("aa");
        assertThat(jsonArray1.size()).isEqualTo(jsonArray.size());
        for(int i=0;i<jsonArray.size();i++){
            assertThat(jsonArray.get(i)).isInstanceOf(JSONObject.class);
            JSONObject object = (JSONObject)jsonArray.get(i);
            assertThat(object.get("a")).isEqualTo("a"+i);
        }
    }
}
