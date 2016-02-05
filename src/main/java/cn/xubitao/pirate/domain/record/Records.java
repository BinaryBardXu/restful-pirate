package cn.xubitao.pirate.domain.record;

import cn.xubitao.dolphin.foundation.date.DateStyle;
import cn.xubitao.dolphin.foundation.date.DateUtil;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.contract.Contracts;
import cn.xubitao.pirate.domain.record.matcher.Matcher;
import cn.xubitao.pirate.persistence.record.RecordsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Records {
    public static final int IS_HIT = 1;
    public static final int NOT_EXIST = -1;
    private static final Integer MISSED = 0;
    @Autowired
    private RecordsPersistence recordsPersistence;
    @Autowired
    private Contracts contracts;
    @Resource(name = "pirateMatcher")
    private Matcher matcher;
    private List<Record> Records;

    public List<Record> getRecords() {
        return Records;
    }

    public void setRecords(List<Record> Records) {
        this.Records = Records;
    }

    public Record create(final Record record) throws Exception {
        List<Contract> contractList = contracts.loadByName(record.getName());
        record.setCreateTime(DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS));
        boolean isMatched = false;
        for (Contract contract : contractList) {
            if (!matcher.match(contract, record)) continue;
            record.setContractId(contract.getId());
            record.setResponse(contract.getResponse());
            record.setIsHit(IS_HIT);
            isMatched = true;
        }
        if (!isMatched) {
            record.setIsHit(MISSED);
            record.setContractId(NOT_EXIST);
        }
        recordsPersistence.create(record);
        return recordsPersistence.findById(record.getId());
    }

    public Records loadAll(Map conditions) throws SQLException {
        List<Record> recordList = recordsPersistence.loadAll(conditions);
        Records records = new Records();
        records.setRecords(recordList);
        return records;
    }
}
