package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.entity.EntityTrampleFarmlandEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.MOISTURIZED_AMOUNT;

/**
 * {@code BlockFarmLandBaseComponentImpl} maintains farmland hydration, support
 * clearance, and trample conversion. Nearby water restores moisture; dry farmland
 * loses moisture and becomes dirt only when no crop occupies the block above.
 * Trampling checks entity fall distance and size before dispatching its cancellable
 * event.
 *
 * @author daoge_cmd
 */
public class BlockFarmLandBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the farm land block behavior for the supplied block type.
     */
    public BlockFarmLandBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Forwards the neighbor event and converts farmland to dirt beneath a solid upper
     * neighbor. Other faces do not apply this conversion, leaving moisture and crop
     * checks to random updates.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.UP && neighbor.getBlockStateData().isSolid()) {
            block.getDimension().setBlockState(block.getPosition(), BlockTypes.DIRT.getDefaultState());
        }
    }

    /**
     * Updates moisture from nearby water and converts fully dry farmland without a
     * crop to dirt. Hydrated farmland restores maximum moisture, while existing crops
     * preserve zero-moisture farmland from this conversion.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var moisture = block.getPropertyValue(MOISTURIZED_AMOUNT);
        if (!hydrated(block)) {
            if (moisture > 0) {
                dimension.setBlockState(pos, block.getBlockState().setPropertyValue(MOISTURIZED_AMOUNT, moisture - 1));
            } else {
                var blockAbove = block.offsetPos(BlockFace.UP);
                if (!blockAbove.getBlockType().hasBlockTag(BlockTags.CROP)) {
                    dimension.setBlockState(pos, BlockTypes.DIRT.getDefaultState());
                }
            }
        } else if (moisture != MOISTURIZED_AMOUNT.getMax()) {
            dimension.setBlockState(pos, block.getBlockState().setPropertyValue(MOISTURIZED_AMOUNT, MOISTURIZED_AMOUNT.getMax()));
        }
    }

    /**
     * Returns whether water occurs within four horizontal blocks at the farmland's
     * level or one block above. The check accepts water from either liquid layer
     * reported by the dimension.
     */
    protected boolean hydrated(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        // Water at farmland height or one block above hydrates the four-block
        // horizontal neighborhood.
        for (var y = 0; y <= 1; y++) {
            for (var x = -4; x <= 4; x++) {
                for (var z = -4; z <= 4; z++) {
                    var liquid = dimension.getLiquid(pos.x() + x, pos.y() + y, pos.z() + z).right();
                    if (liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Attempts trample conversion when fall distance and entity size qualify. Players
     * bypass the size test, and cancellation of {@code EntityTrampleFarmlandEvent}
     * prevents replacement of the farmland with dirt.
     */
    @Override
    public void onEntityFallOn(Entity entity, Block block) {
        if (entity instanceof EntityPhysicsComponent physicsComponent &&
            // The half-block offset leaves short falls without a positive trample
            // probability.
            ThreadLocalRandom.current().nextFloat() < physicsComponent.getFallDistance() - 0.5f) {
            // Players can trample regardless of size; other entities must exceed the
            // tested bounding volume after passing the fall-distance probability
            // gate.
            var aabb = entity.getAABB();
            var width = aabb.maxX() - aabb.minX();
            var height = aabb.maxY() - aabb.minY();
            if (entity instanceof EntityPlayer || (width * width * height) > 0.512f) {
                var event = new EntityTrampleFarmlandEvent(entity, block);
                if (event.call()) {
                    block.getDimension().setBlockState(block.getPosition(), BlockTypes.DIRT.getDefaultState());
                }
            }
        }
    }

    /**
     * Returns one dirt item as the ordinary farmland block drop.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.DIRT.createItemStack());
    }

    /**
     * Returns whether farmland receives random updates for moisture maintenance.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
