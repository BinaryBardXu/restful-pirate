package cn.xubitao.pirate.domain.record;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by xubitao on 12/25/15.
 */
@DatabaseTable(tableName = "record")
public class Record {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(canBeNull = false)
    private String server;
    @DatabaseField(canBeNull = false)
    private String request;
    @DatabaseField
    private String response;
    @DatabaseField(canBeNull = false)
    private String consumerKey;
    @DatabaseField(canBeNull = false)
    private Integer contractId;
    @DatabaseField(canBeNull = false, defaultValue = "0")
    private Integer isHit;
    @DatabaseField(defaultValue = "0")
    private Integer deleteStatus;
    @DatabaseField
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getIsHit() {
        return isHit;
    }

    public void setIsHit(Integer isHit) {
        this.isHit = isHit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}