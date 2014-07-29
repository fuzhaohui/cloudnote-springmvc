package com.ces.cloud.note.base.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class WebJsonUtils {
	
	private static ObjectMapper objectMapper;
	
	static {
		objectMapper = new ObjectMapper();
		// 设置反序列化时忽略JSON字符串中存在而Java对象实际没有的属性
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,  false);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		//Object obj=SerializationConfig.Feature.AUTO_DETECT_FIELDS;
	}
	private static JsonFactory jsonFactory = new JsonFactory();

	public static <T> T fromJson(String jsonAsString, Class<T> pojoClass)
			throws JsonMappingException, JsonParseException, IOException {
		return objectMapper.readValue(jsonAsString, pojoClass);
	}

	public static <T> T fromJson(FileReader fr, Class<T> pojoClass)
			throws JsonParseException, IOException {
		return objectMapper.readValue(fr, pojoClass);
	}

	public static <T> List<T> listFromJson(String jsonAsString,
			Class<T> pojoClass) throws JsonParseException,
			JsonMappingException, IOException {
		List<T> beanList = objectMapper.readValue(jsonAsString,
				new TypeReference<List<T>>() {
				});
		return beanList;
	}

	public static <T> Map<String, T> mapFromJson(String jsonAsString,
			Class<T> pojoClass) throws JsonParseException,
			JsonMappingException, IOException {
		Map<String, T> beanMap = objectMapper.readValue(jsonAsString,
				new TypeReference<Map<String, T>>() {
				});
		return beanMap;
	}

	public static String toJson(Object pojo, boolean prettyPrint)
			throws JsonMappingException, JsonGenerationException, IOException {
		StringWriter stringWriter = new StringWriter();
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(stringWriter);
		if (prettyPrint) {
			jsonGenerator.useDefaultPrettyPrinter();
		}
		
		objectMapper.writeValue(jsonGenerator, pojo);
		jsonGenerator.close();
		return stringWriter.toString();
	}

	public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint)
			throws JsonMappingException, JsonGenerationException, IOException {
		JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(fw);
		if (prettyPrint) {
			jsonGenerator.useDefaultPrettyPrinter();
		}
		objectMapper.writeValue(jsonGenerator, pojo);
		jsonGenerator.close();
	}
}