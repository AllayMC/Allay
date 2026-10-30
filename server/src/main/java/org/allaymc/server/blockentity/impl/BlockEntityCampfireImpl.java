package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityCampfireBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityCampfire;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityCampfireImpl} delegates campfire cooking operations to
 * injected components while inheriting common block-entity behavior. Component
 * assembly initializes the delegation fields before completion hooks run; this
 * class contributes the public capability shape while its configured providers
 * supply the underlying state and implementation.
 *
 * @author daoge_cmd
 */
public class BlockEntityCampfireImpl extends BlockEntityImpl implements BlockEntityCampfire {
    /**
     * {@code campfireBaseComponent} supplies the injected
     * {@link BlockEntityCampfireBaseComponent} capability. Component assembly
     * requires exactly one provider result assignable to this field before
     * delegated operations are available.
     */
    @Delegate
    private BlockEntityCampfireBaseComponent campfireBaseComponent;

    /**
     * Constructs the composed campfire using the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base and every declared delegation
     * capability.
     */
    public BlockEntityCampfireImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
