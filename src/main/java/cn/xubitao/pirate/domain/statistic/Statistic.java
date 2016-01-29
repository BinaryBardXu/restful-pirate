package cn.xubitao.pirate.domain.statistic;

import cn.xubitao.dolphin.foundation.date.DateStyle;
import cn.xubitao.dolphin.foundation.date.DateUtil;
import cn.xubitao.dolphin.foundation.tools.Maps;
import cn.xubitao.pirate.domain.record.Record;
import cn.xubitao.pirate.persistence.statistic.StatisticPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 2016/1/15.
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
    private List<Map> records;
    private List<Map> hitRecords;
    private List<Map> missedRecords;

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

    public Statistic load() throws Exception {
        this.providerCount = statisticPersistence.countProviders();
        this.contractsCount = statisticPersistence.countContracts();
        this.recordsCount = statisticPersistence.countRecords(null);
        Record record = new Record();
        record.setIsHit(IS_HIT);
        this.hitRecordsCount = statisticPersistence.countRecords(record);
        record.setIsHit(MISSED);
        this.missedRecordsCount = statisticPersistence.countRecords(record);

        statisticTrend();
        return this;
    }

    private void statisticTrend() throws Exception {
        records = new ArrayList<Map>();
        hitRecords = new ArrayList<Map>();
        missedRecords = new ArrayList<Map>();
        for (int i = 30; i >= 0; i--) {
            Calendar currentDate = Calendar.getInstance();
            currentDate.add(Calendar.DAY_OF_YEAR, -i);
            Record record = new Record();
            record.setCreateTime(DateUtil.DateToString(currentDate.getTime(), DateStyle.YYYY_MM_DD));

            statisticAll(record);

            statisticHit(record);

            statisticMissed(record);
        }
    }

    private void statisticMissed(Record record) throws Exception {
        record.setIsHit(MISSED);
        Integer missedRecordsCount = statisticPersistence.countRecords(record);
        Map missedCountMap = Maps.build("date", record.getCreateTime(), "count", missedRecordsCount);
        missedRecords.add(missedCountMap);
    }

    private void statisticHit(Record record) throws Exception {
        record.setIsHit(IS_HIT);
        Integer hitCount = statisticPersistence.countRecords(record);
        Map hitCountMap = Maps.build("date", record.getCreateTime(), "count", hitCount);
        hitRecords.add(hitCountMap);
    }

    private void statisticAll(Record record) throws Exception {
        Integer recordsCount = statisticPersistence.countRecords(record);
        Map recordCountMap = Maps.build("date", record.getCreateTime(), "count", recordsCount);
        records.add(recordCountMap);
    }
}
