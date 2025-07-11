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
import mechamax.oficina.modelos.Cargo;
import mechamax.oficina.modelos.Funcionario;
import mechamax.oficina.servicos.CargoServicos;
import mechamax.oficina.servicos.FuncionarioServicos;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController implements IControladores<Funcionario, Long> {

    private final FuncionarioServicos funcServicos;
    private final CargoServicos carServicos; 

    public FuncionarioController(FuncionarioServicos funcServicos, CargoServicos cargoServicos) {
        this.funcServicos = funcServicos;
        this.carServicos = cargoServicos;
    }

    @GetMapping("/novo")
    @Override
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("funcionario/novo.html");
        List<Cargo> cargos = carServicos.todos();
        mv.addObject("cargos", cargos);
        mv.addObject("funcionario", new Funcionario());
        return mv;
}


    @PostMapping("/novo")
    @Override
    public ModelAndView novo(@ModelAttribute Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("funcionario/novo.html");
        try {
            funcServicos.novo(funcionario);
            return new ModelAndView("redirect:/funcionario");
        } catch (IllegalArgumentException e) {
            mv.addObject("erro", e.getMessage());
        }
        return mv;
    }

    @GetMapping
    @Override
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("funcionario/index.html");
        mv.addObject("funcionarios", funcServicos.todos());
        return mv;
    }

    @GetMapping("/editar/{chave}")
    @Override
    public ModelAndView editar(@PathVariable Long chave) {
        ModelAndView mv = new ModelAndView("funcionario/editar.html");
        Optional<Funcionario> funcionario = funcServicos.busca(chave);
        List<Cargo> cargos = carServicos.todos();
        mv.addObject("cargos", cargos);
        funcionario.ifPresent(func -> mv.addObject("funcionario", func));
        return mv;
    }

    @PostMapping("/editar")
    @Override
    public ModelAndView editar(@ModelAttribute Funcionario funcionario, Long chave) {
        funcServicos.atualizar(funcionario);
        return new ModelAndView("redirect:/funcionario");
    }

    @GetMapping("/excluir/{chave}")
    @Override
    public ModelAndView excluir(@PathVariable Long chave) {
        ModelAndView mv = new ModelAndView("funcionario/excluir.html");
        Optional<Funcionario> funcionario = funcServicos.busca(chave);
        if (funcionario.isPresent()) {
            mv = new ModelAndView("funcionario/excluir.html");
            mv.addObject("funcionario", funcionario);
        }
        else{
            mv = new ModelAndView("redirect:/funcionario");
        }
        return mv;
    }

    @PostMapping("/excluir")
    @Override
    public ModelAndView excluir(Funcionario funcionario, Long chave) {
        funcServicos.excluirPorCodigo(funcionario.getIdFuncionario());
        return new ModelAndView("redirect:/funcionario");
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Funcionario> funcionario = funcServicos.busca(id);
        ModelAndView mv;
        if (funcionario.isPresent()){
            mv = new ModelAndView("funcionario/detalhes.html");
            mv.addObject("funcionario", funcionario);
        }
        else{
            mv = new ModelAndView("redirect:/funcionario");
        }
        return mv;
    }
}
