package cathywu.rapidpro.webclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzwu
 * @since 2/3/16
 */
@Controller
public class MainController {

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
}
