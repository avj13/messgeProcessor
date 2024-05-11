package org.example;


//@RestController
public class MesageController {

    private final MessageProcessorFramework messageProcessorFramework;

//    @Autowired
    public MesageController(MessageProcessorFramework messageProcessorFramework) {
        this.messageProcessorFramework = messageProcessorFramework;
    }

//    @PostMapping("/produceMessage")
//    public void produceMessage(@RequestBody MessageRequest request) {
//        messageProcessorFramework.produceMessage(request.getInput(), request.getProcessingTime());
//    }

}
