package cn.xubitao.pirate.resource.record;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by xubitao on 1/1/16.
 */
public class RecordsResource extends RestResource {
    private List<ResourceSupport> records;

    public List<ResourceSupport> getContracts() {
        return records;
    }

    public void setRecords(List<ResourceSupport> records) {
        this.records = records;
    }
}
