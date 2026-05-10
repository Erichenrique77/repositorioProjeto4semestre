package br.com.fatec.apiCastracao.controle;

import br.com.fatec.apiCastracao.dto.MovimentacaoDTO;
import br.com.fatec.apiCastracao.modelo.*;
import br.com.fatec.apiCastracao.repository.InsumoRepository;
import br.com.fatec.apiCastracao.repository.MovimentacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovimentacaoController {
    @Autowired
    MovimentacaoRepository repositorioMovimentacao;

    @Autowired
    InsumoRepository repositorioInsumo;

    @PostMapping("/movimentacoes")
    public ResponseEntity<Movimentacao> salvarMovimentacao(@RequestBody MovimentacaoDTO dto) {

        var movimentacao = new Movimentacao();

        // Copia apenas campos simples
        BeanUtils.copyProperties(dto, movimentacao, "insumoId");

        // Relacionamento: Insumos
        if (dto.insumoId() != null) {
            Insumo insumo = repositorioInsumo.findById(dto.insumoId())
                    .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
            movimentacao.setInsumo(insumo);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repositorioMovimentacao.save(movimentacao));
    }


    @GetMapping("/movimentacoes")
    public ResponseEntity<List<Movimentacao>> getAllMovimentacaos(){
        return ResponseEntity.status(HttpStatus.OK).body(repositorioMovimentacao.findAll());
    }

    @GetMapping("/movimentacao/{id}")
    public ResponseEntity<Object> getMovimentacaoPorId(@PathVariable(value="id") Integer id){
        Optional<Movimentacao> movimentacao = repositorioMovimentacao.findById(id);
        if (movimentacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentacao de id "+id+" não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(movimentacao.get());
    }


    @PutMapping("/movimentacao/{id}")
    public ResponseEntity<Object> atualizarMovimentacao(
            @PathVariable Integer id,
            @RequestBody MovimentacaoDTO movimentacaoDTO) {

        Movimentacao movimentacao = repositorioMovimentacao.findById(id).orElse(null);

        if (movimentacao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Movimentação de id " + id + " não existe.");
        }

        BeanUtils.copyProperties(movimentacaoDTO, movimentacao, "insumoId", "id");

        if (movimentacaoDTO.insumoId() != null) {

            Insumo insumo = repositorioInsumo.findById(movimentacaoDTO.insumoId())
                    .orElseThrow(() -> new RuntimeException("Insumo não encontrado"));
            movimentacao.setInsumo(insumo);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(repositorioMovimentacao.save(movimentacao));
    }

    @DeleteMapping ("/movimentacao/{id}")
    public ResponseEntity<Object> excluirMovimentacao(@PathVariable(value="id") Integer id){
        Optional<Movimentacao> movimentacao = repositorioMovimentacao.findById(id);
        if (movimentacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movimentacao de id "+id+" não existe.");
        }
        repositorioMovimentacao.delete(movimentacao.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movimentacao id "+id+" removido com sucesso!");
    }
}
