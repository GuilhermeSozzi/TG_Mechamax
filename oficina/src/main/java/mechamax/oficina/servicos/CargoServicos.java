package mechamax.oficina.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mechamax.oficina.interfaces.IServicos;
import mechamax.oficina.modelos.Cargo;
import mechamax.oficina.repositorios.ICargoRepositorio;

@Service
public class CargoServicos implements IServicos<Cargo, Long> {
    private final ICargoRepositorio cargoRepo;

    public CargoServicos(ICargoRepositorio cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public Cargo novo(Cargo cargo) {
        return cargoRepo.save(cargo);
    }

    @Override
    public Optional<Cargo> busca(Long chave) {
        return cargoRepo.findById(chave);
    }

    @Override
    public List<Cargo> buscaPorNome(String nome) {
        return cargoRepo.findByNome(nome);
    }

    @Override
    public List<Cargo> todos() {
        return cargoRepo.findAll();
    }

    @Override
    public Cargo atualizar(Cargo cargo) {
        return cargoRepo.save(cargo);
    }

    @Override
    public void excluirPorCodigo(Long chave) {
        cargoRepo.deleteById(chave);
    }
}
