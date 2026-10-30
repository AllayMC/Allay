package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityDispenserBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDispenser;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * {@code BlockEntityDispenserImpl} implements the composed block entity's
 * dispenser inventory and dispensing capabilities through injected components.
 * Component assembly assigns the delegation fields before completion hooks run,
 * and the inherited base component supplies identity, position, naming, and NBT
 * serialization for the same object.
 *
 * @author daoge_cmd
 */
public class BlockEntityDispenserImpl extends BlockEntityImpl implements BlockEntityDispenser {

    /**
     * {@code containerHolderComponent} supplies dispenser inventory operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;
    /**
     * {@code dispenserBaseComponent} supplies dispensing behavior operations.
     * Component assembly requires exactly one assignable provider result before
     * generated delegates can use this reference.
     */
    @Delegate
    private BlockEntityDispenserBaseComponent dispenserBaseComponent;

    /**
     * Creates the composed block entity from the supplied component providers.
     * The superclass performs injection and completion hooks before returning;
     * providers must supply the inherited base capability and every declared
     * delegation capability.
     */
    public BlockEntityDispenserImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
