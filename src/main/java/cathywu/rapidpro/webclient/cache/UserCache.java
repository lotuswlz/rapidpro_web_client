package cathywu.rapidpro.webclient.cache;

import cathywu.rapidpro.webclient.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzwu
 * @since 2/3/16
 */
public class UserCache {

    private static UserCache instance;

    private Map<String, User> userMap;

    private UserCache() {
        userMap = new HashMap<String, User>();
    }

    public static UserCache getInstance() {
        if (instance == null) {
            instance = new UserCache();
        }
        return instance;
    }

    public User get(String userId) {
        return userMap.get(userId);
    }

    public User addUser(String phoneNumber) {
        return addUser(phoneNumber, null);
    }

    public User addUser(String phoneNumber, String name) {
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        if (!userMap.containsKey(user.getUserId())) {
            user.setName(name);
            userMap.put(user.getUserId(), user);
        }
        return userMap.get(user.getUserId());
    }
}
