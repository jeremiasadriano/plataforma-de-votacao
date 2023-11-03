package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {
    @GetMapping("pages/registro")
    public String registar() {
        return "pages/registro";
    }

    @GetMapping("/pages/login")
    public String login() {
        return "index";
    }

    @GetMapping("/pages/profile")
    public String perfil(HttpSession session) {
        if (session.getAttribute("Username") == null) {
            return "redirect:/";
        }
        return "pages/profile";
    }

    @GetMapping("/pages/community")
    public String comunidade(HttpSession session) {
        if (session.getAttribute("Username") == null) {
            return "redirect:/";
        }
        return "pages/community";
    }


    @GetMapping("/session/destroy")
    public String sessionDestroy(HttpSession session) {
        if (session.getAttribute("Username") != null) {
            session.isNew();
            session.removeAttribute("Username");
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }


}
