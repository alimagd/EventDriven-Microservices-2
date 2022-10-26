package com.magd.ShipmentService.command.api.aggregate;

import com.magd.CommonService.commands.ShipOrderCommand;
import com.magd.CommonService.events.OrderShippedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ShipmentAggregation {

    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String shipmentStatus;

    public ShipmentAggregation() {

    }

    public ShipmentAggregation(ShipOrderCommand shipOrderCommand) {
        // Validate the Command
        // Publish the Order Shipped event
        OrderShippedEvent orderShipmentEvent
                = OrderShippedEvent
                .builder()
                .shipmentId(shipOrderCommand.getShipmentId())
                .orderId(shipOrderCommand.getOrderId())
                .shipmentStatus("COMPLETED")
                .build();
        AggregateLifecycle.apply(orderShipmentEvent);
    }

    @EventSourcingHandler
    public void on(OrderShippedEvent event) {
        this.orderId = event.getOrderId();
        this.shipmentId = event.getShipmentId();
        this.shipmentStatus = event.getShipmentStatus();
    }
}
