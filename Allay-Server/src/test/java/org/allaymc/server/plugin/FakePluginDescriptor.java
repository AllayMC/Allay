package org.allaymc.server.plugin;

import lombok.Getter;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.plugin.PluginDescriptor;

import java.util.List;
import java.util.Optional;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Getter
public class FakePluginDescriptor implements PluginDescriptor {

    protected String name;
    protected List<PluginDependency> dependencies;
    protected String version = "1.0.0";
    protected List<String> authors = List.of("daoge_cmd");

    public FakePluginDescriptor(String name, List<PluginDependency> dependencies) {
        this.name = name;
        this.dependencies = dependencies;
    }

    public FakePluginDescriptor(String name, PluginDependency... dependencies) {
        this.name = name;
        this.dependencies = List.of(dependencies);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getWebsite() {
        return Optional.empty();
    }
}
