package com.agr.bankservice.service.impl;

import com.agr.bankservice.api.request.ChangeAuthTypeRequest;
import com.agr.bankservice.api.request.SessionRequest;
import com.agr.bankservice.exception.CardAuthException;
import com.agr.bankservice.exception.CardException;
import com.agr.bankservice.model.CardEntity;
import com.agr.bankservice.model.SessionEntity;
import com.agr.bankservice.model.enums.CardAuthMethodeEnum;
import com.agr.bankservice.model.enums.CardStateEnum;
import com.agr.bankservice.model.enums.SessionState;
import com.agr.bankservice.repository.CardRepo;
import com.agr.bankservice.repository.SessionRepo;
import com.agr.bankservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    HttpServletRequest request;

    private final SessionRepo sessionRepo;
    private final CardRepo cardRepo;

    public AuthServiceImpl(SessionRepo sessionRepo, CardRepo cardRepo) {
        this.sessionRepo = sessionRepo;
        this.cardRepo = cardRepo;
    }

    @Override
    public boolean validateToken(String token) {
        return sessionRepo.existsByTokenAndState(token, SessionState.ACTIVE);
    }

    @Override
    public String saveSession(SessionRequest sessionRequest) {
        CardEntity card = cardRepo.findByCardNumber(sessionRequest.getCardNumber()).orElseThrow(CardException::new);
        if (CardStateEnum.BLOCKED.equals(card.getState())) {
            throw new CardException("card blocked");
        }
        String secret = sessionRequest.getSecret();
        String token;
        if (CardAuthMethodeEnum.PIN.equals(card.getAuthMethode()) && secret.equals(card.getPin())) {
            token = createSession(card);
        } else if (CardAuthMethodeEnum.FINGERPRINT.equals(card.getAuthMethode()) && secret.equals(card.getFingerprint())) {
            token = createSession(card);
        } else {
            int wrongAttempts = card.getWrongAttempts();
            if (++wrongAttempts > 2) {
                card.setState(CardStateEnum.BLOCKED);
            }
            card.setWrongAttempts(wrongAttempts);
            throw new CardAuthException("invalid secret");
        }
        return token;
    }

    private String createSession(CardEntity card) {
        SessionEntity oldSession = card.getSession();
        if (SessionState.ACTIVE.equals(oldSession.getState())) {
            oldSession.setState(SessionState.ENDED);
            sessionRepo.save(oldSession);
        }
        SessionEntity session = new SessionEntity();
        session.setCard(card);
        session.setToken(UUID.randomUUID().toString());
        session.setState(SessionState.ACTIVE);
        card.setWrongAttempts(0);
        SessionEntity savedSession = sessionRepo.save(session);
        return savedSession.getToken();
    }

    @Override
    public void closeSession(String token) {
        SessionEntity session = sessionRepo.findByTokenAndState(token, SessionState.ACTIVE).orElseThrow(CardAuthException::new);
        session.setState(SessionState.ENDED);
        session.getCard().setWrongAttempts(0);
        sessionRepo.save(session);
    }

    @Override
    public void changeAuthSecret(String token, ChangeAuthTypeRequest changeAuthTypeRequest) {
        String newSecret = changeAuthTypeRequest.getSecret();
        CardAuthMethodeEnum authMethodeEnum = changeAuthTypeRequest.getAuthMethodeEnum();
        SessionEntity session = sessionRepo.findByTokenAndState(token, SessionState.ACTIVE).orElseThrow(CardAuthException::new);
        CardEntity card = session.getCard();
        if (CardAuthMethodeEnum.PIN.equals(authMethodeEnum)) {
            card.setFingerprint(null);
            card.setPin(newSecret);
        } else {
            card.setPin(null);
            card.setFingerprint(newSecret);
        }
        card.setAuthMethode(authMethodeEnum);
        session.setState(SessionState.ENDED);
        cardRepo.save(card);
    }

}
