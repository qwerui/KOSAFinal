package v0.project.mysite.main.settlement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import v0.project.mysite.main.settlement.DTO.SettlementDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository settlementRepository;

    @Override
    public List<SettlementDTO> getSettlementBySeller(String sellerId) {
        return settlementRepository.getSettlementBySeller(sellerId);
    }

    @Override
    @Transactional
    public void updateSettlement(List<SettlementDTO> settlements) {
        settlementRepository.updateSettlement(settlements);
    }
}
