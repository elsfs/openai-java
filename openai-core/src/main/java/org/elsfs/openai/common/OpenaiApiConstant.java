package org.elsfs.openai.common;

/**
 * @author zeng
 * @since 0.0.1
 */
public interface OpenaiApiConstant {
    String BASE_URL = "https://api.openai.com/";
    String VERSION = "v1";
    String BASE_URL_V1 = BASE_URL + VERSION;
    String MODELS = "/models";

    static String getModels() {
        return BASE_URL_V1 + MODELS;
    }
    String CANCEL_FINE_TUNE = "/fine-tunes/{fine_tune_id}/cancel";

    static String getCancelFineTune(String fine_tune_id) {
        return BASE_URL_V1 + "/fine-tunes/" + fine_tune_id + "/cancel";

    }

}
