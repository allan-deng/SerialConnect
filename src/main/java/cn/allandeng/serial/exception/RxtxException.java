package cn.allandeng.serial.exception;/**
 * @Auther: Allan
 * @Date: 2020/3/8 15:33
 * @Description:
 */

/**
 * @ClassName RxtxException
 * @Date:2020/3/8 15:33
 * @Description: 串口错误异常
 * @Author: Allan Deng
 * @Version: 1.0
 **/
public class RxtxException extends RuntimeException{

    private String msgDes;  //异常对应的描述信息

    public RxtxException() {
        super();
    }

    public RxtxException(String message) {
        super(message);
        msgDes = message;
    }


    public String getMsgDes() {
        return msgDes;
    }

}
