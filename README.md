## import dependencies

### maven

#### parent dependency

```xml

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.elsfs.openai</groupId>
            <artifactId>openai-java</artifactId>
            <version>${version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

#### project

```xml

<dependencies>
    <dependency>
        <groupId>org.elsfs.openai</groupId>
        <artifactId>openai-okhttp</artifactId>
    </dependency>
</dependencies>

```

### gradle

```groovy
implementation group: 'org.elsfs.openai', name: 'openai-okhttp', version: '${version}'
//or
implementation "org.elsfs.openai:openai-okhttp:${version}"
```

## usage method

### building services

```
       OkHttpClientOpenAIApiService service=OkHttpClientOpenAIApiService.builder("you-openai-key").build();
```

### create chat completion

```
        request.setModel("gpt-3.5-turbo-0301");
        List<CreateChatCompletionRequest.Message> maps = new ArrayList<>();
        CreateChatCompletionRequest.Message message = new CreateChatCompletionRequest.Message();
        message.setRole(ChatCompletionRequestMessageRoleEnum.user);
        message.setContent("Introduce China");
        maps.add(message);
        request.setMessages(maps);
         CreateChatCompletionResponse response = service.createChatCompletion(request);
```

### create completion

```
        CreateCompletionRequest request = new CreateCompletionRequest()
                .setModel("text-davinci-003")
                .setPrompt(Arrays.asList("Introduce China"))
                .setMax_tokens(2000);
        service.createCompletion(request);
       
```

### create moderation

```
CreateModerationRequest request = new CreateModerationRequest()
.setModel("text-moderation-stable")
.setInput(Arrays.asList("I want to kill them" ));
 service.createModeration(request);
```

### generate image

```
        service.createImage(new CreateImageRequest()
                .setPrompt("Chinese Palace Beauty ")
        );
```

### process pictures

```
    File file=new File("1.png");
    service.createImageEdit(file,"A cute baby sea otter wearing a beret");
    service.createImageVariation(file);
```
