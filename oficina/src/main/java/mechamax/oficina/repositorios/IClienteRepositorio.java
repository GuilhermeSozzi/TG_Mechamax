package mechamax.oficina.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mechamax.oficina.modelos.Cliente;


@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente,Long> {
    List<Cliente> findByNome(String nome);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
