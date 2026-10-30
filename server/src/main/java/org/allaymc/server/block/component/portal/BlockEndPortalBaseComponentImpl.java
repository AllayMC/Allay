package org.allaymc.server.block.component.portal;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityBaseComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityPortalEnterEvent;
import org.allaymc.api.eventbus.event.entity.EntityTeleportEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.dimension.DimensionTypes;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.END_PORTAL_EYE_BIT;

/**
 * {@code BlockEndPortalBaseComponentImpl} routes End portal contacts into End
 * travel or return to the world spawn. A non-End source enters the world's End
 * dimension, while an End source returns directly to the world spawn. Horizontal
 * neighbor changes remove portal cells with an invalid portal or eyed-frame
 * neighbor.
 *
 * <p>
 * Accepted entry raises the entity cooldown before asynchronous chunk loading. The
 * positive countdown rejects repeated contacts, but entity ticks continue reducing
 * it during preparation; it can expire before pending work finishes. End-bound
 * players begin their loading screen immediately. A worker loads platform chunks,
 * the End scheduler constructs the platform, and the entity scheduler performs
 * teleport. Explicit failure paths reset cooldown, while worker and scheduled
 * callback exceptions have no recovery hook here.
 *
 * @author daoge_cmd
 */
public class BlockEndPortalBaseComponentImpl extends BlockBaseComponentImpl {

    // END_SPAWN_X, END_SPAWN_Y, and END_SPAWN_Z locate the platform floor;
    // arrival uses centered horizontal coordinates and the block directly
    // above that floor.
    /**
     * End platform center X coordinate in blocks.
     */
    private static final int END_SPAWN_X = 100;
    /**
     * End platform floor Y coordinate in blocks.
     */
    private static final int END_SPAWN_Y = 49;
    /**
     * End platform center Z coordinate in blocks.
     */
    private static final int END_SPAWN_Z = 0;
    /**
     * {@code END_PLATFORM_SIZE} is the platform side length in blocks. Chunk
     * loading and platform construction both use this size to cover the complete
     * arrival floor.
     */
    private static final int END_PLATFORM_SIZE = 5;

    /**
     * Creates this block behavior component for the supplied registered block
     * type.
     */
    public BlockEndPortalBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared handling and removes a portal cell after an invalid horizontal
     * neighbor update. Other portal cells and eyed frame blocks remain valid
     * neighbors; vertical changes do not trigger this removal check.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face.isHorizontal() && !isValidEndPortalNeighbor(neighbor)) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.AIR.getDefaultState());
        }
    }

    /**
     * Returns whether the neighbor is a portal or an eyed portal frame.
     */
    private static boolean isValidEndPortalNeighbor(Block neighbor) {
        var type = neighbor.getBlockType();
        if (type == BlockTypes.END_PORTAL) return true;
        return type == BlockTypes.END_PORTAL_FRAME && neighbor.getPropertyValue(END_PORTAL_EYE_BIT);
    }

    /**
     * Processes a cooldown-free entity's cancellable End portal entry. An accepted
     * event raises cooldown immediately, then selects End travel from a non-End
     * dimension or direct world-spawn return from the End. A missing End dimension
     * resets cooldown.
     *
     * <p>
     * End travel begins the actual player's loading screen before a worker joins
     * the platform chunk loads. The End scheduler then constructs the platform
     * before the entity scheduler attempts teleport. A rejected teleport resets
     * cooldown and closes the End-bound loading screen. Worker exceptions or
     * invalidated scheduled callbacks leave these later cleanup steps unperformed.
     */
    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (entity.getPortalCooldown() > 0) {
            return;
        }

        if (!new EntityPortalEnterEvent(entity, EntityPortalEnterEvent.PortalType.END).call()) {
            return;
        }

        // Raise cooldown before worker submission to reject immediate repeated
        // collisions. Entity ticks still advance the countdown during
        // preparation, so this flag does not reserve the entire transfer lifetime.
        entity.setPortalCooldown(EntityBaseComponent.PORTAL_COOLDOWN_TICKS);

        var world = entity.getWorld();
        var currentDimType = entity.getDimension().getDimensionType();

        if (currentDimType != DimensionTypes.THE_END) {
            var theEnd = world.getTheEnd();
            if (theEnd == null) {
                entity.setPortalCooldown(0);
                return;
            }

            if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
                player.getController().beginDimensionChange(theEnd.getDimensionType(), END_SPAWN_X + 0.5, END_SPAWN_Y + 1, END_SPAWN_Z + 0.5);
            }

            // Join chunk loads on the worker so the dimension callback remains
            // available while the platform's destination chunks are being
            // prepared.
            Server.getInstance().getVirtualThreadPool().submit(() -> {
                int startX = END_SPAWN_X - END_PLATFORM_SIZE / 2;
                int startZ = END_SPAWN_Z - END_PLATFORM_SIZE / 2;
                int endX = startX + END_PLATFORM_SIZE - 1;
                int endZ = startZ + END_PLATFORM_SIZE - 1;
                int minCX = startX >> 4, maxCX = endX >> 4;
                int minCZ = startZ >> 4, maxCZ = endZ >> 4;
                for (int cx = minCX; cx <= maxCX; cx++) {
                    for (int cz = minCZ; cz <= maxCZ; cz++) {
                        theEnd.getChunkManager().getOrLoadChunk(cx, cz).join();
                    }
                }

                var target = new Location3d(END_SPAWN_X + 0.5, END_SPAWN_Y + 1, END_SPAWN_Z + 0.5, theEnd);
                // Construct the arrival floor on the End scheduler before
                // scheduling the entity transfer, preserving
                // platform-before-arrival ordering across the two schedulers.
                theEnd.getScheduler().runLater(entity, () -> {
                    createEndPlatform(theEnd);
                    entity.getScheduler().runLater(entity, () -> {
                        if (!entity.teleport(target, EntityTeleportEvent.Reason.END_PORTAL)) {
                            entity.setPortalCooldown(0);
                            if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
                                player.getController().completeDimensionChange();
                            }
                        }
                    });
                });
            });
        } else {
            var spawnPoint = world.getSpawnPoint();
            if (!entity.teleport(spawnPoint, EntityTeleportEvent.Reason.END_PORTAL)) {
                entity.setPortalCooldown(0);
            }
        }
    }

    /**
     * Writes a centered five-by-five obsidian floor and clears three blocks above
     * each floor cell. The caller ensures the chunks are loaded and invokes
     * construction through the End scheduler; individual write results are ignored
     * without rollback.
     */
    public static void createEndPlatform(Dimension theEnd) {
        var obsidian = BlockTypes.OBSIDIAN.getDefaultState();
        var air = BlockTypes.AIR.getDefaultState();
        int startX = END_SPAWN_X - END_PLATFORM_SIZE / 2;
        int startZ = END_SPAWN_Z - END_PLATFORM_SIZE / 2;

        for (int x = startX; x < startX + END_PLATFORM_SIZE; x++) {
            for (int z = startZ; z < startZ + END_PLATFORM_SIZE; z++) {
                theEnd.setBlockState(x, END_SPAWN_Y, z, obsidian);
                // Clear three blocks above each floor cell for arrival headroom.
                for (int y = END_SPAWN_Y + 1; y <= END_SPAWN_Y + 3; y++) {
                    theEnd.setBlockState(x, y, z, air);
                }
            }
        }
    }

    /**
     * Returns an empty drop set for the End portal interior.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }
}
