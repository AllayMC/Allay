package cn.allay.testutils;

import cn.allay.api.AllayAPI;
import cn.allay.server.Allay;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * @author daoge_cmd <br>
 * @date 2023/5/1 <br>
 * Allay Project <br>
 * <p>
 * This junit extension is used to prepare the test environment
 * TODO
 */
public class AllayTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        //Init allay api
        if (!AllayAPI.getInstance().isImplemented())
            Allay.initAllayAPI();
    }
}
