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
import java.nio.IntBuffer;
import java.text.NumberFormat;

/**
 * {@code Position3i} represents a mutable integer position in a dimension. Coordinates use
 * the inherited JOML vector operations; the dimension association is held weakly, so a
 * value does not keep an otherwise unreachable dimension alive. The overridden arithmetic
 * methods mutate their receiver or destination and also return newly allocated positions
 * with this value's dimension.
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
public class Position3i extends Vector3i implements Position3ic {
    /**
     * {@code dimension} holds the associated dimension weakly, and a cleared referent
     * produces {@code null}. Callers must keep the public reference itself non-null
     * because {@code dimension()} dereferences it directly.
     */
    public WeakReference<Dimension> dimension;

    /**
     * Creates an integer position by copying coordinates from {@code p}. The new value
     * keeps its own weak reference to the same dimension, so it does not retain that
     * dimension alive.
     */
    public Position3i(Position3ic p) {
        super(p);
        this.dimension = new WeakReference<>(p.dimension());
    }

    /**
     * Creates an integer position at the coordinate origin in {@code dimension}. The
     * dimension is held through a weak reference, and {@code null} supplies an initially
     * absent dimension.
     */
    public Position3i(Dimension dimension) {
        super();
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position with every coordinate set to {@code d}. The supplied
     * {@code dimension} is held weakly and may be {@code null} when no dimension is
     * associated.
     */
    public Position3i(int d, Dimension dimension) {
        super(d);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position using the supplied {@code x}, {@code y}, and {@code z}
     * coordinates. The supplied {@code dimension} is held weakly and may be {@code null}
     * when no dimension is associated.
     */
    public Position3i(int x, int y, int z, Dimension dimension) {
        super(x, y, z);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position by copying the coordinates of {@code v}. The source
     * vector is not retained, while {@code dimension} is stored in a new weak reference.
     */
    public Position3i(Vector3ic v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position by copying the coordinates of {@code v} and uses the
     * supplied {@code z}. The source vector is not retained, while {@code dimension} is
     * stored in a new weak reference.
     */
    public Position3i(Vector2ic v, int z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position using the supplied {@code x}, {@code y}, and {@code z}
     * coordinates. The supplied {@code dimension} is held weakly and may be {@code null}
     * when no dimension is associated. JOML applies the supplied {@code mode} when
     * converting floating coordinates to integers.
     */
    public Position3i(float x, float y, float z, int mode, Dimension dimension) {
        super(x, y, z, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position using the supplied {@code x}, {@code y}, and {@code z}
     * coordinates. The supplied {@code dimension} is held weakly and may be {@code null}
     * when no dimension is associated. JOML applies the supplied {@code mode} when
     * converting floating coordinates to integers.
     */
    public Position3i(double x, double y, double z, int mode, Dimension dimension) {
        super(x, y, z, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position by copying the coordinates of {@code v} and uses the
     * supplied {@code z}. The source vector is not retained, while {@code dimension} is
     * stored in a new weak reference. JOML applies the supplied {@code mode} when
     * converting floating coordinates to integers.
     */
    public Position3i(Vector2fc v, float z, int mode, Dimension dimension) {
        super(v, z, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position by copying the coordinates of {@code v}. The source
     * vector is not retained, while {@code dimension} is stored in a new weak reference.
     * JOML applies the supplied {@code mode} when converting floating coordinates to
     * integers.
     */
    public Position3i(Vector3fc v, int mode, Dimension dimension) {
        super(v, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position by copying the coordinates of {@code v} and uses the
     * supplied {@code z}. The source vector is not retained, while {@code dimension} is
     * stored in a new weak reference. JOML applies the supplied {@code mode} when
     * converting floating coordinates to integers.
     */
    public Position3i(Vector2dc v, float z, int mode, Dimension dimension) {
        super(v, z, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position by copying the coordinates of {@code v}. The source
     * vector is not retained, while {@code dimension} is stored in a new weak reference.
     * JOML applies the supplied {@code mode} when converting floating coordinates to
     * integers.
     */
    public Position3i(Vector3dc v, int mode, Dimension dimension) {
        super(v, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position from the first three entries of {@code xyz}. The
     * coordinates are copied in {@code x}, {@code y}, {@code z} order, and
     * {@code dimension} is held weakly rather than retained alive.
     */
    public Position3i(int[] xyz, Dimension dimension) {
        super(xyz);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position from three coordinates at the current buffer position in
     * {@code buffer}. The byte offset selects {@code x}, {@code y}, then {@code z} without
     * advancing the buffer position; {@code dimension} is held weakly.
     */
    public Position3i(ByteBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position from three coordinates at the absolute {@code index} in
     * {@code buffer}. The byte offset selects {@code x}, {@code y}, then {@code z} without
     * advancing the buffer position; {@code dimension} is held weakly.
     */
    public Position3i(int index, ByteBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position from three coordinates at the current buffer position in
     * {@code buffer}. The element offset selects {@code x}, {@code y}, then {@code z}
     * without advancing the buffer position; {@code dimension} is held weakly.
     */
    public Position3i(IntBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    /**
     * Creates an integer position from three coordinates at the absolute {@code index} in
     * {@code buffer}. The element offset selects {@code x}, {@code y}, then {@code z}
     * without advancing the buffer position; {@code dimension} is held weakly.
     */
    public Position3i(int index, IntBuffer buffer, Dimension dimension) {
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
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified.
     */
    @Override
    public Position3i set(Vector3ic v) {
        return new Position3i(super.set(v), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified. JOML truncates the floating coordinates toward zero.
     */
    @Override
    public Position3i set(Vector3dc v) {
        return new Position3i(super.set(v), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified. JOML uses {@code mode} for floating-to-integer rounding.
     */
    @Override
    public Position3i set(Vector3dc v, int mode) {
        return new Position3i(super.set(v, mode), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified. JOML uses {@code mode} for floating-to-integer rounding.
     */
    @Override
    public Position3i set(Vector3fc v, int mode) {
        return new Position3i(super.set(v, mode), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified.
     */
    @Override
    public Position3i set(Vector2ic v, int z) {
        return new Position3i(super.set(v, z), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified.
     */
    @Override
    public Position3i set(int d) {
        return new Position3i(super.set(d), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified.
     */
    @Override
    public Position3i set(int x, int y, int z) {
        return new Position3i(super.set(x, y, z), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified.
     */
    @Override
    public Position3i set(int[] xyz) {
        return new Position3i(super.set(xyz), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified. Reading leaves the buffer position unchanged.
     */
    @Override
    public Position3i set(ByteBuffer buffer) {
        return new Position3i(super.set(buffer), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified. Reading leaves the buffer position unchanged.
     */
    @Override
    public Position3i set(int index, ByteBuffer buffer) {
        return new Position3i(super.set(index, buffer), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified. Reading leaves the buffer position unchanged.
     */
    @Override
    public Position3i set(IntBuffer buffer) {
        return new Position3i(super.set(buffer), dimension());
    }

    /**
     * Sets coordinates in this value and returns a new {@code Position3i} containing that
     * result. The returned position keeps this value's dimension, and the receiver is also
     * modified. Reading leaves the buffer position unchanged.
     */
    @Override
    public Position3i set(int index, IntBuffer buffer) {
        return new Position3i(super.set(index, buffer), dimension());
    }

    /**
     * Rejects raw-address vector access for this dimension-bearing value. This operation
     * always throws {@code UnsupportedOperationException}; the supplied address is never
     * read or written, and the current coordinates and dimension remain unchanged.
     */
    @Override
    public Position3i setFromAddress(long address) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the selected coordinate component in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified. The component index must be {@code 0},
     * {@code 1}, or {@code 2}.
     */
    @Override
    public Position3i setComponent(int component, int value) throws IllegalArgumentException {
        return new Position3i(super.setComponent(component, value), dimension());
    }

    /**
     * Rejects raw-address vector access for this dimension-bearing value. This operation
     * always throws {@code UnsupportedOperationException}; the supplied address is never
     * read or written, and the current coordinates and dimension remain unchanged.
     */
    @Override
    public Position3ic getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    /**
     * Subtracts the supplied coordinates in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i sub(Vector3ic v) {
        return new Position3i(super.sub(v), dimension());
    }

    /**
     * Subtracts the supplied coordinates into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i sub(Vector3ic v, Vector3i dest) {
        return new Position3i(super.sub(v, dest), dimension());
    }

    /**
     * Subtracts the supplied coordinates in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i sub(int x, int y, int z) {
        return new Position3i(super.sub(x, y, z), dimension());
    }

    /**
     * Subtracts the supplied coordinates into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i sub(int x, int y, int z, Vector3i dest) {
        return new Position3i(super.sub(x, y, z, dest), dimension());
    }

    /**
     * Adds the supplied coordinates in this value and returns a new {@code Position3i}
     * containing that result. The returned position keeps this value's dimension, and the
     * receiver is also modified.
     */
    @Override
    public Position3i add(Vector3ic v) {
        return new Position3i(super.add(v), dimension());
    }

    /**
     * Adds the supplied coordinates into {@code dest} and returns a new {@code Position3i}
     * containing that result. The returned position keeps this value's dimension, and the
     * destination vector is also modified.
     */
    @Override
    public Position3i add(Vector3ic v, Vector3i dest) {
        return new Position3i(super.add(v, dest), dimension());
    }

    /**
     * Adds the supplied coordinates in this value and returns a new {@code Position3i}
     * containing that result. The returned position keeps this value's dimension, and the
     * receiver is also modified.
     */
    @Override
    public Position3i add(int x, int y, int z) {
        return new Position3i(super.add(x, y, z), dimension());
    }

    /**
     * Adds the supplied coordinates into {@code dest} and returns a new {@code Position3i}
     * containing that result. The returned position keeps this value's dimension, and the
     * destination vector is also modified.
     */
    @Override
    public Position3i add(int x, int y, int z, Vector3i dest) {
        return new Position3i(super.add(x, y, z, dest), dimension());
    }

    /**
     * Multiplies coordinates by the supplied factors in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i mul(int scalar) {
        return new Position3i(super.mul(scalar), dimension());
    }

    /**
     * Multiplies coordinates by the supplied factors into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i mul(int scalar, Vector3i dest) {
        return new Position3i(super.mul(scalar, dest), dimension());
    }

    /**
     * Multiplies coordinates by the supplied factors in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i mul(Vector3ic v) {
        return new Position3i(super.mul(v), dimension());
    }

    /**
     * Multiplies coordinates by the supplied factors into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i mul(Vector3ic v, Vector3i dest) {
        return new Position3i(super.mul(v, dest), dimension());
    }

    /**
     * Multiplies coordinates by the supplied factors in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i mul(int x, int y, int z) {
        return new Position3i(super.mul(x, y, z), dimension());
    }

    /**
     * Multiplies coordinates by the supplied factors into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i mul(int x, int y, int z, Vector3i dest) {
        return new Position3i(super.mul(x, y, z, dest), dimension());
    }

    /**
     * Divides coordinates by the supplied scalar in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i div(float scalar) {
        return new Position3i(super.div(scalar), dimension());
    }

    /**
     * Divides coordinates by the supplied scalar into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i div(float scalar, Vector3i dest) {
        return new Position3i(super.div(scalar, dest), dimension());
    }

    /**
     * Divides coordinates by the supplied scalar in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i div(int scalar) {
        return new Position3i(super.div(scalar), dimension());
    }

    /**
     * Divides coordinates by the supplied scalar into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i div(int scalar, Vector3i dest) {
        return new Position3i(super.div(scalar, dest), dimension());
    }

    /**
     * Sets every coordinate to zero in this value and returns a new {@code Position3i}
     * containing that result. The returned position keeps this value's dimension, and the
     * receiver is also modified.
     */
    @Override
    public Position3i zero() {
        return new Position3i(super.zero(), dimension());
    }

    /**
     * Writes coordinates and dimension lookup data to {@code out} in the order consumed by
     * {@code readExternal}. The three coordinates use integer values in {@code x},
     * {@code y}, {@code z} order. The dimension must still be reachable when this
     * operation runs.
     *
     * <p>
     * The stream stores the world display name followed by the numeric dimension type
     * identifier, rather than serializing the world itself. Reading requires a live server
     * whose world pool can resolve that stored display name as a lookup key; a display
     * name differing from the loaded world key prevents that lookup.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(z);
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
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        // Resolve through the active world pool because the stream carries identifiers rather
        // than a serialized world or dimension instance.
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    /**
     * Negates the coordinate components in this value and returns a new {@code Position3i}
     * containing that result. The returned position keeps this value's dimension, and the
     * receiver is also modified.
     */
    @Override
    public Position3i negate() {
        return new Position3i(super.negate(), dimension());
    }

    /**
     * Negates the coordinate components into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i negate(Vector3i dest) {
        return new Position3i(super.negate(dest), dimension());
    }

    /**
     * Selects componentwise minimum coordinates in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i min(Vector3ic v) {
        return new Position3i(super.min(v), dimension());
    }

    /**
     * Selects componentwise minimum coordinates into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i min(Vector3ic v, Vector3i dest) {
        return new Position3i(super.min(v, dest), dimension());
    }

    /**
     * Selects componentwise maximum coordinates in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i max(Vector3ic v) {
        return new Position3i(super.max(v), dimension());
    }

    /**
     * Selects componentwise maximum coordinates into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i max(Vector3ic v, Vector3i dest) {
        return new Position3i(super.max(v, dest), dimension());
    }

    /**
     * Takes the absolute value of every coordinate in this value and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the receiver is also modified.
     */
    @Override
    public Position3i absolute() {
        return new Position3i(super.absolute(), dimension());
    }

    /**
     * Takes the absolute value of every coordinate into {@code dest} and returns a new
     * {@code Position3i} containing that result. The returned position keeps this value's
     * dimension, and the destination vector is also modified.
     */
    @Override
    public Position3i absolute(Vector3i dest) {
        return new Position3i(super.absolute(dest), dimension());
    }

    /**
     * Returns a shallow clone with separate primitive coordinate storage. The clone shares
     * the same {@code dimension} weak-reference object, so both values initially observe
     * the same referent and its eventual collection.
     */
    @Override
    public Position3i clone() throws CloneNotSupportedException {
        return (Position3i) super.clone();
    }

    /**
     * Returns whether the other value has matching coordinates and dimension. JOML also
     * requires matching runtime classes, and dimension comparison observes the current
     * weak referents rather than the weak-reference objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3ic that)) return false;
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
