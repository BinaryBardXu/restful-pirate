package cn.xubitao.pirate.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

/**
 * Created by xubitao on 12/26/15.
 */
@Service
public class ProviderResource extends ResourceSupport {

    private String name;
    private String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
