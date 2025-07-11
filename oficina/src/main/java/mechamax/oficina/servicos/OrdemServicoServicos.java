package mechamax.oficina.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mechamax.oficina.interfaces.IServicos;
import mechamax.oficina.modelos.OrdemServico;
import mechamax.oficina.repositorios.IOrdemServicoRepositorio;

@Service
public class OrdemServicoServicos implements IServicos<OrdemServico, Long> {
    private IOrdemServicoRepositorio ordemRepo;

    public OrdemServicoServicos(IOrdemServicoRepositorio ordemRepo){
        this.ordemRepo = ordemRepo;
    }

    @Override
    public OrdemServico novo(OrdemServico ordem){
        return ordemRepo.save(ordem);
    }

    @Override
    public Optional<OrdemServico> busca(Long chave){
        return ordemRepo.findById(chave);
    }

    @Override
    public List<OrdemServico> buscaPorNome(String nome){
         throw new UnsupportedOperationException("OrdemServico não possui um campo nome.");
    }

    @Override
    public List<OrdemServico> todos(){
        return ordemRepo.findAll();
    }

    @Override
    public OrdemServico atualizar(OrdemServico ordem){
        return ordemRepo.save(ordem);
    }

    @Override
    public void excluirPorCodigo(Long chave){
        ordemRepo.deleteById(chave);
    }
}
