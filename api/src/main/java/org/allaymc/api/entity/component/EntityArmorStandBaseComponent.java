package org.allaymc.api.entity.component;

/**
 * EntityArmorStandBaseComponent defines the specific behavior for armor stand entities.
 *
 * @author daoge_cmd
 */
public interface EntityArmorStandBaseComponent extends EntityBaseComponent {

    /**
     * The maximum pose index for armor stands (0-12, 13 poses total).
     */
    int MAX_POSE_INDEX = 12;

    /**
     * Gets the current pose index of the armor stand.
     *
     * @return the pose index (0-12)
     */
    int getPoseIndex();

    /**
     * Sets the pose index of the armor stand.
     *
     * @param poseIndex the pose index to set (0-12)
     */
    void setPoseIndex(int poseIndex);
}
