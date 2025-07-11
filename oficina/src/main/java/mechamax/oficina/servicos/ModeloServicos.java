package mechamax.oficina.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mechamax.oficina.interfaces.IServicos;
import mechamax.oficina.modelos.Modelo;
import mechamax.oficina.repositorios.IModeloRepositorio;

@Service
public class ModeloServicos implements IServicos<Modelo,Long> {
    private IModeloRepositorio modr;

    public ModeloServicos(IModeloRepositorio modr){
        this.modr = modr;
    }

    @Override
    public Modelo novo(Modelo mod){
        return modr.save(mod);
    }

    @Override
    public Optional<Modelo> busca(Long chave){
        Optional<Modelo> mod = modr.findById(chave);
        return mod;
    }

    @Override
    public List<Modelo> buscaPorNome(String nome){
        return modr.findByNome(nome);
    }

    @Override
    public List<Modelo> todos(){
        return modr.findAll();
    }

    @Override
    public Modelo atualizar(Modelo mod){
        return modr.save(mod);
    }

    @Override
    public void excluirPorCodigo(Long chave){
        modr.deleteById(chave);;
    }
}
