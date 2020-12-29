package com.kyurao.atio.service;

import com.kyurao.atio.domain.common.Location;
import com.kyurao.atio.domain.lot.Lot;
import com.kyurao.atio.domain.lot.enums.LotStatus;
import com.kyurao.atio.dto.LotReq;
import com.kyurao.atio.dto.LotRes;
import com.kyurao.atio.repository.LotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

import static com.kyurao.atio.util.SecurityUtils.currentUser;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class LotService {

    private final LotRepository lotRepository;

    public void create(LotReq dto) {
        var lot = toLot(dto);
        lot.setSeller(currentUser());
        lot.setStatus(LotStatus.ACTIVE);

        lotRepository.save(lot);
    }

    public Collection<Lot> getAllActive() {
        return lotRepository.findAllByStatusIn(Set.of(LotStatus.ACTIVE));
    }

    public Collection<Lot> getMyLots() {
        return null;
    }

    private Lot toLot(LotReq dto) {
        var lot = new Lot();
        lot.setTittle(dto.getTitle());
        lot.setDescription(dto.getDescription());
        lot.setStartPrice(dto.getStartPrice());
        lot.setBuyNow(dto.getBuyNow());
        if (isNull(lot.getLocation())) {
            lot.setLocation(new Location());
        }
        var location = lot.getLocation();
        location.setCountry(dto.getCountry());
        location.setRegion(dto.getRegion());
        location.setCity(dto.getCity());
        lot.setState(dto.getState());
        return lot;
    }

    public LotRes toLotResponse(Lot lot) {
        var lotResp = new LotRes();
        lotResp.setTitle(lot.getTittle());
        lotResp.setStartPrice(lot.getStartPrice());
        lotResp.setPrice(lot.getPrice());
        lotResp.setSeller(lot.getSeller());
        lotResp.setBetCount(lot.getBets().size());

        return lotResp;
    }

}
