package mechamax.oficina.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mechamax.oficina.modelos.Modelo;
import java.util.List;


@Repository
public interface IModeloRepositorio extends JpaRepository<Modelo,Long> {
    List<Modelo> findByNome(String nome);
}
