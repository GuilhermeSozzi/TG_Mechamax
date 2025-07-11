package mechamax.oficina.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mechamax.oficina.interfaces.IServicos;
import mechamax.oficina.modelos.Pagamento;
import mechamax.oficina.repositorios.IPagamentoRepositorio;

@Service
public class PagamentoServicos implements IServicos<Pagamento, Long> {
    private IPagamentoRepositorio pagamentoRepo;

    public PagamentoServicos(IPagamentoRepositorio pagamentoRepo){
        this.pagamentoRepo = pagamentoRepo;
    }

    @Override
    public Pagamento novo(Pagamento pagamento){
        return pagamentoRepo.save(pagamento);
    }

    @Override
    public Optional<Pagamento> busca(Long chave){
        return pagamentoRepo.findById(chave);
    }

    @Override
    public List<Pagamento> buscaPorNome(String nome){
         throw new UnsupportedOperationException("Pagamento não possui um campo nome.");
    }

    @Override
    public List<Pagamento> todos(){
        return pagamentoRepo.findAll();
    }

    @Override
    public Pagamento atualizar(Pagamento pagamento){
        return pagamentoRepo.save(pagamento);
    }

    @Override
    public void excluirPorCodigo(Long chave){
        pagamentoRepo.deleteById(chave);
    }
}
