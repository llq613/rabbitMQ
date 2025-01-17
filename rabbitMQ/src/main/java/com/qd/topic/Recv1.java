package com.qd.topic;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * 消费者
 */
public class Recv1 {
    //队列名称
    private static  final String EXCHANGE_NAME="topic_exchange";

    public static void main(String[] args) throws IOException {

            //4.创建通道
           final Channel channel = ConnectionUtil.getConn().createChannel();

            //todo 5.声明交换机  durable:true 开启 持久化 持久化意义在于，当无服务宕机恢复后，我们的消息也能随之恢复
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC );

            //todo 6.交换机和队列进行绑定
             String queue_name = channel.queueDeclare().getQueue();
             //TODO 7.交换机和队列进行绑定
             channel.queueBind(queue_name,EXCHANGE_NAME,"abc.#");
             channel.queueBind(queue_name,EXCHANGE_NAME,"*.qq");

            //监听作用
            DeliverCallback deliverCallback=(consumerTag, delivery) -> {
                String message=new String(delivery.getBody(),"UTF-8");
                System.out.println("Recv1： " + message );

                //效率模拟
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            //TODO 设置消费者 auotAck需要设置false
            channel.basicConsume(queue_name,false,deliverCallback,consumerTag -> {});

    }
}
