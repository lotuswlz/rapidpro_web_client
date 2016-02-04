package cathywu.rapidpro.webclient.controller;

import cathywu.rapidpro.webclient.cache.MessageCache;
import cathywu.rapidpro.webclient.cache.UserCache;
import cathywu.rapidpro.webclient.common.Configurations;
import cathywu.rapidpro.webclient.models.Message;
import cathywu.rapidpro.webclient.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lzwu
 * @since 2/3/16
 */
@Controller
public class WebController {

    @Autowired
    private Configurations configurations;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request) {
        request.setAttribute("message", "Account register successfully");
        return "success";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSubmit(@RequestParam("mobile") String phoneNumber, HttpServletRequest request) {
        User user = UserCache.getInstance().addUser(phoneNumber);
        request.getSession().setAttribute("userId", user.getUserId());
        request.getSession().setAttribute("phoneNumber", user.getPhoneNumber());
        return "redirect:chat";
    }

    @RequestMapping("/chat")
    public String chat() {
        return "chat";
    }

    @RequestMapping("/show_messages")
    public String showMessages(HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId != null && !userId.trim().equals("")) {
            List<Message> messages = MessageCache.getInstance().get(userId);
            request.getSession().setAttribute("messages", messages);
        }
        return "messages";
    }

    @RequestMapping("/settings")
    public String settings(HttpServletRequest request) {
        Configurations.Channel channel = configurations.getChannel();
        if (channel != null) {
            request.setAttribute("channelId", channel.getId());
            request.setAttribute("channelUUID", channel.getUuid());
            request.setAttribute("channelName", channel.getName());
        }
        request.setAttribute("rapidProUrl", configurations.getRapidProUrl());
        request.setAttribute("rapidProReceivedUrl", configurations.getRapidProReceivedUrl());
        return "settings";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String saveSettings(@RequestParam("channelId") int channelId, @RequestParam("channelUUID") String channelUUID, @RequestParam("channelName") String channelName, @RequestParam("rapidProUrl") String rapidProUrl) {
        configurations.setChannel(channelId, channelUUID, channelName);
        configurations.setRapidProUrl(rapidProUrl);
        return "redirect:settings";
    }
}
