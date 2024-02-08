package org.allaymc.server.plugin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.plugin.PluginDescriptor;

import java.util.List;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@Accessors(fluent = true)
public class SimplePluginDescriptor implements PluginDescriptor {
    private String name;
    private String description;
    private String version;
    private List<String> authors;
    private List<PluginDependency> dependencies;
    private String website;
}
