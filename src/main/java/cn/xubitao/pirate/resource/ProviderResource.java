package cn.xubitao.pirate.resource;

/**
 * Created by xubitao on 12/26/15.
 */
public class ProviderResource extends RestResource {

    private String name;


    private String version;


    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }
}
