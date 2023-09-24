package cn.allay.api.block.component.event;

import cn.allay.api.block.data.BlockFace;
import cn.allay.api.component.interfaces.ComponentEvent;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.item.ItemStack;
import cn.allay.api.world.World;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.Objects;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Getter
@Accessors(fluent = true)
public final class BlockOnInteractEvent implements ComponentEvent {
    private final @Nullable EntityPlayer player;
    private final ItemStack itemStack;
    private final World world;
    private final Vector3ic blockPos;
    private final Vector3ic placeBlockPos;
    private final Vector3fc clickPos;
    private final BlockFace blockFace;
    @Setter
    private boolean success = false;

    /**
     *
     */
    public BlockOnInteractEvent(@Nullable EntityPlayer player, ItemStack itemStack, World world, Vector3ic blockPos,
                                Vector3ic placeBlockPos, Vector3fc clickPos,
                                BlockFace blockFace, boolean success) {
        this.player = player;
        this.itemStack = itemStack;
        this.world = world;
        this.blockPos = blockPos;
        this.placeBlockPos = placeBlockPos;
        this.clickPos = clickPos;
        this.blockFace = blockFace;
        this.success = success;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BlockOnInteractEvent) obj;
        return Objects.equals(this.player, that.player) &&
                Objects.equals(this.itemStack, that.itemStack) &&
                Objects.equals(this.world, that.world) &&
                Objects.equals(this.blockPos, that.blockPos) &&
                Objects.equals(this.placeBlockPos, that.placeBlockPos) &&
                Objects.equals(this.clickPos, that.clickPos) &&
                Objects.equals(this.blockFace, that.blockFace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, itemStack, world, blockPos, placeBlockPos, clickPos, blockFace);
    }

    @Override
    public String toString() {
        return "BlockOnInteractEvent[" +
                "player=" + player + ", " +
                "itemStack=" + itemStack + ", " +
                "world=" + world + ", " +
                "blockPos=" + blockPos + ", " +
                "placeBlockPos=" + placeBlockPos + ", " +
                "clickPos=" + clickPos + ", " +
                "blockFace=" + blockFace + ']';
    }

}
