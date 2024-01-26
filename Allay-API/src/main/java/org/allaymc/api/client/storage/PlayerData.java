package org.allaymc.api.client.storage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector2dc;
import org.joml.Vector3fc;

/**
 * Allay Project 2024/1/27
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@Builder
public class PlayerData {
    // EntityPlayer's NBT which can be generated through method EntityPlayer#saveNBT()
    protected NbtMap playerNBT;
    // The following fields are not included in the return object of method EntityPlayer#saveNBT()
    protected String currentWorldName;
    protected int currentDimensionId;
    protected Vector3fc spawnPoint;
    // yaw- pitch
    protected Vector2dc spawnPointRotation;
    protected String spawnPointWorldName;
    protected int spawnPointDimensionId;
}
