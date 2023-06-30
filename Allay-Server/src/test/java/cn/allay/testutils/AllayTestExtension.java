package cn.allay.testutils;

import cn.allay.api.AllayAPI;
import cn.allay.server.Allay;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * This junit extension is used to prepare the test environment
 * <p>
 * Allay Project 2023/5/1
 *
 * @author daoge_cmd
 */
public class AllayTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        //Init allay api
        if (!AllayAPI.getInstance().isImplemented())
            Allay.initAllayAPI();
    }
}
