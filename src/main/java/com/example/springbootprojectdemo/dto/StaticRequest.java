package com.example.springbootprojectdemo.dto;

public class StaticRequest<T> {

    private Header header;

    private T body;

    @Override
    public String toString() {
        return "StaticRequest{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public static class Header {
        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        private String responseCode;
    }

}
