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
import mechamax.oficina.modelos.Cliente;
import mechamax.oficina.servicos.ClienteServicos;

@Controller
@RequestMapping("/cliente")
public class ClienteController implements IControladores<Cliente, Long> {

    private final ClienteServicos clienteServicos;

    public ClienteController(ClienteServicos clienteServicos) {
        this.clienteServicos = clienteServicos;
    }

    @GetMapping("/editar/{chave}")
    @Override
    public ModelAndView editar(@PathVariable Long chave) {
        Optional<Cliente> clienteOpt = clienteServicos.busca(chave);
        ModelAndView mv = new ModelAndView("cliente/editar.html");

        if (clienteOpt.isPresent()) {
            mv.addObject("cliente", clienteOpt.get());
        } else {
            mv.setViewName("redirect:/cliente");
        }
        return mv;
    }

    @PostMapping("/editar")
    @Override
    public ModelAndView editar(@ModelAttribute Cliente cliente, Long chave) {
        clienteServicos.atualizar(cliente);
        return new ModelAndView("redirect:/cliente");
    }

    @GetMapping("/excluir/{chave}")
    @Override
    public ModelAndView excluir(@PathVariable Long chave) {
        Optional<Cliente> cliente = clienteServicos.busca(chave);
        ModelAndView mv;
        if (cliente.isPresent()) {
            mv = new ModelAndView("cliente/excluir.html");
            mv.addObject("cliente", cliente.get());
        } else {
            mv = new ModelAndView("redirect:/cliente");
        }
        return mv;
    }

    @PostMapping("/excluir")
    @Override
    public ModelAndView excluir(@ModelAttribute Cliente cliente, Long chave) {
        clienteServicos.excluirPorCodigo(cliente.getIdCliente());
        return new ModelAndView("redirect:/cliente");
    }

    @GetMapping
    @Override
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("cliente/index.html");
        mv.addObject("clientes", clienteServicos.todos());
        return mv;
    }

    @GetMapping("/novo")
    @Override
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("cliente/novo.html");
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @PostMapping("/novo")
    @Override
    public ModelAndView novo(@ModelAttribute Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/novo.html");

        try {
              clienteServicos.novo(cliente);
              return new ModelAndView("redirect:/cliente");
        } catch (IllegalArgumentException e) {
            mv.addObject("erro", e.getMessage());
        }
    return mv;
}


    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteServicos.busca(id);
        ModelAndView mv;
        if (cliente.isPresent()) {
            mv = new ModelAndView("cliente/detalhes.html");
            mv.addObject("cliente", cliente.get());
        } else {
            mv = new ModelAndView("redirect:/cliente");
        }
        return mv;
    }
}
