package cn.xubitao.pirate.resource;

import cn.xubitao.pirate.domain.Provider;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by xubitao on 1/1/16.
 */
public class ProvidersResource extends ResourceSupport {
    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    private List<Provider> providers;
}
