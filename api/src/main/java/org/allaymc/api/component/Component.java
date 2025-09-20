package org.allaymc.api.component;

import org.allaymc.api.utils.identifier.Identifier;

/**
 * Defines the contract for a component implementation.
 *
 * <p>
 * All component implementations must implement this interface. The namespace ID, marked by a static
 * constant annotated with {@link Identifier.Component}, is used for dependency
 * injection in the component system.
 * </p>
 *
 * @author daoge_cmd
 */
public interface Component {
}
