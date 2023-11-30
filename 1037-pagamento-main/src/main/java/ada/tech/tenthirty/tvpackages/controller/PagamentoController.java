package ada.tech.tenthirty.tvpackages.controller;

import ada.tech.tenthirty.tvpackages.contants.RabbitMQConstantes;
import ada.tech.tenthirty.tvpackages.payloads.PagamentoRequest;
import ada.tech.tenthirty.tvpackages.payloads.PagamentoResponse;
import ada.tech.tenthirty.tvpackages.service.PagamentoService;
import ada.tech.tenthirty.tvpackages.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "pagamento")
public class PagamentoController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    private ResponseEntity criarPagamento(@RequestBody PagamentoRequest pagamentoRequest) {
        this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_CRIAR_PAGAMENTO, pagamentoRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<PagamentoResponse> efetuarPagamento(@PathVariable Long id, @RequestBody PagamentoRequest pagamentoRequest) {
        try {
            return ResponseEntity.ok(pagamentoService.efetuarPagamento(id, pagamentoRequest));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consulta/{usuarioId}")
    public ResponseEntity<List<PagamentoResponse>> consultarPagamento(@PathVariable String usuarioId) {
        List<PagamentoResponse> pagamentos = pagamentoService.consultarUsuario(usuarioId);

        if (pagamentos != null) {
            return ResponseEntity.ok(pagamentos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("atualizar/{id}")
    public ResponseEntity<PagamentoResponse> atualizarPagamento(@PathVariable Long id, @RequestBody PagamentoRequest pagamentoRequest) {
        try {
            PagamentoResponse pagamentoResponse = pagamentoService.atualizarPagamento(id, pagamentoRequest);
            if (pagamentoResponse != null) {
                return ResponseEntity.ok(pagamentoResponse);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}


