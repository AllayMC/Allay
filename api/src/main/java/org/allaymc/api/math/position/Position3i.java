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
 * @author Cool_Loong
 */
public class Position3i extends Vector3i implements Position3ic {
    public WeakReference<Dimension> dimension;

    public Position3i(Position3ic p) {
        super(p);
        this.dimension = new WeakReference<>(p.dimension());
    }

    public Position3i(Dimension dimension) {
        super();
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(int d, Dimension dimension) {
        super(d);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(int x, int y, int z, Dimension dimension) {
        super(x, y, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(Vector3ic v, Dimension dimension) {
        super(v);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(Vector2ic v, int z, Dimension dimension) {
        super(v, z);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(float x, float y, float z, int mode, Dimension dimension) {
        super(x, y, z, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(double x, double y, double z, int mode, Dimension dimension) {
        super(x, y, z, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(Vector2fc v, float z, int mode, Dimension dimension) {
        super(v, z, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(Vector3fc v, int mode, Dimension dimension) {
        super(v, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(Vector2dc v, float z, int mode, Dimension dimension) {
        super(v, z, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(Vector3dc v, int mode, Dimension dimension) {
        super(v, mode);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(int[] xyz, Dimension dimension) {
        super(xyz);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(ByteBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(int index, ByteBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(IntBuffer buffer, Dimension dimension) {
        super(buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    public Position3i(int index, IntBuffer buffer, Dimension dimension) {
        super(index, buffer);
        this.dimension = new WeakReference<>(dimension);
    }

    @Override
    public Dimension dimension() {
        return dimension.get();
    }

    public void setDimension(Dimension dimension) {
        this.dimension = new WeakReference<>(dimension);
    }

    @Override
    public Position3i set(Vector3ic v) {
        return new Position3i(super.set(v), dimension());
    }

    @Override
    public Position3i set(Vector3dc v) {
        return new Position3i(super.set(v), dimension());
    }

    @Override
    public Position3i set(Vector3dc v, int mode) {
        return new Position3i(super.set(v, mode), dimension());
    }

    @Override
    public Position3i set(Vector3fc v, int mode) {
        return new Position3i(super.set(v, mode), dimension());
    }

    @Override
    public Position3i set(Vector2ic v, int z) {
        return new Position3i(super.set(v, z), dimension());
    }

    @Override
    public Position3i set(int d) {
        return new Position3i(super.set(d), dimension());
    }

    @Override
    public Position3i set(int x, int y, int z) {
        return new Position3i(super.set(x, y, z), dimension());
    }

    @Override
    public Position3i set(int[] xyz) {
        return new Position3i(super.set(xyz), dimension());
    }

    @Override
    public Position3i set(ByteBuffer buffer) {
        return new Position3i(super.set(buffer), dimension());
    }

    @Override
    public Position3i set(int index, ByteBuffer buffer) {
        return new Position3i(super.set(index, buffer), dimension());
    }

    @Override
    public Position3i set(IntBuffer buffer) {
        return new Position3i(super.set(buffer), dimension());
    }

    @Override
    public Position3i set(int index, IntBuffer buffer) {
        return new Position3i(super.set(index, buffer), dimension());
    }

    @Override
    public Position3i setFromAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position3i setComponent(int component, int value) throws IllegalArgumentException {
        return new Position3i(super.setComponent(component, value), dimension());
    }

    @Override
    public Position3ic getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position3i sub(Vector3ic v) {
        return new Position3i(super.sub(v), dimension());
    }

    @Override
    public Position3i sub(Vector3ic v, Vector3i dest) {
        return new Position3i(super.sub(v, dest), dimension());
    }

    @Override
    public Position3i sub(int x, int y, int z) {
        return new Position3i(super.sub(x, y, z), dimension());
    }

    @Override
    public Position3i sub(int x, int y, int z, Vector3i dest) {
        return new Position3i(super.sub(x, y, z, dest), dimension());
    }

    @Override
    public Position3i add(Vector3ic v) {
        return new Position3i(super.add(v), dimension());
    }

    @Override
    public Position3i add(Vector3ic v, Vector3i dest) {
        return new Position3i(super.add(v, dest), dimension());
    }

    @Override
    public Position3i add(int x, int y, int z) {
        return new Position3i(super.add(x, y, z), dimension());
    }

    @Override
    public Position3i add(int x, int y, int z, Vector3i dest) {
        return new Position3i(super.add(x, y, z, dest), dimension());
    }

    @Override
    public Position3i mul(int scalar) {
        return new Position3i(super.mul(scalar), dimension());
    }

    @Override
    public Position3i mul(int scalar, Vector3i dest) {
        return new Position3i(super.mul(scalar, dest), dimension());
    }

    @Override
    public Position3i mul(Vector3ic v) {
        return new Position3i(super.mul(v), dimension());
    }

    @Override
    public Position3i mul(Vector3ic v, Vector3i dest) {
        return new Position3i(super.mul(v, dest), dimension());
    }

    @Override
    public Position3i mul(int x, int y, int z) {
        return new Position3i(super.mul(x, y, z), dimension());
    }

    @Override
    public Position3i mul(int x, int y, int z, Vector3i dest) {
        return new Position3i(super.mul(x, y, z, dest), dimension());
    }

    @Override
    public Position3i div(float scalar) {
        return new Position3i(super.div(scalar), dimension());
    }

    @Override
    public Position3i div(float scalar, Vector3i dest) {
        return new Position3i(super.div(scalar, dest), dimension());
    }

    @Override
    public Position3i div(int scalar) {
        return new Position3i(super.div(scalar), dimension());
    }

    @Override
    public Position3i div(int scalar, Vector3i dest) {
        return new Position3i(super.div(scalar, dest), dimension());
    }

    @Override
    public Position3i zero() {
        return new Position3i(super.zero(), dimension());
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(z);
        out.writeUTF(this.dimension().getWorld().getWorldData().getDisplayName());
        out.writeInt(this.dimension().getDimensionInfo().dimensionId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    @Override
    public Position3i negate() {
        return new Position3i(super.negate(), dimension());
    }

    @Override
    public Position3i negate(Vector3i dest) {
        return new Position3i(super.negate(dest), dimension());
    }

    @Override
    public Position3i min(Vector3ic v) {
        return new Position3i(super.min(v), dimension());
    }

    @Override
    public Position3i min(Vector3ic v, Vector3i dest) {
        return new Position3i(super.min(v, dest), dimension());
    }

    @Override
    public Position3i max(Vector3ic v) {
        return new Position3i(super.max(v), dimension());
    }

    @Override
    public Position3i max(Vector3ic v, Vector3i dest) {
        return new Position3i(super.max(v, dest), dimension());
    }

    @Override
    public Position3i absolute() {
        return new Position3i(super.absolute(), dimension());
    }

    @Override
    public Position3i absolute(Vector3i dest) {
        return new Position3i(super.absolute(dest), dimension());
    }

    @Override
    public Position3i clone() throws CloneNotSupportedException {
        return (Position3i) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3ic that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(dimension.get(), that.dimension());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), dimension.get());
    }

    @Override
    public String toString() {
        return toString(Options.NUMBER_FORMAT);
    }

    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " dimension=" + this.dimension().getWorld().getWorldData().getDisplayName() + this.dimension().getDimensionInfo().dimensionId() + ")";
    }
}
