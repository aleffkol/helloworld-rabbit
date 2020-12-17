import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

public class Produtor {
    public static void main(String[] args) throws Exception{

        ConnectionFactory connectionFactory = new ConnectionFactory();

        //Localização do gestor da fila (Queue Manager)
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        String NOME_FILA = "filaOla";
        try(

                Connection connection = connectionFactory.newConnection();

                Channel channel = connection.createChannel()) {


            channel.queueDeclare(NOME_FILA, false, false, false, null);
            String mensagem = "Aleff Kluivert Oliveira de Lima";

            channel.basicPublish("", NOME_FILA, null, mensagem.getBytes());
            System.out.println("Enviei mensagem: " + mensagem);
        }
    }
}
