package com.magd.ShipmentService.command.api.events;


import com.magd.CommonService.events.OrderShippedEvent;
import com.magd.ShipmentService.command.api.data.Shipment;
import com.magd.ShipmentService.command.api.data.ShipmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ShipmentsEventHandler {

    private ShipmentRepository shipRepository;

    public ShipmentsEventHandler(ShipmentRepository shipRepository) {
        this.shipRepository = shipRepository;
    }



    @EventHandler
    public void on(OrderShippedEvent event) {

        Shipment shipment = new Shipment();
        BeanUtils.copyProperties(event, shipment);
        shipRepository.save(shipment);
    }
}
