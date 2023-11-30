package ada.tech.tenthirty.tvpackages.loaddata;
import java.math.BigDecimal;
import java.util.*;

import ada.tech.tenthirty.tvpackages.entity.Pagamento;
import ada.tech.tenthirty.tvpackages.repository.IPagamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class PagamentoLoadData implements CommandLineRunner {

    private final IPagamentoRepository iPagamentoRepository;
    private static final List<String> IDENTIFICADOR_FATURA = Arrays.asList("1"
            , "2"
            , "3");


    @Override
    public void run(String... args) throws Exception {

        if (iPagamentoRepository.count() == 0) {
            List<Pagamento> items = createPagamentos();
            items.forEach(iPagamentoRepository::save);

        }
    }

    private List<Pagamento> createPagamentos() {
        List<String> usuarioId = Arrays.asList("1", "2",
                "2");

        Map<String, String> mapUsuarios = IntStream.range(0, IDENTIFICADOR_FATURA.size())
                .boxed()
                .collect(Collectors.toMap(IDENTIFICADOR_FATURA::get, usuarioId::get));
        List<Pagamento> pagamentos = new ArrayList<>();
        mapUsuarios.forEach((chave, descricao) -> {
            Pagamento pagamento = generatePagamento(chave, descricao);
            pagamentos.add(pagamento);
        });
        return pagamentos;

    }

    private Pagamento generatePagamento(String chave, String descricao) {
        Pagamento pagamento = new Pagamento();
        pagamento.setUsuarioId(descricao);
        pagamento.setFaturaId(chave);
        pagamento.setValor(BigDecimal.valueOf(136.40));
        Date data = new Date( 2023,  12,  23);
        pagamento.setDataVencimento(data);
        pagamento.setStatus("Pendente");
        log.info("Pagamento Salvo {}", pagamento);
        return pagamento;
    }

}
