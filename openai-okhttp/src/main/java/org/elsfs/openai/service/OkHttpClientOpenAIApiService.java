package org.elsfs.openai.service;

import okhttp3.*;
import org.elsfs.openai.common.Header;
import org.elsfs.openai.common.OpenaiApiConstant;
import org.elsfs.openai.exception.OpenaiException;
import org.elsfs.openai.request.*;
import org.elsfs.openai.response.*;
import org.elsfs.openai.util.JsonUtil;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author zeng
 * @since 0.0.1
 */
public class OkHttpClientOpenAIApiService extends AbstractOpenAIApiService {
    private OkHttpClient okHttpClient;
    private final String apiKey;
   private final List<String> apiKeyList;
    private final long timeout;
    private final Proxy proxy;
    private static final MediaType jsonMediaType = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType multipartFormDataMediaType = MediaType.parse("multipart/form-data");
    /**
     * 开启使用随机key模式 需要配置 {@link #apiKeyList}
     */
    private final boolean randomKeyExpired;
    private final Function<List<String>, String> getRandomKey = apiKeyList -> apiKeyList.get(new Random().nextInt(apiKeyList.size()));

    private OkHttpClientOpenAIApiService(OkHttpClient okHttpClient, String apiKey,
                                         List<String> apiKeyList, long timeout,
                                         Proxy proxy,
                                         boolean randomKeyExpired
    ) {
        this.okHttpClient = okHttpClient;
        this.apiKey = apiKey;
        this.apiKeyList = apiKeyList;
        this.timeout = timeout;
        this.proxy = proxy;
        this.randomKeyExpired = randomKeyExpired;
        init();
    }

    protected void init() {
        if (okHttpClient == null) {
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.addInterceptor(chain -> {
                String key = apiKey;
                if (apiKeyList != null && !apiKeyList.isEmpty() && randomKeyExpired) {
                    key = getRandomKey.apply(apiKeyList);
                }
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header(Header.AUTHORIZATION.getValue(), "Bearer " + key)
                        // .header(Header.CONTENT_TYPE.getValue(), "application/json")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });
            client.connectTimeout(timeout, TimeUnit.SECONDS);
            client.writeTimeout(timeout, TimeUnit.SECONDS);
            client.readTimeout(timeout, TimeUnit.SECONDS);
            if (Objects.nonNull(proxy)) {
                client.proxy(proxy);
            }
            okHttpClient = client.build();
        }
    }

    public static Builder builder(String apiKey) {
        return new Builder().apiKey(apiKey);
    }

    public static Builder builder(List<String> apiKeyList) {
        return new Builder().apiKeyList(apiKeyList);
    }

    public static final class Builder {
        private OkHttpClient okHttpClient;
        private String apiKey;
        List<String> apiKeyList;
        private long timeout = 300;
        private Proxy proxy = Proxy.NO_PROXY;
        private boolean randomKeyExpired = false;

        private Builder() {
        }

        public Builder okHttpClient(OkHttpClient okHttpClient) {
            this.okHttpClient = okHttpClient;
            return this;
        }

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder apiKeyList(List<String> apiKeyList) {
            this.apiKeyList = apiKeyList;
            return this;
        }

        public Builder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        public Builder randomKeyExpired(boolean randomKeyExpired) {
            this.randomKeyExpired = randomKeyExpired;
            return this;
        }

        public OkHttpClientOpenAIApiService build() {
            return new OkHttpClientOpenAIApiService(
                    this.okHttpClient,
                    this.apiKey,
                    this.apiKeyList,
                    this.timeout,
                    this.proxy, randomKeyExpired
            );
        }

    }

    private RequestBody jsonRequestBody(Object obj) {
        return RequestBody.create(JsonUtil.toJsonStr(obj), jsonMediaType);
    }

    private Request executePost(RequestBody requestBody, String url) {
        return new Request.Builder().url(url)
                .post(requestBody)
                .build();
    }

    @Override
    public FineTune cancelFineTune(String fineTuneId) throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.getCancelFineTune(fineTuneId))
                .post(new FormBody.Builder().build())
                .build();
        return handle(request, FineTune.class);
    }

    @Override
    public CreateAnswerResponse createAnswer(CreateAnswerRequest createAnswerRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createAnswerRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/answers");
        return handle(request, CreateAnswerResponse.class);
    }

    @Override
    public CreateChatCompletionResponse createChatCompletion(CreateChatCompletionRequest createChatCompletionRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createChatCompletionRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/chat/completions");
        return handle(request, CreateChatCompletionResponse.class);
    }

    @Override
    public CreateCompletionResponse createCompletion(CreateCompletionRequest createCompletionRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createCompletionRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/completions");
        return handle(request, CreateCompletionResponse.class);
    }

    @Override
    public ListModelsResponse listModels() throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.getModels()).build();
        return handle(request, ListModelsResponse.class);
    }

    @Override
    public CreateClassificationResponse createClassification(CreateClassificationRequest createClassificationRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createClassificationRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/classifications");
        return handle(request, CreateClassificationResponse.class);
    }

    @Override
    public CreateEditResponse createEdit(CreateEditRequest createEditRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createEditRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/edits");
        return handle(request, CreateEditResponse.class);
    }

    @Override
    public CreateEmbeddingResponse createEmbedding(CreateEmbeddingRequest createEmbeddingRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createEmbeddingRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/embeddings");
        return handle(request, CreateEmbeddingResponse.class);
    }

    //
    @Override
    public OpenAIFile createFile(File file, String purpose) throws OpenaiException {
        RequestBody fileRequestBody = RequestBody.create(multipartFormDataMediaType, file);
        RequestBody requestBody = new MultipartBody.Builder() //请求体
                .setType(multipartFormDataMediaType)
                .addFormDataPart("purpose", purpose)//参数以json的格式传输
                .addFormDataPart("file", file.getName(), fileRequestBody)//第一个参数是服务端接收的标记，第二个是路径，第三个是请求体信息
                .build();
        Request request = new Request.Builder().url(OpenaiApiConstant.BASE_URL_V1 + "/files")
                .post(requestBody)
                .build();
        return handle(request, OpenAIFile.class);
    }

    @Override
    public FineTune createFineTune(CreateFineTuneRequest createFineTuneRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createFineTuneRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/fine-tunes");
        return handle(request, FineTune.class);
    }

    @Override
    public ImagesResponse createImage(CreateImageRequest createImageRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createImageRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/images/generations");
        return handle(request, ImagesResponse.class);
    }

    /**
     * /images/edits
     *
     * @param image          The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have transparency, which will be used as the mask.
     * @param prompt         A text description of the desired image(s). The maximum length is 1000 characters.
     * @param mask           An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where &#x60;image&#x60; should be edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as &#x60;image&#x60;.
     * @param n              The number of images to generate. Must be between 1 and 10.
     * @param size           The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;.
     * @param responseFormat The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;.
     * @param user           A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).
     */
    @Override
    public ImagesResponse createImageEdit(File image, String prompt, File mask, Number n, String size, String responseFormat, String user) throws OpenaiException {
        RequestBody imageRequestBody = RequestBody.create(multipartFormDataMediaType, image);
        MultipartBody.Builder builderMultipartBody = new MultipartBody.Builder()
                .setType(multipartFormDataMediaType)
                .addFormDataPart("prompt", prompt)//参数以json的格式传输
                .addFormDataPart("image", image.getName(), imageRequestBody);//第一个参数是服务端接收的标记，第二个是路径，第三个是请求体信息
        if (mask != null) {
            builderMultipartBody.addFormDataPart("mask", mask.getName(), RequestBody.create(mask, multipartFormDataMediaType));
        }
        if (n != null) builderMultipartBody.addFormDataPart("n", n.toString());
        if (size != null) builderMultipartBody.addFormDataPart("size", size);
        if (responseFormat != null) builderMultipartBody.addFormDataPart("responseFormat", responseFormat);
        if (user != null) builderMultipartBody.addFormDataPart("user", user);
        Request request = executePost(builderMultipartBody.build(), OpenaiApiConstant.BASE_URL_V1 + "/images/edits");
        return handle(request, ImagesResponse.class);
    }

    /**
     * /images/variations
     *
     * @param image          The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square.
     * @param n              The number of images to generate. Must be between 1 and 10.
     * @param size           The size of the generated images. Must be one of &#x60;256x256&#x60;, &#x60;512x512&#x60;, or &#x60;1024x1024&#x60;.
     * @param responseFormat The format in which the generated images are returned. Must be one of &#x60;url&#x60; or &#x60;b64_json&#x60;.
     * @param user           A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse. [Learn more](/docs/guides/safety-best-practices/end-user-ids).
     */
    @Override
    public ImagesResponse createImageVariation(File image, Number n, String size, String responseFormat, String user) throws OpenaiException {
        RequestBody imageRequestBody = RequestBody.create(image, multipartFormDataMediaType);
//请求体
        MultipartBody.Builder builderMultipartBody = new MultipartBody.Builder()
                .setType(multipartFormDataMediaType)
                .addFormDataPart("image", image.getName(), imageRequestBody);//第一个参数是服务端接收的标记，第二个是路径，第三个是请求体信息
        if (n != null) builderMultipartBody.addFormDataPart("n", n.toString());
        if (size != null) builderMultipartBody.addFormDataPart("size", size);
        if (responseFormat != null) builderMultipartBody.addFormDataPart("responseFormat", responseFormat);
        if (user != null) builderMultipartBody.addFormDataPart("user", user);
        Request request = executePost(builderMultipartBody.build(), OpenaiApiConstant.BASE_URL_V1 + "/images/variations");
        return handle(request, ImagesResponse.class);
    }

    @Override
    public CreateModerationResponse createModeration(CreateModerationRequest createModerationRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createModerationRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + "/moderations");
        return handle(request, CreateModerationResponse.class);
    }

    @Override
    public CreateSearchResponse createSearch(String engineId, CreateSearchRequest createSearchRequest) throws OpenaiException {
        RequestBody requestBody = jsonRequestBody(createSearchRequest);
        Request request = executePost(requestBody, OpenaiApiConstant.BASE_URL_V1 + " /engines/" + engineId + "/search");
        return handle(request, CreateSearchResponse.class);
    }

    @Override
    public CreateTranscriptionResponse createTranscription(File file, String model, String prompt, String responseFormat, Number temperature, String language) throws OpenaiException {
        RequestBody fileRequestBody = RequestBody.create(file, multipartFormDataMediaType);
//请求体
        MultipartBody.Builder builderMultipartBody = new MultipartBody.Builder()
                .setType(multipartFormDataMediaType)
                .addFormDataPart("file", file.getName(), fileRequestBody);//第一个参数是服务端接收的标记，第二个是路径，第三个是请求体信息
        if (model != null) builderMultipartBody.addFormDataPart("model", model);
        if (prompt != null) builderMultipartBody.addFormDataPart("prompt", prompt);
        if (responseFormat != null) builderMultipartBody.addFormDataPart("responseFormat", responseFormat);
        if (temperature != null) builderMultipartBody.addFormDataPart("temperature", temperature.toString());
        if (language != null) builderMultipartBody.addFormDataPart("language", language);
        Request request = executePost(builderMultipartBody.build(), OpenaiApiConstant.BASE_URL_V1 + "/audio/transcriptions");
        return handle(request, CreateTranscriptionResponse.class);
    }

    @Override
    public CreateTranslationResponse createTranslation(File file, String model, String prompt, String responseFormat, Number temperature) throws OpenaiException {
        RequestBody fileRequestBody = RequestBody.create(file, multipartFormDataMediaType);
//请求体
        MultipartBody.Builder builderMultipartBody = new MultipartBody.Builder()
                .setType(multipartFormDataMediaType)
                .addFormDataPart("file", file.getName(), fileRequestBody);//第一个参数是服务端接收的标记，第二个是路径，第三个是请求体信息
        if (model != null) builderMultipartBody.addFormDataPart("model", model);
        if (prompt != null) builderMultipartBody.addFormDataPart("prompt", prompt);
        if (responseFormat != null) builderMultipartBody.addFormDataPart("responseFormat", responseFormat);
        if (temperature != null) builderMultipartBody.addFormDataPart("temperature", temperature.toString());
        Request request = executePost(builderMultipartBody.build(), OpenaiApiConstant.BASE_URL_V1 + "/audio/translations");
        return handle(request, CreateTranslationResponse.class);
    }

    @Override
    public DeleteFileResponse deleteFile(String fileId) throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.BASE_URL_V1 + "/files/" + fileId)
                .delete()
                .build();
        return handle(request, DeleteFileResponse.class);
    }

    @Override
    public DeleteModelResponse deleteModel(String model) throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.BASE_URL_V1 + "/models/" + model)
                .delete()
                .build();
        return handle(request, DeleteModelResponse.class);
    }

    @Override
    public String downloadFile(String fileId) throws OpenaiException {
        Request request = new Request.Builder().get().url("/files/" + fileId + "/content").build();
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            if (!response.isSuccessful()) {
                String errorMsg = response.body().string();
                throw new OpenaiException(errorMsg);
            }
            return response.body().string();
        } catch (IOException e) {
            throw new OpenaiException(e);
        }
    }

    @Override
    @Deprecated
    public ListEnginesResponse listEngines() throws OpenaiException {
        Request request = new Request.Builder().get().url("/engines").build();
        return handle(request, ListEnginesResponse.class);
    }

    @Override
    public ListFilesResponse listFiles() throws OpenaiException {
        Request request = new Request.Builder().get().url("/files").build();
        return handle(request, ListFilesResponse.class);
    }

    @Override
    public ListFineTuneEventsResponse listFineTuneEvents(String fineTuneId, Boolean stream) throws OpenaiException {
        Request request = new Request.Builder().get().url("/fine-tunes"+fineTuneId+"events"+"?stream="+stream).build();
        return handle(request, ListFineTuneEventsResponse.class);
    }

    /**
     * /fine-tunes
     */
    @Override
    public ListFineTunesResponse listFineTunes() throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.BASE_URL_V1 + "/fine-tunes").build();
        return handle(request, ListFineTunesResponse.class);
    }

    @Override
    public Engine retrieveEngine(String engineId) throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.BASE_URL_V1 + "/engines/"+engineId).build();
        return handle(request, Engine.class);
    }

    @Override
    public OpenAIFile retrieveFile(String fileId) throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.BASE_URL_V1 + "/files/"+fileId).build();
        return handle(request, OpenAIFile.class);
    }

    @Override
    public FineTune retrieveFineTune(String fineTuneId) throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.BASE_URL_V1 + "/fine-tune/"+fineTuneId).build();
        return handle(request, FineTune.class);
    }

    @Override
    public Model retrieveModel(String model) throws OpenaiException {
        Request request = new Request.Builder().url(OpenaiApiConstant.BASE_URL_V1 + "/model/"+model).build();
        return handle(request, Model.class);
    }

    protected <T> T handle(Request request, Class<T> tClass) throws OpenaiException {
        try {
            Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            if (!response.isSuccessful()) {
                String errorMsg = response.body().string();
                throw new OpenaiException(errorMsg);
            }
            String jsonString = response.body().string();
            System.out.println(jsonString);
            return JsonUtil.toObject(jsonString, tClass);
        } catch (IOException e) {
            throw new OpenaiException(e);
        }
    }

}
