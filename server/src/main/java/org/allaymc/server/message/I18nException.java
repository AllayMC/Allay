package org.allaymc.server.message;

import lombok.experimental.StandardException;

/**
 * {@code I18nException} represents an unchecked failure while loading translation data. The bundled
 * loader uses this exception to retain an I/O cause, allowing translator setup to report a failed
 * locale and continue attempting the other configured language catalogs.
 *
 * @author daoge_cmd
 */
@StandardException
public class I18nException extends RuntimeException {
}
