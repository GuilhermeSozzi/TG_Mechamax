package mechamax.oficina.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mechamax.oficina.interfaces.IServicos;
import mechamax.oficina.modelos.TipoServico;
import mechamax.oficina.repositorios.ITipoServicoRepositorio;

@Service
public class TipoServicoServicos implements IServicos<TipoServico, Long> {
    private final ITipoServicoRepositorio tipoServicoRepo;

    public TipoServicoServicos(ITipoServicoRepositorio tipoServicoRepo) {
        this.tipoServicoRepo = tipoServicoRepo;
    }

    @Override
    public TipoServico novo(TipoServico tipoServico) {
        return tipoServicoRepo.save(tipoServico);
    }

    @Override
    public Optional<TipoServico> busca(Long chave) {
        return tipoServicoRepo.findById(chave);
    }

    @Override
    public List<TipoServico> buscaPorNome(String nome) {
        return tipoServicoRepo.findByNome(nome);
    }

    @Override
    public List<TipoServico> todos() {
        return tipoServicoRepo.findAll();
    }

    @Override
    public TipoServico atualizar(TipoServico tipoServico) {
        return tipoServicoRepo.save(tipoServico);
    }

    @Override
    public void excluirPorCodigo(Long chave) {
        tipoServicoRepo.deleteById(chave);
    }
}
