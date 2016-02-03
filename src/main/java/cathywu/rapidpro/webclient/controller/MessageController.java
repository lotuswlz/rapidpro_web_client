package cathywu.rapidpro.webclient.controller;

import cathywu.rapidpro.webclient.cache.MessageCache;
import cathywu.rapidpro.webclient.common.Configurations;
import cathywu.rapidpro.webclient.common.HttpClient;
import cathywu.rapidpro.webclient.common.Response;
import cathywu.rapidpro.webclient.models.Message;
import cathywu.rapidpro.webclient.receiptsender.ReceiptSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzwu
 * @since 2/3/16
 */
@RestController
public class MessageController {

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public void send(@RequestParam("text") String text, @RequestParam("to") String to, @RequestParam("id") String id, @RequestParam("channel") Integer channel) {
        Message message = Message.createMessageToUser(to, id, text);
        System.out.println("[Message Received] " + message.toString());

        MessageCache.getInstance().saveMessage(message);

        ReceiptSender sender = new ReceiptSender(id);
        new Thread(sender).start();
    }

    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public String reply(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("content") String content) {
        String url = Configurations.getInstance().getRapidProReceivedUrl();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("from", phoneNumber);
        params.put("text", content);

        try {
            Response response = HttpClient.getInstance().send(url, params, RequestMethod.POST);
            System.out.println("[Response] code: " + response.getResponseCode() + ", message: " + response.getMessage());
            return "[Response] code: " + response.getResponseCode() + ", message: " + response.getMessage();
        } catch (IOException e) {
            System.out.println("[Error] " + e.getMessage());
            return e.getMessage();
        }
    }

}
