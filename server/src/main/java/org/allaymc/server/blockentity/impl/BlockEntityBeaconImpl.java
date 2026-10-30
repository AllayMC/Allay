package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBeaconBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBeacon;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityBeaconImpl} delegates beacon level and effect operations to
 * injected components while inheriting common block-entity behavior. Component
 * assembly initializes the delegation fields before completion hooks run; this
 * class contributes the public capability shape while its configured providers
 * supply the underlying state and implementation.
 *
 * @author daoge_cmd
 */
public class BlockEntityBeaconImpl extends BlockEntityImpl implements BlockEntityBeacon {

    /**
     * {@code beaconBaseComponent} supplies the injected
     * {@link BlockEntityBeaconBaseComponent} capability. Component assembly
     * requires exactly one provider result assignable to this field before
     * delegated operations are available.
     */
    @Delegate
    private BlockEntityBeaconBaseComponent beaconBaseComponent;

    /**
     * Constructs the composed beacon using the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base and every declared delegation
     * capability.
     */
    public BlockEntityBeaconImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
