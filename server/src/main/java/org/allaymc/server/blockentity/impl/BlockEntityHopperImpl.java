package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityHopperBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityHopper;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityHopperImpl} implements the composed block entity's hopper
 * inventory and transfer cooldown capabilities through injected components.
 * Component assembly assigns the delegation fields before completion hooks run,
 * and the inherited base component supplies identity, position, naming, and NBT
 * serialization for the same object.
 *
 * @author ClexaGod
 */
public class BlockEntityHopperImpl extends BlockEntityImpl implements BlockEntityHopper {

    /**
     * {@code containerHolderComponent} supplies hopper inventory operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;
    /**
     * {@code hopperBaseComponent} supplies hopper transfer cooldown operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityHopperBaseComponent hopperBaseComponent;

    /**
     * Creates the composed block entity from the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base capability and every declared
     * delegation capability.
     */
    public BlockEntityHopperImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
