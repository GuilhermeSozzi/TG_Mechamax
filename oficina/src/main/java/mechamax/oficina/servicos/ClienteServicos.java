package mechamax.oficina.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mechamax.oficina.interfaces.IServicos;
import mechamax.oficina.modelos.Cliente;
import mechamax.oficina.repositorios.IClienteRepositorio;

@Service
public class ClienteServicos implements IServicos<Cliente, Long> {

    private final IClienteRepositorio clienteRepo;

    public ClienteServicos(IClienteRepositorio clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public Cliente novo(Cliente cliente) {
        if (clienteRepo.existsByCpf(cliente.getCpf())) {
            throw new IllegalArgumentException("Erro: CPF já cadastrado!");
        }
        if (clienteRepo.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Erro: E-mail já cadastrado!");
        }
        return clienteRepo.save(cliente);
    }

    @Override
    public Optional<Cliente> busca(Long chave) {
        return clienteRepo.findById(chave);
    }

    @Override
    public List<Cliente> buscaPorNome(String nome) {
        return clienteRepo.findByNome(nome);
    }

    @Override
    public List<Cliente> todos() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    @Override
    public void excluirPorCodigo(Long chave) {
        clienteRepo.deleteById(chave);
    }
}
