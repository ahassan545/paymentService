package com.payments.services.handlers;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestHandlerImp implements HttpRequestHandler {
    private static final String splitter = "/";

    public Long extractId(HttpServletRequest request) {
        Long id = null;
        try {

            String[] uriArray = request.getRequestURI().split(splitter);
            id = uriArray.length == 3 ? Long.parseLong(uriArray[2]) : null;

        } catch (Exception ex) {
            return id;
        }

        return id;
    }

    public String extractPayment(HttpServletRequest request) throws IOException {
        String payment = null;
        try {

            StringBuffer paymentBuffer = new StringBuffer();
            String line = null;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                paymentBuffer.append(line);

            payment = getPayment(paymentBuffer.toString());

        } catch (Exception ex) {
            return payment;
        }

        return payment;
    }

    private String getPayment(String value) {
        int startIndex = value.indexOf("{");
        int endIndex = value.lastIndexOf("}") + 1;

        return value.substring(startIndex, endIndex);
    }
}
