package com.johnlewis.web.webclient

import org.springframework.http.client.ClientHttpResponse

class Response {
    private ClientHttpResponse response

    def Response(ClientHttpResponse response) {
        this.response = response
    }

    int getStatusCode() {
        this.response.getRawStatusCode()
    }

    String getHeader(String header) {
        return this.response.getHeaders().get(header)?.first();
    }
}
