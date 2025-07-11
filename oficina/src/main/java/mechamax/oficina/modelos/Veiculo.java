package mechamax.oficina.modelos;

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
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vei_id")
    private long veiCodigo;
    @Column(name = "vei_placa", length = 14, nullable = false)
    private String placa;
    @Column(name = "vei_tipoVeiculo", length = 20, nullable = false)
    private String tipoVeiculo;
    @Column(name = "vei_anoModelo", nullable = false)
    private int anoModelo;
    @Column(name = "vei_quilometragem", nullable = false)
    private double quilometragem;

    @ManyToOne
    @JoinColumn(name = "mod_id")
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<OrdemServico> ordens = new HashSet<>();

    public Veiculo(){
    }

    public Veiculo(String placa, String tipoVeiculo, int anoModelo, double quilometragem, Modelo modelo,
            Cliente cliente) {
        this.placa = placa;
        this.tipoVeiculo = tipoVeiculo;
        this.anoModelo = anoModelo;
        this.quilometragem = quilometragem;
        this.modelo = modelo;
        this.cliente = cliente;
    }

}
