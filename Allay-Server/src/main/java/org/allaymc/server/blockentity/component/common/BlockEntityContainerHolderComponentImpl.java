package org.allaymc.server.blockentity.component.common;

import lombok.Setter;
import org.allaymc.api.block.component.event.CBlockOnInteractEvent;
import org.allaymc.api.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.api.blockentity.component.common.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.api.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.joml.Vector3f;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public class BlockEntityContainerHolderComponentImpl implements BlockEntityContainerHolderComponent {
    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_inventory_holder_component");

    @Dependency
    protected BlockEntityBaseComponent baseComponent;

    @Setter
    protected Container container;

    public BlockEntityContainerHolderComponentImpl(Supplier<Container> containerSupplier) {
        this.container = containerSupplier.get();
    }

    public BlockEntityContainerHolderComponentImpl(
            Supplier<Container> containerSupplier,
            Consumer<ContainerViewer> onOpenListener,
            Consumer<ContainerViewer> onCloseListener
    ) {
        this.container = containerSupplier.get();
        this.container.addOnOpenListener(onOpenListener);
        this.container.addOnCloseListener(onCloseListener);
    }

    @Override
    public <T extends Container> T getContainer() {
        return (T) container;
    }

    @EventHandler
    private void onLoadNBT(CBlockEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList("Items", NbtType.COMPOUND, items -> container.loadNBT(items));
        container.setBlockPos(baseComponent.getPosition());
    }

    @EventHandler
    private void onSaveNBT(CBlockEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        builder.putList(
                "Items",
                NbtType.COMPOUND,
                container.saveNBT()
        );
    }

    @EventHandler
    private void onInteract(CBlockOnInteractEvent event) {
        var player = event.getInteractInfo().player();
        if (player == null || player.isSneaking()) return;

        container.addViewer(player);
        event.setSuccess(true);
    }

    protected boolean dropItemWhenBreak() {
        return true;
    }

    @EventHandler
    private void onReplace(CBlockOnReplaceEvent event) {
        if (!dropItemWhenBreak()) return;

        var pos = event.getCurrentBlockState().pos();
        var dimension = pos.dimension();
        var rand = ThreadLocalRandom.current();

        container.removeAllViewers();
        for (var itemStack : container.getItemStacks()) {
            if (itemStack == ItemAirStack.AIR_STACK) continue;
            dimension.dropItem(itemStack, new Vector3f(
                    pos.x() + rand.nextFloat(0.5f) + 0.25f,
                    pos.y() + rand.nextFloat(0.5f) + 0.25f,
                    pos.z() + rand.nextFloat(0.5f) + 0.25f)
            );
        }

        container.clearAllSlots();
    }

    @Override
    public boolean hasContainer(FullContainerType<?> type) {
        return container.getContainerType() == type;
    }

    @Override
    public <T extends Container> T getContainerBySlotType(ContainerSlotType slotType) {
        // BlockEntityContainerHolder can only hold one container in its lifetime
        // So we only need to check the slotType which caller provided
        if (!container.getContainerType().heldSlotTypes().contains(slotType))
            throw new IllegalArgumentException("The container " + container.getContainerType() + " does not have the slot type " + slotType);
        return (T) container;
    }
}
