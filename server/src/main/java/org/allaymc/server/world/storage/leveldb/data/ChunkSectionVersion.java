package org.allaymc.server.world.storage.leveldb.data;

/**
 * {@code ChunkSectionVersion} names
 * the ordinal slots used for subchunk format versions. The current
 * section writer uses the final paletted format, while the section decoder
 * accepts only versions one, eight, and nine; naming an older slot here does
 * not imply that this decoder supports it.
 *
 * @author PMMP Team | daoge_cmd
 */
public enum ChunkSectionVersion {
    /**
     * Classic subchunk format slot.
     */
    CLASSIC,

    /**
     * Single-palette subchunk format slot.
     */
    PALETTED_SINGLE,

    /**
     * Reserved legacy conversion slot two.
     */
    CLASSIC_BUG_2,
    /**
     * Reserved legacy conversion slot three.
     */
    CLASSIC_BUG_3,
    /**
     * Reserved legacy conversion slot four.
     */
    CLASSIC_BUG_4,
    /**
     * Reserved legacy conversion slot five.
     */
    CLASSIC_BUG_5,
    /**
     * Reserved legacy conversion slot six.
     */
    CLASSIC_BUG_6,
    /**
     * Reserved legacy conversion slot seven.
     */
    CLASSIC_BUG_7,

    /**
     * Paletted format with a layer-count byte before zero or more block
     * storages.
     */
    PALETTED_MULTI,

    /**
     * Paletted format with a layer-count byte followed by a section-coordinate
     * byte before the block storages.
     */
    PALETTED_MULTI_WITH_OFFSET
}
