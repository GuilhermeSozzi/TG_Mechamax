package mechamax.oficina.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mechamax.oficina.interfaces.IControladores;
import mechamax.oficina.modelos.OrdemServico;
import mechamax.oficina.modelos.Veiculo;
import mechamax.oficina.modelos.Funcionario;
import mechamax.oficina.modelos.TipoServico;
import mechamax.oficina.servicos.OrdemServicoServicos;
import mechamax.oficina.servicos.VeiculoServicos;
import mechamax.oficina.servicos.FuncionarioServicos;
import mechamax.oficina.servicos.TipoServicoServicos;

@Controller
@RequestMapping("/ordemservico")
public class OrdemServicoController implements IControladores<OrdemServico, Long> {

    private final OrdemServicoServicos ordemServicos;
    private final VeiculoServicos veiculoServicos;
    private final FuncionarioServicos funcionarioServicos;
    private final TipoServicoServicos tipoServicoServicos;

    public OrdemServicoController(OrdemServicoServicos ordemServicos, VeiculoServicos veiculoServicos, 
                                  FuncionarioServicos funcionarioServicos, TipoServicoServicos tipoServicoServicos) {
        this.ordemServicos = ordemServicos;
        this.veiculoServicos = veiculoServicos;
        this.funcionarioServicos = funcionarioServicos;
        this.tipoServicoServicos = tipoServicoServicos;
    }

    @GetMapping("/novo")
    @Override
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("ordemservico/novo.html");
        List<Veiculo> veiculos = veiculoServicos.todos();
        List<Funcionario> funcionarios = funcionarioServicos.todos();
        List<TipoServico> tiposServico = tipoServicoServicos.todos();
        mv.addObject("veiculos", veiculos);
        mv.addObject("funcionarios", funcionarios);
        mv.addObject("tiposServico", tiposServico);
        mv.addObject("ordemservico", new OrdemServico());
        return mv;
    }

    @PostMapping("/novo")
    @Override
    public ModelAndView novo(@ModelAttribute OrdemServico ordemServico) {
        ModelAndView mv = new ModelAndView("ordemservico/novo.html");
        try {
            ordemServicos.novo(ordemServico);
            return new ModelAndView("redirect:/ordemservico");
        } catch (IllegalArgumentException e) {
            mv.addObject("erro", e.getMessage());
        }
        return mv;
    }

    @GetMapping
    @Override
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("ordemservico/index.html");
        mv.addObject("ordensServico", ordemServicos.todos());
        return mv;
    }

    @GetMapping("/editar/{chave}")
    @Override
    public ModelAndView editar(@PathVariable Long chave) {
        ModelAndView mv = new ModelAndView("ordemservico/editar.html");
        Optional<OrdemServico> ordemServico = ordemServicos.busca(chave);
        List<Veiculo> veiculos = veiculoServicos.todos();
        List<Funcionario> funcionarios = funcionarioServicos.todos();
        List<TipoServico> tiposServico = tipoServicoServicos.todos();
        mv.addObject("veiculos", veiculos);
        mv.addObject("funcionarios", funcionarios);
        mv.addObject("tiposServico", tiposServico);
        ordemServico.ifPresent(ord -> mv.addObject("ordemservico", ord));
        return mv;
    }

    @PostMapping("/editar")
    @Override
    public ModelAndView editar(@ModelAttribute OrdemServico ordemServico, Long chave) {
        ordemServicos.atualizar(ordemServico);
        return new ModelAndView("redirect:/ordemservico");
    }

    @GetMapping("/excluir/{chave}")
    @Override
    public ModelAndView excluir(@PathVariable Long chave) {
        ModelAndView mv = new ModelAndView("ordemservico/excluir.html");
        Optional<OrdemServico> ordemServico = ordemServicos.busca(chave);
        if (ordemServico.isPresent()) {
            mv.addObject("ordemservico", ordemServico.get());
        } else {
            return new ModelAndView("redirect:/ordemservico");
        }
        return mv;
    }

    @PostMapping("/excluir")
    @Override
    public ModelAndView excluir(OrdemServico ordemServico, Long chave) {
        ordemServicos.excluirPorCodigo(ordemServico.getCodOrdem());
        return new ModelAndView("redirect:/ordemservico");
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<OrdemServico> ordemServico = ordemServicos.busca(id);
        ModelAndView mv;
        if (ordemServico.isPresent()) {
            mv = new ModelAndView("ordemservico/detalhes.html");
            mv.addObject("ordemservico", ordemServico.get());
        } else {
            mv = new ModelAndView("redirect:/ordemservico");
        }
        return mv;
    }
}
