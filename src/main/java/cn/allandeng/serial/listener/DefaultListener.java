package cn.allandeng.serial.listener;/**
 * @Auther: Allan
 * @Date: 2020/3/8 15:10
 * @Description:
 */

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * @ClassName SerialPortListener
 * @Date:2020/3/8 15:10
 * @Description:
 * @Author: Allan Deng
 * @Version: 1.0
 **/
public class DefaultListener implements SerialPortListener {

    private DataAvailableListener dataAvailableListener;

    public DefaultListener(DataAvailableListener dataAvailableListener) {
        this.dataAvailableListener = dataAvailableListener;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.DATA_AVAILABLE: // 1.串口存在有效数据
                if (dataAvailableListener != null) {
                    dataAvailableListener.dataAvailable();
                }
                break;

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2.输出缓冲区已清空
                break;

            case SerialPortEvent.CTS: // 3.清除待发送数据
                break;

            case SerialPortEvent.DSR: // 4.待发送数据准备好了
                break;

            case SerialPortEvent.RI: // 5.振铃指示
                break;

            case SerialPortEvent.CD: // 6.载波检测
                break;

            case SerialPortEvent.OE: // 7.溢位（溢出）错误
                break;

            case SerialPortEvent.PE: // 8.奇偶校验错误
                break;

            case SerialPortEvent.FE: // 9.帧错误
                break;

            case SerialPortEvent.BI: // 10.通讯中断
                System.out.println("通讯中断");
                break;

            default:
                break;
        }
    }
}
