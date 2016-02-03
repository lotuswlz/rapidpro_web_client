package cathywu.rapidpro.webclient.models;

import cathywu.rapidpro.webclient.cache.MessageCache;

import java.util.List;

/**
 * @author lzwu
 * @since 2/3/16
 */
public class User {
    private String userId;
    private String phoneNumber;
    private String name;

    public String getUserId() {
        if (userId == null && phoneNumber != null) {
            if (phoneNumber.length() > 11) {
                return phoneNumber.substring(phoneNumber.length() - 11);
            }
            return phoneNumber;
        }
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return MessageCache.getInstance().get(userId);
    }
}
