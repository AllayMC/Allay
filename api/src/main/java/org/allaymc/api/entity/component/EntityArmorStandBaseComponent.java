package org.allaymc.api.entity.component;

/**
 * {@code EntityArmorStandBaseComponent} exposes the selected pose of an armor
 * stand. Pose indices identify thirteen supported poses, and the server clamps
 * updates to the inclusive range from {@code 0} through {@link #MAX_POSE_INDEX}
 * before broadcasting the stand's state.
 *
 * @author daoge_cmd
 */
public interface EntityArmorStandBaseComponent extends EntityBaseComponent {

    /**
     * {@code MAX_POSE_INDEX} bounds the supported armor-stand pose indices; the
     * server uses this inclusive upper bound when clamping updates and cycling
     * poses.
     */
    int MAX_POSE_INDEX = 12;

    /**
     * Returns the currently selected armor-stand pose as an integer index.
     */
    int getPoseIndex();

    /**
     * Selects a pose and publishes the updated armor-stand state. The server
     * clamps {@code poseIndex} to the inclusive range from {@code 0} through
     * {@link #MAX_POSE_INDEX}, so out-of-range inputs select the nearest
     * endpoint.
     */
    void setPoseIndex(int poseIndex);
}
