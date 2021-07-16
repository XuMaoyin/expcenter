package cn.ncist.glx.expcenter.beans.vo;
/**
 * 一个用于返回给前端json格式数据的类
 */
public class ResultVO {

    private int code ;

    private String message ;

    private Object data ;

    private ResultVO(){}

    public static ResultVO getSuccess(String message,Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(200);
        resultVO.setMessage(message);
        resultVO.setData(data);

        return resultVO ;
    }

    public static ResultVO getSuccess(String message){
        return getSuccess(message,null);
    }


    public static ResultVO getFail(String message,Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(500);
        resultVO.setMessage(message);
        resultVO.setData(data);

        return resultVO ;
    }

    public static ResultVO getFail(String message){
        return getFail(message,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
