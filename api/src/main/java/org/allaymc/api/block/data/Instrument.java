package org.allaymc.api.block.data;

import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;

/**
 * {@code Instrument} identifies a note block sound selected from the block type
 * beneath the note block. The constants describe the supported instrument choices,
 * while {@code fromBlockBelow} applies the ordered tag and identity checks and
 * falls back to {@code PIANO}.
 *
 * @author daoge_cmd
 */
public enum Instrument {
    /**
     * Default piano note sound.
     */
    PIANO,
    /**
     * Bass drum note sound.
     */
    BASS_DRUM,
    /**
     * Snare drum note sound.
     */
    SNARE,
    /**
     * Clicks and sticks sound.
     */
    CLICKS_AND_STICKS,
    /**
     * Bass guitar note sound.
     */
    BASS,
    /**
     * Flute note block sound.
     */
    FLUTE,
    /**
     * Bell note block sound.
     */
    BELL,
    /**
     * Guitar note block sound.
     */
    GUITAR,
    /**
     * Chimes note block sound.
     */
    CHIMES,
    /**
     * Xylophone note block sound.
     */
    XYLOPHONE,
    /**
     * Iron xylophone note sound.
     */
    IRON_XYLOPHONE,
    /**
     * Cow bell note sound.
     */
    COW_BELL,
    /**
     * Didgeridoo note block sound.
     */
    DIDGERIDOO,
    /**
     * Bit note block sound.
     */
    BIT,
    /**
     * Banjo note block sound.
     */
    BANJO,
    /**
     * Pling note block sound.
     */
    PLING;

    /**
     * Selects the note block instrument for the supplied supporting block type. The
     * first matching tag or concrete type wins; unrecognized supports use {@code
     * PIANO}, and callers must supply a non-{@code null} type.
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

        // Supports without any recognized tag or type use the base piano sound
        // instead of preventing note playback.
        return PIANO;
    }
}
