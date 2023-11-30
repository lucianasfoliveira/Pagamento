package ada.tech.tenthirty.tvpackages.consumer;

import ada.tech.tenthirty.tvpackages.contants.RabbitMQConstantes;
import ada.tech.tenthirty.tvpackages.payloads.PagamentoRequest;
import ada.tech.tenthirty.tvpackages.service.PagamentoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriarPagamentoConsumer {

    @Autowired
    private PagamentoService pagamentoService;

    @RabbitListener(queues = RabbitMQConstantes.FILA_CRIAR_PAGAMENTO)
    private void criarPagamento(PagamentoRequest pagamentoRequest){
        pagamentoService.criarPagamento(pagamentoRequest);
    }
}
