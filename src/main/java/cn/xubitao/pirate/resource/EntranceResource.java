package cn.xubitao.pirate.resource;

import cn.xubitao.dolphin.foundation.resource.RestResource;

/**
 * Created by 15031046 on 2016/1/15.
 */
public class EntranceResource extends RestResource {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
