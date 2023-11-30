package ada.tech.tenthirty.tvpackages.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fatura_id", nullable = false)
    private String faturaId;

    @Column(name = "usuario_id", nullable = false)
    private String usuarioId;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_vencimento", nullable = false)
    private Date dataVencimento;

    @Column(name = "data_pagamento")
    private Date dataPagamento;

    @Column(name = "status")
    private String status;

}