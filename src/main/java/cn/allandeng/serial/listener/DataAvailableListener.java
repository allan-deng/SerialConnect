package cn.allandeng.serial.listener;/**
 * @Auther: Allan
 * @Date: 2020/3/8 15:15
 * @Description:
 */

/**
 * @ClassName DataAvailableListener
 * @Date:2020/3/8 15:15
 * @Description: 串口存在有效数据监听
 * @Author: Allan Deng
 * @Version: 1.0
 **/
public interface DataAvailableListener {
    /**
     * 串口存在有效数据
     */
    void dataAvailable();
}
