package org.allaymc.server.registry.populator;

import org.allaymc.api.permission.PermissionGroups;

/**
 * @author daoge_cmd
 */
public class PermissionGroupRegistryPopulator implements Runnable {
    @Override
    public void run() {
        // Load PermissionGroups.class
        var $ = PermissionGroups.VISITOR;
    }
}
