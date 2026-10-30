package org.allaymc.server.container.impl;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.blockentity.data.BlockEntityId;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3ic;

/**
 * {@code FakeDoubleChestContainerImpl} represents a synthetic fifty-four-slot chest
 * interface using two client-side chest blocks. Each block's metadata names the other
 * block as its pair, so both halves form one large interface at the recorded primary
 * position.
 *
 * <p>
 * Publication sends both block states before their paired block-entity data and records
 * both positions before delayed opening. The first position identifies the window;
 * restoration later resends world states at both positions. Pair metadata is emitted in
 * both directions, keeping either half associated with the same synthetic double chest
 * rather than two unrelated windows.
 *
 * @author daoge_cmd
 */
public class FakeDoubleChestContainerImpl extends FakeContainerImpl {
    /**
     * Creates empty synthetic large-chest storage using the fake double-chest identity.
     */
    public FakeDoubleChestContainerImpl() {
        super(ContainerTypes.FAKE_DOUBLE_CHEST);
    }

    /**
     * Publishes two adjacent chest states, sends their pair metadata, and records both
     * fake positions for this player. The second position lies east of the first, which
     * remains the delayed window's opening coordinate.
     */
    @Override
    protected void sendFakeBlocks(Player player) {
        var pos1 = computeFakeBlockPos(player);
        var pos2 = BlockFace.EAST.offsetPos(pos1);

        // Both chest states precede their pair metadata so the later window open sees
        // two linked synthetic halves rather than unrelated block updates.
        player.viewBlockUpdate(pos1, 0, BlockTypes.CHEST.getDefaultState());
        player.viewBlockUpdate(pos2, 0, BlockTypes.CHEST.getDefaultState());
        sendFakeBlockEntities(player, pos1, pos2);

        this.fakeBlockPositions.put(player, new Vector3ic[]{pos1, pos2});
    }

    /**
     * Publishes block-entity metadata for both synthetic chest halves. Each call
     * reverses the position arguments so the client receives a pair reference in both
     * directions before the delayed window opens.
     */
    protected void sendFakeBlockEntities(Player player, Vector3ic pos1, Vector3ic pos2) {
        // Each half identifies the other in its pair fields; publish both directions
        // before opening the combined chest interface.
        sendFakeBlockEntity(player, pos1, pos2);
        sendFakeBlockEntity(player, pos2, pos1);
    }

    /**
     * Publishes one fake chest's coordinates and its partner's horizontal pair
     * coordinates. {@code pos1} identifies the metadata target, and {@code pos2}
     * identifies the other half; a configured custom title is included.
     */
    protected void sendFakeBlockEntity(Player player, Vector3ic pos1, Vector3ic pos2) {
        var nbt = NbtMap.builder()
                .putString("id", BlockEntityId.CHEST)
                .putInt("x", pos1.x())
                .putInt("y", pos1.y())
                .putInt("z", pos1.z())
                .putInt("pairx", pos2.x())
                .putInt("pairz", pos2.z());

        if (this.customName != null) {
            nbt.putString("CustomName", this.customName);
        }

        var allayPlayer = (AllayPlayer) player;
        allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeBlockEntityData(
                pos1,
                nbt.build()
        ));
    }
}
