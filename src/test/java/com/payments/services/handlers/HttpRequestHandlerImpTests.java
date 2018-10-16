package com.payments.services.handlers;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class HttpRequestHandlerImpTests {
    private static final String uri = "/payment/5";
    private static final String payment = "{\"notes\":\"notes data\",\"amount\":\"5\"," +
            "\"fromAccount\":{\"ownerName\":\"asddsah\",\"name\":\"aasd\",\"balance\":\"1005\"," +
            "\"sortCode\":\"sort\"},\"toAccount\":{\"ownerName\":\"sdasda\",\"name\":\"aasdsd\"," +
            "\"balance\":\"1002\",\"sortCode\":\"sort\"}}";

    private HttpServletRequest request;
    private HttpRequestHandlerImp handler;

    @Before
    public void setUp() throws IOException {
        request = Mockito.mock(HttpServletRequest.class);

        when(request.getRequestURI()).thenReturn(uri);
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(payment)));

        handler = new HttpRequestHandlerImp();
    }

    @Test
    public void extractId_null_request_returns_null() throws Exception {
        Long id = handler.extractId(null);

        assertNull(id);
    }

    @Test
    public void extractId_empty_uri_returns_null() throws Exception {
        when(request.getRequestURI()).thenReturn(StringUtils.EMPTY);
        Long id = handler.extractId(request);

        assertNull(id);
    }

    @Test
    public void extractId_invalid_id_returns_null() throws Exception {
        when(request.getRequestURI()).thenReturn("/payment/invalidId");

        Long id = handler.extractId(request);

        assertNull(id);
    }

    @Test
    public void extractId_valid_id_returns_null() throws Exception {
        Long id = handler.extractId(request);

        assertEquals(id.longValue(), 5);
    }

    @Test
    public void extractPayment_null_request_returns_null() throws Exception {
        String payment = handler.extractPayment(null);

        assertNull(payment);
    }

    @Test
    public void extractPayment_empty_body_returns_null() throws Exception {
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader(StringUtils.EMPTY)));
        String payment = handler.extractPayment(request);

        assertNull(payment);
    }

    @Test
    public void extractPayment_valid_body_returns_null() throws Exception {
        String payment = handler.extractPayment(request);

        assertEquals(payment, this.payment);
    }

    @After
    public void tearDown() {
        request = null;
        handler = null;
    }
}
