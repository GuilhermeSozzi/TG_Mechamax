package mechamax.oficina.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mechamax.oficina.modelos.Cargo;


@Repository
public interface ICargoRepositorio extends JpaRepository<Cargo,Long> {
    List<Cargo> findByNome(String nome);
}
