package br.com.unicuritiba.CadastroJogos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.unicuritiba.CadastroJogos.models.Jogo;
import br.com.unicuritiba.CadastroJogos.repositories.CadastroJogosRepository;

@RestController
@RequestMapping("/jogos")
public class CadastroJogosController {

    @Autowired
    private CadastroJogosRepository repository;

    @GetMapping
    public ResponseEntity<List<Jogo>> getJogos() {
        List<Jogo> jogos = repository.findAll();
        return ResponseEntity.ok(jogos);
    }

    @PostMapping
    public ResponseEntity<String> saveJogo(@RequestBody Jogo jogo) {
        Jogo jogoSalvo = repository.save(jogo);
        return ResponseEntity.created(URI.create("/jogos/" + jogoSalvo.getId()))
                .body("Jogo cadastrado com sucesso! ID: " + jogoSalvo.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJogo(@PathVariable long id, @RequestBody Jogo jogoAtualizado) {
        Optional<Jogo> jogoExistente = repository.findById(id);

        if (jogoExistente.isPresent()) {
            Jogo jogo = jogoExistente.get();
            jogo.setNome(jogoAtualizado.getNome());
            jogo.setGenero(jogoAtualizado.getGenero());
            jogo.setPreco(jogoAtualizado.getPreco());
            jogo.setDesenvolvedora(jogoAtualizado.getDesenvolvedora());
            jogo.setPlataforma(jogoAtualizado.getPlataforma());
            jogo.setDataLancamento(jogoAtualizado.getDataLancamento());

            repository.save(jogo);
            return ResponseEntity.ok("Jogo com ID " + id + " atualizado com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeJogo(@PathVariable long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Jogo com ID " + id + " foi removido com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }
}
