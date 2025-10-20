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
 * @author Cool_Loong
 */
@Setter
public class Location3f extends Position3f implements Location3fc {

    public double pitch;
    public double yaw;

    public Location3f(Location3fc l) {
        super(l);
        this.pitch = l.pitch();
        this.yaw = l.yaw();
    }

    public Location3f(Location3ic l) {
        super(l.x(), l.y(), l.z(), l.dimension());
        this.pitch = l.pitch();
        this.yaw = l.yaw();
    }

    public Location3f(float x, float y, float z, Dimension dimension) {
        super(x, y, z, dimension);
    }

    public Location3f(float d, double pitch, double yaw, Dimension dimension) {
        super(d, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(float x, float y, float z, double pitch, double yaw, Dimension dimension) {
        super(x, y, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(Vector3fc v, double pitch, double yaw, Dimension dimension) {
        super(v, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(Vector3ic v, double pitch, double yaw, Dimension dimension) {
        super(v, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(Vector2fc v, float z, double pitch, double yaw, Dimension dimension) {
        super(v, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(Vector2ic v, float z, double pitch, double yaw, Dimension dimension) {
        super(v, z, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(float[] xyz, double pitch, double yaw, Dimension dimension) {
        super(xyz, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(ByteBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(int index, ByteBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(index, buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(FloatBuffer buffer, double pitch, double yaw, Dimension dimension) {
        super(buffer, dimension);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location3f(int index, FloatBuffer buffer, double pitch, double yaw, Dimension dimension) {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeFloat(x);
        out.writeFloat(y);
        out.writeFloat(z);
        out.writeDouble(pitch);
        out.writeDouble(yaw);
        out.writeUTF(this.dimension().getWorld().getWorldData().getDisplayName());
        out.writeInt(this.dimension().getDimensionInfo().dimensionId());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException {
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
        pitch = in.readDouble();
        yaw = in.readDouble();
        dimension = new WeakReference<>(Server.getInstance().getWorldPool().getWorld(in.readUTF()).getDimension(in.readInt()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location3fc that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(dimension.get(), that.dimension()) && Objects.equal(pitch, that.pitch()) && Objects.equal(yaw, that.yaw());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), pitch, yaw, dimension.get());
    }

    @Override
    public String toString() {
        return toString(Options.NUMBER_FORMAT);
    }

    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " pitch=" + this.pitch + " yaw=" + this.yaw + " dimension=" + this.dimension().getWorld().getWorldData().getDisplayName() + this.dimension().getDimensionInfo().dimensionId() + ")";
    }
}
