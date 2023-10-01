package ga.ac.um.plataformaVotacao.PlataformaVotacao.controller;

import ga.ac.um.plataformaVotacao.PlataformaVotacao.entity.VotoEntity;
import ga.ac.um.plataformaVotacao.PlataformaVotacao.service.VotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class VotoController {

    private VotoService objVotoService;

    public VotoController(VotoService objVotoService) {
        this.objVotoService = objVotoService;
    }

    @PostMapping("/estudante/criar-votacao")
    public ResponseEntity<?> criarVotacao(@RequestBody VotoEntity dadosVotoEntity){
        return  this.objVotoService.criarVotacao(dadosVotoEntity);
    }
}
