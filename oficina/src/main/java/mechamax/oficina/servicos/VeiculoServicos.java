package mechamax.oficina.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mechamax.oficina.interfaces.IServicos;
import mechamax.oficina.modelos.Veiculo;
import mechamax.oficina.repositorios.IVeiculoRepositorio;

@Service
public class VeiculoServicos implements IServicos<Veiculo, Long> {

    private final IVeiculoRepositorio veiculoRepo;

    public VeiculoServicos(IVeiculoRepositorio veiculoRepo) {
        this.veiculoRepo = veiculoRepo;
    }

    @Override
    public Veiculo novo(Veiculo veiculo) {
        if (veiculoRepo.existsByPlaca(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Erro: Placa já cadastrada!");
        }
        return veiculoRepo.save(veiculo);
    }

    @Override
    public Optional<Veiculo> busca(Long chave) {
        return veiculoRepo.findById(chave);
    }

    @Override
    public List<Veiculo> buscaPorNome(String nome) {
        return veiculoRepo.findByModelo_Nome(nome);
    }

    @Override
    public List<Veiculo> todos() {
        return veiculoRepo.findAll();
    }

    @Override
    public Veiculo atualizar(Veiculo veiculo) {
        return veiculoRepo.save(veiculo);
    }

    @Override
    public void excluirPorCodigo(Long chave) {
        veiculoRepo.deleteById(chave);
    }
}
