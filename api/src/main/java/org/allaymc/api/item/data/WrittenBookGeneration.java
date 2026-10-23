package org.allaymc.api.item.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;

/**
 * {@code WrittenBookGeneration} identifies a written book's copying generation
 * and its translated display name. The item implementation persists each value
 * by enum ordinal, and {@code fromGeneration} resolves that stored ordinal
 * without supplying a fallback for invalid values.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public enum WrittenBookGeneration {
    /**
     * Original written-book generation.
     */
    ORIGINAL_GENERATION(TrKeys.MC_BOOK_GENERATION_0),
    /**
     * First copied-book generation.
     */
    COPY_GENERATION(TrKeys.MC_BOOK_GENERATION_1),
    /**
     * Second copied-book generation.
     */
    COPY_OF_COPY_GENERATION(TrKeys.MC_BOOK_GENERATION_2),
    /**
     * Tattered written-book generation.
     */
    TATTERED(TrKeys.MC_BOOK_GENERATION_3);

    /**
     * {@code VALUES} caches generation declaration order for loading stored
     * ordinals, so serialized generation numbers depend on that enum order.
     */
    private static final WrittenBookGeneration[] VALUES = values();

    /**
     * Translated generation display-name key.
     */
    private final @MayContainTrKey String name;

    /**
     * Returns the generation at the supplied serialized ordinal.
     * {@code generation} must be within the enum's available values; an
     * invalid value causes an array-index exception rather than selecting a
     * default generation.
     */
    public static WrittenBookGeneration fromGeneration(int generation) {
        return VALUES[generation];
    }
}
