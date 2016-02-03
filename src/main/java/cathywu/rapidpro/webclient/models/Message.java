package cathywu.rapidpro.webclient.models;

import cathywu.rapidpro.webclient.cache.UserCache;
import cathywu.rapidpro.webclient.common.Configurations;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lzwu
 * @since 2/3/16
 */
public class Message {
    @JsonProperty("id")
    private String msgId;
    @JsonProperty("text")
    private String content;
    @JsonProperty("to")
    private String userId;
    @JsonProperty("channel")
    private int channelId;
    private Direction direction;

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDirection(int direction) {
        this.direction = Direction.fromValue(direction);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public User getUser() {
        return UserCache.getInstance().get(userId);
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getContent() {
        return content;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", channelId=" + channelId +
                ", direction=" + direction +
                '}';
    }

    public static Message createMessageToUser(String userId, String msgId, String content) {
        Message message = new Message();
        message.setChannelId(Configurations.getInstance().getChannel().getId());
        message.setContent(content);
        message.setMsgId(msgId);
        message.setUserId(userId);
        message.setDirection(Direction.TO_USER);
        return message;
    }
}
