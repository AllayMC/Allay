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
 * @author Cool_Loong
 */
public class Location3i extends Position3i implements Location3ic {
    public double pitch;
    public double yaw;

    public Location3i(Location3ic l) {
        super(l);
        this.pitch = l.pitch();
        this.yaw = l.yaw();
    }

    public Location3i(Position3ic p) {
        super(p);
        this.pitch = 0;
        this.yaw = 0;
    }

    public Location3i(int x, int y, int z, Dimension dimension) {
        super(x, y, z, dimension);
    }

    public Location3i(int d, double pitch, double yaw, Dimension dimension) {
        super(d, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(int x, int y, int z, double pitch, double yaw, Dimension dimension) {
        super(x, y, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(Vector3ic v, double pitch, double yaw, Dimension dimension) {
        super(v, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(Vector2ic v, int z, double pitch, double yaw, Dimension dimension) {
        super(v, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(float x, float y, float z, int mode, double pitch, double yaw, Dimension dimension) {
        super(x, y, z, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(double x, double y, double z, int mode, double pitch, double yaw, Dimension dimension) {
        super(x, y, z, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(Vector2fc v, float z, int mode, double pitch, double yaw, Dimension dimension) {
        super(v, z, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(Vector3fc v, int mode, double pitch, double yaw, Dimension dimension) {
        super(v, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(Vector2dc v, float z, int mode, double pitch, double yaw, Dimension dimension) {
        super(v, z, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(Vector3dc v, int mode, double pitch, double yaw, Dimension dimension) {
        super(v, mode, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(int[] xyz, double pitch, double yaw, Dimension dimension) {
        super(xyz, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(ByteBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(int index, ByteBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(index, buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(IntBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3i(int index, IntBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(index, buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    @Override
    public double pitch() {
        return pitch;
    }

    @Override
    public double yaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    @Override
    public Location3i set(Vector3ic v) {
        super.set(v);
        return this;
    }

    @Override
    public Location3i set(Vector3dc v) {
        super.set(v);
        return this;
    }

    @Override
    public Location3i set(Vector3dc v, int mode) {
        super.set(v, mode);
        return this;
    }

    @Override
    public Location3i set(Vector3fc v, int mode) {
        super.set(v, mode);
        return this;
    }

    @Override
    public Location3i set(Vector2ic v, int z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Location3i set(int d) {
        super.set(d);
        return this;
    }

    @Override
    public Location3i set(int x, int y, int z) {
        super.set(x, y, z);
        return this;
    }

    @Override
    public Location3i set(int[] xyz) {
        super.set(xyz);
        return this;
    }

    @Override
    public Location3i set(ByteBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Location3i set(int index, ByteBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Location3i set(IntBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Location3i set(int index, IntBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Location3i setFromAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Location3i setComponent(int component, int value) throws IllegalArgumentException {
        super.setComponent(component, value);
        return this;
    }

    @Override
    public Position3ic getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Location3i sub(Vector3ic v) {
        super.sub(v);
        return this;
    }

    @Override
    public Location3i sub(Vector3ic v, Vector3i dest) {
        super.sub(v, dest);
        return this;
    }

    @Override
    public Location3i sub(int x, int y, int z) {
        super.sub(x, y, z);
        return this;
    }

    @Override
    public Location3i sub(int x, int y, int z, Vector3i dest) {
        super.sub(x, y, z, dest);
        return this;
    }

    @Override
    public Location3i add(Vector3ic v) {
        super.add(v);
        return this;
    }

    @Override
    public Location3i add(Vector3ic v, Vector3i dest) {
        super.add(v, dest);
        return this;
    }

    @Override
    public Location3i add(int x, int y, int z) {
        super.add(x, y, z);
        return this;
    }

    @Override
    public Location3i add(int x, int y, int z, Vector3i dest) {
        super.add(x, y, z, dest);
        return this;
    }

    @Override
    public Location3i mul(int scalar) {
        super.mul(scalar);
        return this;
    }

    @Override
    public Location3i mul(int scalar, Vector3i dest) {
        super.mul(scalar, dest);
        return this;
    }

    @Override
    public Location3i mul(Vector3ic v) {
        super.mul(v);
        return this;
    }

    @Override
    public Location3i mul(Vector3ic v, Vector3i dest) {
        super.mul(v, dest);
        return this;
    }

    @Override
    public Location3i mul(int x, int y, int z) {
        super.mul(x, y, z);
        return this;
    }

    @Override
    public Location3i mul(int x, int y, int z, Vector3i dest) {
        super.mul(x, y, z, dest);
        return this;
    }

    @Override
    public Location3i div(float scalar) {
        super.div(scalar);
        return this;
    }

    @Override
    public Location3i div(float scalar, Vector3i dest) {
        super.div(scalar, dest);
        return this;
    }

    @Override
    public Location3i div(int scalar) {
        super.div(scalar);
        return this;
    }

    @Override
    public Location3i div(int scalar, Vector3i dest) {
        super.div(scalar, dest);
        return this;
    }

    @Override
    public Location3i zero() {
        super.zero();
        return this;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(x);
        out.writeInt(y);
        out.writeInt(z);
        out.writeDouble(pitch);
        out.writeDouble(yaw);
        out.writeUTF(this.dimension().getWorld().getWorldData().getDisplayName());
        out.writeInt(this.dimension().getDimensionInfo().dimensionId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        x = in.readInt();
        y = in.readInt();
        z = in.readInt();
        pitch = in.readDouble();
        yaw = in.readDouble();
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    @Override
    public Location3i negate() {
        super.negate();
        return this;
    }

    @Override
    public Location3i negate(Vector3i dest) {
        super.negate(dest);
        return this;
    }

    @Override
    public Location3i min(Vector3ic v) {
        super.min(v);
        return this;
    }

    @Override
    public Location3i min(Vector3ic v, Vector3i dest) {
        super.min(v, dest);
        return this;
    }

    @Override
    public Location3i max(Vector3ic v) {
        super.max(v);
        return this;
    }

    @Override
    public Location3i max(Vector3ic v, Vector3i dest) {
        super.max(v, dest);
        return this;
    }

    @Override
    public Location3i absolute() {
        super.absolute();
        return this;
    }

    @Override
    public Location3i absolute(Vector3i dest) {
        super.absolute(dest);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location3ic that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(dimension.get(), that.dimension()) && Objects.equal(pitch, that.pitch()) && Objects.equal(yaw, that.yaw());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), pitch, yaw, dimension.get());
    }

    @Override
    public String toString() {
        return Runtime.formatNumbers(toString(Options.NUMBER_FORMAT));
    }

    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " pitch=" + this.pitch + " yaw=" + this.yaw + " dimension=" + this.dimension().getWorld().getWorldData().getDisplayName() + this.dimension().getDimensionInfo().dimensionId() + ")";
    }
}
