package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.Card;
import com.pe.edu.upc.petcare.resource.CardResource;
import com.pe.edu.upc.petcare.resource.SaveCardResource;
import com.pe.edu.upc.petcare.service.CardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CardService cardService;


    @GetMapping
    public ResponseEntity<List<CardResource>> getAllCards() throws Exception  {
        List<Card> cards = new ArrayList<>();

        cards = cardService.findAll();
        if (cards.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
       List<CardResource> resources = cards.stream().map(this::convertToResource).collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResource> getCardById(@PathVariable("id") Long id) throws Exception{
        Card cardDB = cardService.findById(id);
        if (cardDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertToResource(cardDB));
    }

    @PostMapping
    public ResponseEntity<CardResource> createCard(@Valid @RequestBody SaveCardResource saveCardResource)  throws Exception {
        Card card =convertToEntity(saveCardResource);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(cardService.save(card)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResource> updateCard(@PathVariable("id") Long id, @RequestBody SaveCardResource resource) throws Exception {
        Card card = convertToEntity(resource);
        card.setId(id);
        Card cardDB = cardService.update(card);
        if (cardDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResource(cardDB));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardResource> deleteCard(@PathVariable("id") Long id) throws Exception{
        Card cardDB= cardService.deleteById(id);
        if (cardDB == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToResource(cardDB));
    }

    private Card convertToEntity(SaveCardResource resource) {
        return mapper.map(resource, Card.class);
    }

    private CardResource convertToResource(Card entity) {
        return mapper.map(entity, CardResource.class);
    }

}
