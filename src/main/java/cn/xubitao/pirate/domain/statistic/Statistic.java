package cn.xubitao.pirate.domain.statistic;

import cn.xubitao.pirate.persistence.statistic.StatisticPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 15031046 on 2016/1/15.
 */
@Service
public class Statistic {
    public static final int IS_HIT = 1;
    public static final int MISSED = 0;
    @Autowired
    private StatisticPersistence statisticPersistence;
    private long providerCount;
    private long contractsCount;
    private long recordsCount;
    private long hitRecordsCount;
    private long missedRecordsCount;
    private List<Integer> records;
    private List<Integer> hitRecords;
    private List<Integer> missedRecords;

    public long getProviderCount() {
        return providerCount;
    }

    public void setProviderCount(Integer providerCount) {
        this.providerCount = providerCount;
    }

    public long getContractsCount() {
        return contractsCount;
    }

    public void setContractsCount(Integer contractsCount) {
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

    public void setRecordsCount(Integer recordsCount) {
        this.recordsCount = recordsCount;
    }

    public long getHitRecordsCount() {
        return hitRecordsCount;
    }

    public void setHitRecordsCount(Integer hitRecordsCount) {
        this.hitRecordsCount = hitRecordsCount;
    }

    public long getMissedRecordsCount() {
        return missedRecordsCount;
    }

    public void setMissedRecordsCount(Integer missedRecordsCount) {
        this.missedRecordsCount = missedRecordsCount;
    }

    public Statistic load() {
        this.providerCount = statisticPersistence.countProviders();
        this.contractsCount = statisticPersistence.countContracts();
        this.recordsCount = statisticPersistence.countRecords(null);
        this.hitRecordsCount = statisticPersistence.countRecords(IS_HIT);
        this.missedRecordsCount = statisticPersistence.countRecords(MISSED);
        return this;
    }
}
