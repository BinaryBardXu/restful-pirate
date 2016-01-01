package cn.xubitao.pirate.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by xubitao on 1/1/16.
 */
public class BlankResource extends ResourceSupport {

    public static BlankResource link(Link link) {
        BlankResource blankResource = new BlankResource();
        blankResource.add(link);
        return blankResource;
    }

}
