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
@Table(name = "funcionario")
public class Funcionario extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fun_id")
    private long idFuncionario;
    @Column(name = "fun_salarioFuncionario", precision = 10, scale = 2, nullable = false)
    private BigDecimal salarioFuncionario;

    @ManyToOne
    @JoinColumn(name = "car_codigo")
    private Cargo cargo;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<OrdemServico> ordens = new HashSet<>();

    public Funcionario(){
        super();
    }

    public Funcionario(BigDecimal salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }

    public Funcionario(String nome, String cpf, String email, Date dataNascimento, String cep, String rua,
        String bairro, String cidade, int numeroCasa, String enderecObservacao, BigDecimal salarioFuncionario, Cargo cargo) {
            super(nome, cpf, email, dataNascimento, cep, rua, bairro, cidade, numeroCasa, enderecObservacao);
            this.salarioFuncionario = salarioFuncionario;
            this.cargo = cargo;
        }


}
