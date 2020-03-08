package cn.allandeng.serial.listener;/**
 * @Auther: Allan
 * @Date: 2020/3/8 15:25
 * @Description:
 */

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * @ClassName SerialPortListener
 * @Date:2020/3/8 15:25
 * @Description:
 * @Author: Allan Deng
 * @Version: 1.0
 **/
public interface SerialPortListener extends SerialPortEventListener {

    void serialEvent(SerialPortEvent serialPortEvent);
}
