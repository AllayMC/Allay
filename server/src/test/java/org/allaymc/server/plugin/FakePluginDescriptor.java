package org.allaymc.server.plugin;

import lombok.Getter;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.plugin.PluginDescriptor;

import java.util.List;

/**
 * {@code FakePluginDescriptor} supplies controlled plugin metadata for dependency
 * graph and version tests.
 *
 * @author daoge_cmd
 */
@Getter
public class FakePluginDescriptor implements PluginDescriptor {

    /** Descriptor lookup name. */
    protected String name;
    /**
     * {@code dependencies} exposes declarations directly through the generated
     * getter. The list constructor retains its input, while the varargs
     * constructor creates an immutable list.
     */
    protected List<PluginDependency> dependencies;
    /** Installed plugin version. */
    protected String version;
    /** Fixed fixture author list. */
    protected List<String> authors = List.of("daoge_cmd");

    /**
     * Creates metadata retaining the supplied dependency list without copying.
     * Mutations through the original list remain visible to manager checks;
     * this fixture performs no validation of names or version syntax.
     */
    public FakePluginDescriptor(String name, String version, List<PluginDependency> dependencies) {
        this.name = name;
        this.version = version;
        this.dependencies = dependencies;
    }

    /**
     * Creates metadata with an immutable list of the supplied dependency elements.
     * {@code null} arrays and {@code null} elements are rejected by
     * {@code List.of}, while names and
     * version strings remain unvalidated fixture inputs.
     */
    public FakePluginDescriptor(String name, String version, PluginDependency... dependencies) {
        this.name = name;
        this.version = version;
        this.dependencies = List.of(dependencies);
    }

    /**
     * Returns a blank entry name because tests never instantiate this plugin.
     * This fixture exercises graph and version helpers only, so it does not
     * identify a class suitable for actual plugin loading.
     */
    @Override
    public String getEntrance() {
        return "";
    }

    /**
     * Returns blank description text for this dependency-only test descriptor.
     * The empty string follows the non-null optional-text convention without
     * requiring graph and version tests to provide display metadata.
     */
    @Override
    public String getDescription() {
        return "";
    }

    /**
     * Returns a blank API range so this fixture imposes no API constraint.
     * A manager discovery check would skip API compatibility filtering for this
     * value, independently of dependency version requirements.
     */
    @Override
    public String getAPIVersion() {
        return "";
    }

    /**
     * Returns blank website text for this dependency-only test descriptor.
     * The empty string represents omitted optional metadata while still meeting
     * the non-null value expected by descriptor validation.
     */
    @Override
    public String getWebsite() {
        return "";
    }
}
