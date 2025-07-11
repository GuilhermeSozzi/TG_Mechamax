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
import mechamax.oficina.modelos.Cliente;
import mechamax.oficina.modelos.Modelo;
import mechamax.oficina.modelos.Veiculo;
import mechamax.oficina.servicos.ClienteServicos;
import mechamax.oficina.servicos.ModeloServicos;
import mechamax.oficina.servicos.VeiculoServicos;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController implements IControladores<Veiculo, Long> {

    private final VeiculoServicos veiculoServicos;
    private final ModeloServicos modeloServicos;
    private final ClienteServicos clienteServicos;

    public VeiculoController(VeiculoServicos veiculoServicos, ModeloServicos modeloServicos, ClienteServicos clienteServicos) {
        this.veiculoServicos = veiculoServicos;
        this.modeloServicos = modeloServicos;
        this.clienteServicos = clienteServicos;
    }

    @GetMapping("/novo")
    @Override
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("veiculo/novo.html");
        List<Modelo> modelos = modeloServicos.todos();
        List<Cliente> clientes = clienteServicos.todos();
        mv.addObject("modelos", modelos);
        mv.addObject("clientes", clientes);
        mv.addObject("veiculo", new Veiculo());
        return mv;
    }

    @PostMapping("/novo")
    @Override
    public ModelAndView novo(@ModelAttribute Veiculo veiculo) {
        ModelAndView mv = new ModelAndView("veiculo/novo.html");
        try {
            veiculoServicos.novo(veiculo);
            return new ModelAndView("redirect:/veiculo");
        } catch (IllegalArgumentException e) {
            mv.addObject("erro", e.getMessage());
        }
        return mv;
    }

    @GetMapping
    @Override
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("veiculo/index.html");
        mv.addObject("veiculos", veiculoServicos.todos());
        return mv;
    }

    @GetMapping("/editar/{chave}")
    @Override
    public ModelAndView editar(@PathVariable Long chave) {
        ModelAndView mv = new ModelAndView("veiculo/editar.html");
        Optional<Veiculo> veiculo = veiculoServicos.busca(chave);
        List<Modelo> modelos = modeloServicos.todos();
        List<Cliente> clientes = clienteServicos.todos();
        mv.addObject("modelos", modelos);
        mv.addObject("clientes", clientes);
        veiculo.ifPresent(v -> mv.addObject("veiculo", v));
        return mv;
    }

    @PostMapping("/editar")
    @Override
    public ModelAndView editar(@ModelAttribute Veiculo veiculo, Long chave) {
        veiculoServicos.atualizar(veiculo);
        return new ModelAndView("redirect:/veiculo");
    }

    @GetMapping("/excluir/{chave}")
    @Override
    public ModelAndView excluir(@PathVariable Long chave) {
        ModelAndView mv = new ModelAndView("veiculo/excluir.html");
        Optional<Veiculo> veiculo = veiculoServicos.busca(chave);
        if (veiculo.isPresent()) {
            mv.addObject("veiculo", veiculo);
        } else {
            mv = new ModelAndView("redirect:/veiculo");
        }
        return mv;
    }

    @PostMapping("/excluir")
    @Override
    public ModelAndView excluir(Veiculo veiculo, Long chave) {
        veiculoServicos.excluirPorCodigo(veiculo.getVeiCodigo());
        return new ModelAndView("redirect:/veiculo");
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoServicos.busca(id);
        ModelAndView mv;
        if (veiculo.isPresent()) {
            mv = new ModelAndView("veiculo/detalhes.html");
            mv.addObject("veiculo", veiculo);
        } else {
            mv = new ModelAndView("redirect:/veiculo");
        }
        return mv;
    }
}
