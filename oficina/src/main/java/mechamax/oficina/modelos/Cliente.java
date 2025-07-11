package mechamax.oficina.modelos;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id")
    private long idCliente;
    @Column(name = "cli_dataCadastro", nullable = false)
    private Date dataCadastro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Veiculo> veiculos = new HashSet<>();

    public Cliente(){
        super();
        this.dataCadastro = new Date(System.currentTimeMillis());
    }

    public Cliente(String nome, String cpf, String email, Date dataNascimento, String cep, String rua, String bairro,
            String cidade, int numeroCasa, String enderecObservacao) {
        super(nome, cpf, email, dataNascimento, cep, rua, bairro, cidade, numeroCasa, enderecObservacao);
        this.dataCadastro = new Date(System.currentTimeMillis());
    }

}
