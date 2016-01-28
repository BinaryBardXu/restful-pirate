package cn.xubitao.pirate.domain.record.matcher;

import cn.xubitao.pirate.domain.contract.Contract;
import cn.xubitao.pirate.domain.record.Record;

/**
 * Created by xubitao on 2016/1/12.
 */
public interface Matcher {
    boolean match(Contract contract, Record record);
}
