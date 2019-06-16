package teamlead.transcript.controller;

import org.springframework.web.bind.annotation.*;
import teamlead.transcript.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private int counter = 0;

    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String ,String>() {{put("id", "1"); put("text", "FirstText"); }});
        add(new HashMap<String ,String>() {{put("id", "2"); put("text", "SecondText"); }});
        add(new HashMap<String ,String>() {{put("id", "3"); put("text", "ThirdText"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);

    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromBd = getMessage(message.get(id));
        messageFromBd.putAll(message);
        messageFromBd.put("id", id);

        return messageFromBd;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }
}
