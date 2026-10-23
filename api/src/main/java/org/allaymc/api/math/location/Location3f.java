package org.allaymc.api.math.location;

import com.google.common.base.Objects;
import lombok.Setter;
import org.allaymc.api.math.position.Position3f;
import org.allaymc.api.server.Server;
import org.allaymc.api.world.Dimension;
import org.joml.*;
import org.joml.Runtime;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.NumberFormat;

/**
 * {@code Location3f} represents a mutable single-precision position with {@code pitch} and
 * {@code yaw} angles in degrees in a dimension. Coordinates use the inherited JOML vector
 * operations; the dimension association is held weakly, so a value does not keep an
 * otherwise unreachable dimension alive.
 *
 * <p>
 * Externalization stores the coordinates and angles followed by a world display name and
 * numeric dimension type identifier. Restoration resolves those identifiers through the
 * running server instead of reconstructing a world. The loaded world key must match the
 * stored display name for that lookup to succeed. Mutable coordinates, angles, and weak
 * dimension associations also make these values unsuitable as stable hash keys while their
 * observed state can change.
 * @author Cool_Loong
 */
@Setter
public class Location3f extends Position3f implements Location3fc {

    /**
     * Vertical viewing angle in degrees; this value is stored without normalization.
     */
    public double pitch;
    /**
     * Horizontal viewing angle in degrees; this value is stored without normalization.
     */
    public double yaw;

    /**
     * Creates a single-precision location by copying coordinates and orientation from
     * {@code l}. The new value keeps its own weak reference to the same dimension, so it
     * does not retain that dimension alive.
     */
    public Location3f(Location3fc l) {
        super(l);
        this.pitch = l.pitch();
        this.yaw = l.yaw();
    }

    /**
     * Creates a single-precision location by copying coordinates and orientation from
     * {@code l}. The new value keeps its own weak reference to the same dimension, so it
     * does not retain that dimension alive.
     */
    public Location3f(Location3ic l) {
        super(l.x(), l.y(), l.z(), l.dimension());
        this.pitch = l.pitch();
        this.yaw = l.yaw();
    }

    /**
     * Creates a single-precision location using the supplied {@code x}, {@code y}, and
     * {@code z} coordinates. The supplied {@code dimension} is held weakly and may be
     * {@code null} when no dimension is associated. The orientation starts with zero
     * {@code pitch} and {@code yaw}.
     */
    public Location3f(float x, float y, float z, Dimension dimension) {
        super(x, y, z, dimension);
    }

    /**
     * Creates a single-precision location with every coordinate set to {@code d}. The
     * supplied {@code dimension} is held weakly and may be {@code null} when no dimension
     * is associated. The supplied {@code pitch} and {@code yaw} are stored in degrees
     * without normalization.
     */
    public Location3f(float d, double pitch, double yaw, Dimension dimension) {
        super(d, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location using the supplied {@code x}, {@code y}, and
     * {@code z} coordinates. The supplied {@code dimension} is held weakly and may be
     * {@code null} when no dimension is associated. The supplied {@code pitch} and
     * {@code yaw} are stored in degrees without normalization.
     */
    public Location3f(float x, float y, float z, double pitch, double yaw, Dimension dimension) {
        super(x, y, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location by copying the coordinates of {@code v}. The
     * source vector is not retained, while {@code dimension} is stored in a new weak
     * reference. The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3f(Vector3fc v, double pitch, double yaw, Dimension dimension) {
        super(v, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location by copying the coordinates of {@code v}. The
     * source vector is not retained, while {@code dimension} is stored in a new weak
     * reference. The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3f(Vector3ic v, double pitch, double yaw, Dimension dimension) {
        super(v, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location by copying the coordinates of {@code v} and uses
     * the supplied {@code z}. The source vector is not retained, while {@code dimension}
     * is stored in a new weak reference. The supplied {@code pitch} and {@code yaw} are
     * stored in degrees without normalization.
     */
    public Location3f(Vector2fc v, float z, double pitch, double yaw, Dimension dimension) {
        super(v, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location by copying the coordinates of {@code v} and uses
     * the supplied {@code z}. The source vector is not retained, while {@code dimension}
     * is stored in a new weak reference. The supplied {@code pitch} and {@code yaw} are
     * stored in degrees without normalization.
     */
    public Location3f(Vector2ic v, float z, double pitch, double yaw, Dimension dimension) {
        super(v, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location from the first three entries of {@code xyz}. The
     * coordinates are copied in {@code x}, {@code y}, {@code z} order, and
     * {@code dimension} is held weakly rather than retained alive. The supplied
     * {@code pitch} and {@code yaw} are stored in degrees without normalization.
     */
    public Location3f(float[] xyz, double pitch, double yaw, Dimension dimension) {
        super(xyz, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location from three coordinates at the current buffer
     * position in {@code buffer}. The byte offset selects {@code x}, {@code y}, then
     * {@code z} without advancing the buffer position; {@code dimension} is held weakly.
     * The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3f(ByteBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location from three coordinates at the absolute
     * {@code index} in {@code buffer}. The byte offset selects {@code x}, {@code y}, then
     * {@code z} without advancing the buffer position; {@code dimension} is held weakly.
     * The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3f(int index, ByteBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(index, buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location from three coordinates at the current buffer
     * position in {@code buffer}. The element offset selects {@code x}, {@code y}, then
     * {@code z} without advancing the buffer position; {@code dimension} is held weakly.
     * The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3f(FloatBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates a single-precision location from three coordinates at the absolute
     * {@code index} in {@code buffer}. The element offset selects {@code x}, {@code y},
     * then {@code z} without advancing the buffer position; {@code dimension} is held
     * weakly. The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3f(int index, FloatBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(index, buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Returns the stored {@code pitch} angle in degrees without normalization.
     */
    @Override
    public double pitch() {
        return pitch;
    }

    /**
     * Returns the stored {@code yaw} angle in degrees without normalization.
     */
    @Override
    public double yaw() {
        return yaw;
    }

    /**
     * Writes coordinates and dimension lookup data to {@code out} in the order consumed by
     * {@code readExternal}. The three coordinates use single-precision values in
     * {@code x}, {@code y}, {@code z} order. The stream then receives {@code pitch} and
     * {@code yaw} as doubles. The dimension must still be reachable when this operation
     * runs.
     *
     * <p>
     * The stream stores the world display name followed by the numeric dimension type
     * identifier, rather than serializing the world itself. Reading requires a live server
     * whose world pool can resolve that stored display name as a lookup key; a display
     * name differing from the loaded world key prevents that lookup.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
        out.writeDouble(pitch);
        out.writeDouble(yaw);
        // Write the world lookup key before the dimension identifier so readExternal can
        // resolve the dimension within that world.
        out.writeUTF(this.dimension().getWorld().getWorldData().getDisplayName());
        out.writeInt(this.dimension().getDimensionType().getId());
    }

    /**
     * Reads the coordinate values and dimension lookup data written by
     * {@code writeExternal}. This method replaces existing coordinates before resolving
     * the stored world display name through the current server world pool. A lookup
     * failure can therefore leave this value partially updated.
     *
     * <p>
     * The numeric dimension type identifier selects a dimension from that loaded world,
     * and a new weak reference stores the result. The stream does not load missing worlds
     * or preserve a strong reference to the resolved dimension. Callers must arrange
     * compatible loaded world keys before deserializing these values.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException {
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        pitch = in.readDouble();
        yaw = in.readDouble();
        // Resolve through the active world pool because the stream carries identifiers rather
        // than a serialized world or dimension instance.
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    /**
     * Returns whether the other value has matching coordinates and dimension and
     * orientation. JOML also requires matching runtime classes, and dimension comparison
     * observes the current weak referents rather than the weak-reference objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location3fc that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(dimension.get(), that.dimension()) && Objects.equal(pitch, that.pitch()) && Objects.equal(yaw, that.yaw());
    }

    /**
     * Returns a hash of the coordinates and current dimension and orientation. Coordinate
     * mutation or a changed weak referent can change this result, so callers must avoid
     * such changes while using this value as a hash key.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), pitch, yaw, dimension.get());
    }

    /**
     * Returns this value using JOML's configured number formatter. The dimension must
     * remain reachable because the formatted representation includes its world display
     * name and dimension identifier.
     */
    @Override
    public String toString() {
        return toString(Options.NUMBER_FORMAT);
    }

    /**
     * Returns formatted coordinates and the current world and dimension identifiers
     * together with orientation. The dimension must remain reachable because this method
     * dereferences its world metadata while constructing the text.
     */
    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " pitch=" + this.pitch + " yaw=" + this.yaw + " dimension=" + this.dimension().getWorld().getWorldData().getDisplayName() + ":" + this.dimension().getDimensionType().getIdentifier() + ")";
    }
}
