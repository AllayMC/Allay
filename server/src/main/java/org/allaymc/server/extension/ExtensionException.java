package org.allaymc.server.extension;

import org.allaymc.api.utils.FormativeException;

/**
 * @author daoge_cmd
 */
public class ExtensionException extends FormativeException {
    public ExtensionException(String format, Object... arguments) {
        super(format, arguments);
    }
}