package ada.tech.tenthirty.tvpackages.connections;

import ada.tech.tenthirty.tvpackages.contants.RabbitMQConstantes;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    private RabbitMQConnection (AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }
    private Queue fila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta(){
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE,troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona() {
        Queue filaCriarPagamento = this.fila(RabbitMQConstantes.FILA_CRIAR_PAGAMENTO);
        Queue filaEfetuarPagamento = this.fila(RabbitMQConstantes.FILA_EFETUAR_PAGAMENTO);
        Queue filaConsultarPagamento = this.fila(RabbitMQConstantes.FILA_CONSULTAR_PAGAMENTO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoCriarPagamento = this.relacionamento(filaCriarPagamento, troca);
        Binding ligacaoEfetuarPagamento = this.relacionamento(filaEfetuarPagamento, troca);
        Binding ligacaoConsultarPagamento = this.relacionamento(filaConsultarPagamento, troca);


        this.amqpAdmin.declareQueue(filaCriarPagamento);
        this.amqpAdmin.declareQueue(filaEfetuarPagamento);
        this.amqpAdmin.declareQueue(filaConsultarPagamento);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoCriarPagamento);
        this.amqpAdmin.declareBinding(ligacaoEfetuarPagamento);
        this.amqpAdmin.declareBinding(ligacaoConsultarPagamento);

    }
}
