package cathywu.rapidpro.webclient.models;

import cathywu.rapidpro.webclient.cache.UserCache;
import cathywu.rapidpro.webclient.common.Configurations;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String phoneNumber;
    @JsonProperty("channel")
    private int channelId;
    private Direction direction;
    private Date datetime;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDirection(int direction) {
        this.direction = Direction.fromValue(direction);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public User getUser() {
        return UserCache.getInstance().get(getUserId());
    }

    public String getUserId() {
        if (phoneNumber != null && phoneNumber.length() > 11) {
            return phoneNumber.substring(phoneNumber.length() - 11);
        }
        return phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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
                ", phoneNumber='" + phoneNumber + '\'' +
                ", channelId=" + channelId +
                ", direction=" + direction +
                '}';
    }

    public Date getDatetime() {
        return datetime;
    }

    public String getDatetimeStr() {
        if (this.datetime == null) {
            return "";
        }
        return SIMPLE_DATE_FORMAT.format(this.datetime);
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public static Message createMessageToUser(String userId, String msgId, String content) {
        Message message = new Message();
        message.setChannelId(Configurations.getInstance().getChannel().getId());
        message.setContent(content);
        message.setMsgId(msgId);
        message.setPhoneNumber(userId);
        message.setDirection(Direction.TO_USER);
        message.setDatetime(new Date());
        return message;
    }

    public static Message createMessageFromUser(String userId, String msgId, String content) {
        Message message = new Message();
        message.setChannelId(Configurations.getInstance().getChannel().getId());
        message.setContent(content);
        message.setMsgId(msgId);
        message.setPhoneNumber(userId);
        message.setDirection(Direction.FROM_USER);
        message.setDatetime(new Date());
        return message;
    }
}
