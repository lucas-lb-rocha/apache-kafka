package com.lucas.paymentservice.service;

import com.lucas.paymentservice.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}
