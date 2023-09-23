package cn.allay.api.entity.interfaces.player;

import cn.allay.api.container.Container;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.container.impl.*;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.attribute.EntityAttributeComponent;
import cn.allay.api.entity.component.attribute.EntityAttributeComponentImpl;
import cn.allay.api.entity.component.container.EntityContainerHolderComponent;
import cn.allay.api.entity.component.container.EntityContainerHolderComponentImpl;
import cn.allay.api.entity.component.container.EntityContainerViewerComponent;
import cn.allay.api.entity.init.SimpleEntityInitInfo;
import cn.allay.api.entity.interfaces.item.EntityItem;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.item.ItemStack;
import cn.allay.api.utils.MathUtils;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.joml.primitives.AABBf;

import static cn.allay.api.container.Container.AIR_STACK;
import static cn.allay.api.entity.component.attribute.EntityAttributeComponentImpl.basicAttributes;
import static cn.allay.api.item.interfaces.air.ItemAirStack.AIR_TYPE;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayer extends
        Entity,
        EntityPlayerBaseComponent,
        EntityAttributeComponent,
        EntityContainerHolderComponent,
        EntityContainerViewerComponent {
    EntityType<EntityPlayer> PLAYER_TYPE = EntityTypeBuilder
            .builder(EntityPlayer.class)
            .vanillaEntity(VanillaEntityId.PLAYER)
            .addComponent(
                    info -> new EntityPlayerBaseComponentImpl(info, e -> new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f)),
                    EntityPlayerBaseComponentImpl.class
            )
            .addComponent(info -> new EntityAttributeComponentImpl(basicAttributes()), EntityAttributeComponentImpl.class)
            .addComponent(info -> new EntityContainerHolderComponentImpl(
                            new PlayerInventoryContainer(((EntityPlayerInitInfo) info).getClient()),
                            new PlayerCursorContainer(),
                            new PlayerCreatedOutputContainer(),
                            new PlayerArmorContainer(),
                            new PlayerOffhandContainer()
                    ),
                    EntityContainerHolderComponentImpl.class)
            .addComponent(info -> new EntityPlayerContainerViewerComponentImpl(), EntityPlayerContainerViewerComponentImpl.class)
            .build();

    @Nullable
    default <T extends Container> T getReachableContainer(FullContainerType<?> slotType) {
        var container = getOpenedContainer(slotType);
        if (container == null) container = getContainer(slotType);
        return (T) container;
    }

    @Nullable
    default <T extends Container> T getReachableContainerBySlotType(ContainerSlotType slotType) {
        var container = getOpenedContainerBySlotType(slotType);
        if (container == null) container = getContainerBySlotType(slotType);
        return (T) container;
    }

    default boolean tryDropItemInHand(int count) {
        return tryDropItem(FullContainerType.PLAYER_INVENTORY, getContainer(FullContainerType.PLAYER_INVENTORY).getHandSlot(), count);
    }

    default boolean tryDropItem(FullContainerType<?> containerType, int slot, int count) {
        var container = getReachableContainer(containerType);
        if (container == null) return false;
        var item = container.getItemStack(slot);
        if (item.getItemType() == AIR_TYPE) {
            return false;
        }
        if (item.getCount() < count) {
            return false;
        }
        dropItemWithOutCheck(container, slot, count);
        return true;
    }

    @ApiStatus.Internal
    default void dropItemWithOutCheck(Container container, int slot, int count) {
        var item = container.getItemStack(slot);
        ItemStack droppedItemStack;
        if (item.getCount() > count) {
            item.setCount(item.getCount() - count);
            container.onSlotChange(slot);
            droppedItemStack = item.copy();
            droppedItemStack.setCount(count);
        } else {
            droppedItemStack = item;
            item = AIR_STACK;
            container.setItemStack(slot, item);
        }
        var client = getClient();
        var world = client.getWorld();
        var playerLoc = client.getLocation();
        var entityItem = EntityItem.ITEM_TYPE.createEntity(
                SimpleEntityInitInfo
                        .builder()
                        .world(world)
                        .pos(playerLoc.x(), playerLoc.y() + client.getPlayerEntity().getEyeHeight() - 0.25f, playerLoc.z())
                        .motion(MathUtils.getDirectionVector(playerLoc.yaw(), playerLoc.pitch()).mul(0.5f))
                        .build()
        );
        entityItem.setItemStack(droppedItemStack);
        entityItem.setPickupDelay(40);
        world.addEntity(entityItem);
    }
}

