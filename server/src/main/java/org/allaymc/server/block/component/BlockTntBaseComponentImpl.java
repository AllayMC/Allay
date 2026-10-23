package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockTntBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.entity.component.item.EntityTntBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockTntBaseComponentImpl} handles TNT ignition from item interactions and
 * redstone power, then creates primed explosive entities. Fire charges consume one
 * item, fire-aspect items attempt durability damage, and powered placement or
 * neighbor updates prime at the block position. The default fuse comes from the
 * component interface.
 *
 * <p>
 * Priming first attempts to replace the block with air, then creates an entity with
 * randomized horizontal motion and a small upward impulse. The fuse is stored as a
 * signed short in entity initialization NBT. Neither the block write nor entity
 * addition is used to compensate the other step, so failed writes or additions can
 * leave partial effects rather than an atomic block-to-entity transition.
 *
 * @author daoge_cmd
 */
public class BlockTntBaseComponentImpl extends BlockBaseComponentImpl implements BlockTntBaseComponent {
    /**
     * Creates the tnt block behavior for the supplied block type.
     */
    public BlockTntBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Primes TNT for fire charges or fire-aspect items after forwarding the component
     * interaction. {@code interactInfo} must be present; the forwarded success flag
     * is ignored before these ignition branches run.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        super.onInteract(itemStack, dimension, interactInfo);

        var pos = new Position3i(interactInfo.clickedBlockPos(), dimension);
        if (itemStack.getItemType() == ItemTypes.FIRE_CHARGE) {
            itemStack.reduceCount(1);
            prime(pos);
            return true;
        }

        if (itemStack.hasEnchantment(EnchantmentTypes.FIRE_ASPECT)) {
            itemStack.tryIncreaseDamage(1);
            prime(pos);
            return true;
        }

        return false;
    }

    /**
     * Forwards placement and primes the position when nearby redstone supplies power.
     * {@code placementInfo} may be {@code null}, and the inherited default fuse is
     * used for this immediate ignition.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        var pos = oldBlock.getPosition();
        if (oldBlock.getDimension().isPoweredAt(pos)) {
            prime(pos);
        }
    }

    /**
     * Forwards the neighbor event and primes TNT when the current block is powered.
     * The default fuse applies regardless of which neighboring face triggered this
     * reevaluation of power.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (block.isPowered()) {
            prime(block.getPosition());
        }
    }

    /**
     * Attempts to remove the TNT block and adds an explosive entity at its horizontal
     * center. {@code pos} supplies both world coordinates and the dimension;
     * {@code fuse} is measured in ticks.
     *
     * <p>
     * The entity receives a random horizontal direction, an upward impulse, and the
     * fuse narrowed to a signed short in NBT. The air write result is ignored before
     * entity creation, so failure can leave the block present while an entity is
     * still added; later failures do not restore an already removed block.
     */
    @Override
    public void prime(Position3ic pos, int fuse) {
        var dimension = pos.dimension();
        // Remove the block before adding the primed entity. The result is ignored, so
        // this sequence does not guarantee a single surviving representation on
        // failure.
        dimension.setBlockState(pos, BlockTypes.AIR.getDefaultState());

        // Convert the unit random sample to radians across one horizontal turn.
        var angle = ThreadLocalRandom.current().nextFloat() * Math.PI * 2;
        // The small horizontal impulse scatters TNT around its block center while the
        // vertical impulse starts its upward movement.
        var motion = new Vector3d(-Math.sin(angle) * 0.02, 0.2, -Math.cos(angle) * 0.02);
        var entity = EntityTypes.TNT.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(pos.x() + 0.5, pos.y(), pos.z() + 0.5)
                        .motion(motion)
                        // The entity fuse tag uses a signed short, so larger integer
                        // fuse values narrow before the entity reads its
                        // initialization data.
                        .nbt(NbtMap.builder().putShort(EntityTntBaseComponentImpl.TAG_FUSE, (short) fuse).build())
                        .build()
        );
        dimension.getEntityManager().addEntity(entity);
    }
}
