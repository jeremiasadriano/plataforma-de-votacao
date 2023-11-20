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

    @GetMapping("/pages/contaOff")
    public String contaDesativada() {
        return "pages/contaDesativada";
    }

    @GetMapping("/pages/community")
    public String comunidade(HttpSession session) {
        if (session.getAttribute("Username") == null) {
            return "redirect:/";
        }
        return "pages/gerente/community";
    }

    @GetMapping("/pages/recuperarpass")
    public String recuperar() {
        return "pages/recuperarpass";
    }

    //Admin
    @GetMapping("/login/admin/")
    public String loginAdmin() {
        return "adminLogin";
    }

    @GetMapping("/admin/adicionar")
    public String adicionarAdmin(HttpSession session) {
        if (session.getAttribute("Admin") == null) return "redirect:/login/admin/";
        return "pages/admin/adicionarAdmin";
    }

    //Mandar o session de ralo pohra!
    @GetMapping("/session/destroy")
    public String sessionDestroy(HttpSession session) {
        if (session.getAttribute("Username") != null) {
            session.isNew();
            session.removeAttribute("Username");
        }
        return "redirect:/";
    }

    @GetMapping("/adminsession/destroy")
    public String sessionADminDestroy(HttpSession session) {
        if (session.getAttribute("Admin") != null) {
            session.isNew();
            session.removeAttribute("Admin");
        }
        return "redirect:/";
    }

}
