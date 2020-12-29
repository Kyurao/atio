package com.kyurao.atio.repository;

import com.kyurao.atio.domain.lot.Lot;
import com.kyurao.atio.domain.lot.enums.LotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findAllByStatusIn(Collection<LotStatus> status);
}
