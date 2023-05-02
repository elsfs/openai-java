package org.elsfs.openai.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
@Accessors(chain = true)
public class CreateImageRequest {
    /**
     *
     * A text description of the desired image(s). The maximum length is 1000 characters.

     */
   private String  prompt;
    /**
     * optional
     * The number of images to generate. Must be between 1 and 10.

     */
    private Integer n;
    /**
     * optional
     * The size of the generated images. Must be one of `256x256`, `512x512`, or `1024x1024`.

     */
    private CreateImageRequestSizeEnum size;
    /**
     * optional

     */
    private CreateImageRequestResponseFormatEnum response_format;
    /**
     * optional
     */
    private   String user;
    @AllArgsConstructor
    @Getter
    enum CreateImageRequestSizeEnum{
        _256x256("256x256"),
        _512x512( "512x512"),
        _1024x1024("1024x1024");
        private final String value;
    }
    @AllArgsConstructor
    @Getter
   enum CreateImageRequestResponseFormatEnum{
       Url("url"),
       B64Json("b64_json");
       private final String value;
    }
}

