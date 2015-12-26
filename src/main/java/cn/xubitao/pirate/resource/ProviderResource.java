package cn.xubitao.pirate.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xubitao on 12/26/15.
 */
@Controller
public class ProviderResource {
    @ResponseBody
    @RequestMapping(value = "/p",method = RequestMethod.GET)
    public String showAll() {
        return "Hello";
    }
}
