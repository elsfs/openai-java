package org.elsfs.openai.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.elsfs.openai.request.CreateAnswerRequest;

import java.time.Instant;

/**
 * @author zeng
 * @since 0.0.1
 */
public class JsonUtil {
    private static ObjectMapper MAPPER;
    static {
        JavaTimeModule module = new JavaTimeModule();
        MAPPER = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(module)
                .registerModule(new JSR310Module());
        //这个特性，决定了解析器是否将自动关闭那些不属于parser自己的输入源。
// 如果禁止，则调用应用不得不分别去关闭那些被用来创建parser的基础输入流InputStream和reader；
//默认是true
        MAPPER.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
//是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）
        MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

//反序列化是是否允许属性名称不带双引号
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//是否允许单引号来包住属性名称和字符串值
        MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

//是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

//是否允许JSON整数以多个0开始
        MAPPER.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);

//null的属性不序列化
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//按字母顺序排序属性,默认false
        MAPPER.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, false);

//是否以类名作为根元素，可以通过@JsonRootName来自定义根元素名称,默认false
        MAPPER.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

//是否缩放排列输出,默认false
        MAPPER.configure(SerializationFeature.INDENT_OUTPUT, false);

//序列化Date日期时以timestamps输出，默认true
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);

//序列化枚举是否以toString()来输出，默认false，即默认以name()来输出
        MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

//序列化枚举是否以ordinal()来输出，默认false
        MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, false);

//序列化单元素数组时不以数组来输出，默认false
        MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

//序列化Map时对key进行排序操作，默认false
        MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, false);

//序列化char[]时以json数组输出，默认false
        MAPPER.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);

//序列化BigDecimal时是输出原始数字还是科学计数，默认false，即以toPlainString()科学计数方式来输出
        MAPPER.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 转换为格式化的json
        //MAPPER.enable(SerializationFeature.INDENT_OUTPUT);

        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public static <T> T toObject(String jsonString, Class<T> zClass) {
        try {
            return MAPPER.readValue(jsonString, zClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonStr(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
