package br.com.unicuritiba.CadastroJogos.controller;

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
    CadastroJogosRepository repository;

    @GetMapping
    public ResponseEntity<List<Jogo>> getJogos() {
        List<Jogo> jogos = repository.findAll();
        return ResponseEntity.ok(jogos);
    }

    @PostMapping
    public ResponseEntity<Jogo> saveJogo(@RequestBody Jogo jogo) {
        Jogo jogoSalvo = repository.save(jogo);
        return ResponseEntity.ok(jogoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> updateJogo(@PathVariable long id, @RequestBody Jogo jogoAtualizado) {
        Optional<Jogo> jogoExistente = repository.findById(id);

        if (jogoExistente.isPresent()) {
            Jogo jogo = jogoExistente.get();
            jogo.setNome(jogoAtualizado.getNome());
            jogo.setGenero(jogoAtualizado.getGenero());
            jogo.setPreco(jogoAtualizado.getPreco());

            repository.save(jogo);
            return ResponseEntity.ok(jogo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeJogo(@PathVariable long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Jogo com id " + id + " foi removido com sucesso.");
        }
        return ResponseEntity.notFound().build();
    }
}
