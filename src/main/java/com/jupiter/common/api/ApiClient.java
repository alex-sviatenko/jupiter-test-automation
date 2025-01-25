package com.jupiter.common.api;

import com.jupiter.common.restclient.RestClientWrapper;

public interface ApiClient {

    RestClientWrapper getClient();
}