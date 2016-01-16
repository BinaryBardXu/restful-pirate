package cn.xubitao.pirate.controller;

import cn.xubitao.dolphin.foundation.response.Response;
import cn.xubitao.pirate.assmbler.EntranceResourceAssembler;
import cn.xubitao.pirate.resource.EntranceResource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xubitao on 12/27/15.
 */
@RestController
@RequestMapping(value = "/entrance")
public class EntranceController {
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<ResourceSupport> enter() throws Exception {
        return Response.build(new EntranceResource(), new EntranceResourceAssembler());
    }
}
