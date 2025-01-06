package org.allaymc.server.plugin;

import lombok.Getter;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.plugin.PluginDescriptor;

import java.util.Collections;
import java.util.List;

/**
 * @author daoge_cmd
 */
@Getter
public class SimplePluginDescriptor implements PluginDescriptor {
    private String entrance;
    private String name;
    private String version;
    private List<String> authors;
    // Optional
    private String description = "";
    // Optional
    private List<PluginDependency> dependencies = Collections.emptyList();
    // Optional
    private String website = "";
}
