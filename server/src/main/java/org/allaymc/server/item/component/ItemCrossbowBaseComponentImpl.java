package org.allaymc.server.item.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityArrow;
import org.allaymc.api.entity.interfaces.EntityFireworksRocket;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.entity.EntityShootCrossbowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemCrossbowBaseComponent;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.interfaces.ItemArrowStack;
import org.allaymc.api.item.interfaces.ItemFireworkRocketStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.world.sound.CrossbowLoadSound;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.container.impl.OffhandContainerImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.packet.EntityEventPacket;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class ItemCrossbowBaseComponentImpl extends ItemBaseComponentImpl implements ItemCrossbowBaseComponent {

    protected static final String TAG_CHARGED_ITEM = "chargedItem";

    protected static final long BASE_CHARGE_DURATION_TICKS = 25; // 1.25 seconds
    protected static final long QUICK_CHARGE_REDUCTION_PER_LEVEL = 5; // 0.25 seconds per level
    protected static final double ARROW_VELOCITY = 5.25;
    protected static final double FIREWORK_VELOCITY = 1.6;
    protected static final float ARROW_BASE_DAMAGE = 9.0f;
    protected static final double MULTISHOT_ANGLE = 10.0;

    @Getter
    @Setter
    protected ItemStack loadedProjectile;

    // Charge tracking state
    protected boolean hasPlayedStartSound = false;
    protected boolean hasCompletedCharge = false;
    protected int lastMiddleSoundTick = -1;

    public ItemCrossbowBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public boolean isLoaded() {
        return loadedProjectile != null && !loadedProjectile.isEmptyOrAir();
    }

    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        // Reset charge tracking state
        hasPlayedStartSound = false;
        hasCompletedCharge = false;
        lastMiddleSoundTick = -1;

        // If already loaded, return false so rightClickItemInAir is called for shooting
        if (isLoaded()) {
            return false;
        }
        // If not loaded, check for ammo to start charging
        return player.getGameMode() == GameMode.CREATIVE || hasProjectile(player);
    }

    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        // This is called when canUseItemInAir returns false (i.e., crossbow is loaded)
        if (isLoaded()) {
            shoot(player);
        }
    }

    @Override
    public boolean useItemInAir(EntityPlayer player, long usedTime) {
        // Called when player releases the use button after charging
        long requiredTicks = getChargeDuration();
        if (usedTime < requiredTicks) {
            return false;
        }

        // Find and load projectile
        var projectile = findAndConsumeProjectile(player);
        if (projectile == null) {
            return false;
        }

        setLoadedProjectile(projectile.copy(false));
        // Sound is played in onUseInAirTick when charge completes
        // Update the item in player's hand to reflect loaded state
        player.notifyItemInHandChange();
        return true;
    }

    @Override
    public void onUseInAirTick(EntityPlayer player, long usedTime) {
        // Don't play sounds if already loaded
        if (isLoaded()) {
            return;
        }

        // Check for ammo
        if (player.getGameMode() != GameMode.CREATIVE && !hasProjectile(player)) {
            return;
        }

        int quickChargeLevel = getEnchantmentLevel(EnchantmentTypes.QUICK_CHARGE);
        long chargeDuration = getChargeDuration();

        // Play START sound at beginning (first 2 ticks)
        if (usedTime <= 2 && !hasPlayedStartSound) {
            playLoadSound(player, CrossbowLoadSound.LoadingStage.START);
            hasPlayedStartSound = true;
        }

        // Calculate adjusted ticks for Quick Charge
        // Base reload time is 25 ticks; each Quick Charge level reduces by 5 ticks
        double multiplier = 25.0 / Math.max(25 - (5 * quickChargeLevel), 1);
        int adjustedTicks = (int) (usedTime * multiplier);

        // Play MIDDLE sound every 16 adjusted ticks (but not the same tick twice)
        if (adjustedTicks > 0 && adjustedTicks % 16 == 0 && adjustedTicks != lastMiddleSoundTick) {
            playLoadSound(player, CrossbowLoadSound.LoadingStage.MIDDLE);
            lastMiddleSoundTick = adjustedTicks;
        }

        // Check if charge complete
        if (usedTime >= chargeDuration && !hasCompletedCharge) {
            hasCompletedCharge = true;
            playLoadSound(player, CrossbowLoadSound.LoadingStage.END);

            // Send "finished charging" packet to client
            if (player.isActualPlayer()) {
                var packet = new EntityEventPacket();
                packet.setType(EntityEventType.FINISHED_CHARGING_ITEM);
                packet.setRuntimeEntityId(player.getRuntimeId());
                player.getController().sendPacket(packet);
            }
        }
    }

    protected long getChargeDuration() {
        int quickChargeLevel = getEnchantmentLevel(EnchantmentTypes.QUICK_CHARGE);
        long reduction = quickChargeLevel * QUICK_CHARGE_REDUCTION_PER_LEVEL;
        return Math.max(BASE_CHARGE_DURATION_TICKS - reduction, 10);
    }

    protected boolean hasProjectile(EntityPlayer player) {
        // Check offhand first
        Container offhand = player.getContainer(ContainerTypes.OFFHAND);
        var offhandItem = offhand.getItemStack(OffhandContainerImpl.OFFHAND_SLOT);
        if (offhandItem instanceof ItemFireworkRocketStack || offhandItem instanceof ItemArrowStack) {
            return true;
        }

        // Check inventory for arrows
        Container inventory = player.getContainer(ContainerTypes.INVENTORY);
        for (var itemStack : inventory.getItemStacks()) {
            if (itemStack instanceof ItemArrowStack) {
                return true;
            }
        }

        return false;
    }

    protected ItemStack findAndConsumeProjectile(EntityPlayer player) {
        boolean creative = player.getGameMode() == GameMode.CREATIVE;

        Container container;
        int slot;
        ItemStack projectile = null;

        // Check offhand first (fireworks or arrows)
        container = player.getContainer(ContainerTypes.OFFHAND);
        slot = OffhandContainerImpl.OFFHAND_SLOT;
        var offhandItem = container.getItemStack(slot);
        if (offhandItem instanceof ItemFireworkRocketStack || offhandItem instanceof ItemArrowStack) {
            projectile = offhandItem;
        }

        // If not in offhand, search inventory for arrows
        if (projectile == null) {
            container = player.getContainer(ContainerTypes.INVENTORY);
            var itemStacks = container.getItemStacks();
            for (slot = 0; slot < itemStacks.size(); slot++) {
                var item = itemStacks.get(slot);
                if (item instanceof ItemArrowStack) {
                    projectile = item;
                    break;
                }
            }
        }

        // Creative mode: create arrow if no projectile found
        if (projectile == null) {
            if (creative) {
                return ItemTypes.ARROW.createItemStack(1);
            }
            return null;
        }

        // Consume one projectile (unless creative mode)
        if (!creative) {
            if (projectile.getCount() == 1) {
                container.clearSlot(slot);
            } else {
                projectile.reduceCount(1);
                container.notifySlotChange(slot);
            }
        }

        return projectile;
    }

    protected void shoot(EntityPlayer player) {
        if (!isLoaded()) {
            return;
        }

        var dimension = player.getDimension();
        var location = player.getLocation();
        var shootPos = new Vector3d(location.x(), location.y() + player.getEyeHeight() - 0.1, location.z());
        var hasMultishot = getEnchantmentLevel(EnchantmentTypes.MULTISHOT) > 0;
        var piercingLevel = getEnchantmentLevel(EnchantmentTypes.PIERCING);
        var creative = player.getGameMode() == GameMode.CREATIVE;

        List<Entity> projectiles = new ArrayList<>();

        if (loadedProjectile instanceof ItemFireworkRocketStack fireworkStack) {
            // Create firework(s)
            if (hasMultishot) {
                projectiles.add(createFirework(player, shootPos, 0, fireworkStack));
                projectiles.add(createFirework(player, shootPos, -MULTISHOT_ANGLE, fireworkStack));
                projectiles.add(createFirework(player, shootPos, MULTISHOT_ANGLE, fireworkStack));
            } else {
                projectiles.add(createFirework(player, shootPos, 0, fireworkStack));
            }
            // Fireworks cost 3 durability
            if (!creative) {
                tryIncreaseDamage(3);
            }
        } else {
            // Create arrow(s)
            if (hasMultishot) {
                // Center arrow can be picked up, side arrows cannot
                projectiles.add(createArrow(player, shootPos, 0, piercingLevel, false));
                projectiles.add(createArrow(player, shootPos, -MULTISHOT_ANGLE, piercingLevel, true));
                projectiles.add(createArrow(player, shootPos, MULTISHOT_ANGLE, piercingLevel, true));
            } else {
                projectiles.add(createArrow(player, shootPos, 0, piercingLevel, false));
            }
            // Arrows cost 1 durability
            if (!creative) {
                tryIncreaseDamage(1);
            }
        }

        // Fire event
        var event = new EntityShootCrossbowEvent(player, thisItemStack, projectiles);
        if (!event.call()) {
            return;
        }

        // Spawn all projectiles
        for (var projectile : projectiles) {
            dimension.getEntityManager().addEntity(projectile);
        }

        // Play shoot sound
        dimension.addSound(shootPos, SimpleSound.CROSSBOW_SHOOT);

        // Clear loaded projectile
        setLoadedProjectile(null);
        player.notifyItemInHandChange();
    }

    protected EntityArrow createArrow(EntityPlayer player, Vector3dc shootPos, double yawOffset, int piercingLevel, boolean pickUpDisabled) {
        var location = player.getLocation();
        var playerMotion = new Vector3d(player.getMotion());
        if (player.isOnGround()) {
            playerMotion.setComponent(1, 0);
        }
        var direction = MathUtils.getDirectionVector(location.yaw() + yawOffset, location.pitch());
        var arrow = EntityTypes.ARROW.createEntity(
                EntityInitInfo.builder()
                        .dimension(player.getDimension())
                        .pos(shootPos)
                        .rot(-location.yaw() - yawOffset, -location.pitch())
                        .motion(direction.mul(ARROW_VELOCITY).add(playerMotion))
                        .build()
        );

        arrow.setShooter(player);
        arrow.setBaseDamage(ARROW_BASE_DAMAGE);
        arrow.setPiercingLevel(piercingLevel);
        arrow.setPickUpDisabled(pickUpDisabled);
        // Crossbow arrows are always critical
        arrow.setCritical(true);
        // Handle tipped arrows
        arrow.setPotionType(((ItemArrowStack) loadedProjectile).getPotionType());
        // Creative mode arrows cannot be picked up
        arrow.setInfinite(player.getGameMode() == GameMode.CREATIVE);

        return arrow;
    }

    protected EntityFireworksRocket createFirework(EntityPlayer player, Vector3dc shootPos, double yawOffset, ItemFireworkRocketStack fireworkStack) {
        var location = player.getLocation();
        var direction = MathUtils.getDirectionVector(location.yaw() + yawOffset, location.pitch());
        var firework = EntityTypes.FIREWORKS_ROCKET.createEntity(
                EntityInitInfo.builder()
                        .dimension(player.getDimension())
                        .pos(shootPos)
                        .rot(-location.yaw() - yawOffset, -location.pitch())
                        .motion(direction.mul(FIREWORK_VELOCITY))
                        .build()
        );

        // Shorter lifetime for crossbow-launched fireworks (similar to vanilla)
        firework.setExistenceTicks(fireworkStack.getDuration() * 10 + 10);
        firework.setExplosions(fireworkStack.getExplosions());
        firework.setSidewaysMotionMultiplier(1.0);
        firework.setUpwardsMotion(0);

        return firework;
    }

    protected void playLoadSound(EntityPlayer player, CrossbowLoadSound.LoadingStage stage) {
        boolean quickCharge = getEnchantmentLevel(EnchantmentTypes.QUICK_CHARGE) > 0;
        player.getDimension().addSound(player.getLocation(), new CrossbowLoadSound(stage, quickCharge));
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForCompound(TAG_CHARGED_ITEM, nbt -> {
            this.loadedProjectile = NBTIO.getAPI().fromItemStackNBT(nbt);
        });
    }

    @Override
    public NbtMap saveExtraTag() {
        var builder = super.saveExtraTag().toBuilder();
        if (isLoaded()) {
            builder.putCompound(TAG_CHARGED_ITEM, loadedProjectile.saveNBT());
        }
        return builder.build();
    }
}
