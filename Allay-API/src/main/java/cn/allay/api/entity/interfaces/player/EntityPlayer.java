package cn.allay.api.entity.interfaces.player;

import cn.allay.api.component.annotation.ComponentedObject;
import cn.allay.api.component.interfaces.ComponentInitInfo;
import cn.allay.api.container.Container;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.container.impl.*;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
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

import static cn.allay.api.container.Container.EMPTY_SLOT_PLACE_HOLDER;
import static cn.allay.api.entity.component.attribute.EntityAttributeComponentImpl.basicAttributes;
import static cn.allay.api.item.interfaces.ItemAirStack.AIR_TYPE;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayer extends
        Entity,
        EntityPlayerBaseComponent,
        EntityPlayerNetworkComponent,
        EntityPlayerAttributeComponent,
        EntityContainerHolderComponent,
        EntityContainerViewerComponent {
    EntityType<EntityPlayer> PLAYER_TYPE = EntityTypeBuilder
            .builder(EntityPlayer.class)
            .vanillaEntity(VanillaEntityId.PLAYER)
            .addComponent(
                    EntityPlayerBaseComponentImpl::new,
                    EntityPlayerBaseComponentImpl.class
            )
            .addComponent(
                    initInfo -> new EntityPlayerNetworkComponentImpl(),
                    EntityPlayerNetworkComponentImpl.class
            )
            .addComponent(info -> new EntityAttributeComponentImpl(basicAttributes()), EntityAttributeComponentImpl.class)
            .addComponent(info -> new EntityContainerHolderComponentImpl(
                            new PlayerCursorContainer(),
                            new PlayerCreatedOutputContainer(),
                            new PlayerArmorContainer(),
                            new PlayerOffhandContainer()
                    ) {
                        @ComponentedObject
                        private EntityPlayer player;

                        @Override
                        public void onInitFinish(ComponentInitInfo initInfo) {
                            addContainer(new PlayerInventoryContainer(player));
                        }
                    },
                    EntityContainerHolderComponentImpl.class)
            .addComponent(info -> new EntityPlayerContainerViewerComponentImpl(), EntityPlayerContainerViewerComponentImpl.class)
            .build();

    default <T extends Container> T getReachableContainer(FullContainerType<?> slotType) {
        var container = getOpenedContainer(slotType);
        if (container == null) container = getContainer(slotType);
        return (T) container;
    }

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
        forceDropItem(container, slot, count);
        return true;
    }

    default void forceDropItem(Container container, int slot, int count) {
        var item = container.getItemStack(slot);
        ItemStack droppedItemStack;
        if (item.getCount() > count) {
            item.setCount(item.getCount() - count);
            container.onSlotChange(slot);
            droppedItemStack = item.copy();
            droppedItemStack.setCount(count);
        } else {
            droppedItemStack = item;
            item = EMPTY_SLOT_PLACE_HOLDER;
            container.setItemStack(slot, item);
        }
        var playerLoc = getLocation();
        var world = playerLoc.world();
        var entityItem = EntityItem.ITEM_TYPE.createEntity(
                SimpleEntityInitInfo
                        .builder()
                        .world(world)
                        .pos(playerLoc.x(), playerLoc.y() + this.getEyeHeight() - 0.25f, playerLoc.z())
                        .motion(MathUtils.getDirectionVector(playerLoc.yaw(), playerLoc.pitch()).mul(0.5f))
                        .build()
        );
        entityItem.setItemStack(droppedItemStack);
        entityItem.setPickupDelay(40);
        world.addEntity(entityItem);
    }
}

