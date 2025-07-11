package mechamax.oficina.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mechamax.oficina.modelos.TipoServico;

import java.util.List;


@Repository
public interface ITipoServicoRepositorio extends JpaRepository<TipoServico,Long> {
    List<TipoServico> findByNome(String nome);
}
