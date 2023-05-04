package org.elsfs.openai.service;

import org.elsfs.openai.exception.OpenaiException;
import org.elsfs.openai.request.*;
import org.elsfs.openai.response.*;
import org.elsfs.openai.response.CreateAnswerResponse;
import org.elsfs.openai.response.CreateChatCompletionResponse;
import org.elsfs.openai.response.CreateCompletionResponse;
import org.elsfs.openai.response.FineTune;
import org.elsfs.openai.response.ListModelsResponse;
import org.elsfs.openai.response.OpenAIFile;

import java.io.File;

/**
 * @author zeng
 * @since 0.0.1
 */
public interface OpenAIApiService {

    /**
     * <a href="https://platform.openai.com/docs/api-reference/fine-tunes/create">openaai doc</a>
     *
     * @param fineTuneId fineTuneId要取消的微调作业的ID
     *                   <p>
     *                   POST /fine-tunes/{fine_tune_id}/cancel
     *                   <p>
     *                   Immediately cancel a fine-tune job.
     *                   <p>
     *                   立即取消微调作业。
     * @return FineTune
     * @throws OpenaiException  OpenaiException
     */
    FineTune cancelFineTune(String fineTuneId) throws OpenaiException;

    /**
     * <a href=“/api”>openaai doc</a>
     * `<p>
     * POST  /answers
     * <p>
     * Answers the specified question using the provided documents and examples.  The endpoint first [searches](/docs/api-reference/searches) over provided documents or files to find relevant context. The relevant context is combined with the provided examples and question to create the prompt for [completion](/docs/api-reference/completions).
     * <p>
     * 使用提供的文档和示例回答指定的问题。端点首先对提供的文档或文件进行[搜索]（/docs/api reference/searchs）以查找相关上下文。相关上下文与提供的示例和问题相结合，以创建[完成]（/docs/api reference/completions）的提示。
     */
    CreateAnswerResponse createAnswer(CreateAnswerRequest createAnswerRequest) throws OpenaiException;

    /**
     * <a href=“”>openaai doc</a>
     * <p>
     * POST /chat/completions
     * <p>
     * Creates a completion for the chat message
     * </p>
     * 为聊天消息创建一个 completions
     */
    CreateChatCompletionResponse createChatCompletion(CreateChatCompletionRequest createChatCompletionRequest) throws OpenaiException;

    /**
     * <a href=“”>openaai doc</a>
     * <p>
     * POST  /completions
     * </p>
     * Creates a completion for the provided prompt and parameters
     * <p>
     * 为提供的提示和参数创建完成
     * </p>
     */
    CreateCompletionResponse createCompletion(CreateCompletionRequest createCompletionRequest) throws OpenaiException;

    /**
     * <a href=“”>openaai doc</a>
     * <p>
     * GET /models
     * </p>
     * Lists the currently available models, and provides basic information about each one such as the owner and availability.
     * <p>
     * 列出当前可用的型号，并提供每个型号的基本信息，
     * </p>
     */
    ListModelsResponse listModels() throws OpenaiException;


    /**
     * <a href=“”>openaai doc</a>
     * <p>
     * POST /classifications
     * </p>
     * Classifies the specified `query` using provided examples.  The endpoint first [searches](/docs/api-reference/searches)
     * over the labeled examples to select the ones most relevant for the particular query. Then,
     * the relevant examples are combined with the query to construct a prompt to produce the final label via the [completions](/docs/api-reference/completions) endpoint.
     * Labeled examples can be provided via an uploaded `file`, or explicitly listed in the request using the `examples` parameter for quick tests and small scale use cases.
     */
    CreateClassificationResponse createClassification(CreateClassificationRequest createClassificationRequest) throws OpenaiException;


    /**
     * <a href=“”>openaai doc</a>
     * <p>
     * POST /edits
     * </p>
     * Creates a new edit for the provided input, instruction, and parameters.
     */
    CreateEditResponse createEdit(CreateEditRequest createEditRequest) throws OpenaiException;

    /**
     * <a href=“<a href="https://platform.openai.com/docs/api-reference/embeddings">...</a>”>openaai doc</a>
     *
     * <p>
     * POST /embeddings
     * <p>
     * 嵌入式
     * </p>
     * Creates an embedding vector representing the input text.
     */
    CreateEmbeddingResponse createEmbedding(CreateEmbeddingRequest createEmbeddingRequest) throws OpenaiException;

    /**
     * @param file    Name of the [JSON Lines](https://jsonlines.readthedocs.io/en/latest/) file to be uploaded.  If the &#x60;purpose&#x60; is set to \\\&quot;fine-tune\\\&quot;, each line is a JSON record with \\\&quot;prompt\\\&quot; and \\\&quot;completion\\\&quot; fields representing your [training examples](/docs/guides/fine-tuning/prepare-training-data).
     * @param purpose The intended purpose of the uploaded documents.  Use \\\&quot;fine-tune\\\&quot; for [Fine-tuning](/docs/api-reference/fine-tunes). This allows us to validate the format of the uploaded file.
     * @summary Upload a file that contains document(s) to be used across various endpoints/features. Currently, the size of all the files uploaded by one organization can be up to 1 GB. Please contact us if you need to increase the storage limit.
     * <a href=“”>openaai doc</a>
     * <a href=“https://platform.openai.com/docs/api-reference/files”>openaai doc</a>
     * POST /files ['Content-Type'] = 'multipart/form-data';
     * <p>
     */
    OpenAIFile createFile(File file, String purpose) throws OpenaiException;

    /**
     * <a href=“https://platform.openai.com/docs/api-reference/fine-tunes/create”>openaai doc</a>
     * <p>
     * POST /fine-tunes
     * </p>
     * Creates a job that fine-tunes a specified model from a given dataset.  Response includes details of the enqueued job including job status and the name of the fine-tuned models once complete.  [Learn more about Fine-tuning](/docs/guides/fine-tuning)
     */
    FineTune createFineTune(CreateFineTuneRequest createFineTuneRequest) throws OpenaiException;

    /**
     * <a href=“https://platform.openai.com/docs/api-reference/images”>openaai doc</a>
     * <p>
     * POST /images/generations
     * </p>
     * Creates an image given a prompt.
     * <p>
     * 给定提示和/或输入图像，模型将生成一个新图像
     */
    ImagesResponse createImage(CreateImageRequest createImageRequest) throws OpenaiException;

    /**
     * @param image          The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have transparency, which will be used as the mask.
     * @param prompt         A text description of the desired image(s). The maximum length is 1000 characters.
     * @param mask           An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where &#x60;image&#x60; should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as &#x60;image&#x60;.
     * @param n              The number of images to generate. Must be between 1 and 10.
     * @param size           The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;.
     * @param responseFormat The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;.
     * @param user           A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).
     * @summary Creates an edited or extended image given an original image and a prompt.
     * <a href=“<a href="https://platform.openai.com/docs/api-reference/images/create-edit">openaai doc</a>
     * <p>
     * POST  /images/edits ['Content-Type'] = 'multipart/form-data';
     * </p>
     * 创建图像编辑
     */
    ImagesResponse createImageEdit(File image, String prompt, File mask, Number n, String size, String responseFormat, String user) throws OpenaiException;

    default ImagesResponse createImageEdit(File image, String prompt) throws OpenaiException {
        return createImageEdit(image, prompt, null, null, null, null, null);
    }

    /**
     * @param image          The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square.
     * @param n              The number of images to generate. Must be between 1 and 10.
     * @param size           The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;.
     * @param responseFormat The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;.
     * @param user           A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).
     * @summary Creates a variation of a given image.
     * <a href=“”>openaai doc</a>
     * <p>
     * POST /images/variations ['Content-Type'] = 'multipart/form-data'
     * </p>
     * 创建给定图像的变体。
     */
    ImagesResponse createImageVariation(File image, Number n, String size, String responseFormat, String user) throws OpenaiException;

    default ImagesResponse createImageVariation(File image) throws OpenaiException {
        return createImageVariation(image, null, null, null, null);
    }

    /**
     * <a href=“https://platform.openai.com/docs/api-reference/moderations/create”>openaai doc</a>
     * <p>
     * POST /moderations
     * </P>
     * Classifies if text violates OpenAI\'s Content Policy
     * </P>
     * 如果文本违反OpenAI\的内容策略，则进行分类
     */
    CreateModerationResponse createModeration(CreateModerationRequest createModerationRequest) throws OpenaiException;

    /**
     * @param engineId engineId The ID of the engine to use for this request.  You can select one of &#x60;ada&#x60;, &#x60;babbage&#x60;, &#x60;curie&#x60;, or &#x60;davinci&#x60;.
     *                 <p>
     *                 POST  /engines/{engine_id}/search
     *                 </p>
     *                 The search endpoint computes similarity scores between provided query and documents. Documents can be passed directly to the API if there are no more than 200 of them.  To go beyond the 200 document limit, documents can be processed offline and then used for efficient retrieval at query time. When `file` is set, the search endpoint searches over all the documents in the given file and returns up to the `max_rerank` number of documents. These documents will be returned along with their search scores.  The similarity score is a positive score that usually ranges from 0 to 300 (but can sometimes go higher), where a score above 200 usually means the document is semantically similar to the query.
     *                 <a href=“https://platform.openai.com/docs/api-reference/engines”>openaai doc</a>
     */
    @Deprecated
    CreateSearchResponse createSearch(String engineId, CreateSearchRequest createSearchRequest) throws OpenaiException;

    /**
     * @param file           The audio file to transcribe, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     * @param model          ID of the model to use. Only &#x60;whisper-1&#x60; is currently available.
     * @param prompt         An optional text to guide the model\\\&#39;s style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should match the audio language.
     * @param responseFormat The format of the transcript output, in one of these options: json, text, srt, verbose_json, or vtt.
     * @param temperature    The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to automatically increase the temperature until certain thresholds are hit.
     * @param language       The language of the input audio. Supplying the input language in [ISO-639-1](https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes) format will improve accuracy and latency.
     *                       Transcribes audio into the input language.
     *                       <a href=“https://platform.openai.com/docs/api-reference/audio”>openaai doc</a>
     *                       <p>
     *                       POST /audio/transcriptions "Content-Type: multipart/form-data"
     *                       </p>
     *                       创建听录
     */
    CreateTranscriptionResponse createTranscription(File file, String model, String prompt, String responseFormat, Number temperature, String language) throws OpenaiException;

    default CreateTranscriptionResponse createTranscription(File file, String model) throws OpenaiException {
        return createTranscription(file, model, null, null, null, null);
    }

    /**
     * @param file           file The audio file to translate, in one of these formats: mp3, mp4, mpeg, mpga, m4a, wav, or webm.
     * @param model          model ID of the model to use. Only &#x60;whisper-1&#x60; is currently available.
     * @param prompt         An optional text to guide the model\\\&#39;s style or continue a previous audio segment. The [prompt](/docs/guides/speech-to-text/prompting) should be in English.
     * @param responseFormat The format of the transcript output, in one of these options: json, text, srt, verbose_json, or vtt.
     * @param temperature    The sampling temperature, between 0 and 1. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic. If set to 0, the model will use [log probability](https://en.wikipedia.org/wiki/Log_probability) to automatically increase the temperature until certain thresholds are hit.
     *                       <a href=“https://platform.openai.com/docs/api-reference/audio/create”>openaai doc</a>
     *                       <p>
     *                       POST  /audio/translations  ['Content-Type'] = 'multipart/form-data'
     *                       <p>
     *                       翻译
     *                       Translates audio into into English.
     *                       <p>
     */
    CreateTranslationResponse createTranslation(File file, String model, String prompt, String responseFormat, Number temperature) throws OpenaiException;

    default CreateTranslationResponse createTranslation(File file, String model) throws OpenaiException {
        return createTranslation(file, model, null, null, null);
    }

    /**
     * @param fileId The ID of the file to use for this request
     *               <a href=“https://platform.openai.com/docs/api-reference/files”>openaai doc</a>
     *               <p>
     *               DELETE /files/{file_id}
     *               </p>
     *               Delete a file.
     */
    DeleteFileResponse deleteFile(String fileId) throws OpenaiException;

    /**
     * @param model model The model to delete
     *              <a href=“”>openaai doc</a>
     *              <p>
     *              DELETE /models/{model}
     *              </p>
     *              Delete a fine-tuned model. You must have the Owner role in your organization.
     */
    DeleteModelResponse deleteModel(String model) throws OpenaiException;

    /**
     * @param fileId The ID of the file to use for this request
     * @return <a href=“https://platform.openai.com/docs/api-reference/files”>openaai doc</a>
     * <p>
     * GET /files/{file_id}/content
     * </p>
     * Returns the contents of the specified file
     */
    String downloadFile(String fileId) throws OpenaiException;

    /**
     * <a href=“https://platform.openai.com/docs/api-reference/engines”>openaai doc</a>
     * <p>
     * GET  /engines
     * </p>
     * Lists the currently available (non-finetuned) models, and provides basic information about each one such as the owner and availability.
     */
    @Deprecated
    ListEnginesResponse listEngines() throws OpenaiException;

    /**
     * @return ListFilesResponse
     * <a href=“https://platform.openai.com/docs/api-reference/files”>openaai doc</a>
     * <p>
     * GET /files
     * </P>
     * Returns a list of files that belong to the user\'s organization.
     */
    ListFilesResponse listFiles() throws OpenaiException;

    /**
     * @param fineTuneId The ID of the fine-tune job to get events for.
     * @param stream     Whether to stream events for the fine-tune job. If set to true, events will be sent as data-only [server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events#Event_stream_format) as they become available. The stream will terminate with a &#x60;data: [DONE]&#x60; message when the job is finished (succeeded, cancelled, or failed).  If set to false, only events generated so far will be returned.
     *                   <a href=“https://platform.openai.com/docs/api-reference/fine-tunes/create”>openaai doc</a>
     *                   <p>
     *                   GET /fine-tunes/{fine_tune_id}/events
     *                   </p>
     *                   Get fine-grained status updates for a fine-tune job.
     */
    ListFineTuneEventsResponse listFineTuneEvents(String fineTuneId, Boolean stream) throws OpenaiException;

    default ListFineTuneEventsResponse listFineTuneEvents(String fineTuneId) throws OpenaiException {
        return listFineTuneEvents(fineTuneId, null);
    }

    /**
     * <a href=“https://platform.openai.com/docs/api-reference/fine-tunes/create”>openaai doc</a>
     * <p>
     * GET /fine-tunes
     * </p>
     * List your organization\'s fine-tuning jobs
     */
    ListFineTunesResponse listFineTunes() throws OpenaiException;


    /**
     * @param engineId The ID of the engine to use for this request
     *                 <a href=“”>openaai doc</a>
     *                 <p>
     *                 GET /engines/{engine_id}
     *                 </p>
     *                 Retrieves a model instance, providing basic information about it such as the owner and availability.
     */
    @Deprecated
    Engine retrieveEngine(String engineId) throws OpenaiException;

    /**
     * <a href=“https://platform.openai.com/docs/api-reference/files”>openaai doc</a>
     * <p>
     * GET /files/{file_id}
     * </p>
     * Returns information about a specific file.
     *
     * @param fileId The ID of the file to use for this request
     */
    OpenAIFile retrieveFile(String fileId) throws OpenaiException;

    /**
     * <a href=“https://platform.openai.com/docs/api-reference/fine-tunes/create”>openaai doc</a>
     * <p>
     * GET /fine-tunes/{fine_tune_id}
     * </P>
     * Gets info about the fine-tune job.  [Learn more about Fine-tuning](/docs/guides/fine-tuning)
     *
     * @param fineTuneId The ID of the fine-tune job
     * @return
     */
    FineTune retrieveFineTune(String fineTuneId) throws OpenaiException;

    /**
     * <a href=“”>openaai doc</a>
     * <p>
     * GET  /models/{model}
     * </p>
     * Retrieves a model instance, providing basic information about the model such as the owner and permissioning.
     *
     * @param model The ID of the model to use for this request
     */
    Model retrieveModel(String model) throws OpenaiException;

}

