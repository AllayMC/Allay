package org.allaymc.server.component;

import org.allaymc.api.component.ComponentInitInfo;

/**
 * {@code ComponentLifecycle} defines the server-internal post-initialization hook
 * invoked after all sibling components have been constructed and wired. Keeping
 * this hook in the server module avoids exposing internal lifecycle methods on
 * public composed API interfaces such as entities, item stacks, and block
 * behaviors, while preserving ordinary Java inheritance and compile-time override
 * checks for component subclasses.
 *
 * @param <I> initialization-data subtype expected by this component
 *
 * @author daoge_cmd
 */
public interface ComponentLifecycle<I extends ComponentInitInfo> {

    /**
     * Runs after all components in the owning object have been constructed and
     * injected with their dependencies, manager, and owner references. Blueprint
     * initialization invokes base components before sibling components in
     * registration order, allowing hooks to dispatch internal component events
     * safely.
     *
     * @param initInfo initialization payload supplied when creating the owning object
     */
    default void onInitFinish(I initInfo) {
    }
}
