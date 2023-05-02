package org.elsfs.openai.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CreateSearchRequest {
    /**
     * Query to search against the documents.
     */
    private String query;
    /**
     * Up to 200 documents to search over, provided as a list of strings.  The maximum document length (in tokens) is 2034 minus the number of tokens in the query.  You should specify either `documents` or a `file`, but not both.
     */
    private List<String> documents;
    /**
     * The ID of an uploaded file that contains documents to search over.  You should specify either `documents` or a `file`, but not both.
     */
    private String file;
    /**
     * The maximum number of documents to be re-ranked and returned by search.  This flag only takes effect when `file` is set.
     */
    @JsonProperty("max_rerank")
    private Number maxRerank;
    /**
     * A special boolean flag for showing metadata. If set to `true`, each document entry in the returned JSON will contain a \"metadata\" field.  This flag only takes effect when `file` is set.
     */
    @JsonProperty("return_metadata")
    private Boolean returnMetadata;
    private String user;
}
