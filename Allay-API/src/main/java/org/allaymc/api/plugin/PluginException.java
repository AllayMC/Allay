package org.allaymc.api.plugin;

import org.allaymc.api.common.exception.FormativeException;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public class PluginException extends FormativeException {
    public PluginException(String format, Object... arguments) {
        super(format, arguments);
    }
}