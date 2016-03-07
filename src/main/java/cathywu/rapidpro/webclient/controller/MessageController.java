package cathywu.rapidpro.webclient.controller;

import cathywu.rapidpro.webclient.cache.MessageCache;
import cathywu.rapidpro.webclient.common.Configurations;
import cathywu.rapidpro.webclient.common.HttpClient;
import cathywu.rapidpro.webclient.common.Response;
import cathywu.rapidpro.webclient.models.Message;
import cathywu.rapidpro.webclient.receiptsender.ReceiptSender;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public String reply(@RequestBody Map<String, Object> params) {
        String url = Configurations.getInstance().getRapidProReceivedUrl();
        String phoneNumber = (String) params.get("from");
        String content = (String) params.get("text");

        try {
            Response response = HttpClient.getInstance().send(url, params, RequestMethod.POST);
            String responseMsg = response.getMessage();
            String msgId = responseMsg.replaceAll("^SMS Accepted: (\\d+)]$", "$1");
            Message message = Message.createMessageFromUser(phoneNumber, msgId, content);
            MessageCache.getInstance().saveMessage(message);
            return "[Response] code: " + response.getResponseCode() + ", message: " + response.getMessage();
        } catch (IOException e) {
            System.out.println("[Error] " + e.getMessage());
            return e.getMessage();
        }

        //TODO: while sending Chinese words to rapid pro, it will raise an exception,
        //TODO: but something weird is the message has been sent to rapid pro and could be display on its record page.
    }

    @RequestMapping("/messages")
    public List<Message> list(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId != null && !userId.trim().equals("")) {
            return MessageCache.getInstance().get(userId);
        }
        return new ArrayList<Message>();
    }

    @RequestMapping("/allMsgs")
    public Map<String, List<Message>> allMessages() {
        return MessageCache.getInstance().getUserMessageMap();
    }

    @RequestMapping(value = "/messages/{userId}", method = RequestMethod.DELETE)
    public void clearMessage(@PathVariable String userId) {
        userId = getPhoneNumber(userId);
        System.out.println("clear cache for " + userId);
        MessageCache.getInstance().clear(userId);
    }

    private String getPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.length() > 11) {
            return phoneNumber.substring(phoneNumber.length() - 11);
        }
        return phoneNumber;
    }

}
