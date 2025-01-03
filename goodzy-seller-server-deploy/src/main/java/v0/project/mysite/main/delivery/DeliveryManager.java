package v0.project.mysite.main.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 배송 상태를 업데이트하기 위한 환경 구축용 클래스
@Component
public class DeliveryManager {

    @Autowired
    private DeliveryRepository deliveryRepository;

    // 스레드 풀 100개로 설정
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(100);
    private final Random random = new Random();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        // DB의 배송 중인 상품 조회해서 등록
        for(var product : deliveryRepository.getDeliveringProducts()) {
            scheduleDeliveryUpdate(product.getOrderProductId().getProductId(), product.getOrderProductId().getOrderId());
        }
    }

    public void scheduleDeliveryUpdate(long productId, Long orderId) {
            int delay = random.nextInt(5) + 5; //5~10분

            scheduler.schedule(() -> {
                //TODO: 배송상태 업데이트
                deliveryRepository.updateDeliveryStatus(productId, orderId);
            }, delay, TimeUnit.MINUTES);
    }


}
