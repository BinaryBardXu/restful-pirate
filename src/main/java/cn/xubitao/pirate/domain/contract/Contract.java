package cn.xubitao.pirate.domain.contract;

import cn.xubitao.pirate.persistence.contract.ContractPersistence;

import java.sql.SQLException;

/**
 * Created by xubitao on 1/6/16.
 */
public class Contract {
    private ContractPersistence contractPersistence;

    private Integer id;

    private String request;

    private String response;

    private String name;

    private String server;

    private String description;

    private String excludeFields;

    private Integer deleteStatus;

    private Integer providerId;

    private long recordsCount;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getExcludeFields() {
        return excludeFields;
    }

    public void setExcludeFields(String excludeFields) {
        this.excludeFields = excludeFields;
    }

    public long getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(long recordsCount) {
        this.recordsCount = recordsCount;
    }

    public void setContractPersistence(ContractPersistence contractPersistence) {
        this.contractPersistence = contractPersistence;
    }

    public void countRecords() throws SQLException {
        this.recordsCount = contractPersistence.countRecords(id);
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }
}
