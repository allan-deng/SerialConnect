# SerialConnect

对java的串口通讯包RXTX进行了进一步的封装。

可以通过SerialPortFactory获得串口对象，方便打开关闭串口和发送数据。

## 使用前准备

1. 拷贝文件

   拷贝文件 `rxtxParallel.dll rxtxSerial.dll`到`$JAVA_HOME/bin`目录下

2. 安装RXTXcomm.jar到maven仓库

   命令行执行 `mvn install:install-file -Dfile=D:\Code\project\SerialPort\RXTXcomm.jar -DgroupId=rxtx -DartifactId=rxtx  -Dversion=2.2 -Dpackaging=jar`

## 使用

```java
//获取连接
SerialPortFactory serialPortFactory = new SerialPortFactory();
Port port = serialPortFactory.getSerialPort(portName);

//打开关闭连接
port.openPort(9600);
port.closePort();

//添加监听
port.addListener(new DefaultListener(new DataAvailableListener() {
	@Override
	public void dataAvailable() {
       //接收到数据时执行的操作
    }
}));
```



   