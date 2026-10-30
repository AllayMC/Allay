package org.allaymc.api.item.interfaces;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.GoatHornInstrument;

import java.util.Objects;

/**
 * {@code ItemGoatHornStack} represents a goat horn whose instrument is encoded
 * in ordinary item metadata. The instrument accessors translate between that
 * numeric state and {@link GoatHornInstrument}, using its fallback instrument
 * when stored metadata does not identify an instrument.
 */
public interface ItemGoatHornStack extends ItemStack {
    /**
     * Returns the goat-horn instrument selected by this stack's current stored
     * metadata. {@link GoatHornInstrument#fromMeta(int)} returns
     * {@code PONDER} for metadata outside its instrument range, so callers
     * always receive an instrument value.
     */
    default GoatHornInstrument getInstrument() {
        return GoatHornInstrument.fromMeta(getMeta());
    }

    /**
     * Replaces this stack's metadata with the selected instrument identifier.
     * {@code instrument} must be non-{@code null}; this method changes the
     * metadata used by later reads and goat-horn behavior.
     */
    default void setInstrument(GoatHornInstrument instrument) {
        setMeta(Objects.requireNonNull(instrument, "instrument").getMeta());
    }
}
