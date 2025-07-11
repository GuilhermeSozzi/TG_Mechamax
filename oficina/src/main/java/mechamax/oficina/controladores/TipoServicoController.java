package mechamax.oficina.controladores;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import mechamax.oficina.interfaces.IControladores;
import mechamax.oficina.modelos.TipoServico;
import mechamax.oficina.servicos.TipoServicoServicos;

@Controller
@RequestMapping("/tipoServico")
public class TipoServicoController implements IControladores<TipoServico, Long> {
    private final TipoServicoServicos tipoServicoServicos;

    public TipoServicoController(TipoServicoServicos tipoServicoServicos) {
        this.tipoServicoServicos = tipoServicoServicos;
    }

    @GetMapping("/editar/{chave}")
    @Override
    public ModelAndView editar(@PathVariable Long chave) {
        Optional<TipoServico> tipoServicoOpt = tipoServicoServicos.busca(chave);
        ModelAndView mv = new ModelAndView("tipoServico/editar.html");

        if (tipoServicoOpt.isPresent()) {
            mv.addObject("tipoServico", tipoServicoOpt.get());
        } else {
            mv.setViewName("redirect:/tipoServico");
        }
        return mv;
    }

    @PostMapping("/editar")
    @Override
    public ModelAndView editar(@ModelAttribute TipoServico tipoServico, Long chave) {
        tipoServicoServicos.atualizar(tipoServico);
        return new ModelAndView("redirect:/tipoServico");
    }

    @GetMapping("/excluir/{chave}")
    @Override
    public ModelAndView excluir(@PathVariable Long chave) {
        Optional<TipoServico> tipoServico = tipoServicoServicos.busca(chave);
        ModelAndView mv;
        if (tipoServico.isPresent()) {
            mv = new ModelAndView("tipoServico/excluir.html");
            mv.addObject("tipoServico", tipoServico.get());
        } else {
            mv = new ModelAndView("redirect:/tipoServico");
        }
        return mv;
    }

    @PostMapping("/excluir")
    @Override
    public ModelAndView excluir(@ModelAttribute TipoServico tipoServico, Long chave) {
        tipoServicoServicos.excluirPorCodigo(tipoServico.getIdTipo());
        return new ModelAndView("redirect:/tipoServico");
    }

    @GetMapping
    @Override
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("tipoServico/index.html");
        mv.addObject("tipoServicos", tipoServicoServicos.todos());
        return mv;
    }

    @GetMapping("/novo")
    @Override
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("tipoServico/novo.html");
        mv.addObject("tipoServico", new TipoServico());
        return mv;
    }

    @PostMapping("/novo")
    @Override
    public ModelAndView novo(TipoServico tipoServico) {
        tipoServicoServicos.novo(tipoServico);
        return new ModelAndView("redirect:/tipoServico");
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<TipoServico> tipoServico = tipoServicoServicos.busca(id);
        ModelAndView mv;
        if (tipoServico.isPresent()) {
            mv = new ModelAndView("tipoServico/detalhes.html");
            mv.addObject("tipoServico", tipoServico.get());
        } else {
            mv = new ModelAndView("redirect:/tipoServico");
        }
        return mv;
    }
}
