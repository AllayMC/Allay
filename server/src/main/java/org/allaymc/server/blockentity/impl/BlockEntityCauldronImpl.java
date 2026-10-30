package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCauldronBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCauldron;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityCauldronImpl} delegates cauldron potion and color
 * operations to injected components while inheriting common block-entity
 * behavior. Component assembly initializes the delegation fields before
 * completion hooks run; this class contributes the public capability shape
 * while its configured providers supply the underlying state and
 * implementation.
 *
 * @author daoge_cmd
 */
public class BlockEntityCauldronImpl extends BlockEntityImpl implements BlockEntityCauldron {

    /**
     * {@code cauldronBaseComponent} supplies the injected
     * {@link BlockEntityCauldronBaseComponent} capability. Component assembly
     * requires exactly one provider result assignable to this field before
     * delegated operations are available.
     */
    @Delegate
    private BlockEntityCauldronBaseComponent cauldronBaseComponent;

    /**
     * Constructs the composed cauldron using the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base and every declared delegation
     * capability.
     */
    public BlockEntityCauldronImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
