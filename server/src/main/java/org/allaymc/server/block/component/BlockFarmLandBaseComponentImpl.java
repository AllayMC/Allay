package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.data.EntityId;
import org.allaymc.api.eventbus.event.entity.EntityTrampleFarmlandEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MOISTURIZED_AMOUNT;

/**
 * @author daoge_cmd
 */
public class BlockFarmLandBaseComponentImpl extends BlockBaseComponentImpl {
    // TODO: add more entities or find a better way
    protected static final Set<Identifier> VALID_ENTITIES = Set.of(EntityId.PLAYER.getIdentifier());

    public BlockFarmLandBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        super.onNeighborUpdate(current, neighbor, face);

        if (face == BlockFace.UP && neighbor.getBlockStateData().isSolid()) {
            current.getDimension().setBlockState(current.getPos(), BlockTypes.DIRT.getDefaultState());
        }
    }

    @Override
    public void onRandomUpdate(BlockStateWithPos current) {
        super.onRandomUpdate(current);

        var dimension = current.getDimension();
        var pos = current.getPos();
        var moisture = current.getPropertyValue(MOISTURIZED_AMOUNT);
        if (!hydrated(current)) {
            if (moisture > 0) {
                dimension.setBlockState(pos, current.setPropertyValue(MOISTURIZED_AMOUNT, moisture - 1));
            } else {
                var blockAbove = current.offsetPos(BlockFace.UP);
                if (!blockAbove.getBlockType().hasBlockTag(BlockTags.CROP)) {
                    // Convert farmland to dirt only if there is no crop above.
                    dimension.setBlockState(pos, BlockTypes.DIRT.getDefaultState());
                }
            }
        } else if (moisture != MOISTURIZED_AMOUNT.getMax()) {
            dimension.setBlockState(pos, current.setPropertyValue(MOISTURIZED_AMOUNT, MOISTURIZED_AMOUNT.getMax()));
        }
    }

    /**
     * Check for water within 4 blocks in each direction from the farmland.
     *
     * @param block The farmland block.
     *
     * @return {@code true} if the farmland is hydrated, {@code false} otherwise.
     */
    protected boolean hydrated(BlockStateWithPos block) {
        var dimension = block.getDimension();
        var pos = block.getPos();
        for (var y = 0; y <= 1; y++) {
            for (var x = -4; x <= 4; x++) {
                for (var z = -4; z <= 4; z++) {
                    var liquid = dimension.getLiquid(pos.x() + x, pos.y() + y, pos.z() + z).right();
                    if (liquid != null && liquid.getBlockType().hasBlockTag(BlockCustomTags.WATER)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void onEntityFallOn(Entity entity, BlockStateWithPos block) {
        if (ThreadLocalRandom.current().nextFloat() < entity.getFallDistance() - 0.5f) {
            if (!VALID_ENTITIES.contains(entity.getEntityType().getIdentifier()) || entity.getMetadata().get(EntityFlag.BABY)) {
                return;
            }

            var event = new EntityTrampleFarmlandEvent(entity, block);
            if (event.call()) {
                block.getDimension().setBlockState(block.getPos(), BlockTypes.DIRT.getDefaultState());
            }
        }
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos current, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.DIRT.createItemStack());
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
