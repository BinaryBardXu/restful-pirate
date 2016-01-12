package cn.xubitao.pirate.domain.record.matcher;

import cn.xubitao.dolphin.foundation.comparator.People;
import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.record.Record;
import org.springframework.stereotype.Service;

/**
 * Created by 15031046 on 2016/1/12.
 */
@Service
public class PirateMatcher implements Matcher {
    public boolean match(Contract contract, Record record) {
        if (!contract.getUrl().equals(record.getUrl())) return false;
        if (!People.compare(contract.getRequest(), record.getRequest())) return false;
        return People.compare(contract.getResponse(), record.getResponse());
    }
}
