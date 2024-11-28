package org.allaymc.api.component.interfaces;

/**
 * Describe the implementation of a component.
 * <p>
 * Any component implementation needs to implement this interface.
 * The namespace ID of this component implementation will be used when injecting component dependencies,
 * which should be a 'static' constant annotated with {@link org.allaymc.api.utils.Identifier.Component}.
 *
 * @author daoge_cmd
 */
public interface Component {
}
