package org.allaymc.server.item.component;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.player.PlayerStartFishEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.joml.Vector3d;

/**
 * {@code ItemFishingRodBaseComponentImpl} switches fishing-rod use between
 * casting a new hook and reeling an existing hook. Casting publishes an
 * approved hook and links it to the player, while durability costs depend on
 * creative mode and the hook state observed before reeling.
 *
 * @author daoge_cmd
 */
public class ItemFishingRodBaseComponentImpl extends ItemBaseComponentImpl {

    /**
     * {@code DURABILITY_COST_CAST} specifies one damage point requested after
     * an approved noncreative cast links its hook to the player.
     */
    protected static final int DURABILITY_COST_CAST = 1;
    /**
     * {@code DURABILITY_COST_ENTITY} specifies three damage points requested
     * when a noncreative player reels a hook that had a hooked entity.
     */
    protected static final int DURABILITY_COST_ENTITY = 3;
    /**
     * {@code THROW_FORCE} scales the direction vector into the new fishing
     * hook's initial velocity when the player casts.
     */
    protected static final double THROW_FORCE = 0.8;

    /**
     * {@code thisItemStack} holds the injected owning fishing rod.
     * {@code cast} retains this stack on the hook so later loot calculation
     * can read its enchantments.
     */
    @ComponentObject
    protected ItemStack thisItemStack;

    /**
     * Creates fishing-rod behavior from the supplied item initialization data.
     */
    public ItemFishingRodBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Casts when the player has no hook and otherwise reels the current hook.
     * The player swings an arm after either path, including when a new-cast
     * event is cancelled.
     */
    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        if (player.isFishing()) {
            reelIn(player);
        } else {
            cast(player);
        }
        player.applyAction(SimpleEntityAction.SWING_ARM);
    }

    /**
     * Creates a fishing hook and publishes it after the start-fishing event
     * succeeds. Cancellation leaves the hook unpublished; an approved cast
     * links the player, requests noncreative durability damage, and emits the
     * throw sound.
     */
    protected void cast(EntityPlayer player) {
        var dimension = player.getDimension();
        var location = player.getLocation();

        // Lower the launch point by 0.3 blocks from eye height so the hook begins
        // below the player's exact viewpoint while following the viewing direction.
        var spawnPos = new Vector3d(
                location.x(),
                location.y() + player.getEyeHeight() - 0.3,
                location.z()
        );
        var direction = MathUtils.getDirectionVector(location);
        var motion = direction.mul(THROW_FORCE);

        var fishingHook = EntityTypes.FISHING_HOOK.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos)
                        .rot(-location.yaw(), -location.pitch())
                        .motion(motion)
                        .build()
        );

        fishingHook.setShooter(player);
        fishingHook.setFishingRod(thisItemStack);

        // Let listeners inspect the configured hook before publishing it or linking
        // the player, so cancellation prevents those world-visible cast side effects.
        var event = new PlayerStartFishEvent(player, fishingHook);
        if (!event.call()) {
            return;
        }

        dimension.getEntityManager().addEntity(fishingHook);

        player.setFishingHook(fishingHook);

        if (player.getGameMode() != GameMode.CREATIVE) {
            tryIncreaseDamage(DURABILITY_COST_CAST);
        }

        dimension.addSound(location.x(), location.y(), location.z(), SimpleSound.ITEM_THROW);
    }

    /**
     * Reels the player's current hook, returning immediately when no hook
     * exists. A hook that had an attached entity requests extra noncreative
     * durability damage after reeling, even when the hook's fish event rejects
     * its outcome.
     */
    protected void reelIn(EntityPlayer player) {
        var fishingHook = player.getFishingHook();
        if (fishingHook == null) {
            return;
        }

        // Capture attachment before reelLine can remove the hook and clear its owner;
        // the later durability decision uses what the player reeled in.
        boolean caughtEntity = fishingHook.hasHookedEntity();

        fishingHook.reelLine();

        if (caughtEntity && player.getGameMode() != GameMode.CREATIVE) {
            tryIncreaseDamage(DURABILITY_COST_ENTITY);
        }
    }
}
