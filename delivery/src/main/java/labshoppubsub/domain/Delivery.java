package labshoppubsub.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import labshoppubsub.DeliveryApplication;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
//<<< DDD / Aggregate Root
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productId;
    private Integer qty;
    private String customerId;
    private String status;

    @PostPersist
    public void onPostPersist() {
        DeliveryAdded deliveryAdded = new DeliveryAdded(this);
        deliveryAdded.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = DeliveryApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void addDelivery(OrderPlaced orderPlaced) {
        Delivery delivery = new Delivery();
        delivery.setCustomerId(orderPlaced.getCustomerId());
        delivery.setProductId(orderPlaced.getProductId());
        delivery.setQty(orderPlaced.getQty());

        delivery.setStatus("Delivery started");
        repository().save(delivery);

        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
