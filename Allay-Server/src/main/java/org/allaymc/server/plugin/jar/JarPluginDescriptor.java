package org.allaymc.server.plugin.jar;

import lombok.Getter;
import org.allaymc.api.plugin.PluginDescriptor;
import org.allaymc.server.plugin.SimplePluginDescriptor;
import org.cloudburstmc.protocol.common.util.Preconditions;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Getter
public class JarPluginDescriptor extends SimplePluginDescriptor {
    // Main class
    private String main;

    public static void checkDescriptorValid(PluginDescriptor descriptor) {
        PluginDescriptor.checkDescriptorValid(descriptor);
        Preconditions.checkNotNull(((JarPluginDescriptor) descriptor).getMain(), "Jar plugin main class cannot be null");
    }
}
