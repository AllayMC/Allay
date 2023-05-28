package cn.allay.testutils;

import cn.allay.Allay;
import cn.allay.api.AllayAPI;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/1 <br>
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
