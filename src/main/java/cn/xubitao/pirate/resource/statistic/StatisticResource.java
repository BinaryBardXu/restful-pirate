package cn.xubitao.pirate.resource.statistic;

import cn.xubitao.dolphin.foundation.resource.RestResource;

import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 2016/1/15.
 */
public class StatisticResource extends RestResource {
    private long providersCount;
    private long contractsCount;
    private long recordsCount;
    private long hitRecordsCount;
    private long missedRecordsCount;
    private List<Map> records;
    private List<Map> hitRecords;
    private List<Map> missedRecords;

    public long getProvidersCount() {
        return providersCount;
    }

    public void setProvidersCount(long providersCount) {
        this.providersCount = providersCount;
    }

    public long getContractsCount() {
        return contractsCount;
    }

    public void setContractsCount(long contractsCount) {
        this.contractsCount = contractsCount;
    }

    public List<Map> getRecords() {
        return records;
    }

    public void setRecords(List<Map> records) {
        this.records = records;
    }

    public List<Map> getHitRecords() {
        return hitRecords;
    }

    public void setHitRecords(List<Map> hitRecords) {
        this.hitRecords = hitRecords;
    }

    public List<Map> getMissedRecords() {
        return missedRecords;
    }

    public void setMissedRecords(List<Map> missedRecords) {
        this.missedRecords = missedRecords;
    }

    public long getRecordsCount() {
        return recordsCount;
    }

    public void setRecordsCount(long recordsCount) {
        this.recordsCount = recordsCount;
    }

    public long getHitRecordsCount() {
        return hitRecordsCount;
    }

    public void setHitRecordsCount(long hitRecordsCount) {
        this.hitRecordsCount = hitRecordsCount;
    }

    public long getMissedRecordsCount() {
        return missedRecordsCount;
    }

    public void setMissedRecordsCount(long missedRecordsCount) {
        this.missedRecordsCount = missedRecordsCount;
    }
}
