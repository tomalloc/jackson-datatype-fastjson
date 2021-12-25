Project to build [Jackson](https://github.com/FasterXML/jackson) extension module (jar) to support datatypes of ["fastjson" JSON library](https://github.com/alibaba/fastjson)


## Usage

### Maven dependency

To use module (version 2.x) on Maven-based projects, use following dependency:

```xml
<dependency>
    <groupId>com.github.tomalloc</groupId>
    <artifactId>jackson-datatype-fastjson</artifactId>
  <version>2.11.4</version>
</dependency>
```

(or whatever version is most up-to-date at the moment)

### Registering module

To use the the Module in Jackson, simply register it with the ObjectMapper instance:

```java
// import com.fasterxml.jackson.datatype.fastjson.FastJsonModule;

ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new FastJsonModule());
```

This will ensure that basic datatype of `com.alibaba.fastjson` package can be read and written using Jackson data-binding functionality.

### Data conversions

After registering the module, you can read and write JSON to/from org.json.JSONObject similar to handling custom POJOs or standard JDK types:

```java
JSONObject ob = mapper.readValue(json, JSONObject.class); // read from a source
String json = mapper.writeValue(ob); // output as String
```

As well as do conversion to/from POJOs:

```java
MyValue value = mapper.convertValue(jsonObject, MyValue.class);
JSONObject jsonObject = mapper.convertValue(value, JSONObject.class);
```

or to/from Tree Model:

```java
JsonNode root = mapper.valueToTree(jsonObject);
jsonObject = mapper.treeToValue(root, JSONObject.class);
```

Similarly, you can read/write/convert-to/convert-from `JSONArray` instead of `JSONObject`.
