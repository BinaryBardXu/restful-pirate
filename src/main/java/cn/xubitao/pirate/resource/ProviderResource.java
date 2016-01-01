package cn.xubitao.pirate.resource;

import cn.xubitao.pirate.domain.ProviderEntities;
import cn.xubitao.pirate.domain.ProviderEntity;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/26/15.
 */
@Service
public class ProviderResource extends ResourceSupport {

    private String name;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
