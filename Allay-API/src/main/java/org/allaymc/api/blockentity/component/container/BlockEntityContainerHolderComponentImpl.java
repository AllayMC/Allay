package org.allaymc.api.blockentity.component.container;

import org.allaymc.api.block.component.event.BlockOnInteractEvent;
import org.allaymc.api.block.component.event.BlockOnReplaceEvent;
import org.allaymc.api.component.annotation.ComponentEventListener;
import org.allaymc.api.component.annotation.ComponentIdentifier;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.item.EntityItem;
import org.allaymc.api.identifier.Identifier;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public class BlockEntityContainerHolderComponentImpl implements BlockEntityContainerHolderComponent {
    @ComponentIdentifier
    protected static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_inventory_holder_component");
    protected Container container;

    public BlockEntityContainerHolderComponentImpl(Container container) {
        this.container = container;
    }

    public BlockEntityContainerHolderComponentImpl(
            Container container,
            Consumer<ContainerViewer> onOpenListener,
            Consumer<ContainerViewer> onCloseListener
    ) {
        this.container = container;
        this.container.addOnOpenListener(onOpenListener);
        this.container.addOnCloseListener(onCloseListener);
    }

    @Override
    public <T extends Container> T getContainer() {
        return (T) container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    @ComponentEventListener
    private void onInteract(BlockOnInteractEvent event) {
        var player = event.player();
        if (player == null || player.isSneaking()) return;
        Objects.requireNonNull(container).addViewer(player);
        event.success(true);
    }

    @ComponentEventListener
    private void onReplace(BlockOnReplaceEvent event) {
        var pos = event.currentBlockState().pos();
        var world = pos.world();
        var rand = ThreadLocalRandom.current();
        for (var itemStack : container.getItemStacks()) {
            if (itemStack != Container.EMPTY_SLOT_PLACE_HOLDER) {
                var entity = EntityItem.ITEM_TYPE.createEntity(
                        SimpleEntityInitInfo
                                .builder()
                                .pos(pos.x() + rand.nextFloat(0.5f) + 0.25f, pos.y() + rand.nextFloat(0.5f) + 0.25f, pos.z() + rand.nextFloat(0.5f) + 0.25f)
                                .world(world)
                                .motion(rand.nextFloat(0.2f) - 0.1f, 0.2f, rand.nextFloat(0.2f) - 0.1f)
                                .build()
                );
                entity.setItemStack(itemStack);
                entity.setPickupDelay(10);
                world.addEntity(entity);
            }
        }
    }
}
