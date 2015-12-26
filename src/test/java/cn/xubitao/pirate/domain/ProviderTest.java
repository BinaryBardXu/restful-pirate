package cn.xubitao.pirate.domain;

import cn.xubitao.pirate.persistence.provider.ProviderPersistence;
import cn.xubitao.pirate.persistence.provider.ProviderModel;
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
public class ProviderTest {

    @Rule
    public MockitoRule rule=MockitoJUnit.rule();
    @Mock
    private ProviderPersistence providerPersistence;

    @InjectMocks
    private Provider provider;

    @Test
    public void 添加一个新的Provider() throws SQLException {
        String name = "foo";
        String version = "fee";
        ProviderModel providerModel = new ProviderModel();
        providerModel.setName(name);
        providerModel.setVersion(version);

        Integer one = new Integer(1);
        when(providerPersistence.create(providerModel)).thenReturn(one);
        Integer actualResult = provider.create(providerModel);

        assertEquals(actualResult, one);
        verify(providerPersistence).create(providerModel);
    }
}