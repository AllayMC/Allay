package org.allaymc.api.utils.config;

import lombok.experimental.StandardException;

/**
 * {@code ConfigException} represents configuration failures reported by the configuration loader
 * and its typed accessors. Configuration operations supply messages or causes through the
 * Lombok-generated constructors, allowing callers to distinguish configuration rejection from
 * other runtime failures. The generated
 * constructors follow the standard runtime-exception message and cause forms.
 *
 * @author daoge_cmd
 */
@StandardException
public class ConfigException extends RuntimeException {
}
