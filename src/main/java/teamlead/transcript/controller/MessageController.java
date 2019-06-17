package teamlead.transcript.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import teamlead.transcript.domain.Message;
import teamlead.transcript.domain.Views;
import teamlead.transcript.exceptions.NotFoundException;
import teamlead.transcript.repo.MessageRepo;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepo messageRepo;

    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;

    }

    @PostMapping
    public Message create(@RequestBody Message message) {

       messageRepo.save(message);

        return message;
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {

        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Message message) {
        messageRepo.delete(message);
    }
}
