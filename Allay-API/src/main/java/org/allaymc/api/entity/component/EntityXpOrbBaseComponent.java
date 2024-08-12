package org.allaymc.api.entity.component;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import org.allaymc.api.entity.component.common.EntityBaseComponent;

import java.util.List;

/**
 * Allay Project 2024/8/12
 *
 * @author daoge_cmd
 */
public interface EntityXpOrbBaseComponent extends EntityBaseComponent {

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

    short getAge();

    void setAge(short age);

    int getExperienceValue();

    void setExperienceValue(int experienceValue);
}
