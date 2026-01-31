package org.allaymc.server.entity.component;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.ArmorContainer;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityArmorStandBaseComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemArmorBaseComponent;
import org.allaymc.api.item.data.ArmorType;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import static org.allaymc.api.item.data.ItemTags.HEAD;

/**
 * Implementation of EntityArmorStandBaseComponent.
 *
 * @author daoge_cmd
 */
public class EntityArmorStandBaseComponentImpl extends EntityBaseComponentImpl implements EntityArmorStandBaseComponent {

    protected static final String TAG_POSE_INDEX = "PoseIndex";

    @Dependency
    protected EntityContainerHolderComponent containerHolder;

    protected int poseIndex = 0;

    public EntityArmorStandBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public int getPoseIndex() {
        return poseIndex;
    }

    @Override
    public void setPoseIndex(int poseIndex) {
        this.poseIndex = Math.max(0, Math.min(poseIndex, MAX_POSE_INDEX));
        thisEntity.broadcastState();
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.25, 0.0, -0.25, 0.25, 1.975, 0.25);
    }

    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        if (player.getGameMode() == GameMode.SPECTATOR) {
            return false;
        }

        // Sneaking changes pose
        if (player.isSneaking()) {
            int newPose = (poseIndex + 1) % (MAX_POSE_INDEX + 1);
            setPoseIndex(newPose);
            return true;
        }

        // Equipment interaction
        return tryEquipmentInteraction(player, itemStack);
    }

    protected boolean tryEquipmentInteraction(EntityPlayer player, ItemStack itemStack) {
        var armorContainer = containerHolder.getContainer(ContainerTypes.ARMOR);
        var handContainer = containerHolder.getContainer(ContainerTypes.ARMOR_STAND_HAND);
        var offhandContainer = containerHolder.getContainer(ContainerTypes.OFFHAND);

        if (itemStack != null && itemStack != ItemAirStack.AIR_STACK) {
            // Try to equip the item
            if (itemStack instanceof ItemArmorBaseComponent armorComponent) {
                ArmorType armorType = armorComponent.getArmorType();
                int slot = armorType.ordinal();
                return trySwapItem(player, itemStack, armorContainer, slot);
            }

            // Check if it's a skull/head that can go in helmet slot
            if (itemStack.getItemType().hasItemTag(HEAD)) {
                return trySwapItem(player, itemStack, armorContainer, ArmorContainer.HELMET_SLOT);
            }

            // Check if it's a shield for offhand
            if (itemStack.getItemType() == ItemTypes.SHIELD) {
                return trySwapItem(player, itemStack, offhandContainer, 0);
            }

            // Otherwise put in main hand
            return trySwapItem(player, itemStack, handContainer, 0);
        } else {
            // Empty hand - try to take equipment
            if (!handContainer.isEmpty(0)) {
                return tryTakeItem(player, handContainer, 0);
            }
            if (!offhandContainer.isEmpty(0)) {
                return tryTakeItem(player, offhandContainer, 0);
            }
            for (int slot = 0; slot < 4; slot++) {
                if (!armorContainer.isEmpty(slot)) {
                    return tryTakeItem(player, armorContainer, slot);
                }
            }
        }

        return false;
    }

    protected boolean trySwapItem(EntityPlayer player, ItemStack playerItem, Container container, int slot) {
        var existingItem = container.getItemStack(slot);
        boolean slotWasEmpty = (existingItem == ItemAirStack.AIR_STACK);

        var itemToPlace = playerItem.copy();
        itemToPlace.setCount(1);
        container.setItemStack(slot, itemToPlace);

        if (player.getGameMode() != GameMode.CREATIVE) {
            playerItem.setCount(playerItem.getCount() - 1);
            player.getContainer(ContainerTypes.INVENTORY).notifySlotChange(player.getContainer(ContainerTypes.INVENTORY).getHandSlot());
        }

        if (!slotWasEmpty) {
            int addedSlot = player.getContainer(ContainerTypes.INVENTORY).tryAddItem(existingItem);
            if (addedSlot == -1) {
                getDimension().dropItem(existingItem, player.getLocation());
            }
        }

        getDimension().addSound(getLocation(), SimpleSound.ARMOR_STAND_PLACE);
        return true;
    }

    protected boolean tryTakeItem(EntityPlayer player, Container container, int slot) {
        if (container.isEmpty(slot)) {
            return false;
        }
        var item = container.getItemStack(slot);

        int addedSlot = player.getContainer(ContainerTypes.INVENTORY).tryAddItem(item);
        if (addedSlot == -1) {
            getDimension().dropItem(item, player.getLocation());
        }
        container.clearSlot(slot);

        getDimension().addSound(getLocation(), SimpleSound.ARMOR_STAND_PLACE);
        return true;
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        if (nbt.containsKey(TAG_POSE_INDEX)) {
            this.poseIndex = Math.max(0, Math.min(nbt.getInt(TAG_POSE_INDEX), MAX_POSE_INDEX));
        }
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putInt(TAG_POSE_INDEX, poseIndex)
                .build();
    }
}
