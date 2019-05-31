package io.github.wotjd243.aladin.person.application;

import io.github.wotjd243.aladin.exception.AlreadyReservationException;
import io.github.wotjd243.aladin.exception.BusinessException;
import io.github.wotjd243.aladin.exception.NotFoundException;
import io.github.wotjd243.aladin.person.application.dto.SellerCreateDto;
import io.github.wotjd243.aladin.person.application.dto.SellerLoginDto;
import io.github.wotjd243.aladin.person.application.dto.SellerUpdateDto;
import io.github.wotjd243.aladin.person.domain.Seller;
import io.github.wotjd243.aladin.person.domain.SellerRepository;
import io.github.wotjd243.aladin.person.infra.SellerTranslate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.github.wotjd243.aladin.error.ErrorCode.HANDLE_ACCESS_DENIED;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    @Transactional
    public Seller createSeller(SellerCreateDto create) {

        if (sellerRepository.findById(create.getId()).isPresent()) {

            throw new AlreadyReservationException("이미 존재하는 아이디입니다.");
        }

        Seller seller = SellerTranslate.translate(create);
        return sellerRepository.save(seller);
    }

    public Seller findById(String id) {

        return sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 판매자 입니다.", id)));
    }

    @Transactional
    public Seller updateSeller(String id, SellerUpdateDto update) {

        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 판매자 입니다.", id)));

        seller.update(update.getPassword(), update.getName(), update.getPhoneNumber());
        return seller;
    }

    public Seller loginSeller(SellerLoginDto loginDto) {

        Seller seller = sellerRepository.findById(loginDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("[%s] 존재하지 않는 아이디 입니다.", loginDto.getId())));

        if (seller.getUser().getPassword().equals(loginDto.getPassword())) {
            return seller;
        }

        throw new BusinessException(HANDLE_ACCESS_DENIED, "비밀번호가 일치하지 않습니다.");
    }
}
