package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.Anuncios;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.OpcoesVotos;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.Component.Roles;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.CursoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.repository.EstudanteRepository;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.EstudanteService;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.VotoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class EstudanteController {
    private final EstudanteService estudanteService;
    private final VotoService votoService;
    private final EstudanteRepository estudanteRepository;


    @PostMapping("/registar/estudante")
    public Object criarConta(@Valid EstudanteEntity dadosEstudante) {
        var response = this.estudanteService.criarConta(dadosEstudante);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/";
        } else {
            ModelAndView view = new ModelAndView("pages/registro");
            view.addObject("DadosRegistro", "Os dados inseridos são inválidos!");
            return view;
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String emailEstudante, @RequestParam("senha") String senhaEstudante, HttpSession session) {
        var response = this.estudanteService.login(emailEstudante, senhaEstudante);
        if (response.getStatusCode().is2xxSuccessful()) {
            session.setAttribute("Username", Objects.requireNonNull(response.getBody()));
            session.setMaxInactiveInterval(300);
            return "redirect:/dashboard";
        } else if (response.getStatusCode().is3xxRedirection()) {
            return "redirect:/pages/contaOff";
        } else {
            return "redirect:/login/DadosIncorrectos";
        }
    }

    @PostMapping("/perfil/editar")
    public String editarPerfil(EstudanteEntity dadosEstudante, HttpSession session) {
        if (session.getAttribute("Username") == null) return "redirect:/pages/login";
        var response = this.estudanteService.atualizarPerfil(dadosEstudante);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/pages/profile";
        }
        return null;
    }

    @ModelAttribute("listarEstudantesVotes")
    public List<EstudanteEntity> listarEstudantsVotes() {
        return this.estudanteService.listarEstudantsVotes();
    }

    @GetMapping("estudantes")
    public List<EstudanteEntity> entities() {
        return this.estudanteRepository.findAll();
    }

    @ModelAttribute("listaAnuncios")
    public List<Anuncios> anuncio() {
        ResponseEntity<List<EstudanteEntity>> response = this.estudanteService.anuncios();
        if (response.getStatusCode().is2xxSuccessful()) {
            for (EstudanteEntity estudante : response.getBody()) {
                return estudante.getAnuncios();
            }
        }
        return Collections.emptyList();

    }

    @ModelAttribute("listarVotantes")
    public List<OpcoesVotos> opcoesVotos() {
        ResponseEntity<List<OpcoesVotos>> response = this.votoService.listarOpcoesVoto();
        if (response.getBody() != null) {
            return response.getBody();
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping("/login/DadosIncorrectos")
    public ModelAndView loginError() {
        ModelAndView view = new ModelAndView("index");
        view.addObject("DataError", "Dados inseridos são inválidos!");
        return view;
    }

    @PostMapping("/recuperarPass")
    public String recuperar(@RequestParam("email") String emailEstudante, @RequestParam("animal") String animal) {
        ResponseEntity<EstudanteEntity> response = this.estudanteService.recuperarSenha(emailEstudante, animal);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:pages/login";
        }
        return "redirect:/recuperarPass/invalido";
    }

    @GetMapping("/recuperarPass/invalido")
    public ModelAndView recuperar() {
        ModelAndView view = new ModelAndView("pages/recuperarpass");
        view.addObject("dadosInvalidos", "Os dados inseridos são inválidos");
        return view;
    }

    @ModelAttribute("verPerfil")
    public EstudanteEntity verPerfil(HttpSession session) throws Exception {
        if (session.getAttribute("Username") != null) {
            EstudanteEntity estudante = (EstudanteEntity) session.getAttribute("Username");
            ResponseEntity<EstudanteEntity> response = this.estudanteService.verPerfil(estudante.getId());
            return response.getBody();
        }
        return null;
    }

    @ModelAttribute("cursoEstudante")
    public String cursoEstudante(HttpSession session) {
        if (session.getAttribute("Username") != null) {
            EstudanteEntity estudante = (EstudanteEntity) session.getAttribute("Username");
            CursoEntity curso = this.estudanteService.cursoEstudante(estudante.getCursoId());
            return curso.getNomeCurso();
        }
        return "redirect:/";
    }

    //    Referenciando os dois dashboards :) (Código podre né? Eu sei, but i don't give a shit kkkkkkkk i just wanna pass this subject and delete this code)
    //    Mas agora falando a sério, este código foi despachado em todos os níveis, queria fazer em React, mas procrastinei tanto que tive que improvisar na última hora com o thymeleaf T^T
    @GetMapping({"/", "/dashboard"})
    public String dashboard(HttpSession session) {
        if (session.getAttribute("Username") == null) {
            return "index";
        } else {
            EstudanteEntity estudante = (EstudanteEntity) session.getAttribute("Username");
            if (estudante.getRoles().equals(Roles.ESTUDANTE)) {
                return "pages/estudante/dashboardEstudante";
            } else {
                return "pages/gerente/dashboard";
            }
        }
    }

    @GetMapping("/pages/profile")
    public String perfil(HttpSession session) {
        if (session.getAttribute("Username") == null) {
            return "redirect:/";
        } else {
            EstudanteEntity estudante = (EstudanteEntity) session.getAttribute("Username");
            if (estudante.getRoles().equals(Roles.ESTUDANTE)) {
                return "pages/estudante/profileEstudante";
            } else {
                return "pages/gerente/profile";
            }
        }
    }
}