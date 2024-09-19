package org.allaymc.api.plugin;

import org.allaymc.api.utils.FormativeException;

/**
 * @author daoge_cmd
 */
public class PluginException extends FormativeException {
    public PluginException(String format, Object... arguments) {
        super(format, arguments);
    }
}