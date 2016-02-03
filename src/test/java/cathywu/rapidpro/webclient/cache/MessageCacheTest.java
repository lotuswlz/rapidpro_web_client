package cathywu.rapidpro.webclient.cache;

import cathywu.rapidpro.webclient.models.Direction;
import cathywu.rapidpro.webclient.models.Message;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author lzwu
 * @since 2/4/16
 */
public class MessageCacheTest {

    @Test
    public void should_save_message() throws Exception {
        Message message = new Message();
        message.setDirection(Direction.TO_USER);
        message.setPhoneNumber("+15863532222");
        message.setMsgId("25");
        message.setContent("Message");
        message.setChannelId(2);
        MessageCache.getInstance().saveMessage(message);

        assertEquals(1, MessageCache.getInstance().get("15863532222").size());
        assertEquals("+15863532222", MessageCache.getInstance().get("15863532222").get(0).getPhoneNumber());
        assertEquals("+15863532222", UserCache.getInstance().get("15863532222").getPhoneNumber());
    }
}
