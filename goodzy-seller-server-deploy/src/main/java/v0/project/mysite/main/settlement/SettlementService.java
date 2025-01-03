package v0.project.mysite.main.settlement;

import v0.project.mysite.main.settlement.DTO.SettlementDTO;

import java.util.List;

public interface SettlementService {
    List<SettlementDTO> getSettlementBySeller(String sellerId);
    void updateSettlement(List<SettlementDTO> settlements);
}
