package org.allaymc.server.block.component.vine;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.IntPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.FortuneDropHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code BlockNetherVinesBaseComponentImpl} implements directional growth and support for
 * twisting and weeping vines. Construction supplies a growth face, a support face, and the
 * appropriate age property so the same behavior works for upward and downward chains.
 *
 * <p>
 * Player placement derives age from a same-type support or samples a fresh age on solid
 * support. Natural updates stop at the age cutoff and sample a growth chance before finding
 * the chain tip. Bone meal instead attempts several tip extensions, retaining successful
 * earlier segments if a later event is cancelled or space becomes blocked. Its capped age does
 * not itself stop further bone-meal segments, and item consumption depends on whether at least
 * one growth proposal was accepted.
 *
 * @author daoge_cmd
 */
public class BlockNetherVinesBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code MAX_AGE} caps stored ages and prevents natural growth at the cutoff, while
     * bone-meal extension can continue with that capped value.
     */
    protected static final int MAX_AGE = 25;
    /**
     * {@code GROWTH_CHANCE} controls natural per-callback extension attempts before tip
     * discovery; bone-meal handling uses a separately sampled segment count instead.
     */
    protected static final double GROWTH_CHANCE = 0.1;

    /**
     * {@code growthFace} identifies the vine extension direction, while {@code supportFace}
     * identifies the supporting neighbor direction. Placement requires the support's opposite
     * clicked face, and tip traversal follows {@code growthFace}.
     */
    protected final BlockFace growthFace, supportFace;
    /**
     * {@code ageProperty} selects the registered vine family's age encoding. Every state
     * passed to growth or placement must support this property and its used age values.
     */
    protected final IntPropertyType ageProperty;

    /**
     * Creates vine behavior with its extension direction, support direction, and age property.
     * The constructor retains these values without checking that faces oppose each other or
     * that the block type exposes the property.
     */
    public BlockNetherVinesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockFace growthFace, BlockFace supportFace, IntPropertyType ageProperty) {
        super(blockType);
        this.growthFace = growthFace;
        this.supportFace = supportFace;
        this.ageProperty = ageProperty;
    }

    /**
     * Returns whether these vines receive random updates for natural extension.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Returns whether contact with these vines can reset entity fall damage.
     */
    @Override
    public boolean canResetFallDamage() {
        return true;
    }

    /**
     * Places the supplied vine directly through inherited handling when placement information
     * is absent. Player placement requires the configured support face, suitable clicked
     * support, and an age derived from that support.
     *
     * <p>
     * A same-type vine contributes its age plus one capped at twenty-five; other valid solid
     * support samples zero through twenty-four. The player path writes the selected state
     * directly without ordinary property processing or passing placement information to the
     * final dimension overload.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        if (placementInfo.blockFace() != supportFace.opposite()) {
            return false;
        }

        var supportBlock = placementInfo.getClickedBlock();
        if (!canBeSupportedBy(supportBlock.getBlockState())) {
            return false;
        }

        int age;
        if (supportBlock.getBlockType() == blockType) {
            var supportAge = supportBlock.getPropertyValue(ageProperty);
            age = Math.min(supportAge + 1, MAX_AGE);
        } else {
            age = ThreadLocalRandom.current().nextInt(MAX_AGE);
        }

        blockState = blockState.setPropertyValue(ageProperty, age);
        return dimension.setBlockState(placeBlockPos, blockState);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks a vine after invalid support-face
     * changes. The configured support direction selects the relevant neighbor, which must
     * remain solid or the same vine type.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == supportFace) {
            if (!canBeSupportedBy(neighbor.getBlockState())) {
                block.breakBlock();
            }
        }
    }

    /**
     * Attempts one natural extension after age and probability checks. Both the triggering
     * segment and the discovered tip must be below the maximum age, and the next tip position
     * must be air.
     *
     * <p>
     * The growth event is associated with the current tip and proposes a new segment one age
     * older. Acceptance writes that event state at the next position without changing the old
     * tip's age. The callback therefore keeps the triggering segment's own age gate separate
     * from the age and location discovered along the chain.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var currentAge = block.getPropertyValue(ageProperty);
        if (currentAge >= MAX_AGE) {
            return;
        }

        if (ThreadLocalRandom.current().nextDouble() >= GROWTH_CHANCE) {
            return;
        }

        var tip = findVineTip(block);
        var tipAge = tip.getPropertyValue(ageProperty);
        if (tipAge >= MAX_AGE) {
            return;
        }

        var targetPos = tip.offsetPos(growthFace);
        if (targetPos.getBlockType() != BlockTypes.AIR) {
            return;
        }

        var newAge = Math.min(tipAge + 1, MAX_AGE);
        var newBlockState = blockType.getDefaultState().setPropertyValue(ageProperty, newAge);

        var event = new BlockGrowEvent(tip, newBlockState);
        if (!event.call()) {
            return;
        }

        tip.getDimension().setBlockState(targetPos.getPosition(), event.getNewBlockState());
    }

    /**
     * Attempts one through seven bone-meal extensions from the current chain tip. Every
     * candidate must be air and pass its own cancellable growth event; a blocked position or
     * cancelled proposal ends the loop while earlier accepted writes remain.
     *
     * <p>
     * New ages are capped at twenty-five without ending the loop at that age. The callback
     * advances its working tip after each write call without checking the dimension result,
     * and it consumes bone meal only when at least one proposal reached that call. It then
     * emits particles at the clicked block.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        var tip = findVineTip(clickedBlock);
        var tipAge = tip.getPropertyValue(ageProperty);

        // One bone-meal action samples its extension budget once, while each segment
        // still receives a separate growth proposal that can stop only the remaining attempts.
        var growCount = ThreadLocalRandom.current().nextInt(1, 8);
        Vector3ic currentTipPos = tip.getPosition();
        var currentAge = tipAge;
        var grewAny = false;

        for (int i = 0; i < growCount; i++) {
            var targetPos = growthFace.offsetPos(currentTipPos);
            if (dimension.getBlockState(targetPos).getBlockType() != BlockTypes.AIR) {
                break;
            }

            var newAge = Math.min(currentAge + 1, MAX_AGE);
            var newBlockState = blockType.getDefaultState().setPropertyValue(ageProperty, newAge);

            var event = new BlockGrowEvent(
                    new Block(dimension.getBlockState(currentTipPos), new Position3i(currentTipPos, dimension)),
                    newBlockState
            );
            if (!event.call()) {
                break;
            }

            dimension.setBlockState(targetPos, event.getNewBlockState());
            currentTipPos = targetPos;
            currentAge = newAge;
            grewAny = true;
        }

        if (grewAny) {
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    /**
     * Returns one vine item for shears or a silk-touch tool. Other tools use a one-third base
     * chance plus twenty-two percentage points per Fortune level, capped by the shared helper
     * at certainty.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null) {
            if (usedItem.getItemType() == ItemTypes.SHEARS) {
                return Set.of(blockType.getItemType().createItemStack());
            }

            if (usedItem.hasEnchantment(EnchantmentTypes.SILK_TOUCH)) {
                return Set.of(blockType.getItemType().createItemStack());
            }
        }

        // Fortune adds percentage points to the one-third base chance, with the
        // shared helper clamping the probability so sufficiently high levels guarantee an item.
        if (FortuneDropHelper.bonusChanceFixed(usedItem, 1.0 / 3.0, 22.0 / 100.0)) {
            return Set.of(blockType.getItemType().createItemStack());
        }

        return Set.of();
    }

    /**
     * Returns whether the state is this vine type or a solid block.
     */
    protected boolean canBeSupportedBy(BlockState blockState) {
        if (blockState.getBlockType() == blockType) {
            return true;
        }
        return blockState.getBlockStateData().isSolid();
    }

    /**
     * Returns the last contiguous matching vine in the configured growth direction. The
     * traversal starts from the supplied snapshot and reads each next block from the dimension
     * until a different type ends the chain.
     */
    protected Block findVineTip(Block block) {
        var current = block;

        while (true) {
            var next = current.offsetPos(growthFace);
            if (next.getBlockType() != blockType) {
                return current;
            }
            current = next;
        }
    }
}
