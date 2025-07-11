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
@Table(name = "tipoServico")
public class TipoServico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip_id")
    private long idTipo;
    @Column(name = "tip_nome")
    private String nome;

    @OneToMany(mappedBy = "tipoServico", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<OrdemServico> ordens = new HashSet<>();
}
