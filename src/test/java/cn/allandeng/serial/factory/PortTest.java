package cn.allandeng.serial.factory;

import cn.allandeng.serial.listener.DataAvailableListener;
import cn.allandeng.serial.listener.DefaultListener;
import cn.allandeng.serial.listener.SerialPortListener;
import cn.allandeng.serial.port.Port;
import gnu.io.PortInUseException;
import gnu.io.SerialPortEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @Auther: Allan
 * @Date: 2020/3/8 14:38
 * @Description:
 */
public class PortTest {

    private Port port;

    //@Before
    public void before(){
        SerialPortFactory serialPortFactory = new SerialPortFactory();
        List<String> ports = serialPortFactory.findPorts();
        System.out.println(ports);
        port = serialPortFactory.getSerialPort("COM6");
        System.out.println("创建端口" + port.getSerialName());
    }

    //@Test
    public void openPort() {
        try {
            port.openPort(9600);
            port.getSerialPort();
            System.out.println("端口" + port.getSerialName() +"已打开");
        } catch (PortInUseException e) {
            e.printStackTrace();
            System.out.println("端口" + port.getSerialName() +"被占用");
        }
    }

    //@Test
    public void closePort() {
        openPort();
        port.closePort();
        System.out.println("端口" + port.getSerialName() +"已关闭");
    }

    //@Test
    public void send() {
        openPort();
        byte[] bytes = { (byte)0xaa , (byte)0x55 };
        print(bytes);
        System.out.println(port.getSerialPort());
        port.send(bytes);
        port.closePort();
        System.out.println("端口" + port.getSerialName() +"已关闭");

    }

    //@Test
    public void read() throws InterruptedException {
        openPort();
        byte[] bytes = { (byte)0xaa , (byte)0x55 };
        System.out.println(port.getSerialPort());
        port.send(bytes);

        byte[] read = port.read();
        print(read);
        port.closePort();
        System.out.println("端口" + port.getSerialName() +"已关闭");
    }

    //@Test
    public void listener(){
        openPort();
        port.addListener(new DefaultListener(new DataAvailableListener() {
            @Override
            public void dataAvailable() {
                byte[] data = null;
                try {
                    data = port.read();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                print(data);
            }
        }));
        new Thread(new Producer(port)).start();
        while (true){}
    }

    void print(byte[] bytes){
        for(byte b:bytes){
            System.out.print(b + " ");
        }
        System.out.println();
    }
}