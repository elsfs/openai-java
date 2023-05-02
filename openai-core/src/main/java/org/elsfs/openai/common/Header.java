package org.elsfs.openai.common;


/**
 * @author zeng
 * @since 0.0.1
 */

public enum Header {
    AUTHORIZATION("Authorization"),
    CONTENT_TYPE("Content-type");

    public String getValue() {
        return value;
    }

    private final String value;

    Header(String value) {
        this.value = value;
    }

}
