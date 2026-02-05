package org.allaymc.server.block.component;

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
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Base component implementation for nether vines (Twisting Vines and Weeping Vines).
 *
 * @author daoge_cmd
 */
public class BlockNetherVinesBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final int MAX_AGE = 25;
    protected static final double GROWTH_CHANCE = 0.1;

    protected final BlockFace growthFace, supportFace;
    protected final IntPropertyType ageProperty;

    public BlockNetherVinesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockFace growthFace, BlockFace supportFace, IntPropertyType ageProperty) {
        super(blockType);
        this.growthFace = growthFace;
        this.supportFace = supportFace;
        this.ageProperty = ageProperty;
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public boolean canResetFallDamage() {
        return true;
    }

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

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == supportFace) {
            if (!canBeSupportedBy(neighbor.getBlockState())) {
                block.breakBlock();
            }
        }
    }

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

        // Base chance: 33% (1/3), Fortune adds 22% per level
        // Fortune 0: 33%, Fortune I: 55%, Fortune II: 77%, Fortune III+: 100%
        if (FortuneDropHelper.bonusChanceFixed(usedItem, 1.0 / 3.0, 22.0 / 100.0)) {
            return Set.of(blockType.getItemType().createItemStack());
        }

        return Set.of();
    }

    /**
     * Checks if the vine can be supported by the given block state.
     *
     * @param blockState the block state to check
     * @return true if the vine can be supported, false otherwise
     */
    protected boolean canBeSupportedBy(BlockState blockState) {
        if (blockState.getBlockType() == blockType) {
            return true;
        }
        return blockState.getBlockStateData().isSolid();
    }

    /**
     * Finds the tip (end) of the vine chain in the growth direction.
     *
     * @param block any block in the vine chain
     * @return the tip block of the vine
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
