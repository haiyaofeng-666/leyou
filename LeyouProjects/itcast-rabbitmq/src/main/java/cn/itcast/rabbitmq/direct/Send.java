package cn.itcast.rabbitmq.direct;

import cn.itcast.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 生产者，模拟为商品服务
 */
public class Send {
//    private final static String EXCHANGE_NAME = "direct_exchange_test";
//
//    public static void main(String[] argv) throws Exception {
//        // 获取到连接
//        Connection connection = ConnectionUtil.getConnection();
//        // 获取通道
//        Channel channel = connection.createChannel();
//        // 声明exchange，指定类型为direct
//        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//        // 消息内容
//        String message = "商品删除了， id = 1001";
//        // 发送消息，并且指定routing key 为：insert ,代表新增商品
//        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
//        System.out.println(" [商品服务：] Sent '" + message + "'");
//
//        channel.close();
//        connection.close();
//    }

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道，这是完成大部分API的地方。
        Channel channel = connection.createChannel();

        // 声明（创建）队列，必须声明队列才能够发送消息，我们可以把消息发送到队列中。
        // 声明一个队列是幂等的 - 只有当它不存在时才会被创建
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}