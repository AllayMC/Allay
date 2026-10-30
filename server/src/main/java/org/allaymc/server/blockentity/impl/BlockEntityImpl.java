package org.allaymc.server.blockentity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentClass;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityImpl} represents a composed block entity whose common base
 * contract delegates to an injected component. Component construction injects
 * {@code baseComponent} and connects dependencies before initialization hooks
 * load NBT. Those hooks execute during superclass construction, so subclasses
 * must not assume their own initialization has finished.
 *
 * @author daoge_cmd
 */
public class BlockEntityImpl extends ComponentClass implements BlockEntity {

    /**
     * {@code baseComponent} supplies the injected
     * {@link BlockEntityBaseComponent} capability. Component assembly requires
     * exactly one provider result assignable to this field before delegated
     * operations are available.
     */
    @Delegate
    @Getter
    private BlockEntityBaseComponent baseComponent;

    /**
     * Constructs and wires components using the supplied initialization
     * information. Providers run before dependency injection and completion
     * hooks; failures abort construction without undoing earlier provider or
     * hook side effects.
     */
    public BlockEntityImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
