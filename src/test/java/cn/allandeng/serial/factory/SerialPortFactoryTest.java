package cn.allandeng.serial.factory;


import cn.allandeng.serial.port.Port;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: Allan
 * @Date: 2020/3/8 14:03
 * @Description:
 */
public class SerialPortFactoryTest {

   // @Test
    public void findPorts() {
        SerialPortFactory serialPortFactory = new SerialPortFactory();
        List<String> ports = serialPortFactory.findPorts();
        System.out.println(ports);
    }

   // @Test
    public void getSerialPort() {
        SerialPortFactory serialPortFactory = new SerialPortFactory();
        Port com5 = serialPortFactory.getSerialPort("COM5");
        System.out.println(com5.getSerialName());
    }
}