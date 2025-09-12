package org.allaymc.server.entity.component.player;

import org.allaymc.api.debugshape.*;
import org.allaymc.api.entity.component.player.EntityPlayerDebugShapeViewerComponent;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.protocol.bedrock.packet.ServerScriptDebugDrawerPacket;

import java.util.Set;

import static org.cloudburstmc.protocol.bedrock.data.DebugShape.Type.*;

/**
 * @author daoge_cmd
 */
public class EntityPlayerDebugShapeViewerComponentImpl implements EntityPlayerDebugShapeViewerComponent {

    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_player_debug_shape_viewer_component");

    @Dependency
    protected EntityPlayerNetworkComponentImpl networkComponent;

    @Override
    public void viewDebugShape(DebugShape debugShape) {
        var packet = new ServerScriptDebugDrawerPacket();
        packet.getShapes().add(toNetworkData(debugShape));
        networkComponent.sendPacket(packet);
    }

    @Override
    public void viewDebugShapes(Set<DebugShape> debugShapes) {
        var packet = new ServerScriptDebugDrawerPacket();
        for (var debugShape : debugShapes) {
            packet.getShapes().add(toNetworkData(debugShape));
        }
        networkComponent.sendPacket(packet);
    }

    @Override
    public void removeDebugShape(DebugShape debugShape) {
        var packet = new ServerScriptDebugDrawerPacket();
        packet.getShapes().add(createRemovalNotice(debugShape));
        networkComponent.sendPacket(packet);
    }

    @Override
    public void removeDebugShapes(Set<DebugShape> debugShapes) {
        var packet = new ServerScriptDebugDrawerPacket();
        for (var debugShape : debugShapes) {
            packet.getShapes().add(createRemovalNotice(debugShape));
        }
        networkComponent.sendPacket(packet);
    }

    protected static org.cloudburstmc.protocol.bedrock.data.DebugShape createRemovalNotice(DebugShape shape) {
        return new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                shape.getId(), null, null,
                null, null, null,
                null, null, null,
                null, null, null, null
        );
    }

    protected static org.cloudburstmc.protocol.bedrock.data.DebugShape toNetworkData(DebugShape shape) {
        return switch (shape) {
            case DebugArrow arrow -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    arrow.getId(), ARROW, MathUtils.toCBVec(arrow.getPosition()), arrow.getArrowHeadScale(),
                    null, null, arrow.getColor(),
                    null, null, MathUtils.toCBVec(arrow.getEndPosition()),
                    arrow.getArrowHeadLength(), arrow.getArrowHeadRadius(), arrow.getArrowHeadSegments()
            );
            case DebugBox box -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    box.getId(), BOX, MathUtils.toCBVec(box.getPosition()), box.getScale(),
                    null, null, box.getColor(),
                    null, MathUtils.toCBVec(box.getBoxBounds()), null,
                    null, null, null
            );
            case DebugCircle circle -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    circle.getId(), CIRCLE, MathUtils.toCBVec(circle.getPosition()), circle.getScale(),
                    null, null, circle.getColor(),
                    null, null, null,
                    null, null, circle.getSegments()
            );
            case DebugLine line -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    line.getId(), LINE, MathUtils.toCBVec(line.getPosition()), null,
                    null, null, line.getColor(),
                    null, null, MathUtils.toCBVec(line.getEndPosition()),
                    null, null, null
            );
            case DebugSphere sphere -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    sphere.getId(), SPHERE, MathUtils.toCBVec(sphere.getPosition()), sphere.getScale(),
                    null, null, sphere.getColor(),
                    null, null, null,
                    null, null, sphere.getSegments()
            );
            case DebugText text -> new org.cloudburstmc.protocol.bedrock.data.DebugShape(
                    text.getId(), TEXT, MathUtils.toCBVec(text.getPosition()), null,
                    null, null, text.getColor(),
                    text.getText(), null, null,
                    null, null, null
            );
            default -> throw new IllegalStateException("Unexpected value: " + shape);
        };
    }
}
