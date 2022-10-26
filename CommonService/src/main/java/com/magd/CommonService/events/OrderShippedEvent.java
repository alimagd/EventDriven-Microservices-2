package com.magd.CommonService.events;

import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderShippedEvent {

    private String shipmentId;
    private String orderId;
    private String shipmentStatus;
}
