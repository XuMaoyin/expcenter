package cn.ncist.glx.expcenter.beans.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 一个用于json数组和java数组互转的工具类，目前仅支持整形数字数组
 */
public class JSONUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

    /**
     * json转list类型，本来想写根据传入类型返回任意类型的list，但是没有成功，只写了我需要的int型
     * @param str
     * @return
     */
    public static  List<Integer> jsonToList(String str){
        try {

            List<String> list = objectMapper.readValue(str, new TypeReference<List<String>>() {});



            List<Integer> list1 = new ArrayList<>();

            for(String s :list){
                Integer i = Integer.parseInt(s);
                list1.add(i);
            }
            /*System.out.println(list1.get(0));
            System.out.println(list1.get(0).getClass());*/
            return list1;
            //System.out.println(tidList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static String  listToJson(List list){
        /*List<String> list = new ArrayList<>();
        list.add("111");
        list.add("111");
        list.add("111");*/
        try {
            String s = objectMapper.writeValueAsString(list);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
