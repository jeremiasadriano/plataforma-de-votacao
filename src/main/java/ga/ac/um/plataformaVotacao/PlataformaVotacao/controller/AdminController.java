package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.AdminEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.AdminService;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.EstudanteService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final EstudanteService estudanteService;

    @PostMapping("/admin/login")
    public String login(@RequestParam("email") String emailEstudante, @RequestParam("senha") String senhaEstudante, HttpSession adminSession) {
        var response = this.adminService.fazerLogin(emailEstudante, senhaEstudante);
        if (response.getStatusCode().is2xxSuccessful()) {
            adminSession.setAttribute("Admin", response.getBody());
            adminSession.setMaxInactiveInterval(300);
            return "redirect:/admin/";
        }
        return "redirect:/admin/";
    }

    @GetMapping("/admin/")
    public String adminScreen(HttpSession session) {
        if (session.getAttribute("Admin") == null) {
            return "redirect:/login/admin/";
        }
        return "pages/admin/admin";
    }

    @ModelAttribute("todosOsEstudantes")
    public List<EstudanteEntity> listarTodosEstudantes() {
        return this.estudanteService.listarTodosEstudantes();
    }

    @PostMapping("/admin/atualizarEstudante")
    public String atualizarEstudante(EstudanteEntity estudante, HttpSession session) {
        if (session.getAttribute("Admin") == null) {
            return "redirect:/login/admin/";
        }
        ResponseEntity<? extends EstudanteEntity> response = this.estudanteService.editarPerfil(estudante);
        return "redirect:/admin/";
    }


    @GetMapping("/admin/perfil")
    public String perfil(HttpSession session) {
        if (session.getAttribute("Admin") == null) {
            return "redirect:/login/admin/";
        }
        return "pages/admin/profileAdmin";
    }

    @ModelAttribute("adminDetalhes")
    public AdminEntity detalhesPerfil(HttpSession session) {
        if (session.getAttribute("Admin") != null) {
            AdminEntity adminId = (AdminEntity) session.getAttribute("Admin");
            ResponseEntity<AdminEntity> response = this.adminService.verPerfil(adminId.getId());
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        }
        return null;
    }

    @PostMapping("/admin/atualizar")
    public String atualizarPerfil(AdminEntity adminEntity) {
        ResponseEntity<? extends AdminEntity> response = this.adminService.editarPerfil(adminEntity);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/admin/perfil";
        }
        return null;
    }
}