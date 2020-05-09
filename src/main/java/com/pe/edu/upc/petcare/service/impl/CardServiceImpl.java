package com.pe.edu.upc.petcare.service.impl;


import com.pe.edu.upc.petcare.model.Card;
import com.pe.edu.upc.petcare.repository.CardRepository;
import com.pe.edu.upc.petcare.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;
/*
    @Override
    public List<Card> findCardAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card createCard(Card card) {
        /*Card cardDB= cardRepository.findByNumberId((card.getNumberId()));
        if (cardDB != null){
            return cardDB;
        }
        card.setState("CREATED");
       // Card cardDB = cardRepository.save(card);
        return cardRepository.save(card);
    }
*/
    @Override
    public Card save(Card card) throws Exception {
        Card cardDB= cardRepository.findById((card.getId())).orElse(null);
        if (cardDB != null){
            return cardDB;
        }
        return cardRepository.save(card);
    }

    @Override
    public Card update(Card card) throws Exception {
        Card cardDB= cardRepository.findById(card.getId()).orElse(null);
        if (cardDB == null){
            return null;
        }

        cardDB.setNumber(card.getNumber());
        cardDB.setName(card.getName());
        cardDB.setCvv_number(card.getCvv_number());
        cardDB.setExpiry_date(card.getExpiry_date());

        return cardRepository.save(cardDB);
    }



    @Override
    public Card findById(Long id) throws Exception {
        return cardRepository.findById(id).orElse(null);
    }



    @Override
    public List<Card> findAll() throws Exception {
        return cardRepository.findAll();
    }

    @Override
    public Card deleteById(Long id) throws Exception {
        Card cardDB= cardRepository.findById(id).orElse(null);

        if (cardDB != null){
            cardRepository.deleteById(cardDB.getId());
            return cardDB;
        }

        return null;
    }
}
