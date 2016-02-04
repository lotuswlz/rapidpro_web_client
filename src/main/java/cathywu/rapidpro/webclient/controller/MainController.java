package cathywu.rapidpro.webclient.controller;

import cathywu.rapidpro.webclient.common.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzwu
 * @since 2/3/16
 */
@Controller
public class MainController {

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
