package mechamax.oficina.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PrincipalController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index.html");
        return mv;
    }
    
}
