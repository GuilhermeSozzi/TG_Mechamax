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
@Table(name = "cargo")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private long idCargo;
    @Column(name = "car_nome", length = 30, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<Funcionario> funcionarios = new HashSet<>();

    public Cargo(){
    }

    public Cargo(String nome) {
        this.nome = nome;
    }

}
