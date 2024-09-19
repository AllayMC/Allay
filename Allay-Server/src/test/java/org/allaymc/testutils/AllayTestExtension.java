package org.allaymc.testutils;

import org.allaymc.api.AllayAPI;
import org.allaymc.server.Allay;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * AllayTestExtension is used to prepare the test environment.
 *
 * @author daoge_cmd
 */
public class AllayTestExtension implements BeforeAllCallback {
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (!AllayAPI.getInstance().isImplemented()) {
            Allay.initI18n();
            Allay.initAllay();
        }
    }
}
