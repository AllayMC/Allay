package org.allaymc.api.entity.interfaces;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAttributeComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityXpOrbBaseComponent;

import java.util.List;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityXpOrb extends
        Entity,
        EntityXpOrbBaseComponent,
        EntityDamageComponent,
        EntityAttributeComponent {

    // This is indexed biggest to smallest so that we can return as soon as we found the biggest value
    int[] ORB_SPLIT_SIZES = {2477, 1237, 617, 307, 149, 73, 37, 17, 7, 3, 1};

    /**
     * Splits the specified amount of XP into an array of acceptable XP orb sizes.
     */
    static List<Integer> splitIntoOrbSizes(int amount) {
        List<Integer> result = new IntArrayList();

        while (amount > 0) {
            int size = getMaxOrbSize(amount);
            result.add(size);
            amount -= size;
        }

        return result;
    }

    /**
     * Returns the largest size of normal XP orb that will be spawned for the specified amount of XP. Used to split XP
     * up into multiple orbs when an amount of XP is dropped.
     */
    static int getMaxOrbSize(int amount) {
        for (int split : ORB_SPLIT_SIZES) {
            if (amount >= split) {
                return split;
            }
        }

        return 1;
    }
}
