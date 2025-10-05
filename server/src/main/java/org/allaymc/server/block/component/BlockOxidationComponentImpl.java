package org.allaymc.server.block.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.allaymc.api.block.component.BlockBaseComponent;
import org.allaymc.api.block.component.BlockOxidationComponent;
import org.allaymc.api.block.data.OxidationLevel;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.component.event.CBlockRandomUpdateEvent;
import org.allaymc.server.component.annotation.ComponentObject;

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

        var current = event.getBlock();
        var currentLevel = this.oxidationLevel.ordinal();

        int higherOxidizedBlocks = 0;
        int sameOxidizedBlocks = 0;
        for (int x = -SCAN_RANGE; x <= SCAN_RANGE; x++) {
            for (int y = -SCAN_RANGE; y <= SCAN_RANGE; y++) {
                for (int z = -SCAN_RANGE; z <= SCAN_RANGE; z++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }

                    var neighbor = current.offsetPos(x, y, z);
                    if (!(neighbor.getBehavior() instanceof BlockOxidationComponent neighborOxidation)) {
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

        var chance = (higherOxidizedBlocks + 1f) / (higherOxidizedBlocks + sameOxidizedBlocks + 1f);
        chance *= currentLevel == OxidationLevel.UNAFFECTED.ordinal() ? 0.75f : 1f;
        chance *= chance;
        if (random.nextFloat() < chance) {
            var nextBlockType = getBlockWithOxidationLevel(OxidationLevel.values()[currentLevel + 1]);
            var blockFadeEvent = new BlockFadeEvent(current, nextBlockType.copyPropertyValuesFrom(current.getBlockState()));
            if (blockFadeEvent.call()) {
                current.getDimension().setBlockState(current.getPosition(), blockFadeEvent.getNewBlockState());
            }
        }
    }

    @Override
    public BlockType<?> getBlockWithOxidationLevel(OxidationLevel oxidationLevel) {
        return blockTypeFunction.apply(oxidationLevel, isWaxed());
    }

    @Override
    public boolean isWaxed() {
        return baseComponent.getBlockType().getIdentifier().path().startsWith("waxed");
    }

    @Override
    public BlockType<?> getBlockWithWaxed(boolean waxed) {
        return blockTypeFunction.apply(this.oxidationLevel, waxed);
    }
}
