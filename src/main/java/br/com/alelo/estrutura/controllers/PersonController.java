package br.com.alelo.estrutura.controllers;

import br.com.alelo.estrutura.producers.Producer;
import br.com.alelo.estrutura.useCases.*;
import br.com.alelo.estrutura.vos.PersonVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("people")
@AllArgsConstructor
public class PersonController {

    private final FindAllPerson findAllPerson;
    private final FindPerson findPerson;
    private final FindPersonByDocument findPersonByDocument;
    private final InactivatePerson inactivatePerson;
    private final SavePersonInQueue savePersonInQueue;

    @GetMapping
    public @ResponseBody
    List<PersonVO> getAll() {
        return findAllPerson.findAll();
    }

    @GetMapping("{id}")
    public @ResponseBody
    PersonVO get(@PathVariable Long id) {
        return findPerson.findById(id);
    }

    @GetMapping("document/{document}")
    public @ResponseBody
    PersonVO findByDocument(@PathVariable String document) {
        return findPersonByDocument.findByDocument(document);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    PersonVO save(@RequestBody @Valid PersonVO vo) {
        return savePersonInQueue.saveInQueue(vo);
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    ResponseEntity<Void> inactivate(@PathVariable Long id) {
        inactivatePerson.inactivate(id);
        return ResponseEntity.ok().build();
    }

}
