package com.magd.PaymentService.command.api.events;

import com.magd.CommonService.events.PaymentProcessedEvent;
import com.magd.PaymentService.command.api.data.Payment;
import com.magd.PaymentService.command.api.data.PaymentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentEventHandler {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentEventHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {

        Payment payment = Payment.builder()
                .paymentId(event.getPaymentId())
                .orderId(event.getOrderId())
                .paymentStatus("COMPLETED")
                .timeStamp(new Date())
                .build();
        paymentRepository.save(payment);
    }

}
