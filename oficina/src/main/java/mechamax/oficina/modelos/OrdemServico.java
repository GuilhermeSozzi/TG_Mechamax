package mechamax.oficina.modelos;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ordemServico")
public class OrdemServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ord_id")
    private long codOrdem;

    @Column(name = "ord_dataServico", nullable = false)
    private Date dataServico;

    @Column(name = "ord_valorOrdem", precision = 10, scale = 2, nullable = false)
    private BigDecimal valorOrdem;

    @Column(name = "ord_numeroParcelas", nullable = false)
    private int numeroParcelas;

    @Column(name = "ord_observacoes", length = 255, nullable = false)
    private String observacoes;

    //falta coisa toOne
    @ManyToOne
    @JoinColumn(name = "vei_id")
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "fun_id")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "tip_id")
    private TipoServico tipoServico;

    @OneToMany(mappedBy = "ordemServico", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Pagamento> pagamentos = new HashSet<>();

    public OrdemServico(){
        this.dataServico = new Date(System.currentTimeMillis());
    }

    public OrdemServico(BigDecimal valorOrdem, int numeroParcelas, String observacoes, Veiculo veiculo,
            Funcionario funcionario, TipoServico tipoServico) {
        this.valorOrdem = valorOrdem;
        this.numeroParcelas = numeroParcelas;
        this.observacoes = observacoes;
        this.veiculo = veiculo;
        this.funcionario = funcionario;
        this.tipoServico = tipoServico;
        this.dataServico = new Date(System.currentTimeMillis());
    }
    
}
