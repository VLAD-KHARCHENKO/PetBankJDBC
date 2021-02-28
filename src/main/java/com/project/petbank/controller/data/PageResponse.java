package com.project.petbank.controller.data;

public class PageResponse {

    private String url;
    private boolean redirect;

    public PageResponse(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    public PageResponse(String url) {
        this.url = url;
    }

    public PageResponse() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

}
