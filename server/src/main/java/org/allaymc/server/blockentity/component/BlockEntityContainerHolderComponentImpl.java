package org.allaymc.server.blockentity.component;

import lombok.Setter;
import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.BlockContainer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class BlockEntityContainerHolderComponentImpl implements BlockEntityContainerHolderComponent {
    @Identifier.Component
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_inventory_holder_component");

    @Dependency
    protected BlockEntityBaseComponent baseComponent;

    @Setter
    protected Container container;

    public BlockEntityContainerHolderComponentImpl(Supplier<Container> containerSupplier) {
        this.container = containerSupplier.get();
    }

    @Override
    public <T extends Container> T getContainer() {
        return (T) container;
    }

    @EventHandler
    protected void onLoadNBT(CBlockEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForList("Items", NbtType.COMPOUND, items -> container.loadNBT(items));
        if (container instanceof BlockContainer blockContainer) {
            blockContainer.setBlockPos(baseComponent.getPosition());
        }
    }

    @EventHandler
    protected void onSaveNBT(CBlockEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        builder.putList(
                "Items",
                NbtType.COMPOUND,
                container.saveNBT()
        );
    }

    @EventHandler
    protected void onInteract(CBlockOnInteractEvent event) {
        var player = event.getInteractInfo().player();
        if (player == null || player.isSneaking()) return;

        container.addViewer(player);
        event.setSuccess(true);
    }

    protected boolean dropItemWhenBreak() {
        return true;
    }

    @EventHandler
    protected void onReplace(CBlockOnReplaceEvent event) {
        if (!dropItemWhenBreak()) return;

        var current = event.getCurrentBlockState();
        var pos = current.getPos();
        var rand = ThreadLocalRandom.current();

        container.removeAllViewers();
        for (var itemStack : container.getItemStacks()) {
            if (itemStack == ItemAirStack.AIR_STACK) continue;
            current.getDimension().dropItem(itemStack, new Vector3d(
                    pos.x() + rand.nextDouble(0.5) + 0.25,
                    pos.y() + rand.nextDouble(0.5) + 0.25,
                    pos.z() + rand.nextDouble(0.5) + 0.25
            ));
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
        if (!container.getContainerType().heldSlotTypes().contains(slotType)) {
            throw new IllegalArgumentException("The container " + container.getContainerType() + " does not have the slot type " + slotType);
        }

        return (T) container;
    }
}
