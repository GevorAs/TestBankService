package com.agr.bankservice.service.impl;

import com.agr.bankservice.dto.CardDto;
import com.agr.bankservice.exception.CardAuthException;
import com.agr.bankservice.exception.CardException;
import com.agr.bankservice.mapper.CardEntityMapper;
import com.agr.bankservice.model.CardEntity;
import com.agr.bankservice.model.SessionEntity;
import com.agr.bankservice.model.enums.CardAuthMethodeEnum;
import com.agr.bankservice.model.enums.CardStateEnum;
import com.agr.bankservice.model.enums.SessionState;
import com.agr.bankservice.repository.CardRepo;
import com.agr.bankservice.repository.SessionRepo;
import com.agr.bankservice.service.CardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepo cardRepo;
    private final SessionRepo sessionRepo;
    private final CardEntityMapper cardEntityMapper;

    public CardServiceImpl(CardRepo cardRepo, SessionRepo sessionRepo, CardEntityMapper cardEntityMapper) {
        this.cardRepo = cardRepo;
        this.sessionRepo = sessionRepo;
        this.cardEntityMapper = cardEntityMapper;
    }

    @Override
    public CardDto getCard(String token) {
        SessionEntity session = sessionRepo.findByTokenAndState(token, SessionState.ACTIVE).orElseThrow(CardAuthException::new);
        return cardEntityMapper.toDto(session.getCard());
    }

    @Override
    public CardEntity getCardEntity(String token) {
        SessionEntity session = sessionRepo.findByTokenAndState(token, SessionState.ACTIVE).orElseThrow(CardAuthException::new);
        return session.getCard();

    }

    @Override
    public CardAuthMethodeEnum getAuthType(String cardNumber) {
        CardEntity card = cardRepo.findByCardNumberAndState(cardNumber, CardStateEnum.ACTIVE).orElseThrow(CardException::new);
        return card.getAuthMethode();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void deposit(BigDecimal amount, String token) {
        CardEntity card = getCardEntity(token);
        card.setBalance(card.getBalance().add(amount));
        cardRepo.save(card);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void withdrawal(BigDecimal amount, String token) {

        CardEntity card = getCardEntity(token);
        if (card.getBalance().compareTo(amount) < 0) {
            throw new CardException("Card amount is less then withdrawal amount");
        }
        card.setBalance(card.getBalance().subtract(amount));
    }
}
