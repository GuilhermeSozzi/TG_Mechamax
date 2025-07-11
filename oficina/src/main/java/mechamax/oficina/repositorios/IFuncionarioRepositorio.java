package mechamax.oficina.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mechamax.oficina.modelos.Funcionario;


@Repository
public interface IFuncionarioRepositorio extends JpaRepository<Funcionario,Long> {
    List<Funcionario> findByNome(String nome);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
