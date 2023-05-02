package org.elsfs.openai.response;

import lombok.Data;


import java.util.List;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
public class ListEnginesResponse {
   private String object;
    private List<Engine> data;

}
