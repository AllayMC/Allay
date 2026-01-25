package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockExplodeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.explosion.Explosion;
import org.allaymc.api.world.gamerule.GameRule;
import org.allaymc.api.world.sound.RespawnAnchorChargeSound;
import org.allaymc.api.world.sound.SimpleSound;

import java.util.Set;

/**
 * @author IWareQ
 */
public class BlockRespawnAnchorBaseComponentImpl extends BlockBaseComponentImpl {
    private static final int MAX_CHARGES = BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE.getMax();

    public BlockRespawnAnchorBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (interactInfo == null) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        var player = interactInfo.player();
        var currentCharge = block.getPropertyValue(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE);

        if (!itemStack.isEmptyOrAir() && itemStack.getItemType() == ItemTypes.GLOWSTONE && currentCharge < MAX_CHARGES) {
            block.updateBlockProperty(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE, currentCharge + 1);
            player.tryConsumeItemInHand();

            block.addSound(new RespawnAnchorChargeSound(block.getBlockState()));
            dimension.updateComparatorOutputLevel(block.getPosition());
            return true;
        }

        if (currentCharge <= 0) {
            return false;
        }

        if (dimension.getDimensionInfo() != DimensionInfo.NETHER) {
            if (!dimension.getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.RESPAWN_BLOCKS_EXPLODE)) {
                return true;
            }

            var explosion = new Explosion(5, true);
            explosion.setSourceBlockType(this.blockType);
            explosion.setDestroyBlocks(true);
            var event = new BlockExplodeEvent(block, explosion);
            if (!event.call()) {
                return true;
            }

            // i love mojang, explode cannot break this block
            dimension.setBlockState(interactInfo.clickedBlockPos(), BlockTypes.AIR.getDefaultState());
            explosion.explode(dimension, interactInfo.clickedBlockPos());
            return true;
        }

        var spawnPoint = player.validateAndGetSpawnPoint();
        if (spawnPoint == null || !spawnPoint.equals(block.getLocation())) {
            player.setSpawnPoint(block.getLocation());
            block.addSound(SimpleSound.RESPAWN_ANCHOR_SET_SPAWN);

            player.sendTranslatable(TrKeys.MC_TILE_RESPAWN_ANCHOR_RESPAWNSET);
            return true;
        }

        return false;
    }

    @Override
    public boolean hasComparatorInputOverride() {
        return true;
    }

    @Override
    public int getComparatorInputOverride(Block block) {
        var charge = block.getPropertyValue(BlockPropertyTypes.RESPAWN_ANCHOR_CHARGE);
        return switch (charge) {
            case 1 -> 3;
            case 2 -> 7;
            case 3 -> 11;
            case 4 -> 15;
            default -> 0;
        };
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(block.toItemStack());
    }
}
