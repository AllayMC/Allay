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
import org.allaymc.server.block.component.event.CBlockTryRandomUpdateEvent;
import org.allaymc.server.component.annotation.Dependency;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiFunction;

/**
 * {@code BlockOxidationComponentImpl} supplies oxidation and wax-state lookup for a
 * copper block and advances oxidation during eligible random updates. A configured
 * function resolves block types from the requested oxidation level and wax flag,
 * while the owning block identifier determines the current wax flag. The component
 * requests random updates only while the inherited eligibility check permits further
 * oxidation.
 *
 * <p>
 * The update calculation inspects a cube of neighboring oxidation components. Any
 * less-oxidized neighbor prevents advancement; equal and more-oxidized neighbors
 * otherwise determine a squared probability, with an additional reduction for
 * unaffected copper. Accepted changes preserve property values through counterpart
 * lookup and pass a cancellable fade event before the dimension receives the
 * replacement state.
 *
 * @author IWareQ
 */
@Getter
@RequiredArgsConstructor
public class BlockOxidationComponentImpl implements BlockOxidationComponent {
    /**
     * {@code IDENTIFIER} names the copper oxidation implementation when component
     * providers and dependency injection identify this component.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_oxidation_component");

    /**
     * {@code PRE_OXIDATION_CHANCE} is the random lower-bound cutoff that skips a
     * neighbor scan when the first draw falls below it.
     */
    private static final float PRE_OXIDATION_CHANCE = 64f / 1125f;
    /**
     * {@code SCAN_RANGE} bounds each scan axis in blocks, forming the cube of
     * neighbors considered by the oxidation probability calculation.
     */
    private static final int SCAN_RANGE = 4;

    /**
     * Configured block oxidation level.
     */
    private final OxidationLevel oxidationLevel;
    /**
     * Counterpart block type resolver.
     */
    private final BiFunction<OxidationLevel, Boolean, BlockType<? extends BlockOxidationComponent>> blockTypeFunction;

    /**
     * Injected owning block component.
     */
    @Dependency
    private BlockBaseComponent baseComponent;

    /**
     * Attempts to advance an eligible block by one oxidation level after random
     * screening. Callers must honor {@code canOxidate} when scheduling this handler,
     * because the next-level lookup assumes a successor exists.
     *
     * <p>
     * The scan ignores nonoxidation blocks and stops when any neighbor has a lower
     * oxidation level. Otherwise equal and higher levels determine the squared
     * advancement chance, with unaffected copper receiving a further reduction. A
     * successful draw dispatches {@code BlockFadeEvent}; cancellation prevents
     * writing the event's replacement state.
     */
    @EventHandler
    protected void onBlockRandomUpdate(CBlockRandomUpdateEvent event) {
        var random = ThreadLocalRandom.current();
        // The first draw is a skip cutoff rather than the advancement probability;
        // neighbors and a second draw determine whether oxidation actually changes.
        if (random.nextFloat() < PRE_OXIDATION_CHANCE) {
            return;
        }

        var current = event.getBlock();
        // Oxidation level declaration order defines progression and neighbor
        // comparisons here; the next ordinal selects the next block variant after the
        // shared eligibility check permits advancement.
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
                    // A less-oxidized neighbor vetoes the entire attempt, so
                    // additional equal or older neighbors cannot compensate for its
                    // presence.
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

        // The added unit leaves isolated copper with a defined chance; equal
        // neighbors dilute higher-level neighbors before the unaffected penalty and
        // final squaring.
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

    /**
     * Resolves a counterpart at the supplied oxidation level while retaining the
     * current wax flag. The configured {@code blockTypeFunction} determines the
     * resulting block type and any unsupported-input behavior.
     */
    @Override
    public BlockType<? extends BlockOxidationComponent> getBlockWithOxidationLevel(OxidationLevel oxidationLevel) {
        return blockTypeFunction.apply(oxidationLevel, isWaxed());
    }

    /**
     * Returns whether the owning block identifier path begins with {@code "waxed"}.
     * This naming convention determines the wax flag passed to counterpart lookup
     * rather than a separate stored boolean.
     */
    @Override
    public boolean isWaxed() {
        return baseComponent.getBlockType().getIdentifier().path().startsWith("waxed");
    }

    /**
     * Resolves a counterpart with the supplied wax flag while retaining the
     * configured oxidation level. The configured {@code blockTypeFunction} determines
     * the resulting type and any unsupported-input behavior.
     */
    @Override
    public BlockType<? extends BlockOxidationComponent> getBlockWithWaxed(boolean waxed) {
        return blockTypeFunction.apply(this.oxidationLevel, waxed);
    }

    /**
     * Sets the component random-update request from current oxidation eligibility.
     * Waxed and fully oxidized variants clear the flag, while other levels request
     * updates through the shared base component.
     */
    @EventHandler
    protected void onTryRandomUpdate(CBlockTryRandomUpdateEvent event) {
        event.canRandomUpdate(canOxidate());
    }
}
