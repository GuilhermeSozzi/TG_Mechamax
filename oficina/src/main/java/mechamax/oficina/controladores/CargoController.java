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
import mechamax.oficina.modelos.Cargo;
import mechamax.oficina.servicos.CargoServicos;

@Controller
@RequestMapping("/cargo")
public class CargoController implements IControladores<Cargo, Long> {
    private final CargoServicos cargoServicos;

    public CargoController(CargoServicos cargoServicos) {
        this.cargoServicos = cargoServicos;
    }

    @GetMapping("/editar/{chave}")
    @Override
    public ModelAndView editar(@PathVariable Long chave) {
        Optional<Cargo> cargoOpt = cargoServicos.busca(chave);
        ModelAndView mv = new ModelAndView("cargo/editar.html");

        if (cargoOpt.isPresent()) {
            mv.addObject("cargo", cargoOpt.get());
        } else {
            mv.setViewName("redirect:/cargo");
        }
        return mv;
    }

    @PostMapping("/editar")
    @Override
    public ModelAndView editar(@ModelAttribute Cargo cargo, Long chave) {
        cargoServicos.atualizar(cargo);
        return new ModelAndView("redirect:/cargo");
    }

    @GetMapping("/excluir/{chave}")
    @Override
    public ModelAndView excluir(@PathVariable Long chave) {
        Optional<Cargo> cargo = cargoServicos.busca(chave);
        ModelAndView mv;
        if (cargo.isPresent()) {
            mv = new ModelAndView("cargo/excluir.html");
            mv.addObject("cargo", cargo.get());
        } else {
            mv = new ModelAndView("redirect:/cargo");
        }
        return mv;
    }

    @PostMapping("/excluir")
    @Override
    public ModelAndView excluir(@ModelAttribute Cargo cargo, Long chave) {
        cargoServicos.excluirPorCodigo(cargo.getIdCargo());
        return new ModelAndView("redirect:/cargo");
    }

    @GetMapping
    @Override
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("cargo/index.html");
        mv.addObject("cargos", cargoServicos.todos());
        return mv;
    }

    @GetMapping("/novo")
    @Override
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("cargo/novo.html");
        mv.addObject("cargo", new Cargo());
        return mv;
    }

    @PostMapping("/novo")
    @Override
    public ModelAndView novo(Cargo cargo) {
        cargoServicos.novo(cargo);
        return new ModelAndView("redirect:/cargo");
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Cargo> cargo = cargoServicos.busca(id);
        ModelAndView mv;
        if (cargo.isPresent()) {
            mv = new ModelAndView("cargo/detalhes.html");
            mv.addObject("cargo", cargo.get());
        } else {
            mv = new ModelAndView("redirect:/cargo");
        }
        return mv;
    }
}
