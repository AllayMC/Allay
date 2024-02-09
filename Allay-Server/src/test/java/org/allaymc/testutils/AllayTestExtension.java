package org.allaymc.testutils;

import org.allaymc.api.AllayAPI;
import org.allaymc.server.Allay;
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
        if (!AllayAPI.getInstance().isImplemented()) Allay.initAllayAPI();
    }
}
