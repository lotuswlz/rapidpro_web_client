package cathywu.rapidpro.webclient.models;

import cathywu.rapidpro.webclient.cache.UserCache;

/**
 * @author lzwu
 * @since 2/3/16
 */
public class Message {
    private String msgId;
    private String content;
    private String userId;
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

    public User getUser() {
        return UserCache.getInstance().get(userId);
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
}
