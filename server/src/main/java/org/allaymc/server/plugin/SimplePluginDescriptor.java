package org.allaymc.server.plugin;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.plugin.PluginDescriptor;

import java.util.Collections;
import java.util.List;

/**
 * {@code SimplePluginDescriptor} stores deserialized plugin metadata for the
 * JAR loader's validation step.
 * {@code entrance}, {@code name}, {@code version}, and {@code authors} remain
 * {@code null} until populated, while omitted optional members
 * retain empty defaults. Generated getters expose the stored lists directly;
 * this descriptor does not independently validate values or copy collections.
 *
 * @author daoge_cmd
 */
@Getter
public class SimplePluginDescriptor implements PluginDescriptor {
    /** Fully qualified entry class name. */
    private String entrance;
    /** Manager lookup key. */
    private String name;
    /** Declared plugin version. */
    private String version;
    /**
     * {@code authors} retains author names from JSON. The generated getter
     * exposes the stored list directly without copying its contents.
     */
    private List<String> authors;
    /**
     * {@code apiVersion} supplies the accepted API version range. Omission
     * retains a blank value that skips the manager's compatibility check, while
     * explicit JSON {@code null} fails validation.
     */
    @SerializedName("api_version")
    @Getter(AccessLevel.NONE)
    private String apiVersion = "";
    /**
     * {@code description} supplies optional descriptive text. Omission from the
     * JSON descriptor retains the blank string initialized for this member.
     */
    private String description = "";
    /**
     * {@code dependencies} exposes declarations directly to consumers.
     * Omission retains an immutable empty list, while parsed lists are not
     * defensively copied.
     */
    private List<PluginDependency> dependencies = Collections.emptyList();
    /**
     * {@code website} supplies optional website text. Omission from the JSON
     * descriptor retains the blank string initialized for this member.
     */
    private String website = "";

    /**
     * Returns the stored API range used by manager compatibility checks.
     * Omitted JSON metadata retains a blank value that skips checking; explicit
     * {@code null} is rejected later by descriptor validation.
     */
    @Override
    public String getAPIVersion() {
        return apiVersion;
    }
}
