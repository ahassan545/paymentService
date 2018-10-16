package com.payments.services;

import com.payments.dataSource.store.PaymentStore;
import com.payments.dataSource.store.Store;
import com.payments.dataSource.validators.AccountValidator;
import com.payments.dataSource.validators.PaymentValidator;
import com.payments.models.Payment;
import com.payments.services.converters.JsonConverter;
import com.payments.services.converters.JsonConverterImp;
import com.payments.services.handlers.HttpRequestHandler;
import com.payments.services.handlers.HttpRequestHandlerImp;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentsServlet extends HttpServlet {
    private static final String delimiter = ",";
    private static final String orderNotFound = "Order with Id %s Not found";
    private static final String ordersNotAvailable = "No Orders Available";
    private static final String orderCreated = "Payment Order Created";
    private static final String OrderFailed = "Failed to create Payment Order";

    private Store<Payment> repository;
    private JsonConverter jsonConverter;
    private HttpRequestHandler requestHandler;

    public PaymentsServlet() {
        this.jsonConverter = new JsonConverterImp();
        this.requestHandler = new HttpRequestHandlerImp();
        this.repository = new PaymentStore(new PaymentValidator(new AccountValidator()));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws IOException {
        String data = StringUtils.EMPTY;
        Long id = requestHandler.extractId(request);

        if (id != null)
            data = getPaymentById(id);
        else
            data = getAllPayments();

        httpServletResponse.getWriter().print(data);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String paymentString = requestHandler.extractPayment(request);
        Payment payment = jsonConverter.fromJson(paymentString, Payment.class);

        Payment createdOrder = repository.create(payment);

        String result = createdOrder != null ? orderCreated : OrderFailed;

        response.getWriter().print(result);
    }

    private String getPaymentById(long id) {
        Optional<Payment> order = repository.findById(id);

        String response = order.isPresent() ? jsonConverter.toJson(order.get(), Payment.class)
                : String.format(orderNotFound, id);

        return response;
    }


    private String getAllPayments() {
        String orders = repository.getAll()
                .stream()
                .filter(order -> order != null)
                .map(order -> jsonConverter.toJson(order, Payment.class))
                .collect(Collectors.joining(delimiter + "\n"));

        String response = StringUtils.isNoneEmpty(orders) ? orders : ordersNotAvailable;

        return response;
    }
}