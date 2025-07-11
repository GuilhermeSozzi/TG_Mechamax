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
import mechamax.oficina.modelos.Pagamento;
import mechamax.oficina.modelos.OrdemServico;
import mechamax.oficina.servicos.PagamentoServicos;
import mechamax.oficina.servicos.OrdemServicoServicos;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController implements IControladores<Pagamento, Long> {

    private final PagamentoServicos pagamentoServicos;
    private final OrdemServicoServicos ordemServicoServicos;

    public PagamentoController(PagamentoServicos pagamentoServicos, OrdemServicoServicos ordemServicoServicos) {
        this.pagamentoServicos = pagamentoServicos;
        this.ordemServicoServicos = ordemServicoServicos;
    }

    @GetMapping("/novo")
    @Override
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("pagamento/novo.html");
        List<OrdemServico> ordensServico = ordemServicoServicos.todos();
        mv.addObject("ordensServico", ordensServico);
        mv.addObject("pagamento", new Pagamento());
        return mv;
    }

    @PostMapping("/novo")
    @Override
    public ModelAndView novo(@ModelAttribute Pagamento pagamento) {
        ModelAndView mv = new ModelAndView("pagamento/novo.html");
        try {
            pagamentoServicos.novo(pagamento);
            return new ModelAndView("redirect:/pagamento");
        } catch (IllegalArgumentException e) {
            mv.addObject("erro", e.getMessage());
        }
        return mv;
    }

    @GetMapping("/editar/{chave}")
    @Override
    public ModelAndView editar(@PathVariable Long chave) {
        ModelAndView mv = new ModelAndView("pagamento/editar.html");
        Optional<Pagamento> pagamentoOpt = pagamentoServicos.busca(chave);
        List<OrdemServico> ordensServico = ordemServicoServicos.todos();
        mv.addObject("ordensServico", ordensServico);

        pagamentoOpt.ifPresent(p -> mv.addObject("pagamento", p));
        return mv;
    }

    @PostMapping("/editar")
    @Override
    public ModelAndView editar(@ModelAttribute Pagamento pagamento, Long chave) {
        pagamentoServicos.atualizar(pagamento);
        return new ModelAndView("redirect:/pagamento");
    }

    @GetMapping
    @Override
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("pagamento/index.html");
        mv.addObject("pagamentos", pagamentoServicos.todos());
        return mv;
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Pagamento> pagamento = pagamentoServicos.busca(id);
        ModelAndView mv;
        if (pagamento.isPresent()) {
            mv = new ModelAndView("pagamento/detalhes.html");
            mv.addObject("pagamento", pagamento.get());
        } else {
            mv = new ModelAndView("redirect:/pagamento");
        }
        return mv;
    }

    @GetMapping("/excluir/{chave}")
    @Override
    public ModelAndView excluir(@PathVariable Long chave) {
        Optional<Pagamento> pagamento = pagamentoServicos.busca(chave);
        ModelAndView mv;
        if (pagamento.isPresent()) {
            mv = new ModelAndView("pagamento/excluir.html");
            mv.addObject("pagamento", pagamento.get());
        } else {
            mv = new ModelAndView("redirect:/pagamento");
        }
        return mv;
    }

    @PostMapping("/excluir")
    @Override
    public ModelAndView excluir(@ModelAttribute Pagamento pagamento, Long chave) {
        pagamentoServicos.excluirPorCodigo(pagamento.getIdPagamento());
        return new ModelAndView("redirect:/pagamento");
    }

}
