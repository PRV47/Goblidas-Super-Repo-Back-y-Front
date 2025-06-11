package com.example.goblidas_backend.services;

import com.example.goblidas_backend.DTOs.CartItemDTO;
import com.example.goblidas_backend.entities.Detail;
import com.example.goblidas_backend.entities.Order;
import com.example.goblidas_backend.entities.OrderDetail;
import com.example.goblidas_backend.entities.enums.OrderStatus;
import com.example.goblidas_backend.repositories.DetailRepository;
import com.example.goblidas_backend.repositories.OrderRepository;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.resources.preference.PreferenceItem;
import jakarta.annotation.PostConstruct;
//import org.eclipse.sisu.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {


    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Value("${mercadopago.access.token}")
    private String accessToken;

    @Autowired
    private DetailRepository detailRepository;

    @PostConstruct
    public void init() {
        System.out.println(accessToken);
        MercadoPagoConfig.setAccessToken(accessToken);
    }

    //@Value("${mercadopago.access.token}")
    //private String accessToken;

    public PaymentService(OrderRepository orderRepository, OrderService orderService, DetailRepository detailRepository) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.detailRepository = detailRepository;
    }

    public String createPreferenceFromOrder(Long orderId) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken(accessToken);
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Orden no encontrada"));



        List<PreferenceItemRequest> items = order.getOrderDetails().stream()
                .map(detail -> PreferenceItemRequest.builder()
                        .title(detail.getDetailId().getProductIdj().getName())
                        .id("123")
                        .description("Por favor enviate")
                        .quantity(detail.getQuantity())
                        .unitPrice(detail.getUnitPrice())
                        .currencyId("ARS")
                        .build())
                .collect(Collectors.toList());


        //Descomentar si se quiere redireccionar
        //Hecho con ngrok (Produccion real, bash que levanta una url publica)

        //String backUrl = System.getenv("PUBLIC_BACK_URL");
        //PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
        //        .success(backUrl + "/api/payment/success")
        //        .failure(backUrl + "/api/payment/failure")
        //        .pending(backUrl + "/api/payment/pending")

        //        .build();

        //Comentar si no se quiere redireccionar
        //BackUrls de backend, privadas
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                .success("http://localhost:8080/api/payment/success")
                .failure("http://localhost:8080/api/payment/failure")
                .pending("http://localhost:8080/api/payment/pending")

                .build();

        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .externalReference(String.valueOf(orderId))
                 //.autoReturn("approved")          //Dejar comentado si no se quiere redireccion
                 //.autoReturn("all")               //Si se descomenta, usar urls publicas seteadas
                                                    //con ngrok (Mirar mas arriba)
                .backUrls(backUrls)
                .externalReference(orderId.toString())
                .build();


        try {
            PreferenceClient preferenceClient = new PreferenceClient();

            Preference preference = preferenceClient.create(preferenceRequest);
            System.out.println(preferenceRequest);

            //return preference.getInitPoint();

            return preference.getId();
        } catch (MPApiException exapi) {
            var apiReponse = exapi.getApiResponse();
            var content = apiReponse.getContent();
            System.out.println(content);
            System.out.println("Error status: " + exapi.getStatusCode());
            System.out.println("Error cause: " + exapi.getApiResponse().getContent());
           throw exapi;
        } catch (MPException ex) {
            //ex.printStackTrace();
            ex.getMessage();
            throw ex;
        }

        //System.out.println(accessToken);


        //System.out.println("Init point" + preference.getInitPoint());

        //return preference.getInitPoint();
    }

}
