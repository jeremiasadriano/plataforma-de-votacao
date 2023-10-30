package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.model.EstudanteEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.EstudanteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class EstudanteController {
    private final EstudanteService estudanteService;

    @PostMapping("/registar/estudante")
    public ResponseEntity<?> criarConta(@Valid @RequestBody EstudanteEntity dadosEstudante) {
        return this.estudanteService.criarConta(dadosEstudante);
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String emailEstudante, @RequestParam("senha") String senhaEstudante) {
        ModelAndView view = new ModelAndView();
        var response = this.estudanteService.login(emailEstudante, senhaEstudante);
        if (response.getStatusCode().is2xxSuccessful()) {
            view.addObject("Usuario", response.getBody());
            view.setViewName("/page/home");
            return view;
        } else {
            view.addObject("DataError", "Dados inseridos são inválidos!");
            view.setViewName("index");
            return view;
        }
    }

    @GetMapping("/perfil/id={idEstudante}")
    public ResponseEntity<EstudanteEntity> verPerfil(@RequestBody @PathVariable("idEstudante") long idEstudante) throws Exception {
        return this.estudanteService.verPerfil(idEstudante);
    }

    @PutMapping("/perfil/id={idEstudante}")
    public ResponseEntity<String> editarPerfil(@Valid @PathVariable("idEstudante") long idEstudante, EstudanteEntity dadosEstudante) throws Exception {
        return this.estudanteService.editarPerfil(idEstudante, dadosEstudante);
    }

    @DeleteMapping("/perfil/id={idEstudante}")
    public ResponseEntity<?> apagarConta(@PathVariable("idEstudante") long idEstudante) {
        return this.estudanteService.apagarConta(idEstudante);
    }
}