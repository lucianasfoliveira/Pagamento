package ada.tech.tenthirty.tvpackages.payloads;

import lombok.Setter;

import java.util.Date;

@Setter
public class PagamentoResponse {
    public String faturaId;
    public Date dataVencimento;
}
