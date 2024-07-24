package com.atduck.openai.client;

import java.time.Duration;

/**
 * Created on 2024/7/24 8:51.
 *
 * @author ronger
 * @email ronger-x@outlook.com
 * @desc : com.theokanning.openai.client
 */
public class OpenAiApiConfig {

    private String host = "https://api.openai.com/";

    private String token;

    private Duration timeout;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Duration getTimeout() {
        return timeout;
    }

    public void setTimeout(Duration timeout) {
        this.timeout = timeout;
    }

}
