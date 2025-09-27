package org.allaymc.server.registry.populator;

import org.allaymc.api.permission.PermissionGroup;
import org.allaymc.api.permission.PermissionGroups;
import org.allaymc.server.AllayServer;

/**
 * @author daoge_cmd
 */
public class PermissionGroupRegistryPopulator implements Runnable {
    @Override
    public void run() {
        PermissionGroups.DEFAULT.set(PermissionGroup.get(AllayServer.getSettings().genericSettings().defaultPermission()));
    }
}
