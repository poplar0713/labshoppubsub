package labshoppubsub.domain;

import java.time.LocalDate;
import java.util.*;
import labshoppubsub.domain.*;
import labshoppubsub.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryAdded extends AbstractEvent {

    private Long id;
    private Long productId;
    private String status;
    private String productName;
    private Integer qty;
    private Long customerId;

    public DeliveryAdded(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryAdded() {
        super();
    }
}
//>>> DDD / Domain Event
