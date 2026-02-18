package org.allaymc.server.block.component;

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
import org.allaymc.api.world.data.DimensionInfo;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.END_PORTAL_EYE_BIT;

/**
 * Block component for end portal blocks.
 *
 * <p>Teleports entities to The End (from overworld) or back to the overworld spawn (from The End).
 * When entering The End, a 5×5 obsidian platform is created at the spawn point.
 *
 * @author daoge_cmd
 */
public class BlockEndPortalBaseComponentImpl extends BlockBaseComponentImpl {

    private static final int END_SPAWN_X = 100;
    private static final int END_SPAWN_Y = 49;
    private static final int END_SPAWN_Z = 0;
    private static final int END_PLATFORM_SIZE = 5;

    public BlockEndPortalBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        // End portal blocks are removed when a surrounding frame block is broken
        if (face.isHorizontal() && !isValidEndPortalNeighbor(neighbor)) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.AIR.getDefaultState());
        }
    }

    private static boolean isValidEndPortalNeighbor(Block neighbor) {
        var type = neighbor.getBlockType();
        if (type == BlockTypes.END_PORTAL) return true;
        return type == BlockTypes.END_PORTAL_FRAME && neighbor.getPropertyValue(END_PORTAL_EYE_BIT);
    }

    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (entity.getPortalCooldown() > 0) {
            return;
        }

        if (!new EntityPortalEnterEvent(entity, EntityPortalEnterEvent.PortalType.END).call()) {
            return;
        }

        // Set cooldown immediately to prevent re-entry while async teleport is in progress
        entity.setPortalCooldown(EntityBaseComponent.PORTAL_COOLDOWN_TICKS);

        var world = entity.getWorld();
        var currentDimInfo = entity.getDimension().getDimensionInfo();

        if (currentDimInfo != DimensionInfo.THE_END) {
            // Teleport to The End — run in virtual thread to allow chunk loading
            var theEnd = world.getTheEnd();
            if (theEnd == null) {
                entity.setPortalCooldown(0);
                return;
            }

            // Show the dimension switching loading screen immediately for players
            if (entity instanceof EntityPlayer player && player.isActualPlayer()) {
                player.getController().beginDimensionChange(theEnd.getDimensionInfo(), END_SPAWN_X + 0.5, END_SPAWN_Y + 1, END_SPAWN_Z + 0.5);
            }

            Server.getInstance().getVirtualThreadPool().submit(() -> {
                // Load chunks covering the 5×5 platform area before placing blocks
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
                // Schedule platform creation on the End dimension thread (BlockPlaceEvent contract),
                // then teleport on the entity's current dimension thread (EntityTeleportEvent contract)
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
            // Teleport back to overworld spawn
            var spawnPoint = world.getSpawnPoint();
            if (!entity.teleport(spawnPoint, EntityTeleportEvent.Reason.END_PORTAL)) {
                entity.setPortalCooldown(0);
            }
        }
    }

    /**
     * Create a 5×5 obsidian platform at the end spawn point and clear 3 blocks above it.
     */
    public static void createEndPlatform(Dimension theEnd) {
        var obsidian = BlockTypes.OBSIDIAN.getDefaultState();
        var air = BlockTypes.AIR.getDefaultState();
        int startX = END_SPAWN_X - END_PLATFORM_SIZE / 2;
        int startZ = END_SPAWN_Z - END_PLATFORM_SIZE / 2;

        for (int x = startX; x < startX + END_PLATFORM_SIZE; x++) {
            for (int z = startZ; z < startZ + END_PLATFORM_SIZE; z++) {
                // Place obsidian platform
                theEnd.setBlockState(x, END_SPAWN_Y, z, obsidian);
                // Clear 3 blocks above
                for (int y = END_SPAWN_Y + 1; y <= END_SPAWN_Y + 3; y++) {
                    theEnd.setBlockState(x, y, z, air);
                }
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }
}
