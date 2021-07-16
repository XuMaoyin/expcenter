package cn.ncist.glx.expcenter.manage;

import cn.ncist.glx.expcenter.beans.utils.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.writer.ArraysMapper;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilTest {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);


    @Test
    public void jsonToList(){
        String arr = "[\"111\",\"111\",\"111\"]";


        try {
            Class<?> name = Class.forName("java.lang.String");

            System.out.println(name);

            List<String> tidList = objectMapper.readValue(arr, new TypeReference<List<String>>() {});
            System.out.println(tidList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void listToJson(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("111");
        list.add("111");
        try {
            String s = objectMapper.writeValueAsString(list);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void date(){
        String weak = DateUtil.getWeak("20210301", "20210501");
    }
}
