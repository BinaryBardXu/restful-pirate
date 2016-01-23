package cn.xubitao.pirate.domain.provider;

import cn.xubitao.pirate.persistence.provider.ProviderPersistence;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
public class Provider {
    private ProviderPersistence providerPersistence;
    private Integer id;
    private String name;
    private String version;
    private String consumerKey;
    private Integer deleteStatus;
    private long contractsCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public long getContractsCount() {
        return contractsCount;
    }

    public void setContractsCount(Integer contractsCount) {
        this.contractsCount = contractsCount;
    }

    public void countContracts() throws SQLException {
        this.contractsCount = providerPersistence.countContract(id);
    }

    public void setProviderPersistence(ProviderPersistence providerPersistence) {
        this.providerPersistence = providerPersistence;
    }
}
