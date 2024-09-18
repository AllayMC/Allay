package org.allaymc.api.plugin;

import org.allaymc.api.utils.exception.FormativeException;

/**
 * @author daoge_cmd
 */
public class PluginException extends FormativeException {
    public PluginException(String format, Object... arguments) {
        super(format, arguments);
    }
}