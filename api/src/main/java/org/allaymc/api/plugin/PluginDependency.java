package org.allaymc.api.plugin;

/**
 * {@code PluginDependency} describes another plugin required or preferred by a
 * plugin descriptor.
 * Both required and optional dependencies influence loading order. Optionality
 * permits absence, but an installed dependency with an incompatible requested
 * version still prevents the depending plugin from loading.
 *
 * @param name exact dependency plugin name
 * @param version accepted semantic version range. {@code null} accepts every
 *                installed version without checking its compatibility
 * @param optional missing-dependency policy. {@code true} allows absence, while
 *                 an available dependency still participates in version checking
 *                 and ordering
 * @author daoge_cmd
 */
public record PluginDependency(String name, String version, boolean optional) {
}
