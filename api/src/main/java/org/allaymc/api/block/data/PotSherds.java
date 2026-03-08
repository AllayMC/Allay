package org.allaymc.api.block.data;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

import java.util.List;
import java.util.Objects;

/**
 * Represents the four sherds on a decorated pot.
 * <p>
 * Each side can have either a pottery sherd identifier or an empty string for brick.
 *
 * @param front the front sherd identifier
 * @param back  the back sherd identifier
 * @param left  the left sherd identifier
 * @param right the right sherd identifier
 * @author IWareQ
 */
public record PotSherds(String front, String back, String left, String right) {
    public static final String TAG_SHERDS = "sherds";

    public static final PotSherds DEFAULT = new PotSherds("", "", "", "");

    public PotSherds {
        front = Objects.requireNonNullElse(front, "");
        back = Objects.requireNonNullElse(back, "");
        left = Objects.requireNonNullElse(left, "");
        right = Objects.requireNonNullElse(right, "");
    }

    /**
     * Create PotSherds from NBT.
     *
     * @param nbt the NBT map containing the sherds list
     * @return new PotSherds instance
     */
    public static PotSherds fromNBT(NbtMap nbt) {
        var sherdsList = nbt.getList(TAG_SHERDS, NbtType.STRING);
        if (sherdsList == null || sherdsList.size() < 4) {
            return DEFAULT;
        }

        return new PotSherds(sherdsList.get(0), sherdsList.get(1), sherdsList.get(2), sherdsList.get(3));
    }

    /**
     * Convert to NBT list for saving.
     *
     * @return list of sherd identifiers
     */
    public List<String> toNBTList() {
        return List.of(front, back, left, right);
    }
}
