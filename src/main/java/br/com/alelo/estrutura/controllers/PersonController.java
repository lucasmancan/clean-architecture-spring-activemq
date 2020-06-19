package br.com.alelo.estrutura.controllers;

import br.com.alelo.estrutura.useCases.*;
import br.com.alelo.estrutura.vos.PersonVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("people")
public class PersonController {
    private final FindAllPerson findAllPerson;

    private final FindPerson findPerson;

    private final FindPersonByDocument findPersonByDocument;

    private final SavePerson savePerson;

    // @Autowired OBS: field Injection impossibilita mockar o objeto
    private final InactivatePerson inactivatePerson;

    // Dessa forma é possível fazer testes o Spring automaticamente injeta as dependencias
    public PersonController(FindAllPerson findAllPerson, FindPerson findPerson, FindPersonByDocument findPersonByDocument, SavePerson savePerson, InactivatePerson inactivatePerson) {
        this.findAllPerson = findAllPerson;
        this.findPerson = findPerson;
        this.findPersonByDocument = findPersonByDocument;
        this.savePerson = savePerson;
        this.inactivatePerson = inactivatePerson;
    }

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
        return savePerson.save(vo);
    }

    @DeleteMapping("{id}")
    public @ResponseBody
    ResponseEntity<Void> inactivate(@PathVariable Long id) {
        inactivatePerson.inactivate(id);
        return ResponseEntity.ok().build();
    }

}
