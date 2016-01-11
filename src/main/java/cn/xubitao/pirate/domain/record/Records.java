package cn.xubitao.pirate.domain.record;

import cn.xubitao.dolphin.foundation.comparator.People;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.contract.Contracts;
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
    public static final int IS_NOT_HIT = 0;
    public static final int NOT_EXIST = -1;
    @Autowired
    private RecordPersistence recordPersistence;
    @Autowired
    private Contracts contracts;

    public List<Record> getRecords() {
        return Records;
    }

    public void setRecords(List<Record> Records) {
        this.Records = Records;
    }

    private List<Record> Records;

    public Record create(final Record record) throws Exception {
        List<Contract> contractList = contracts.loadByConsumerKey(record.getConsumerKey());
        for (Contract contract : contractList) {
            if (!contract.getUrl().equals(record.getUrl())) continue;
            if (!People.compare(contract.getRequest(), record.getRequest())) continue;
            if (!People.compare(contract.getResponse(), record.getResponse())) continue;
            record.setContractId(contract.getId());
            record.setIsHit(IS_HIT);
            return recordPersistence.create(record);
        }
        record.setContractId(NOT_EXIST);
        return recordPersistence.create(record);
    }

    public cn.xubitao.pirate.domain.record.Records loadAll(Integer contractId) throws SQLException {
        return recordPersistence.loadAll(contractId);
    }
}
