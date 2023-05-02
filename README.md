```java
       OkHttpClientOpenAIApiService service = OkHttpClientOpenAIApiService.builder("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").build();

//        CreateChatCompletionRequest request = new CreateChatCompletionRequest();
//        request.setModel("gpt-3.5-turbo-0301");
//        List<CreateChatCompletionRequest.Message> maps = new ArrayList<>();
//        CreateChatCompletionRequest.Message message = new CreateChatCompletionRequest.Message();
//        message.setRole(ChatCompletionRequestMessageRoleEnum.user);
//        message.setContent("介绍一下中国");
//        maps.add(message);
//        request.setMessages(maps);
//         CreateChatCompletionResponse response = service.createChatCompletion(request);

//        CreateCompletionRequest request = new CreateCompletionRequest()
//                .setModel("text-davinci-003")
//                .setPrompt(Arrays.asList("介绍一下中国"))
//                .setMax_tokens(2000);
//        service.createCompletion(request);
//        CreateModerationRequest request = new CreateModerationRequest()
//                .setModel("text-moderation-stable")
//                        .setInput(Arrays.asList("我想杀了他们" ));
//
//        service.createModeration(request);
       // service.listFineTunes();
//        service.createImage(new CreateImageRequest()
//                .setPrompt("中国宫廷美女")
//        );
        File file = new File("C:\\Users\\zeng\\Pictures\\1\\1.png");

//        service.createImageEdit(file,"A cute baby sea otter wearing a beret");

        service.createImageVariation(file);
```