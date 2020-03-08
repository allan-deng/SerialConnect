package cn.allandeng.serial.port;/**
 * @Auther: Allan
 * @Date: 2020/3/8 13:40
 * @Description:
 */

import cn.allandeng.serial.exception.RxtxException;
import cn.allandeng.serial.listener.SerialPortListener;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

/**
 * @ClassName Port
 * @Date:2020/3/8 13:40
 * @Description:
 * @Author: Allan Deng
 * @Version: 1.0
 **/
public class Port {

    private gnu.io.SerialPort serialPort;

    private final String serialName;

    //缓冲区大小，单位Byte
    private int bufferLength = 1024;

    public Port(String serialName) {
        this.serialName = serialName;
    }

    public String getSerialName() {
        return serialName;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    /**
     * 打开端口
     * @Param [baudrate] 波特率
     * @return void
     * @throws PortInUseException 串口已被占用
     **/
    public void openPort(int baudrate) throws PortInUseException{
        //判断端口是否打开
        if(serialPort != null) return;
        try {
            // 通过端口名识别端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialName);
            // 打开端口，并给端口名字和一个timeout（打开操作的超时时间）
            CommPort commPort = portIdentifier.open(serialName, 2000);
            // 判断是不是串口
            if (commPort instanceof SerialPort) {
                SerialPort serialPortTemp = (SerialPort) commPort;
                try {
                    // 设置一下串口的波特率等参数
                    // 数据位：8
                    // 停止位：1
                    // 校验位：None
                    serialPortTemp.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE);
                } catch (UnsupportedCommOperationException e) {
                    e.printStackTrace();
                    throw new RxtxException("串口号参数错误");
                }
                serialPort = serialPortTemp;
                return;
            }
        } catch (NoSuchPortException e1) {
            e1.printStackTrace();
            throw new RxtxException("无此端口");
        }
        serialPort = null;
    }

    /**
     * 端口关闭
     * @Param []
     * @return void
     **/
    public void closePort(){
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

    /**
     * 端口复位
     * @Param []
     * @return void
     **/
    public void reset(){
        serialPort = null;
    }


    /**
     * 往串口发送数据
     * @Param [data]
     * @return boolean 发送结果
     **/
    public boolean send(byte[] data){
        OutputStream out = null;
        try {
            out = serialPort.getOutputStream();
            out.write(data);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RxtxException("串口发送错误");
        } finally {
            try {
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * 读取串口数据
     * @Param []
     * @return byte[]
     **/
    public byte[] read(){
        InputStream in = null;
        byte[] bytes = {};
        try {
            in = serialPort.getInputStream();
            // 缓冲区大小
            byte[] readBuffer = new byte[bufferLength];
            // 每次读取一个缓冲区大小 循环读取
            int bytesNum = in.read(readBuffer);
            while (bytesNum > 0) {
                bytes = coArray(bytes, readBuffer ,bytesNum);
                bytesNum = in.read(readBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RxtxException("串口读取错误");
        } finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 获得串口输入流
     * @Param []
     * @return java.io.InputStream
     **/
    public InputStream getInputStream(){
        try {
            return serialPort.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得串口输出流
     * @Param []
     * @return java.io.OutputStream
     **/
    public OutputStream getOutputStream(){
        try {
            return serialPort.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 给串口添加事件监听器
     * @Param [listener]
     * @return void
     **/
    public void addListener(SerialPortListener listener){
        if(serialPort == null) return;
        try {
            // 给串口添加监听器
            serialPort.addEventListener(listener);
            // 设置当有数据到达时唤醒监听接收线程
            serialPort.notifyOnDataAvailable(true);
            // 设置当通信中断时唤醒中断线程
            serialPort.notifyOnBreakInterrupt(true);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除串口的事件监听器
     * @Param []
     * @return void
     **/
    public void removeListener(){
        if(serialPort == null) return;
        serialPort.removeEventListener();
    }


    private byte[] coArray(byte[] firstArray, byte[] secondArray , int dataLength){
        if (firstArray == null || secondArray == null) {
            return null;
        }
        byte[] bytes = new byte[firstArray.length + dataLength];
        System.arraycopy(firstArray, 0, bytes, 0, firstArray.length);
        System.arraycopy(secondArray, 0, bytes, firstArray.length, dataLength);
        return bytes;
    }


}
