package org.elsfs.openai.response;

import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class ListFineTuneEventsResponse {
  private   String object;
    private List<FineTune.FineTuneEvent> data;
}
