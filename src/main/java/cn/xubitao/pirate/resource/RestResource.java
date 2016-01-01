package cn.xubitao.pirate.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by xubitao on 1/1/16.
 */
public class RestResource extends ResourceSupport {

    public static RestResource link(Link link) {
        RestResource resource = new RestResource();
        resource.add(link);
        return resource;
    }
}
