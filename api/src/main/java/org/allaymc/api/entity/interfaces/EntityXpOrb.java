package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityXpOrbBaseComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code EntityXpOrb} represents a pickable experience amount with lifetime,
 * motion, and damage handling. Its splitting helpers decompose positive
 * experience totals into the descending sizes in {@code ORB_SPLIT_SIZES},
 * allowing dimension drop handling to create several orb entities.
 */
public interface EntityXpOrb extends
        EntityLiving,
        EntityXpOrbBaseComponent,
        EntityPhysicsComponent,
        EntityAgeComponent {

    /**
     * {@code ORB_SPLIT_SIZES} lists canonical experience amounts in descending
     * order so {@code getMaxOrbSize} can stop at the first fitting entry.
     * Callers must leave this shared array unchanged because both splitting
     * helpers use its contents directly.
     */
    int[] ORB_SPLIT_SIZES = {2477, 1237, 617, 307, 149, 73, 37, 17, 7, 3, 1};

    /**
     * Splits a positive experience total into a new mutable list of canonical
     * orb amounts. A nonpositive {@code amount} produces an empty list, and
     * repeated largest-fitting selection preserves the positive total.
     */
    static List<Integer> splitIntoOrbSizes(int amount) {
        List<Integer> result = new ArrayList<>();

        while (amount > 0) {
            int size = getMaxOrbSize(amount);
            result.add(size);
            amount -= size;
        }

        return result;
    }

    /**
     * Returns the first canonical orb amount no larger than {@code amount}.
     * The descending table makes that entry the largest fitting size; values
     * below every entry return {@code 1}, including nonpositive inputs.
     */
    static int getMaxOrbSize(int amount) {
        for (int split : ORB_SPLIT_SIZES) {
            if (amount >= split) {
                return split;
            }
        }

        // Retain a positive minimum when no table entry fits, so a positive
        // remainder can still decrease during greedy splitting.
        return 1;
    }
}
