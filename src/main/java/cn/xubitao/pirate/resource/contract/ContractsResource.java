package cn.xubitao.pirate.resource.contract;

import cn.xubitao.dolphin.foundation.resource.RestResource;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by xubitao on 1/1/16.
 */
public class ContractsResource extends RestResource {
    private List<ResourceSupport> contracts;

    public List<ResourceSupport> getContracts() {
        return contracts;
    }

    public void setContracts(List<ResourceSupport> contracts) {
        this.contracts = contracts;
    }
}