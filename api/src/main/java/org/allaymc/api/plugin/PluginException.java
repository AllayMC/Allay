package org.allaymc.api.plugin;

import lombok.experimental.StandardException;

/**
 * {@code PluginException} reports failures while locating, constructing, or
 * loading a plugin.
 * Loaders use this unchecked exception to attach plugin-specific context to
 * invalid entry classes and reflection failures; the plugin manager decides
 * which loading failures to log and skip.
 *
 * @author daoge_cmd
 */
@StandardException
public class PluginException extends RuntimeException {
}
