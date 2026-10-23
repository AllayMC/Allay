package org.allaymc.api.math.location;

import com.google.common.base.Objects;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
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
 * {@code Location3i} represents a mutable integer position with {@code pitch} and
 * {@code yaw} angles in degrees in a dimension. Coordinates use the inherited JOML vector
 * operations; the dimension association is held weakly, so a value does not keep an
 * otherwise unreachable dimension alive. The overridden coordinate operations return this
 * location even when an explicit destination receives the coordinate result.
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
public class Location3i extends Position3i implements Location3ic {
    /**
     * Vertical viewing angle in degrees; this value is stored without normalization.
     */
    public double pitch;
    /**
     * Horizontal viewing angle in degrees; this value is stored without normalization.
     */
    public double yaw;

    /**
     * Creates an integer location by copying coordinates and orientation from {@code l}.
     * The new value keeps its own weak reference to the same dimension, so it does not
     * retain that dimension alive.
     */
    public Location3i(Location3ic l) {
        super(l);
        this.pitch = l.pitch();
        this.yaw = l.yaw();
    }

    /**
     * Creates an integer location by copying coordinates from {@code p}. The new value
     * keeps its own weak reference to the same dimension, so it does not retain that
     * dimension alive. The orientation starts at zero degrees.
     */
    public Location3i(Position3ic p) {
        super(p);
        this.pitch = 0;
        this.yaw = 0;
    }

    /**
     * Creates an integer location using the supplied {@code x}, {@code y}, and {@code z}
     * coordinates. The supplied {@code dimension} is held weakly and may be {@code null}
     * when no dimension is associated. The orientation starts with zero {@code pitch} and
     * {@code yaw}.
     */
    public Location3i(int x, int y, int z, Dimension dimension) {
        super(x, y, z, dimension);
    }

    /**
     * Creates an integer location with every coordinate set to {@code d}. The supplied
     * {@code dimension} is held weakly and may be {@code null} when no dimension is
     * associated. The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3i(int d, double pitch, double yaw, Dimension dimension) {
        super(d, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location using the supplied {@code x}, {@code y}, and {@code z}
     * coordinates. The supplied {@code dimension} is held weakly and may be {@code null}
     * when no dimension is associated. The supplied {@code pitch} and {@code yaw} are
     * stored in degrees without normalization.
     */
    public Location3i(int x, int y, int z, double pitch, double yaw, Dimension dimension) {
        super(x, y, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location by copying the coordinates of {@code v}. The source
     * vector is not retained, while {@code dimension} is stored in a new weak reference.
     * The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3i(Vector3ic v, double pitch, double yaw, Dimension dimension) {
        super(v, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location by copying the coordinates of {@code v} and uses the
     * supplied {@code z}. The source vector is not retained, while {@code dimension} is
     * stored in a new weak reference. The supplied {@code pitch} and {@code yaw} are
     * stored in degrees without normalization.
     */
    public Location3i(Vector2ic v, int z, double pitch, double yaw, Dimension dimension) {
        super(v, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location using the supplied {@code x}, {@code y}, and {@code z}
     * coordinates. The supplied {@code dimension} is held weakly and may be {@code null}
     * when no dimension is associated. JOML applies the supplied {@code mode} when
     * converting floating coordinates to integers. The supplied {@code pitch} and
     * {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(float x, float y, float z, int mode, double pitch, double yaw, Dimension dimension) {
        super(x, y, z, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location using the supplied {@code x}, {@code y}, and {@code z}
     * coordinates. The supplied {@code dimension} is held weakly and may be {@code null}
     * when no dimension is associated. JOML applies the supplied {@code mode} when
     * converting floating coordinates to integers. The supplied {@code pitch} and
     * {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(double x, double y, double z, int mode, double pitch, double yaw, Dimension dimension) {
        super(x, y, z, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location by copying the coordinates of {@code v} and uses the
     * supplied {@code z}. The source vector is not retained, while {@code dimension} is
     * stored in a new weak reference. JOML applies the supplied {@code mode} when
     * converting floating coordinates to integers. The supplied {@code pitch} and
     * {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(Vector2fc v, float z, int mode, double pitch, double yaw, Dimension dimension) {
        super(v, z, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location by copying the coordinates of {@code v}. The source
     * vector is not retained, while {@code dimension} is stored in a new weak reference.
     * JOML applies the supplied {@code mode} when converting floating coordinates to
     * integers. The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3i(Vector3fc v, int mode, double pitch, double yaw, Dimension dimension) {
        super(v, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location by copying the coordinates of {@code v} and uses the
     * supplied {@code z}. The source vector is not retained, while {@code dimension} is
     * stored in a new weak reference. JOML applies the supplied {@code mode} when
     * converting floating coordinates to integers. The supplied {@code pitch} and
     * {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(Vector2dc v, float z, int mode, double pitch, double yaw, Dimension dimension) {
        super(v, z, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location by copying the coordinates of {@code v}. The source
     * vector is not retained, while {@code dimension} is stored in a new weak reference.
     * JOML applies the supplied {@code mode} when converting floating coordinates to
     * integers. The supplied {@code pitch} and {@code yaw} are stored in degrees without
     * normalization.
     */
    public Location3i(Vector3dc v, int mode, double pitch, double yaw, Dimension dimension) {
        super(v, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location from the first three entries of {@code xyz}. The
     * coordinates are copied in {@code x}, {@code y}, {@code z} order, and
     * {@code dimension} is held weakly rather than retained alive. The supplied
     * {@code pitch} and {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(int[] xyz, double pitch, double yaw, Dimension dimension) {
        super(xyz, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location from three coordinates at the current buffer position in
     * {@code buffer}. The byte offset selects {@code x}, {@code y}, then {@code z} without
     * advancing the buffer position; {@code dimension} is held weakly. The supplied
     * {@code pitch} and {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(ByteBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location from three coordinates at the absolute {@code index} in
     * {@code buffer}. The byte offset selects {@code x}, {@code y}, then {@code z} without
     * advancing the buffer position; {@code dimension} is held weakly. The supplied
     * {@code pitch} and {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(int index, ByteBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(index, buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location from three coordinates at the current buffer position in
     * {@code buffer}. The element offset selects {@code x}, {@code y}, then {@code z}
     * without advancing the buffer position; {@code dimension} is held weakly. The
     * supplied {@code pitch} and {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(IntBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    /**
     * Creates an integer location from three coordinates at the absolute {@code index} in
     * {@code buffer}. The element offset selects {@code x}, {@code y}, then {@code z}
     * without advancing the buffer position; {@code dimension} is held weakly. The
     * supplied {@code pitch} and {@code yaw} are stored in degrees without normalization.
     */
    public Location3i(int index, IntBuffer buffer, double pitch, double yaw, Dimension dimension) {
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
     * Stores the supplied {@code yaw} angle in degrees without normalization.
     */
    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    /**
     * Stores the supplied {@code pitch} angle in degrees without normalization.
     */
    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    /**
     * Copies the three integer coordinates from {@code v} into this location and returns
     * this location. The operation preserves its dimension, {@code pitch}, and
     * {@code yaw}; no source-vector reference is retained.
     */
    @Override
    public Location3i set(Vector3ic v) {
        super.set(v);
        return this;
    }

    /**
     * Sets coordinates in this value and returns this location. The operation preserves
     * this location's dimension, {@code pitch}, and {@code yaw} while modifying its
     * coordinates in place. JOML truncates the floating coordinates toward zero.
     */
    @Override
    public Location3i set(Vector3dc v) {
        super.set(v);
        return this;
    }

    /**
     * Sets coordinates in this value and returns this location. The operation preserves
     * this location's dimension, {@code pitch}, and {@code yaw} while modifying its
     * coordinates in place. JOML uses {@code mode} for floating-to-integer rounding.
     */
    @Override
    public Location3i set(Vector3dc v, int mode) {
        super.set(v, mode);
        return this;
    }

    /**
     * Sets coordinates in this value and returns this location. The operation preserves
     * this location's dimension, {@code pitch}, and {@code yaw} while modifying its
     * coordinates in place. JOML uses {@code mode} for floating-to-integer rounding.
     */
    @Override
    public Location3i set(Vector3fc v, int mode) {
        super.set(v, mode);
        return this;
    }

    /**
     * Copies the two coordinates of {@code v} and the supplied {@code z} into this
     * location. The result is this same location, preserving its dimension, {@code pitch},
     * and {@code yaw}.
     */
    @Override
    public Location3i set(Vector2ic v, int z) {
        super.set(v, z);
        return this;
    }

    /**
     * Sets every coordinate in this location to {@code d} and returns this location. The
     * dimension association, {@code pitch}, and {@code yaw} retain their existing values
     * after the coordinate update.
     */
    @Override
    public Location3i set(int d) {
        super.set(d);
        return this;
    }

    /**
     * Sets this location's three coordinates in place to the supplied {@code x}, {@code y}, and
     * {@code z}. The result is this same location, preserving its dimension,
     * {@code pitch}, and {@code yaw}.
     */
    @Override
    public Location3i set(int x, int y, int z) {
        super.set(x, y, z);
        return this;
    }

    /**
     * Copies the first three entries of {@code xyz} into this location in coordinate
     * order. The result is this same location, preserving its dimension, {@code pitch},
     * and {@code yaw}.
     */
    @Override
    public Location3i set(int[] xyz) {
        super.set(xyz);
        return this;
    }

    /**
     * Sets coordinates in this value and returns this location. The operation preserves
     * this location's dimension, {@code pitch}, and {@code yaw} while modifying its
     * coordinates in place. Reading leaves the buffer position unchanged.
     */
    @Override
    public Location3i set(ByteBuffer buffer) {
        super.set(buffer);
        return this;
    }

    /**
     * Sets coordinates in this value and returns this location. The operation preserves
     * this location's dimension, {@code pitch}, and {@code yaw} while modifying its
     * coordinates in place. Reading leaves the buffer position unchanged.
     */
    @Override
    public Location3i set(int index, ByteBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    /**
     * Sets coordinates in this value and returns this location. The operation preserves
     * this location's dimension, {@code pitch}, and {@code yaw} while modifying its
     * coordinates in place. Reading leaves the buffer position unchanged.
     */
    @Override
    public Location3i set(IntBuffer buffer) {
        super.set(buffer);
        return this;
    }

    /**
     * Sets coordinates in this value and returns this location. The operation preserves
     * this location's dimension, {@code pitch}, and {@code yaw} while modifying its
     * coordinates in place. Reading leaves the buffer position unchanged.
     */
    @Override
    public Location3i set(int index, IntBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    /**
     * Rejects raw-address vector access for this dimension-bearing value. This operation
     * always throws {@code UnsupportedOperationException}; the supplied address is never
     * read or written, and the current coordinates and dimension remain unchanged.
     */
    @Override
    public Location3i setFromAddress(long address) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets the selected coordinate component in this value and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw} while
     * modifying its coordinates in place. The component index must be {@code 0},
     * {@code 1}, or {@code 2}.
     */
    @Override
    public Location3i setComponent(int component, int value) throws IllegalArgumentException {
        super.setComponent(component, value);
        return this;
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
     * Subtracts the supplied coordinates in this value and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw} while
     * modifying its coordinates in place.
     */
    @Override
    public Location3i sub(Vector3ic v) {
        super.sub(v);
        return this;
    }

    /**
     * Subtracts the supplied coordinates into {@code dest} and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw}; a
     * distinct destination receives coordinates while the returned receiver retains its
     * existing coordinates.
     */
    @Override
    public Location3i sub(Vector3ic v, Vector3i dest) {
        super.sub(v, dest);
        return this;
    }

    /**
     * Subtracts the supplied coordinates in this value and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw} while
     * modifying its coordinates in place.
     */
    @Override
    public Location3i sub(int x, int y, int z) {
        super.sub(x, y, z);
        return this;
    }

    /**
     * Subtracts the supplied coordinates into {@code dest} and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw}; a
     * distinct destination receives coordinates while the returned receiver retains its
     * existing coordinates.
     */
    @Override
    public Location3i sub(int x, int y, int z, Vector3i dest) {
        super.sub(x, y, z, dest);
        return this;
    }

    /**
     * Adds the supplied coordinates in this value and returns this location. The operation
     * preserves this location's dimension, {@code pitch}, and {@code yaw} while modifying
     * its coordinates in place.
     */
    @Override
    public Location3i add(Vector3ic v) {
        super.add(v);
        return this;
    }

    /**
     * Adds the supplied coordinates into {@code dest} and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw}; a
     * distinct destination receives coordinates while the returned receiver retains its
     * existing coordinates.
     */
    @Override
    public Location3i add(Vector3ic v, Vector3i dest) {
        super.add(v, dest);
        return this;
    }

    /**
     * Adds the supplied coordinates in this value and returns this location. The operation
     * preserves this location's dimension, {@code pitch}, and {@code yaw} while modifying
     * its coordinates in place.
     */
    @Override
    public Location3i add(int x, int y, int z) {
        super.add(x, y, z);
        return this;
    }

    /**
     * Adds the supplied coordinates into {@code dest} and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw}; a
     * distinct destination receives coordinates while the returned receiver retains its
     * existing coordinates.
     */
    @Override
    public Location3i add(int x, int y, int z, Vector3i dest) {
        super.add(x, y, z, dest);
        return this;
    }

    /**
     * Multiplies coordinates by the supplied factors in this value and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw} while modifying its coordinates in place.
     */
    @Override
    public Location3i mul(int scalar) {
        super.mul(scalar);
        return this;
    }

    /**
     * Multiplies coordinates by the supplied factors into {@code dest} and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw}; a distinct destination receives coordinates while the returned receiver
     * retains its existing coordinates.
     */
    @Override
    public Location3i mul(int scalar, Vector3i dest) {
        super.mul(scalar, dest);
        return this;
    }

    /**
     * Multiplies coordinates by the supplied factors in this value and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw} while modifying its coordinates in place.
     */
    @Override
    public Location3i mul(Vector3ic v) {
        super.mul(v);
        return this;
    }

    /**
     * Multiplies coordinates by the supplied factors into {@code dest} and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw}; a distinct destination receives coordinates while the returned receiver
     * retains its existing coordinates.
     */
    @Override
    public Location3i mul(Vector3ic v, Vector3i dest) {
        super.mul(v, dest);
        return this;
    }

    /**
     * Multiplies coordinates by the supplied factors in this value and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw} while modifying its coordinates in place.
     */
    @Override
    public Location3i mul(int x, int y, int z) {
        super.mul(x, y, z);
        return this;
    }

    /**
     * Multiplies coordinates by the supplied factors into {@code dest} and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw}; a distinct destination receives coordinates while the returned receiver
     * retains its existing coordinates.
     */
    @Override
    public Location3i mul(int x, int y, int z, Vector3i dest) {
        super.mul(x, y, z, dest);
        return this;
    }

    /**
     * Divides coordinates by the supplied scalar in this value and returns this location.
     * The operation preserves this location's dimension, {@code pitch}, and {@code yaw}
     * while modifying its coordinates in place.
     */
    @Override
    public Location3i div(float scalar) {
        super.div(scalar);
        return this;
    }

    /**
     * Divides coordinates by the supplied scalar into {@code dest} and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw}; a distinct destination receives coordinates while the returned receiver
     * retains its existing coordinates.
     */
    @Override
    public Location3i div(float scalar, Vector3i dest) {
        super.div(scalar, dest);
        return this;
    }

    /**
     * Divides coordinates by the supplied scalar in this value and returns this location.
     * The operation preserves this location's dimension, {@code pitch}, and {@code yaw}
     * while modifying its coordinates in place.
     */
    @Override
    public Location3i div(int scalar) {
        super.div(scalar);
        return this;
    }

    /**
     * Divides coordinates by the supplied scalar into {@code dest} and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw}; a distinct destination receives coordinates while the returned receiver
     * retains its existing coordinates.
     */
    @Override
    public Location3i div(int scalar, Vector3i dest) {
        super.div(scalar, dest);
        return this;
    }

    /**
     * Sets every coordinate to zero in this value and returns this location. The operation
     * preserves this location's dimension, {@code pitch}, and {@code yaw} while modifying
     * its coordinates in place.
     */
    @Override
    public Location3i zero() {
        super.zero();
        return this;
    }

    /**
     * Writes coordinates and dimension lookup data to {@code out} in the order consumed by
     * {@code readExternal}. The three coordinates use integer values in {@code x},
     * {@code y}, {@code z} order. The stream then receives {@code pitch} and {@code yaw}
     * as doubles. The dimension must still be reachable when this operation runs.
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
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        pitch = in.readDouble();
        yaw = in.readDouble();
        // Resolve through the active world pool because the stream carries identifiers rather
        // than a serialized world or dimension instance.
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    /**
     * Negates the coordinate components in this value and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw} while
     * modifying its coordinates in place.
     */
    @Override
    public Location3i negate() {
        super.negate();
        return this;
    }

    /**
     * Negates the coordinate components into {@code dest} and returns this location. The
     * operation preserves this location's dimension, {@code pitch}, and {@code yaw}; a
     * distinct destination receives coordinates while the returned receiver retains its
     * existing coordinates.
     */
    @Override
    public Location3i negate(Vector3i dest) {
        super.negate(dest);
        return this;
    }

    /**
     * Selects componentwise minimum coordinates in this value and returns this location.
     * The operation preserves this location's dimension, {@code pitch}, and {@code yaw}
     * while modifying its coordinates in place.
     */
    @Override
    public Location3i min(Vector3ic v) {
        super.min(v);
        return this;
    }

    /**
     * Selects componentwise minimum coordinates into {@code dest} and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw}; a distinct destination receives coordinates while the returned receiver
     * retains its existing coordinates.
     */
    @Override
    public Location3i min(Vector3ic v, Vector3i dest) {
        super.min(v, dest);
        return this;
    }

    /**
     * Selects componentwise maximum coordinates in this value and returns this location.
     * The operation preserves this location's dimension, {@code pitch}, and {@code yaw}
     * while modifying its coordinates in place.
     */
    @Override
    public Location3i max(Vector3ic v) {
        super.max(v);
        return this;
    }

    /**
     * Selects componentwise maximum coordinates into {@code dest} and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw}; a distinct destination receives coordinates while the returned receiver
     * retains its existing coordinates.
     */
    @Override
    public Location3i max(Vector3ic v, Vector3i dest) {
        super.max(v, dest);
        return this;
    }

    /**
     * Takes the absolute value of every coordinate in this value and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw} while modifying its coordinates in place.
     */
    @Override
    public Location3i absolute() {
        super.absolute();
        return this;
    }

    /**
     * Takes the absolute value of every coordinate into {@code dest} and returns this
     * location. The operation preserves this location's dimension, {@code pitch}, and
     * {@code yaw}; a distinct destination receives coordinates while the returned receiver
     * retains its existing coordinates.
     */
    @Override
    public Location3i absolute(Vector3i dest) {
        super.absolute(dest);
        return this;
    }

    /**
     * Returns whether the other value has matching coordinates and dimension and
     * orientation. JOML also requires matching runtime classes, and dimension comparison
     * observes the current weak referents rather than the weak-reference objects.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location3ic that)) return false;
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
        return Runtime.formatNumbers(toString(Options.NUMBER_FORMAT));
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
