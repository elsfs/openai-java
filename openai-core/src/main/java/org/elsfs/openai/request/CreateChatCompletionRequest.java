package org.elsfs.openai.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.elsfs.openai.common.ChatCompletionRequestMessageRoleEnum;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author zeng
 * @since 0.0.1
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CreateChatCompletionRequest extends CommonRequest {
    /**
     * D of the model to use for completion. You can select one of `ada`, `babbage`, `curie`, or `davinci`.
     * 要使用的模型的ID。目前，仅支持“gpt-3.5-turbo”和“gpt-3.5-turbo-0301”。
     */
    private String model;
    /**
     * The messages to generate chat completions for, in the [chat format](/docs/guides/chat/introduction).
     * 要为生成聊天完成的消息，格式为[聊天格式]（/docs/guideschat/introduction）。
     * https://platform.openai.com/docs/guides/chat
     */
    private List<Message> messages;
    /**
     * optional
     * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are considered.  We generally recommend altering this or `temperature` but not both.
     * 一种替代温度采样的方法，称为核采样，其中模型考虑具有top_p概率质量的令牌的结果。因此，0.1意味着只考虑包含前10%概率质量的代币。我们通常建议改变这个或“温度”，但不能同时改变。
     */
    private Float topP;
    /**
     * optional
     * If set, partial message deltas will be sent, like in ChatGPT. Tokens will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available, with the stream terminated by a `data: [DONE]` message.
     * 如果设置，将发送部分消息增量，就像在ChatGPT中一样。令牌将仅作为数据发送[服务器发送的事件](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format)当它们变得可用时，流由“data:[DONE]”消息终止。
     */
    private String stream;

    /**
     * optional
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far, increasing the model\'s likelihood to talk about new topics.  [See more information about frequency and presence penalties.](/docs/api-reference/parameter-details)
     * *介于-2.0和2.0之间的数字。到目前为止，正值会根据新标记是否出现在文本中来惩罚它们，从而增加模型谈论新主题的可能性。[查看有关频率和存在惩罚的更多信息。]（/docs/api reference/parameter details）
     */
    private Float presencePenalty;
    /**
     * optional
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency in the text so far, decreasing the model\'s likelihood to repeat the same line verbatim.  [See more information about frequency and presence penalties.](/docs/api-reference/parameter-details)
     * 介于-2.0和2.0之间的数字。到目前为止，正值会根据新标记在文本中的现有频率对其进行惩罚，从而降低模型逐字重复同一行的可能性。[查看有关频率和存在惩罚的更多信息。]（/docs/api reference/parameter details）
     */
    private Float frequencyPenalty;

    @Data
    public static class Message {
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private ChatCompletionRequestMessageRoleEnum role;
        private String content;
    }
}
