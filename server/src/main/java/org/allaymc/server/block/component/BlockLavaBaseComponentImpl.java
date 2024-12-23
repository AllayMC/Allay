package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.eventbus.event.entity.EntityCombustEvent;
import org.allaymc.api.eventbus.event.entity.EntityDamageEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.gamerule.GameRule;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
public class BlockLavaBaseComponentImpl extends BlockLiquidBaseComponentImpl {
    public BlockLavaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onCollideWithEntity(BlockStateWithPos blockStateWithPos, Entity entity) {
        if (!(entity instanceof EntityDamageComponent damageComponent)) {
            return;
        }

        // Set on fire ticks
        var event1 = new EntityCombustEvent(entity, EntityCombustEvent.CombusterType.BLOCK, blockStateWithPos.blockState(), 20 * 15);
        if (event1.call()) {
            damageComponent.setOnFireTicks(event1.getOnFireTicks());
        }

        // Lava damage
        if (damageComponent.hasFireDamage() && entity.getWorld().getTick() % 10 == 0) {
            var event2 = new EntityDamageEvent(entity, DamageContainer.lava(4));
            if (event2.call()) {
                damageComponent.attack(event2.getDamageContainer(), true);
            }
        }
    }

    // See https://minecraft.wiki/w/Lava#Fire_spread
    @Override
    public void onRandomUpdate(BlockStateWithPos blockStateWithPos) {
        if (!blockStateWithPos.dimension().getWorld().getWorldData().<Boolean>getGameRuleValue(GameRule.DO_FIRE_TICK)) {
            return;
        }

        var pos = blockStateWithPos.pos();
        var dimension = blockStateWithPos.dimension();
        var random = ThreadLocalRandom.current();
        var i = random.nextInt(3);

        if (i > 0) {
            for (int k = 0; k < i; ++k) {
                var v = new Vector3i(pos.x() + random.nextInt(3) - 2, pos.y() + 1, pos.z() + random.nextInt(3) - 2);
                var blockState = dimension.getBlockState(v);

                if (blockState.getBlockType() == BlockTypes.AIR) {
                    if (!this.canNeighborBurn(dimension, v)) {
                        continue;
                    }

                    var event = new BlockIgniteEvent(new BlockStateWithPos(blockState, new Position3i(v, dimension), 0), blockStateWithPos, null, BlockIgniteEvent.BlockIgniteCause.LAVA);
                    if (event.call()) {
                        var fireBlockState = getFireBlockState(dimension, v);
                        dimension.setBlockState(v, fireBlockState);
                    }

                    return;
                }
            }
        } else {
            for (int k = 0; k < 3; ++k) {
                var v = new Vector3i(pos.x() + random.nextInt(3) - 1, pos.y(), pos.z() + random.nextInt(3) - 1);
                var blockState = dimension.getBlockState(v);
                if (dimension.getBlockState(v.x(), v.y() + 1, v.z()).getBlockType() != BlockTypes.AIR || blockState.getBlockStateData().flameOdds() <= 0) {
                    continue;
                }

                var event = new BlockIgniteEvent(new BlockStateWithPos(blockState, new Position3i(v, dimension), 0), blockStateWithPos, null, BlockIgniteEvent.BlockIgniteCause.LAVA);
                if (event.call()) {
                    var fireBlockState = getFireBlockState(dimension, v);
                    dimension.setBlockState(v, fireBlockState);
                }
            }
        }
    }

    protected static BlockState getFireBlockState(Dimension dimension, Vector3ic pos) {
        // Check if the block that the player clicked on is a soul fire converter
        // In that case, we should place a soul fire instead of a normal fire
        return dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType().hasBlockTag(BlockCustomTags.SOUL_FIRE_CONVERTER) ? BlockTypes.SOUL_FIRE.getDefaultState() : BlockTypes.FIRE.getDefaultState();
    }

    protected boolean canNeighborBurn(Dimension dimension, Vector3ic pos) {
        for (var face : BlockFace.values()) {
            if (dimension.getBlockState(face.offsetPos(pos)).getBlockStateData().flameOdds() > 0) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
