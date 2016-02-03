package cathywu.rapidpro.webclient.cache;

import cathywu.rapidpro.webclient.models.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzwu
 * @since 2/3/16
 */
public class MessageCache {

    private static MessageCache instance;

    private Map<String, List<Message>> userMessageMap;

    private MessageCache() {
        userMessageMap = new HashMap<String, List<Message>>();
    }

    public static MessageCache getInstance() {
        if (instance == null) {
            instance = new MessageCache();
        }
        return instance;
    }

    public List<Message> get(String userId) {
        return userMessageMap.get(userId);
    }

    public void saveMessage(Message message) {
        String userId = message.getUser().getUserId();
        List<Message> messages = userMessageMap.get(userId);
        if (messages == null) {
            messages = new ArrayList<Message>();
        }
        messages.add(message);
        userMessageMap.put(userId, messages);
    }
}
