package org.allaymc.api.block.data;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;

/**
 * Instrument represents a note block instrument.
 *
 * @author daoge_cmd
 */
public enum Instrument {
    PIANO,
    BASS_DRUM,
    SNARE,
    CLICKS_AND_STICKS,
    BASS,
    FLUTE,
    BELL,
    GUITAR,
    CHIMES,
    XYLOPHONE,
    IRON_XYLOPHONE,
    COW_BELL,
    DIDGERIDOO,
    BIT,
    BANJO,
    PLING;

    /**
     * Gets the instrument based on the block below the noteblock.
     *
     * @param block the block type below the noteblock
     * @return the instrument to use
     */
    public static Instrument fromBlockBelow(BlockType<?> block) {
        if (block.hasBlockTag(BlockTags.WOOD) ||
            block.hasBlockTag(BlockTags.LOG)) {
            return BASS;
        }

        if (block.hasBlockTag(BlockTags.SAND) ||
            block.hasBlockTag(BlockTags.GRAVEL) ||
            block.hasBlockTag(BlockTags.CONCRETE_POWDER) ||
            block == BlockTypes.HEAVY_CORE) {
            return SNARE;
        }

        if (block.hasBlockTag(BlockTags.STONE)) {
            return BASS_DRUM;
        }

        if (block == BlockTypes.GOLD_BLOCK) {
            return BELL;
        }

        if (block.hasBlockTag(BlockTags.INFESTED) ||
            block == BlockTypes.CLAY ||
            block == BlockTypes.HONEYCOMB_BLOCK ||
            block == BlockTypes.RESIN_BLOCK) {
            return FLUTE;
        }

        if (block == BlockTypes.PACKED_ICE) {
            return CHIMES;
        }

        if (block.hasBlockTag(BlockTags.WOOL)) {
            return GUITAR;
        }

        if (block == BlockTypes.BONE_BLOCK) {
            return XYLOPHONE;
        }

        if (block == BlockTypes.IRON_BLOCK) {
            return IRON_XYLOPHONE;
        }

        if (block == BlockTypes.SOUL_SAND) {
            return COW_BELL;
        }

        if (block == BlockTypes.PUMPKIN) {
            return DIDGERIDOO;
        }

        if (block == BlockTypes.EMERALD_BLOCK) {
            return BIT;
        }

        if (block == BlockTypes.HAY_BLOCK) {
            return BANJO;
        }

        if (block == BlockTypes.GLOWSTONE) {
            return PLING;
        }

        return PIANO;
    }
}
