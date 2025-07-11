package mechamax.oficina.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mechamax.oficina.modelos.Pagamento;


@Repository
public interface IPagamentoRepositorio extends JpaRepository<Pagamento,Long> {
}
