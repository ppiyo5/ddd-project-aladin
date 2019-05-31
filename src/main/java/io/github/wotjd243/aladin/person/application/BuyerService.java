package io.github.wotjd243.aladin.person.application;

import io.github.wotjd243.aladin.exception.AlreadyReservationException;
import io.github.wotjd243.aladin.exception.BusinessException;
import io.github.wotjd243.aladin.exception.NotFoundException;
import io.github.wotjd243.aladin.person.application.dto.BuyerCreateDto;
import io.github.wotjd243.aladin.person.application.dto.BuyerLoginDto;
import io.github.wotjd243.aladin.person.application.dto.BuyerUpdateDto;
import io.github.wotjd243.aladin.person.domain.Buyer;
import io.github.wotjd243.aladin.person.domain.BuyerRepository;
import io.github.wotjd243.aladin.person.infra.BuyerTranslate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static io.github.wotjd243.aladin.error.ErrorCode.HANDLE_ACCESS_DENIED;

@Service
@RequiredArgsConstructor
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public Buyer createBuyer(BuyerCreateDto create) {

        if(buyerRepository.findById(create.getId()).isPresent()) {

           throw new AlreadyReservationException("이미 존재하는 아이디입니다.");
        }

        Buyer buyer = BuyerTranslate.translate(create);
        return buyerRepository.save(buyer);
    }

    public Buyer findById(String id) {

        return buyerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 구매자 입니다.", id)));
    }

    @Transactional
    public Buyer updateBuyer(String id, BuyerUpdateDto update) {

        Buyer buyer = buyerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 구매자 입니다.", id)));

        buyer.update(update.getPassword(), update.getName(), update.getPhoneNumber(), update.getAddress());
        return buyer;
    }

    public Buyer loginBuyer(BuyerLoginDto loginDto) {
        Buyer buyer = buyerRepository.findById(loginDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 아이디 입니다.", loginDto.getId())));

        if (buyer.getUser().getPassword().equals(loginDto.getPassword())) {
            return buyer;
        }

        throw new BusinessException(HANDLE_ACCESS_DENIED, "비밀번호가 일치하지 않습니다.");
    }

}
