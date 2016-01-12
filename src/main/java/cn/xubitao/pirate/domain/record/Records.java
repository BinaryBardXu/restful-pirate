package cn.xubitao.pirate.domain.record;

import cn.xubitao.dolphin.foundation.date.DateUtil;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.contract.Contracts;
import cn.xubitao.pirate.domain.record.matcher.Matcher;
import cn.xubitao.pirate.persistence.record.RecordPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by xubitao on 12/25/15.
 */
@Service
public class Records {
    public static final int IS_HIT = 1;
    public static final int NOT_EXIST = -1;
    @Autowired
    private RecordPersistence recordPersistence;
    @Autowired
    private Contracts contracts;
    @Autowired
    private Matcher matcher;

    public List<Record> getRecords() {
        return Records;
    }

    public void setRecords(List<Record> Records) {
        this.Records = Records;
    }

    private List<Record> Records;

    public Record create(final Record record) throws Exception {
        List<Contract> contractList = contracts.loadByConsumerKey(record.getConsumerKey());
        record.setCreateTime(DateUtil.getNow());
        for (Contract contract : contractList) {
            if (!matcher.match(contract, record)) continue;
            record.setContractId(contract.getId());
            record.setIsHit(IS_HIT);
            return recordPersistence.create(record);
        }
        record.setContractId(NOT_EXIST);
        recordPersistence.create(record);
        return recordPersistence.create(record);
    }

    public Records loadAll(Integer contractId, Integer isHit, String consumerKey) throws SQLException {
        return recordPersistence.loadAll(contractId, isHit, consumerKey);
    }
}
