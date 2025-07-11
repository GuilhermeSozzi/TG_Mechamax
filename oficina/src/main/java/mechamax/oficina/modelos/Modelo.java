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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "modelo")
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mod_id")
    private long idModelo;
    @Column(name = "mod_nome", length = 30, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Veiculo> veiculos = new HashSet<>();

    public Modelo(){
    }

    public Modelo(String nome) {
        this.nome = nome;
    }
}
