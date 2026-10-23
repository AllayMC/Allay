package org.allaymc.server.command.tree.node;

import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandNode;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParam;
import org.cloudburstmc.protocol.bedrock.data.command.CommandParamData;
import org.joml.Vector3d;

/**
 * {@code PosNode} parses three consecutive coordinate tokens into one
 * {@code Vector3d} result relative to the current sender's location. Absolute
 * values use float parsing before widening, and tilde values add a parsed float
 * offset to the corresponding double-precision origin coordinate. The node
 * advertises position syntax and a maximum token cost of three.
 *
 * <p> Matching queries all three tokens before changing context state. A
 * conversion or indexed-read failure returns {@code false} without consuming a
 * partial position or appending a result, so sibling selection sees the
 * original cursor. Only after every coordinate succeeds does the node append
 * the vector and consume exactly three arguments. The constructor's optional
 * default remains a retained object and bypasses coordinate parsing when
 * selected without input.
 *
 * @author daoge_cmd
 */
public class PosNode extends BaseNode {
    /**
     * Creates an unattached position node retaining the supplied parent and
     * omission default. Optional omission inserts that object directly; normal
     * matching instead allocates a new {@code Vector3d} from three parsed
     * coordinates.
     */
    public PosNode(String name, CommandNode parent, Object defaultValue) {
        super(name, parent, defaultValue);
    }

    /**
     * Parses three coordinates against the sender's current location and
     * appends one vector result. Absolute tokens use float precision, while
     * tilde tokens add their parsed offset to the corresponding origin
     * coordinate. The sender location is read before coordinate parsing begins.
     *
     * <p> All indexed reads and conversions happen before result insertion or
     * token consumption. An exception in that parsing block returns
     * {@code false} with both cursors unchanged; a missing sender location
     * fails earlier and propagates. Success appends a new {@code Vector3d} and
     * then consumes exactly three tokens, preserving their association with the
     * single position result.
     */
    @Override
    public boolean match(CommandContext context) {
        var basePos = context.getSender().getCommandExecuteLocation();
        var baseX = basePos.x();
        var baseY = basePos.y();
        var baseZ = basePos.z();

        double x, y, z;
        try {
            var index = context.getCurrentArgIndex();
            x = getRelative(context.queryArg(index), baseX);
            y = getRelative(context.queryArg(index + 1), baseY);
            z = getRelative(context.queryArg(index + 2), baseZ);
        } catch (Exception e) {
            // A partial coordinate triple must not advance parsing. All three reads
            // and conversions finish before this node appends a result or consumes input.
            return false;
        }
        context.putResult(new Vector3d(x, y, z));
        context.popArg();
        context.popArg();
        context.popArg();
        return true;
    }

    /**
     * Returns an absolute float-parsed coordinate or an offset from
     * {@code base} for tilde input. A bare tilde returns {@code base}; longer
     * relative input drops its first character before float parsing, and
     * invalid numeric text propagates.
     */
    protected double getRelative(String token, double base) {
        // Absolute tokens are parsed at float precision before widening to double.
        if (!token.contains("~")) return Float.parseFloat(token);

        if (token.length() > 1) return base + Float.parseFloat(token.substring(1));
        else return base;
    }

    /**
     * Returns three as the maximum token cost of one coordinate triple.
     */
    @Override
    public int getMaxArgCost() {
        return 3;
    }

    /**
     * Creates client metadata advertising {@code CommandParam.POSITION} for
     * three-coordinate input.
     */
    @Override
    public CommandParamData toNetworkData() {
        var data = super.toNetworkData();
        data.setType(CommandParam.POSITION);
        return data;
    }
}
