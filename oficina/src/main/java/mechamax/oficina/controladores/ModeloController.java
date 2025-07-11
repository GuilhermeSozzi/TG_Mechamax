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
import mechamax.oficina.modelos.Modelo;
import mechamax.oficina.servicos.ModeloServicos;

@Controller
@RequestMapping("/modelo")
public class ModeloController implements IControladores<Modelo,Long> {
    private ModeloServicos mods;

    public ModeloController(ModeloServicos mods){
        this.mods = mods;
    }

    @GetMapping("/editar/{chave}")
    @Override
    public ModelAndView editar(@PathVariable Long chave) {
        Optional<Modelo> modeloOpt = mods.busca(chave);
        ModelAndView mv = new ModelAndView("modelo/editar.html");

        if (modeloOpt.isPresent()) {
            mv.addObject("modelo", modeloOpt.get());
        } 
        else {
            mv.setViewName("redirect:/modelo");
        }
        return mv;
    }

    @PostMapping("/editar")
    @Override
    public ModelAndView editar(@ModelAttribute Modelo modelo, Long chave) {
        mods.atualizar(modelo);
        return new ModelAndView("redirect:/modelo");
    }


    @GetMapping("/excluir/{chave}")
    @Override
    public ModelAndView excluir(@PathVariable Long chave) {
        Optional<Modelo> modelo = mods.busca(chave);
        ModelAndView mv;
        if (modelo.isPresent()) {
            mv = new ModelAndView("modelo/excluir.html");
            mv.addObject("modelo", modelo);
        }
        else{
            mv = new ModelAndView("redirect:/modelo");
        }
        return mv;
    }

    @PostMapping("/excluir")
    @Override
    public ModelAndView excluir(@ModelAttribute Modelo obj, Long chave) {
        ModelAndView mv = new ModelAndView("redirect:/modelo");
        mods.excluirPorCodigo(obj.getIdModelo());
        return mv;
    }

    @GetMapping
    @Override
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("modelo/index.html");
        mv.addObject("modelos", mods.todos());
        return mv;
    }

    @GetMapping("/novo")
    @Override
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("modelo/novo.html");
        mv.addObject("modelo", new Modelo());
        return mv;
    }

    @PostMapping("/novo")
    @Override
    public ModelAndView novo(Modelo obj) {
        obj = mods.novo(obj);
        ModelAndView mv = new ModelAndView("redirect:/modelo");
        return mv;
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        Optional<Modelo> modelo = mods.busca(id);
        ModelAndView mv;
        if (modelo.isPresent()){
            mv = new ModelAndView("modelo/detalhes.html");
            mv.addObject("modelo", modelo);
        }
        else{
            mv = new ModelAndView("redirect:/modelo");
        }
        return mv;
    }
    
}
