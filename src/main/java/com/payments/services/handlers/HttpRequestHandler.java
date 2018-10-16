package com.payments.services.handlers;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface HttpRequestHandler {
    String extractPayment(HttpServletRequest request) throws IOException;

    Long extractId(HttpServletRequest request);
}
