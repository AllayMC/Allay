package org.allaymc.api.component;

import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code Component} marks implementations supplying part of a composed object's
 * behavior.
 * Component interfaces describe the capabilities exposed by an implementation,
 * while fields on the composed object select implementations by assignable
 * type. A static {@link Identifier} field annotated with
 * {@link Identifier.Component} identifies an implementation for provider
 * registration and dependencies that explicitly select an identifier.
 *
 * @author daoge_cmd
 */
public interface Component {
}
