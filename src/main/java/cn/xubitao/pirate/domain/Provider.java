package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by xubitao on 12/25/15.
 */
@DatabaseTable(tableName = "provider")
@Service
public class Provider {
    // 主键 id 自增长
    @DatabaseField(generatedId = true)
    private Integer id;
    // 映射
    @DatabaseField(canBeNull = false)
    private String name;
    // 不为空
    @DatabaseField(canBeNull = false)

    private String version;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}