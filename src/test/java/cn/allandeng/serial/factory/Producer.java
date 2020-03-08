package cn.allandeng.serial.factory;/**
 * @Auther: Allan
 * @Date: 2020/3/8 15:45
 * @Description:
 */

import cn.allandeng.serial.port.Port;

import static java.lang.Thread.sleep;

/**
 * @ClassName Producer
 * @Date:2020/3/8 15:45
 * @Description:
 * @Author: Allan Deng
 * @Version: 1.0
 **/
public class Producer implements Runnable {

    Port port;
    byte[] data = {'t' , 'e' , 's' ,'t'};

    public Producer(Port port) {
        this.port = port;
    }


    @Override
    public void run() {
        System.out.println("启动发送线程");
        int i = 0;
        while (true){
            i++;
            System.out.println(i);
            port.send(data);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
