package org.allaymc.api.math.position;

import com.google.common.base.Objects;
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
 * {@code Position3f} represents a mutable single-precision position in a dimension.
 * Coordinates use the inherited JOML vector operations; the dimension association is held
 * weakly, so a value does not keep an otherwise unreachable dimension alive.
 *
 * <p>
 * Externalization stores the coordinates followed by a world display name and numeric
 * dimension type identifier. Restoration resolves those identifiers through the running
 * server instead of reconstructing a world. The loaded world key must match the stored
 * display name for that lookup to succeed. Mutable coordinates and weak dimension
 * associations also make these values unsuitable as stable hash keys while their observed
 * state can change.
 * @author Cool_Loong
 */
public class Position3f extends Vector3f implements Position3fc {
    /**
     * {@code dimension} holds the associated dimension weakly, and a cleared referent
     * produces {@code null}. Callers must keep the public reference itself non-null
     * because {@code dimension()} dereferences it directly.
     */
    public WeakReference<Dimension> dimension;

    /**
     * Creates a single-precision position by copying coordinates from {@code p}. The new
     * value keeps its own weak reference to the same dimension, so it does not retain that
     * dimension alive.
     */
    public Position3f(Position3ic p) {
        super(p);
        this.dimension = new WeakReference<>(p.dimension());
    }

    /**
     * Creates a single-precision position by copying coordinates from {@code p}. The new
     * value keeps its own weak reference to the same dimension, so it does not retain that
     * dimension alive.
     */
    public Position3f(Position3fc p) {
        super(p);
        this.dimension = new WeakReference<>(p.dimension());
    }

    /**
     * Creates a single-precision position at the coordinate origin in {@code dimension}.
     * The dimension is held through a weak reference, and {@code null} supplies an
     * initially absent dimension.
     */
    public Position3f(Dimension dimension) {
        super();
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position with every coordinate set to {@code d}. The
     * supplied {@code dimension} is held weakly and may be {@code null} when no dimension
     * is associated.
     */
    public Position3f(float d, Dimension dimension) {
        super(d);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position using the supplied {@code x}, {@code y}, and
     * {@code z} coordinates. The supplied {@code dimension} is held weakly and may be
     * {@code null} when no dimension is associated.
     */
    public Position3f(float x, float y, float z, Dimension dimension) {
        super(x, y, z);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position by copying the coordinates of {@code v}. The
     * source vector is not retained, while {@code dimension} is stored in a new weak
     * reference.
     */
    public Position3f(Vector3fc v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position by copying the coordinates of {@code v}. The
     * source vector is not retained, while {@code dimension} is stored in a new weak
     * reference.
     */
    public Position3f(Vector3ic v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position by copying the coordinates of {@code v} and uses
     * the supplied {@code z}. The source vector is not retained, while {@code dimension}
     * is stored in a new weak reference.
     */
    public Position3f(Vector2fc v, float z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position by copying the coordinates of {@code v} and uses
     * the supplied {@code z}. The source vector is not retained, while {@code dimension}
     * is stored in a new weak reference.
     */
    public Position3f(Vector2ic v, float z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position from the first three entries of {@code xyz}. The
     * coordinates are copied in {@code x}, {@code y}, {@code z} order, and
     * {@code dimension} is held weakly rather than retained alive.
     */
    public Position3f(float[] xyz, Dimension dimension) {
        super(xyz);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position from three coordinates at the current buffer
     * position in {@code buffer}. The byte offset selects {@code x}, {@code y}, then
     * {@code z} without advancing the buffer position; {@code dimension} is held weakly.
     */
    public Position3f(ByteBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position from three coordinates at the absolute
     * {@code index} in {@code buffer}. The byte offset selects {@code x}, {@code y}, then
     * {@code z} without advancing the buffer position; {@code dimension} is held weakly.
     */
    public Position3f(int index, ByteBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position from three coordinates at the current buffer
     * position in {@code buffer}. The element offset selects {@code x}, {@code y}, then
     * {@code z} without advancing the buffer position; {@code dimension} is held weakly.
     */
    public Position3f(FloatBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates a single-precision position from three coordinates at the absolute
     * {@code index} in {@code buffer}. The element offset selects {@code x}, {@code y},
     * then {@code z} without advancing the buffer position; {@code dimension} is held
     * weakly.
     */
    public Position3f(int index, FloatBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Returns the dimension currently reachable through the weak reference. The result is
     * {@code null} when the reference was created with {@code null} or its referent has
     * been collected.
     */
    @Override
    public Dimension dimension() {
        return dimension.get();
    }

    /**
     * Replaces the associated dimension with a newly created weak reference. The supplied
     * value may be {@code null}, and changing the association does not modify any
     * coordinate or retain the dimension alive.
     */
    public void setDimension(Dimension dimension) {
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Rejects raw-address vector access for this dimension-bearing value. This operation
     * always throws {@code UnsupportedOperationException}; the supplied address is never
     * read or written, and the current coordinates and dimension remain unchanged.
     */
    @Override
    public Position3fc getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    /**
     * Writes coordinates and dimension lookup data to {@code out} in the order consumed by
     * {@code readExternal}. The three coordinates use single-precision values in
     * {@code x}, {@code y}, {@code z} order. The dimension must still be reachable when
     * this operation runs.
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
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        // Resolve through the active world pool because the stream carries identifiers rather
        // than a serialized world or dimension instance.
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    /**
     * Returns whether the other value has matching coordinates and dimension. JOML also
     * requires matching runtime classes, and dimension comparison observes the current
     * weak referents rather than the weak-reference objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3fc that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(dimension.get(), that.dimension());
    }

    /**
     * Returns a hash of the coordinates and current dimension. Coordinate mutation or a
     * changed weak referent can change this result, so callers must avoid such changes
     * while using this value as a hash key.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), dimension.get());
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
     * Returns formatted coordinates and the current world and dimension identifiers. The
     * dimension must remain reachable because this method dereferences its world metadata
     * while constructing the text.
     */
    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " dimension=" + this.dimension().getWorld().getWorldData().getDisplayName() + ":" + this.dimension().getDimensionType().getIdentifier() + ")";
    }
}
