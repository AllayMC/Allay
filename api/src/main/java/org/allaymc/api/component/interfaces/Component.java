package org.allaymc.api.component.interfaces;

/**
 * Describe the implementation of a component.
 * <p>
 * Any component implementation needs to implement this interface, otherwise obviously you can't pass it into the injector.
 * <p>
 * The namespace ID of this component implementation will be used when injecting component dependencies.
 * <p>
 * Which should be a 'static' constant annotated with @ComponentIdentifier.
 * <p>
 *
 * @author daoge_cmd
 */
public interface Component {
}
