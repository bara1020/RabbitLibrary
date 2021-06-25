package co.com.cadena.rabbitlibrary;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Rabbit {

	private String user;
	private String password;
	private String virtualHost;
	private String host;
	private int port;

	public Rabbit(String host, int port, String user, String password, String virtualHost) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.virtualHost = virtualHost;
	}

	public void publicMessage(String exchangeName, String queue, String data, String contentType)
			 {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		factory.setUsername(user);
		factory.setPassword(password);
		factory.setVirtualHost(virtualHost);

		Connection connection = null;
		Channel channel = null;

			try {
			  connection = factory.newConnection();
		    channel = connection.createChannel();
        channel.queueDeclare(queue, true, false, false, null);
        channel.basicPublish(exchangeName, queue,
            new AMQP.BasicProperties.Builder().contentType(contentType).build(), data.getBytes());

      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if(channel != null){
          try {
            channel.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
        
        if(connection != null){
          try {
            connection.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
          
        
      }
		
		

	}
}
