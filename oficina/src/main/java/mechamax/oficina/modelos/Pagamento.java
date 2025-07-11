package mechamax.oficina.modelos;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "pag_id")
    private long idPagamento;
    @Column(name = "pag_valorPagamento", nullable = false)
    private BigDecimal valorPagamento;
    @Column(name = "pag_numeroParcela", nullable = false)
    private int numeroParcela;
    @Column(name = "pag_tipoPagamento", length = 40, nullable = false)
    private String tipoPagamento;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "pag_data_pagamento_realizado", nullable = false)
    private Date dataPagamentoRealizado;
    @Column(name = "pag_statusPagamento", length = 25,nullable = false)
    private String statusPagamento;

    @ManyToOne
    @JoinColumn(name = "ord_id")
    private OrdemServico ordemServico;

    public Pagamento(){
    }

    public Pagamento(BigDecimal valorPagamento, int numeroParcela, String tipoPagamento, Date dataPagamentoRealizado, String statusPagamento) {
        this.valorPagamento = valorPagamento;
        this.numeroParcela = numeroParcela;
        this.tipoPagamento = tipoPagamento;
        this.dataPagamentoRealizado = dataPagamentoRealizado;
        this.statusPagamento = statusPagamento;
    }

}