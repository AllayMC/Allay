package org.allaymc.server.block.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemHelper;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.block.component.event.CBlockOnInteractEvent;
import org.allaymc.server.block.component.event.CBlockRandomUpdateEvent;
import org.allaymc.server.component.annotation.ComponentObject;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;

/**
 * @author IWareQ
 */
@Getter
@RequiredArgsConstructor
public class BlockOxidationComponentImpl implements BlockOxidationComponent {
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_oxidation_component");

    private static final float PRE_OXIDATION_CHANCE = 64f / 1125f;
    private static final int SCAN_RANGE = 4;

    private final OxidationLevel oxidationLevel;
    private final BiFunction<OxidationLevel, Boolean, BlockType<?>> blockTypeFunction;

    @ComponentObject
    private BlockBaseComponent baseComponent;

    @EventHandler
    protected void onBlockRandomUpdate(CBlockRandomUpdateEvent event) {
        var random = ThreadLocalRandom.current();
        if (random.nextFloat() < PRE_OXIDATION_CHANCE) {
            return;
        }

        var current = event.getBlockState();
        var position = current.pos();
        var dimension = position.dimension();
        var currentLevel = this.getOxidationLevel().ordinal();

        int higherOxidizedBlocks = 0;
        int sameOxidizedBlocks = 0;
        for (int x = -SCAN_RANGE; x <= SCAN_RANGE; x++) {
            for (int y = -SCAN_RANGE; y <= SCAN_RANGE; y++) {
                for (int z = -SCAN_RANGE; z <= SCAN_RANGE; z++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }

                    var neighborPos = position.add(x, y, z, new Position3i(position.dimension()));
                    if (position.distance(neighborPos) > SCAN_RANGE) {
                        continue;
                    }

                    var neighborState = dimension.getBlockState(neighborPos);
                    if (!(neighborState instanceof BlockOxidationComponent neighborOxidation)) {
                        continue;
                    }

                    var neighborLevel = neighborOxidation.getOxidationLevel().ordinal();
                    if (neighborLevel < currentLevel) {
                        return;
                    } else if (neighborLevel > currentLevel) {
                        higherOxidizedBlocks++;
                    } else {
                        sameOxidizedBlocks++;
                    }
                }
            }
        }

        float chance = (higherOxidizedBlocks + 1f) / (higherOxidizedBlocks + sameOxidizedBlocks + 1f);
        chance *= currentLevel == 0 ? 0.75f : 1f;
        chance *= chance;
        if (random.nextFloat() < chance) {
            var nextBlockType = getBlockWithOxidationLevel(OxidationLevel.values()[currentLevel + 1]);
            var blockFadeEvent = new BlockFadeEvent(current, nextBlockType.copyPropertyValuesFrom(current.blockState()));
            if (blockFadeEvent.call()) {
                dimension.setBlockState(position, blockFadeEvent.getNewBlockState());
            }
        }
    }

    // TODO: move to ItemAxeBaseComponent
    @EventHandler
    protected void onDeoxidation(CBlockOnInteractEvent event) {
        var dimension = event.getDimension();
        var interactInfo = event.getInteractInfo();
        var itemStack = event.getItemStack();
        if (!ItemHelper.isAxe(itemStack.getItemType()) || this.isWaxed()) {
            return;
        }

        var oxidationLevel = this.getOxidationLevel();
        if (oxidationLevel == OxidationLevel.UNAFFECTED) {
            return;
        }

        oxidationLevel = OxidationLevel.values()[oxidationLevel.ordinal() - 1];
        var nextBlockType = getBlockWithOxidationLevel(oxidationLevel);
        var oldBlockState = new BlockStateWithPos(
                dimension.getBlockState(interactInfo.clickBlockPos()),
                new Position3i(interactInfo.clickBlockPos(), dimension),
                0
        );
        var blockFadeEvent = new BlockFadeEvent(oldBlockState, nextBlockType.copyPropertyValuesFrom(oldBlockState.blockState()));
        if (blockFadeEvent.call()) {
            dimension.setBlockState(interactInfo.clickBlockPos(), blockFadeEvent.getNewBlockState());
            if (event.getInteractInfo().player().getGameType() != GameType.CREATIVE) {
                itemStack.tryReduceDurability(1);
            }
            dimension.addLevelEvent(interactInfo.clickBlockPos(), LevelEvent.PARTICLE_SCRAPE);
        }
    }

    @Override
    public BlockType<?> getBlockWithOxidationLevel(OxidationLevel oxidationLevel) {
        return blockTypeFunction.apply(oxidationLevel, this.isWaxed());
    }

    @Override
    public boolean isWaxed() {
        return baseComponent.getBlockType().getIdentifier().path().startsWith("waxed");
    }

    @Override
    public BlockType<?> getBlockWithWaxed(boolean waxed) {
        return blockTypeFunction.apply(this.getOxidationLevel(), waxed);
    }
}
