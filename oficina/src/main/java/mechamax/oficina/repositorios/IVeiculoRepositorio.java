package mechamax.oficina.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mechamax.oficina.modelos.Veiculo;

@Repository
public interface IVeiculoRepositorio extends JpaRepository<Veiculo,Long> {
    boolean existsByPlaca(String placa);
    Optional<Veiculo> findByPlaca(String placa);
    List<Veiculo> findByModelo_Nome(String modelo);
}