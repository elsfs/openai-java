package org.elsfs.openai.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
@Accessors(chain = true)
public class CreateAnswerRequest {
    /**
     * D of the model to use for completion. You can select one of `ada`, `babbage`, `curie`, or `davinci`.
     * 用于完成的模型的D。您可以选择“ada”、“babbage”、“curie”或“davinci”中的一个。
     */
    private String model;
    /**
     * Question to get answered.
     * 待回答的问题。
     */
    private String question;
    /**
     * List of (question, answer) pairs that will help steer the model towards the tone and answer format you\'d like. We recommend adding 2 to 3 examples.
     * 列出（问题，答案）键值对对，这些对将有助于引导模型朝着你想要的语气和答案格式发展。我们建议添加2到3个示例。
     */
    private Map<String, String> examples;
    /**
     * A text snippet containing the contextual information used to generate the answers for the `examples` you provide.
     * 包含上下文信息的文本片段，用于为您提供的“examples”生成答案。
     */
    @JsonProperty("examples_context")
    private String examplesContext;
    /**
     * optional
     * string|array
     * 应从中得出输入“问题”答案的文件列表。如果这是一个空列表，则将根据问答示例回答问题。您应该指定“documents”或“file”，但不能同时指定两者。
     */
    private List<String> documents;
    /**
     * optional
     * The ID of an uploaded file that contains documents to search over. See [upload file](/docs/api-reference/files/upload) for how to upload a file of the desired format and purpose.  You should specify either `documents` or a `file`, but not both.
     * 上载文件的ID，该文件包含要搜索的文档。请参阅[上传文件]（/docs/api reference/files/upload），了解如何上传所需格式和目的的文件。您应该指定“documents”或“file”，但不能同时指定两者。
     */
    private String file;
    /**
     * optional
     * ID of the model to use for [Search](/docs/api-reference/searches/create). You can select one of `ada`, `babbage`, `curie`, or `davinci`.
     * 用于[Search]（/docs/api reference/searchs/create）的模型的ID。您可以选择“ada”、“babbage”、“curie”或“davinci”中的一个。
     */
    @JsonProperty("search_model")
    private String searchModel;
    /**
     * optional
     * The maximum number of documents to be ranked by [Search](/docs/api-reference/searches/create) when using `file`. Setting it to a higher value leads to improved accuracy but with increased latency and cost.
     * 使用“file”时，按[Search]（/docs/api reference/searchs/create）排序的最大文档数。将其设置为更高的值可以提高准确性，但会增加延迟和成本。
     */
    @JsonProperty("max_rerank")
    private Integer maxRerank;

    /**
     * optional
     * <p>
     * Include the log probabilities on the `logprobs` most likely tokens, as well the chosen tokens. For example,
     * if `logprobs` is 5, the API will return a list of the 5 most likely tokens. The API will always return the `logprob` of the sampled token,
     * so there may be up to `logprobs+1` elements in the response.  The maximum value for `logprobs` is 5.
     * If you need more than this, please contact us through our [Help center](https://help.openai.com) and describe your use case.
     * When `logprobs` is set, `completion` will be automatically added into `expand` to get the logprobs.
     * 包括“logprobs”最可能的令牌以及所选令牌的日志概率。例如，如果“logprobs”为5，API将返回5个最可能的标记的列表。API将始终返回采样令牌的“logprob”，因此响应中最多可能有“logprobs+1”元素。“logprobs”的最大值为5。如果您需要更多信息，请通过我们的[帮助中心]与我们联系(https://help.openai.com)并描述您的用例。设置“logprobs”时，“completion”将自动添加到“expand”中以获取logprobs。
     */
    private Integer logprobs;


    /**
     * optional
     * A special boolean flag for showing metadata. If set to `true`, each document entry in the returned JSON will contain a \"metadata\" field.  This flag only takes effect when `file` is set.
     * 用于显示元数据的特殊布尔标志。如果设置为“true”，则返回的JSON中的每个文档条目都将包含一个“元数据”字段。此标志仅在设置了“file”时生效。
     */
    @JsonProperty("return_metadata")
    private Boolean returnMetadata;
    /**
     * optional
     * If set to `true`, the returned JSON will include a \"prompt\" field containing the final prompt that was used to request a completion. This is mainly useful for debugging purposes.
     * 如果设置为“true”，返回的JSON将包括一个“prompt”字段，该字段包含用于请求完成的最终提示。这主要用于调试目的。
     */
    @JsonProperty("return_prompt")
    private Boolean returnPrompt;
    /**
     * optional
     * If an object name is in the list, we provide the full information of the object; otherwise, we only provide the object ID.
     * Currently we support `completion` and `file` objects for expansion.
     * 如果列表中有对象名称，我们将提供该对象的完整信息；否则，我们只提供对象ID。目前，我们支持“completion”和“file”对象进行扩展。
     */
    private String expand;
}
