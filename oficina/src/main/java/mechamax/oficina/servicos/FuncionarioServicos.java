package mechamax.oficina.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mechamax.oficina.interfaces.IServicos;
import mechamax.oficina.modelos.Funcionario;
import mechamax.oficina.repositorios.IFuncionarioRepositorio;

@Service
public class FuncionarioServicos implements IServicos<Funcionario, Long> {

    private final IFuncionarioRepositorio funcionarioRepo;

    public FuncionarioServicos(IFuncionarioRepositorio funcionarioRepo) {
        this.funcionarioRepo = funcionarioRepo;
    }

    @Override
    public Funcionario novo(Funcionario funcionario) {
        if (funcionarioRepo.existsByCpf(funcionario.getCpf())) {
        throw new IllegalArgumentException("Erro: CPF já cadastrado!");
        }
        if (funcionarioRepo.existsByEmail(funcionario.getEmail())) {
            throw new IllegalArgumentException("Erro: E-mail já cadastrado!");
        }
    return funcionarioRepo.save(funcionario);
}

    @Override
    public Optional<Funcionario> busca(Long chave) {
        return funcionarioRepo.findById(chave);
    }

    @Override
    public List<Funcionario> buscaPorNome(String nome) {
        return funcionarioRepo.findByNome(nome);
    }

    @Override
    public List<Funcionario> todos() {
        return funcionarioRepo.findAll();
    }

    @Override
    public Funcionario atualizar(Funcionario funcionario) {
        return funcionarioRepo.save(funcionario);
    }

    @Override
    public void excluirPorCodigo(Long chave) {
        funcionarioRepo.deleteById(chave);
    }
}
