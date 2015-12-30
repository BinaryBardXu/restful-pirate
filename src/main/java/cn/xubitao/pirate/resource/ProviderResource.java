package cn.xubitao.pirate.resource;

import cn.xubitao.pirate.domain.Provider;
import cn.xubitao.pirate.persistence.provider.ProviderModel;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by xubitao on 12/26/15.
 */
@Service
public class ProviderResource extends ResourceSupport {
    @Resource
    private Provider provider;

    private Integer id;
    private String name;
    private String version;

    public ProviderResource findById(Integer id) throws SQLException {
        ProviderModel providerModel = provider.findById(id);
        this.id = providerModel.getId();
        this.name = providerModel.getName();
        this.version = providerModel.getVersion();
        return this;
    }
}
