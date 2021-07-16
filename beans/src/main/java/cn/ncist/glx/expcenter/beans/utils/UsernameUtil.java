package cn.ncist.glx.expcenter.beans.utils;

/**
 * 一个用于生成六位随机字符串用于做用户名的工具类
 */
public class UsernameUtil {

    public static String createUsername(){
        //定义一个空字符串
        String re = " ";
        //进行6次循环
        for(int i = 0; i<6 ;i++) {
            //生成一个97~122之间的int类型整数
            int intVal = (int)(Math.random()*26+97);
            //将intValue强制转换为char 类型后连接到re后面
            re = re + (char)intVal;

        }
        //输出随机字符串
        System.out.println(re);
        return re;

    }
}
