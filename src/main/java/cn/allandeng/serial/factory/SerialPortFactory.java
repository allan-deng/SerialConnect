package cn.allandeng.serial.factory;/**
 * @Auther: Allan
 * @Date: 2020/3/8 13:33
 * @Description:
 */



import cn.allandeng.serial.exception.RxtxException;
import cn.allandeng.serial.port.Port;
import gnu.io.CommPortIdentifier;

import java.util.*;

/**
 * @ClassName SerialPortFactory
 * @Date:2020/3/8 13:33
 * @Description:
 * @Author: Allan Deng
 * @Version: 1.0
 **/
public class SerialPortFactory {

    Map<String,Port> serialPortMap = new HashMap<>();

    /**
     * 查找所有可用端口
     * @Param []
     * @return java.util.List<java.lang.String> 可用端口列表
     **/
    public List<String> findPorts(){
        // 获得当前所有可用串口
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        List<String> portNameList = new ArrayList<String>();
        // 将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }

    /**
     * 获取port对象
     * @Param 端口名称[portName]
     * @return cn.allandeng.serial.port.Port
     **/
    public Port getSerialPort(String portName){
        if (isPort(portName)){
            //判断map中是否已经有Port对象，如果没有这new一个并添加到map
            if (serialPortMap.containsKey(portName)){
                return serialPortMap.get(portName);
            }else{
                Port port = new Port(portName);
                serialPortMap.put(portName , port);
                return port;
            }
        }else{
            throw new RxtxException("串口号不存在："+portName);
        }
    }

    /**
     * 判断字符串是否为端口名
     * @Param [portName]
     * @return boolean
     **/
    private boolean isPort(String portName){
        if (portName == null || portName.length() == 0) return false;
        List<String> ports = findPorts();
        for (String port:ports){
            if (portName == port || portName.equals(port)) return true;
        }
        return false;
    }



}
