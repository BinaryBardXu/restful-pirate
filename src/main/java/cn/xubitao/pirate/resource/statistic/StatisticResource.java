package cn.xubitao.pirate.resource.statistic;

import cn.xubitao.dolphin.foundation.resource.RestResource;

import java.util.List;

/**
 * Created by xubitao on 2016/1/15.
 */
public class StatisticResource extends RestResource {
    private long providersCount;
    private long contractsCount;
    private long recordsCount;
    private long hitRecordsCount;
    private long missedRecordsCount;
    private List<Integer> records;
    private List<Integer> hitRecords;
    private List<Integer> missedRecords;

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

    public List<Integer> getRecords() {
        return records;
    }

    public void setRecords(List<Integer> records) {
        this.records = records;
    }

    public List<Integer> getHitRecords() {
        return hitRecords;
    }

    public void setHitRecords(List<Integer> hitRecords) {
        this.hitRecords = hitRecords;
    }

    public List<Integer> getMissedRecords() {
        return missedRecords;
    }

    public void setMissedRecords(List<Integer> missedRecords) {
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
