package cn.xubitao.pirate.resource.provider;

import cn.xubitao.dolphin.foundation.resource.RestResource;

/**
 * Created by xubitao on 12/26/15.
 */
public class ProviderResource extends RestResource {

    private String name;

    private String version;

    private long contractsCount;

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

    public long getContractsCount() {
        return contractsCount;
    }

    public void setContractsCount(long contractsCount) {
        this.contractsCount = contractsCount;
    }
}
