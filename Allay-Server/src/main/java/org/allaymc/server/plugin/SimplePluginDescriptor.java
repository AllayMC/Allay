package org.allaymc.server.plugin;

import lombok.Getter;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.plugin.PluginDescriptor;

import java.util.Collections;
import java.util.List;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Getter
public class SimplePluginDescriptor implements PluginDescriptor {
    private String name;
    private String description;
    private String version;
    private List<String> authors;
    private List<PluginDependency> dependencies;
    private String website;

    public String getDescription() {
        if (description == null) description = "";
        return description;
    }

    public List<PluginDependency> getDependencies() {
        if (dependencies == null) dependencies = Collections.emptyList();
        return dependencies;
    }

    public String getWebsite() {
        if (website == null) website = "";
        return website;
    }
}
