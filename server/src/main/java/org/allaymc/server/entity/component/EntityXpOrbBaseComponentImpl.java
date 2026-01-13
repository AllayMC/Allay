package org.allaymc.server.entity.component;

import lombok.Getter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityXpOrbBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class EntityXpOrbBaseComponentImpl extends EntityPickableBaseComponentImpl implements EntityXpOrbBaseComponent {

    protected static final String TAG_EXPERIENCE_VALUE = "ExperienceValue";
    protected static float MAX_MOVE_DISTANCE = 7.25f;
    protected static float MAX_MOVE_DISTANCE_SQUARED = MAX_MOVE_DISTANCE * MAX_MOVE_DISTANCE;

    @Dependency
    protected EntityPhysicsComponent physicsComponent;

    @Getter
    protected int experienceValue;

    public EntityXpOrbBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        moveToNearestPlayer();
    }

    @Override
    public void onCollideWithEntity(Entity other) {
        if (this.experienceValue == 0 || !canBePicked() || !(other instanceof EntityPlayer player)) {
            return;
        }

        var remainingExperience = applyMending(player, this.experienceValue);
        player.addExperience(remainingExperience);
        this.setExperienceValue(0);
        remove();
    }

    protected int applyMending(EntityPlayer player, int experienceValue) {
        if (experienceValue <= 0) {
            return experienceValue;
        }

        var targets = collectMendingTargets(player);
        if (targets.isEmpty()) {
            return experienceValue;
        }

        var target = targets.get(ThreadLocalRandom.current().nextInt(targets.size()));
        var item = target.container().getItemStack(target.slot());

        var damage = item.getDamage();
        var repairAmount = Math.min(damage, experienceValue * 2);

        item.setDamage(damage - repairAmount);
        target.container().notifySlotChange(target.slot());

        var consumedExperience = (repairAmount + 1) / 2;
        return experienceValue - consumedExperience;
    }

    protected List<MendingTarget> collectMendingTargets(EntityPlayer player) {
        var targets = new ArrayList<MendingTarget>();

        var inventory = player.getContainer(ContainerTypes.INVENTORY);
        if (inventory != null) {
            var handSlot = inventory.getHandSlot();
            var handItem = inventory.getItemStack(handSlot);
            if (isMendingTarget(handItem)) {
                targets.add(new MendingTarget(inventory, handSlot));
            }
        }

        var offhand = player.getContainer(ContainerTypes.OFFHAND);
        if (offhand != null) {
            var offhandItem = offhand.getItemStack(0);
            if (isMendingTarget(offhandItem)) {
                targets.add(new MendingTarget(offhand, 0));
            }
        }

        var armor = player.getContainer(ContainerTypes.ARMOR);
        if (armor != null) {
            var armorItems = armor.getItemStackArray();
            for (int slot = 0; slot < armorItems.length; slot++) {
                var armorItem = armor.getItemStack(slot);
                if (isMendingTarget(armorItem)) {
                    targets.add(new MendingTarget(armor, slot));
                }
            }
        }

        return targets;
    }

    protected boolean isMendingTarget(ItemStack itemStack) {
        if (itemStack == ItemAirStack.AIR_STACK) {
            return false;
        }
        if (!itemStack.getItemType().getItemData().isDamageable()) {
            return false;
        }
        if (itemStack.getDamage() <= 0) {
            return false;
        }
        return itemStack.hasEnchantment(EnchantmentTypes.MENDING);
    }

    protected record MendingTarget(Container container, int slot) {
    }

    protected void moveToNearestPlayer() {
        var nearestPlayer = findNearestPlayer();
        if (nearestPlayer == null) return;

        var playerLoc = nearestPlayer.getLocation();
        var dX = (playerLoc.x() - this.location.x) / 8f;
        var dY = (playerLoc.y() + nearestPlayer.getEyeHeight() / 2f - this.location.y) / 8f;
        var dZ = (playerLoc.z() - this.location.z) / 8f;
        var d = (float) Math.sqrt(dX * dX + dY * dY + dZ * dZ);
        var diff = 1f - d;

        if (diff > 0D) {
            diff = diff * diff;
            physicsComponent.addMotion(
                    dX / d * diff * 0.1f,
                    dY / d * diff * 0.1f,
                    dZ / d * diff * 0.1f
            );
        }
    }

    protected EntityPlayer findNearestPlayer() {
        EntityPlayer nearestPlayer = null;
        var nearestDistanceSquared = MAX_MOVE_DISTANCE_SQUARED;
        for (var player : getDimension().getPlayers()) {
            var entity = player.getControlledEntity();
            var distanceSquared = entity.getLocation().distanceSquared(location);
            if (distanceSquared < nearestDistanceSquared) {
                nearestPlayer = entity;
            }
        }
        return nearestPlayer;
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_EXPERIENCE_VALUE, this::setExperienceValue);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_EXPERIENCE_VALUE, this.experienceValue)
                .build();
    }

    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.05, 0, -0.05, 0.05, 0.1, 0.05);
    }

    @Override
    public void setExperienceValue(int experienceValue) {
        this.experienceValue = experienceValue;
        broadcastState();
    }
}
