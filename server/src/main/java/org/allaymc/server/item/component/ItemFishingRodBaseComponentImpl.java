package org.allaymc.server.item.component;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.joml.Vector3d;

/**
 * Component implementation for fishing rod item.
 *
 * @author daoge_cmd
 */
public class ItemFishingRodBaseComponentImpl extends ItemBaseComponentImpl {

    /**
     * Durability cost when casting the fishing rod.
     */
    protected static final int DURABILITY_COST_CAST = 1;
    /**
     * Durability cost when catching an entity.
     */
    protected static final int DURABILITY_COST_ENTITY = 3;
    /**
     * Initial throwing force.
     */
    protected static final double THROW_FORCE = 1.21;

    @ComponentObject
    protected ItemStack thisItemStack;

    public ItemFishingRodBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        if (player.isFishing()) {
            // Already fishing - reel in
            reelIn(player);
        } else {
            // Not fishing - cast
            cast(player);
        }
        player.applyAction(SimpleEntityAction.SWING_ARM);
    }

    /**
     * Casts the fishing line.
     *
     * @param player the player casting the line
     */
    protected void cast(EntityPlayer player) {
        var dimension = player.getDimension();
        var location = player.getLocation();

        // Calculate initial position and velocity
        var spawnPos = new Vector3d(
                location.x(),
                location.y() + player.getEyeHeight(),
                location.z()
        );
        var direction = MathUtils.getDirectionVector(location);
        var motion = direction.mul(THROW_FORCE);

        // Create fishing hook entity
        var fishingHook = EntityTypes.FISHING_HOOK.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(spawnPos)
                        .rot(-location.yaw(), -location.pitch())
                        .motion(motion)
                        .build()
        );

        // Set shooter and fishing rod reference
        fishingHook.setShooter(player);
        fishingHook.setFishingRod(thisItemStack);

        // Add entity to world
        dimension.getEntityManager().addEntity(fishingHook);

        // Link player to fishing hook
        player.setFishingHook(fishingHook);

        // Consume durability (only in survival/adventure)
        if (player.getGameMode() != GameMode.CREATIVE) {
            tryIncreaseDamage(DURABILITY_COST_CAST);
        }

        // Play throw sound
        dimension.addSound(location.x(), location.y(), location.z(), SimpleSound.ITEM_THROW);
    }

    /**
     * Reels in the fishing line.
     *
     * @param player the player reeling in
     */
    protected void reelIn(EntityPlayer player) {
        var fishingHook = player.getFishingHook();
        if (fishingHook == null) {
            return;
        }

        // Check if we caught an entity (for durability cost calculation)
        boolean caughtEntity = fishingHook.hasHookedEntity();

        // Reel line (handles loot dropping, entity pulling, etc.)
        fishingHook.reelLine();

        // Consume durability for catching entities (only in survival/adventure)
        if (caughtEntity && player.getGameMode() != GameMode.CREATIVE) {
            tryIncreaseDamage(DURABILITY_COST_ENTITY);
        }
    }
}
