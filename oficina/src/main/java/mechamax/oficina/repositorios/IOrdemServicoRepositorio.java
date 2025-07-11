package mechamax.oficina.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mechamax.oficina.modelos.OrdemServico;


@Repository
public interface IOrdemServicoRepositorio extends JpaRepository<OrdemServico,Long> {
}
