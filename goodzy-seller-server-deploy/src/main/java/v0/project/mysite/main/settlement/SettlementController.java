package v0.project.mysite.main.settlement;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import v0.project.mysite.main.settlement.DTO.SettlementDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/settlement")
public class SettlementController {

    private final SettlementService settlementService;

    @GetMapping
    public List<SettlementDTO> getSettlementBySeller(@RequestParam String sellerId) {
        return settlementService.getSettlementBySeller(sellerId);
    }

    @PutMapping
    public ResponseEntity<?> updateSettlement(@RequestBody List<SettlementDTO> settlements) {
        settlementService.updateSettlement(settlements);
        return ResponseEntity.ok().build();
    }

}
