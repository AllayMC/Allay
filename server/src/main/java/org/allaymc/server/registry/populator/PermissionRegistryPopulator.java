package org.allaymc.server.registry.populator;

import org.allaymc.api.permission.Permissions;

/**
 * @author daoge_cmd
 */
public class PermissionRegistryPopulator implements Runnable {
    @Override
    public void run() {
        // Load Permissions.class
        var $ = Permissions.ABILITY_ATTACK_MOBS;
    }
}
