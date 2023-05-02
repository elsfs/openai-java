package org.elsfs.openai.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
@Accessors(chain = true)
public class CreateModerationRequest {
    /**
     * Two content moderations models are available:
     * `text-moderation-stable` and `text-moderation-latest`.
     * The default is `text-moderation-latest` which will be automatically upgraded over time.
     * This ensures you are always using our most accurate model.
     * If you use `text-moderation-stable`,
     * we will provide advanced notice before updating the model.
     * Accuracy of `text-moderation-stable` may be slightly lower than for `text-moderation-latest`.
     */
   private String model;

   private List<String> input;
}
