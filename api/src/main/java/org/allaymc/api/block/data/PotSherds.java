package org.allaymc.api.block.data;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.List;
import java.util.Objects;

/**
 * {@code PotSherds} represents the four decorated-pot face identifiers in front,
 * back, left, and right order. An empty identifier represents a brick face. The
 * compact constructor replaces {@code null} identifiers with empty strings so
 * serialization can produce a complete four-element list.
 *
 * @param front front face identifier
 * @param back back face identifier
 * @param left left face identifier
 * @param right right face identifier
 *
 * @author IWareQ
 */
public record PotSherds(String front, String back, String left, String right) {
    /**
     * {@code TAG_SHERDS} names the ordered NBT string list shared by decorated-pot
     * item and block-entity persistence.
     */
    public static final String TAG_SHERDS = "sherds";

    /**
     * {@code DEFAULT} represents four brick faces and supplies the fallback when
     * stored data has fewer than four identifiers.
     */
    public static final PotSherds DEFAULT = new PotSherds("", "", "", "");

    /**
     * Creates four face identifiers, normalizing each {@code null} value to the
     * empty brick identifier. Other strings are retained without identifier
     * validation, and the component order remains front, back, left, then right.
     */
    public PotSherds {
        front = Objects.requireNonNullElse(front, "");
        back = Objects.requireNonNullElse(back, "");
        left = Objects.requireNonNullElse(left, "");
        right = Objects.requireNonNullElse(right, "");
    }

    /**
     * Reads the first four strings from the stored sherd list. A missing or shorter
     * list yields {@code DEFAULT}; additional entries are ignored, and the returned
     * identifiers follow front, back, left, right order.
     */
    public static PotSherds fromNBT(NbtMap nbt) {
        var sherdsList = nbt.getList(TAG_SHERDS, NbtType.STRING);
        // Incomplete lists cannot describe every pot face, so load four brick faces
        // instead of partially assigning decorations.
        if (sherdsList == null || sherdsList.size() < 4) {
            return DEFAULT;
        }

        return new PotSherds(sherdsList.get(0), sherdsList.get(1), sherdsList.get(2), sherdsList.get(3));
    }

    /**
     * Returns an unmodifiable list of the four identifiers in front, back, left,
     * and right order.
     */
    public List<String> toNBTList() {
        return List.of(front, back, left, right);
    }
}
