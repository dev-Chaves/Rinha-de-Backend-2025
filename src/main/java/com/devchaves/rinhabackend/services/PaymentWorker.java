package com.devchaves.rinhabackend.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.devchaves.rinhabackend.dto.PaymentDTO;

import reactor.core.publisher.Mono;

@Component
public class PaymentWorker {

    private final WebClient webClient;

    String defaulturl = "http://payment-processor-default:8080/payments";

    String fallback = "http://payment-processor-default:8080";

    public PaymentWorker(WebClient webClient) {
        this.webClient = webClient;
    }

    @RabbitListener(queues = "paymentQueue")
    public void receivePayment(PaymentDTO paymentDTO) {

        System.out.println("Received Payment: " + paymentDTO);

        webClient.post()
        .uri(defaulturl)
        .bodyValue(paymentDTO)
        .retrieve()

            .onStatus(status -> status.is4xxClientError(), response ->
                Mono.error(new RuntimeException("Client Error: " + response.statusCode())))

            .onStatus(status -> status.is5xxServerError(), response ->
                Mono.error(new RuntimeException("Server Error: " + response.statusCode())))

        .bodyToMono(String.class)
        
        .subscribe(
            body -> System.out.println("Resposta do Payment Processor: " + body),
            error -> System.err.println("Erro ao processar pagamento: " + error.getMessage()));
        
    }

}
