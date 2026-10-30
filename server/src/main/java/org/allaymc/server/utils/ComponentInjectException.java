package org.allaymc.server.utils;

import lombok.experimental.StandardException;

/**
 * {@code ComponentInjectException} reports component wiring and
 * initialization-hook failures during assembly. Injection rejects missing
 * required dependencies and ambiguous matches with this exception. Checked
 * reflective access failures and exceptions from hook invocation retain their
 * causes, so diagnostics identify the component whose assembly failed.
 *
 * @author daoge_cmd
 */
@StandardException
public class ComponentInjectException extends RuntimeException {
}
