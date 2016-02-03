package cathywu.rapidpro.webclient.controller;

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

        ReceiptSender sender = new ReceiptSender(id);
        new Thread(sender).start();
    }

    private Response reply(String userId, String content) {
        try {
            String url = Configurations.getInstance().getRapidProReceivedUrl();
            System.out.println("received url: " + url);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("from", userId);
            params.put("text", "Yes");

            return HttpClient.getInstance().send(url, params, RequestMethod.POST);
        } catch (IOException e) {
            return new Response(500, e.getMessage());
        }
    }

    private Response sendDeliveryFlag(String msgId) {
        try {
            String url = Configurations.getInstance().getRapidProDeliveryFlagUrl();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", msgId);
            return HttpClient.getInstance().send(url, params, RequestMethod.POST);
        } catch (IOException e) {
            return new Response(500, e.getMessage());
        }
    }
}
