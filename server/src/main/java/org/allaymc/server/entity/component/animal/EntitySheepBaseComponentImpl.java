package org.allaymc.server.entity.component.animal;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.ai.memory.MemoryTypes;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.ItemDyeComponent;
import org.allaymc.api.item.type.ItemTypes;

import org.allaymc.api.utils.DyeColor;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.event.CEntityGetDropEvent;
import org.allaymc.server.entity.component.event.CEntityGetDropXpEvent;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.joml.Vector3d;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code EntitySheepBaseComponentImpl} handles sheep interactions, wool state,
 * and adult loot. Shearing changes the stored wool flag, dyeing updates the
 * separate color component, and feeding writes the player's identity and
 * current tick into optional AI memory. The NBT handlers persist the sheared
 * flag independently of color and baby state.
 *
 * <p>
 * Interaction order gives shears priority over dye and feeding. Only adult
 * sheep with wool can be sheared. Death drops separately consult the mob-loot
 * rule, baby state, wool availability, current dye color, fire state, and
 * looting level. A sheared adult can still drop mutton, while an unsheared
 * adult contributes one wool item matching its current color.
 *
 * @author daoge_cmd
 */
public class EntitySheepBaseComponentImpl extends EntityBaseComponentImpl implements EntitySheepBaseComponent {

    /**
     * {@code TAG_SHEARED} identifies the boolean NBT entry used by both
     * persistence handlers to retain the sheep's wool-availability state.
     */
    protected static final String TAG_SHEARED = "Sheared";

    /**
     * Current sheep dye color for wool drops.
     */
    @Dependency
    protected EntityDyeableComponent dyeableComponent;
    /**
     * Breeding-item recognition for feeding interactions.
     */
    @Dependency
    protected EntityAnimalComponent animalComponent;
    /**
     * Baby state gating shearing and adult drops.
     */
    @Dependency
    protected EntityBabyComponent babyComponent;
    /**
     * Fire state selecting raw or cooked mutton.
     */
    @Dependency
    protected EntityLivingComponent livingComponent;
    /**
     * {@code aiComponent} receives feeding memory when the sheep has AI. An
     * absent optional dependency prevents feeding from consuming an item or
     * reporting an accepted interaction.
     */
    @Dependency(optional = true)
    protected EntityAIComponent aiComponent;

    /**
     * {@code sheared} records whether wool is unavailable. Explicit updates
     * broadcast entity state; the NBT listener restores the flag directly
     * without a broadcast.
     */
    protected boolean sheared;

    /**
     * Creates sheep base state using the supplied entity initialization data.
     */
    public EntitySheepBaseComponentImpl(EntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Returns a fresh unscaled sheep collision box in local block coordinates.
     * The box measures {@code 0.9} blocks across and {@code 1.3} blocks high,
     * with its lower face at the entity origin and no retained geometry shared
     * with callers.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.45, 0.0, -0.45, 0.45, 1.3, 0.45);
    }

    /**
     * Returns whether this sheep currently has its wool removed.
     */
    @Override
    public boolean isSheared() {
        return sheared;
    }

    /**
     * Replaces the wool-availability flag and broadcasts the sheep's state to
     * viewers. The update does not change the separate dye color, and it
     * broadcasts even when the requested value already matches the stored flag.
     */
    @Override
    public void setSheared(boolean sheared) {
        this.sheared = sheared;
        broadcastState();
    }

    /**
     * Handles shearing, dyeing, or feeding in that priority order. A
     * {@code null} item returns {@code false}; other calls require a non-{@code null}
     * player. Accepted paths mutate sheep or AI state before consuming or
     * damaging the player's hand item. Shearing requires an adult with wool,
     * and feeding requires the optional AI dependency.
     */
    @Override
    public boolean onInteract(EntityPlayer player, ItemStack itemStack) {
        if (itemStack == null) return false;

        if (itemStack.getItemType() == ItemTypes.SHEARS && !sheared && !babyComponent.isBaby()) {
            setSheared(true);
            dropWool();
            itemStack.tryIncreaseDamage(1);
            player.getContainer(org.allaymc.api.container.ContainerTypes.INVENTORY)
                    .notifySlotChange(player.getContainer(org.allaymc.api.container.ContainerTypes.INVENTORY).getHandSlot());
            getDimension().addSound(
                    new Vector3d(location.x(), location.y(), location.z()),
                    new CustomSound(SoundNames.MOB_SHEEP_SHEAR)
            );
            return true;
        }

        if (itemStack instanceof ItemDyeComponent dyeComponent) {
            var newColor = dyeComponent.getDyeColor();
            if (newColor != dyeableComponent.getColor()) {
                dyeableComponent.setColor(newColor);
                player.tryConsumeItemInHand();
                return true;
            }
        }

        if (animalComponent.isBreedingItem(itemStack) && aiComponent != null) {
            aiComponent.getMemoryStorage().put(MemoryTypes.LAST_BE_FEED_TIME, thisEntity.getTick());
            aiComponent.getMemoryStorage().put(MemoryTypes.LAST_FEED_PLAYER, player.getRuntimeId());
            player.tryConsumeItemInHand();
            return true;
        }

        return false;
    }

    /**
     * Drops one to three wool items using the sheep's current dye color. The
     * stack appears half a block above the entity's origin, and this helper
     * does not check baby state, sheared state, or the mob-loot rule itself.
     */
    protected void dropWool() {
        var rand = ThreadLocalRandom.current();
        // Shearing uses a separate random wool quantity; death drops below use
        // one wool item, so this draw belongs only to the shearing helper.
        int count = rand.nextInt(1, 4);
        var woolType = getWoolItemForColor(dyeableComponent.getColor());
        if (woolType != null) {
            var woolStack = woolType.createItemStack(count);
            // Place sheared wool above the sheep's feet so the drop originates
            // within its body rather than directly at the ground contact point.
            getDimension().dropItem(woolStack, new Vector3d(location.x(), location.y() + 0.5, location.z()));
        }
    }

    /**
     * Returns the wool item type matching the supplied dye color. The
     * exhaustive color switch selects the corresponding colored wool directly;
     * callers must supply a non-{@code null} {@code color} because a {@code null}
     * switch input fails.
     */
    protected org.allaymc.api.item.type.ItemType<?> getWoolItemForColor(DyeColor color) {
        return switch (color) {
            case WHITE -> ItemTypes.WHITE_WOOL;
            case ORANGE -> ItemTypes.ORANGE_WOOL;
            case MAGENTA -> ItemTypes.MAGENTA_WOOL;
            case LIGHT_BLUE -> ItemTypes.LIGHT_BLUE_WOOL;
            case YELLOW -> ItemTypes.YELLOW_WOOL;
            case LIME -> ItemTypes.LIME_WOOL;
            case PINK -> ItemTypes.PINK_WOOL;
            case GRAY -> ItemTypes.GRAY_WOOL;
            case LIGHT_GRAY -> ItemTypes.LIGHT_GRAY_WOOL;
            case CYAN -> ItemTypes.CYAN_WOOL;
            case PURPLE -> ItemTypes.PURPLE_WOOL;
            case BLUE -> ItemTypes.BLUE_WOOL;
            case BROWN -> ItemTypes.BROWN_WOOL;
            case GREEN -> ItemTypes.GREEN_WOOL;
            case RED -> ItemTypes.RED_WOOL;
            case BLACK -> ItemTypes.BLACK_WOOL;
        };
    }

    /**
     * Writes the current sheared flag into the entity NBT builder.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().putBoolean(TAG_SHEARED, sheared);
    }

    /**
     * Restores the sheared flag from a present boolean NBT entry. A missing
     * entry or another value type leaves the current flag unchanged, and
     * restoring this persisted value does not trigger the explicit setter's
     * broadcast.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForBoolean(TAG_SHEARED, v -> this.sheared = v);
    }

    /**
     * Adds adult wool and mutton drops when the mob-loot rule permits them.
     * Babies and disabled mob loot leave the existing event drops unchanged. An
     * unsheared adult contributes one wool item matching its current dye color,
     * while a sheared adult skips only that wool addition.
     *
     * <p>
     * Mutton combines a one-to-two base draw with an independent
     * zero-to-looting bonus. The current fire state chooses raw or cooked
     * mutton. These decisions preserve meat drops independently of whether
     * earlier shearing removed the sheep's wool.
     */
    @EventHandler
    protected void onGetDrop(CEntityGetDropEvent event) {
        if (!thisEntity.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_MOB_LOOT)) return;
        if (babyComponent.isBaby()) return;

        if (!sheared) {
            var woolType = getWoolItemForColor(dyeableComponent.getColor());
            if (woolType != null) {
                event.getDrops().add(woolType.createItemStack(1));
            }
        }

        // Keep the base and looting bonus as separate random draws so the meat
        // count retains their combined distribution independently of the wool flag.
        var rand = ThreadLocalRandom.current();
        int muttonCount = rand.nextInt(1, 3) + rand.nextInt(event.getLootingLevel() + 1);
        var muttonType = livingComponent.isOnFire() ? ItemTypes.COOKED_MUTTON : ItemTypes.MUTTON;
        event.getDrops().add(muttonType.createItemStack(muttonCount));
    }

    /**
     * Assigns one to three experience points when the sheep is an adult. Babies
     * leave the event's current value unchanged, and this handler does not
     * perform its own mob-loot-rule check before assigning adult experience.
     */
    @EventHandler
    protected void onGetDropXp(CEntityGetDropXpEvent event) {
        if (babyComponent.isBaby()) return;
        // Adult sheep use their base experience range independently of the item
        // drops, whose wool and mutton quantities depend on other state.
        event.setXp(ThreadLocalRandom.current().nextInt(1, 4));
    }

}
