package org.allaymc.server.block.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.component.BlockStateDataComponent;
import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockBehaviorImpl} exposes the core behavior of a block type by delegating
 * to injected base and state-data components. Its constructor passes component
 * providers through the component framework with empty initialization data,
 * allowing specialized behavior implementations to reuse the same component
 * assembly.
 *
 * @author daoge_cmd
 */
public class BlockBehaviorImpl extends ComponentClass implements BlockBehavior {

    /**
     * {@code EMPTY_INIT_INFO} supplies the shared empty initialization payload used
     * when component providers construct block behavior components.
     */
    private static final ComponentInitInfo EMPTY_INIT_INFO = new ComponentInitInfo() {
    };

    /**
     * {@code baseComponent} receives the framework-injected base behavior. Lombok
     * delegates its operations and exposes the same component through the generated
     * getter.
     */
    @Getter
    @Delegate
    private BlockBaseComponent baseComponent;
    /**
     * {@code stateDataComponent} receives the framework-injected metadata
     * component. Lombok delegates state-data lookup and exposes this same component
     * through the generated getter.
     */
    @Getter
    @Delegate
    private BlockStateDataComponent stateDataComponent;

    /**
     * Creates block behavior by constructing and injecting the supplied component
     * providers. The framework uses the shared empty initialization payload,
     * connects component dependencies, and invokes completion hooks before subclass
     * construction finishes.
     */
    public BlockBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(EMPTY_INIT_INFO, componentProviders);
    }
}
