package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xubitao on 12/25/15.
 */
public class ProvidersTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Mock
    private ProviderPersistence providerPersistence;

    @InjectMocks
    private Providers providers;

    private String name="foo";
    private String version="fee";

    @Test
    public void 添加一个新的Provider() throws SQLException {
        Provider providerModel = new Provider();
        providerModel.setName(name);
        providerModel.setVersion(version);

        Integer one = 1;
        when(providerPersistence.create(providerModel)).thenReturn(one);
        Integer actualResult = providers.create(providerModel);

        assertEquals(actualResult, one);
        verify(providerPersistence).create(providerModel);
    }

    @Test
    public void 根据ID获取一个Provider的信息() throws SQLException {
        Integer id = 0;
        Provider expectedProviderModel=new Provider();
        expectedProviderModel.setName(name);
        expectedProviderModel.setVersion(version);
        expectedProviderModel.setId(id);

        Provider foundProviderModel=new Provider();
        foundProviderModel.setName(name);
        foundProviderModel.setVersion(version);
        foundProviderModel.setId(id);

        when(providerPersistence.findById(id)).thenReturn(foundProviderModel);

        Provider actualResult = providers.findById(id);

        verify(providerPersistence).findById(id);
        assertEquals(actualResult, foundProviderModel);
    }
}