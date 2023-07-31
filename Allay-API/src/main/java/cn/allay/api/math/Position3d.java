package cn.allay.api.math;

import cn.allay.api.server.Server;
import cn.allay.api.world.World;
import com.google.common.base.Objects;
import org.joml.Runtime;
import org.joml.*;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.text.NumberFormat;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public class Position3d extends Vector3d implements Position3dc {
    public World world;

    public Position3d(World world) {
        super();
        this.world = world;
    }

    public Position3d(double d, World world) {
        super(d);
        this.world = world;
    }

    public Position3d(double x, double y, double z, World world) {
        super(x, y, z);
        this.world = world;
    }

    public Position3d(Vector3fc v, World world) {
        super(v);
        this.world = world;
    }

    public Position3d(Vector3ic v, World world) {
        super(v);
        this.world = world;
    }

    public Position3d(Vector2fc v, double z, World world) {
        super(v, z);
        this.world = world;
    }

    public Position3d(Vector2ic v, double z, World world) {
        super(v, z);
        this.world = world;
    }

    public Position3d(Vector3dc v, World world) {
        super(v);
        this.world = world;
    }

    public Position3d(Vector2dc v, double z, World world) {
        super(v, z);
        this.world = world;
    }

    public Position3d(double[] xyz, World world) {
        super(xyz);
        this.world = world;
    }

    public Position3d(float[] xyz, World world) {
        super(xyz);
        this.world = world;
    }

    public Position3d(ByteBuffer buffer, World world) {
        super(buffer);
        this.world = world;
    }

    public Position3d(int index, ByteBuffer buffer, World world) {
        super(index, buffer);
        this.world = world;
    }

    public Position3d(DoubleBuffer buffer, World world) {
        super(buffer);
        this.world = world;
    }

    public Position3d(int index, DoubleBuffer buffer, World world) {
        super(index, buffer);
        this.world = world;
    }

    @Override
    public World world() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public Position3d set(Vector3dc v) {
        super.set(v);
        return this;
    }

    @Override
    public Position3d set(Vector3ic v) {
        super.set(v);
        return this;
    }

    @Override
    public Position3d set(Vector2dc v, double z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Position3d set(Vector2ic v, double z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Position3d set(Vector3fc v) {
        super.set(v);
        return this;
    }

    @Override
    public Position3d set(Vector2fc v, double z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Position3d set(double d) {
        super.set(d);
        return this;
    }

    @Override
    public Position3d set(double x, double y, double z) {
        super.set(x, y, z);
        return this;
    }

    @Override
    public Position3d set(double[] xyz) {
        super.set(xyz);
        return this;
    }

    @Override
    public Position3d set(float[] xyz) {
        super.set(xyz);
        return this;
    }

    @Override
    public Position3d set(ByteBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Position3d set(int index, ByteBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Position3d set(DoubleBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Position3d set(int index, DoubleBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Position3d setFromAddress(long address) {
        super.setFromAddress(address);
        return this;
    }

    @Override
    public Position3d setComponent(int component, double value) throws IllegalArgumentException {
        super.setComponent(component, value);
        return this;
    }

    @Override
    public Position3dc getToAddress(long address) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Position3d sub(Vector3dc v) {
        super.sub(v);
        return this;
    }

    @Override
    public Position3d sub(Vector3dc v, Vector3d dest) {
        super.sub(v, dest);
        return this;
    }

    @Override
    public Position3d sub(Vector3fc v) {
        super.sub(v);
        return this;
    }

    @Override
    public Position3d sub(Vector3fc v, Vector3d dest) {
        super.sub(v, dest);
        return this;
    }

    @Override
    public Position3d sub(double x, double y, double z) {
        super.sub(x, y, z);
        return this;
    }

    @Override
    public Position3d sub(double x, double y, double z, Vector3d dest) {
        super.sub(x, y, z, dest);
        return this;
    }

    @Override
    public Position3d add(Vector3dc v) {
        super.add(v);
        return this;
    }

    @Override
    public Position3d add(Vector3dc v, Vector3d dest) {
        super.add(v, dest);
        return this;
    }

    @Override
    public Position3d add(Vector3fc v) {
        super.add(v);
        return this;
    }

    @Override
    public Position3d add(Vector3fc v, Vector3d dest) {
        super.add(v, dest);
        return this;
    }

    @Override
    public Position3d add(double x, double y, double z) {
        super.add(x, y, z);
        return this;
    }

    @Override
    public Position3d add(double x, double y, double z, Vector3d dest) {
        super.add(x, y, z, dest);
        return this;
    }

    @Override
    public Position3d fma(Vector3dc a, Vector3dc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Position3d fma(double a, Vector3dc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Position3d fma(Vector3fc a, Vector3fc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Position3d fma(Vector3fc a, Vector3fc b, Vector3d dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Position3d fma(double a, Vector3fc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Position3d fma(Vector3dc a, Vector3dc b, Vector3d dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Position3d fma(double a, Vector3dc b, Vector3d dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Position3d fma(Vector3dc a, Vector3fc b, Vector3d dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Position3d fma(double a, Vector3fc b, Vector3d dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Position3d mulAdd(Vector3dc a, Vector3dc b) {
        super.mulAdd(a, b);
        return this;
    }

    @Override
    public Position3d mulAdd(double a, Vector3dc b) {
        super.mulAdd(a, b);
        return this;
    }

    @Override
    public Position3d mulAdd(Vector3dc a, Vector3dc b, Vector3d dest) {
        super.mulAdd(a, b, dest);
        return this;
    }

    @Override
    public Position3d mulAdd(double a, Vector3dc b, Vector3d dest) {
        super.mulAdd(a, b, dest);
        return this;
    }

    @Override
    public Position3d mulAdd(Vector3fc a, Vector3dc b, Vector3d dest) {
        super.mulAdd(a, b, dest);
        return this;
    }

    @Override
    public Position3d mul(Vector3dc v) {
        super.mul(v);
        return this;
    }

    @Override
    public Position3d mul(Vector3fc v) {
        super.mul(v);
        return this;
    }

    @Override
    public Position3d mul(Vector3fc v, Vector3d dest) {
        super.mul(v, dest);
        return this;
    }

    @Override
    public Position3d mul(Vector3dc v, Vector3d dest) {
        super.mul(v, dest);
        return this;
    }

    @Override
    public Position3d div(Vector3d v) {
        super.div(v);
        return this;
    }

    @Override
    public Position3d div(Vector3fc v) {
        super.div(v);
        return this;
    }

    @Override
    public Position3d div(Vector3fc v, Vector3d dest) {
        super.div(v, dest);
        return this;
    }

    @Override
    public Position3d div(Vector3dc v, Vector3d dest) {
        super.div(v, dest);
        return this;
    }

    @Override
    public Position3d mulProject(Matrix4dc mat, double w, Vector3d dest) {
        super.mulProject(mat, w, dest);
        return this;
    }

    @Override
    public Position3d mulProject(Matrix4dc mat, Vector3d dest) {
        super.mulProject(mat, dest);
        return this;
    }

    @Override
    public Position3d mulProject(Matrix4dc mat) {
        super.mulProject(mat);
        return this;
    }

    @Override
    public Position3d mulProject(Matrix4fc mat, Vector3d dest) {
        super.mulProject(mat, dest);
        return this;
    }

    @Override
    public Position3d mulProject(Matrix4fc mat) {
        super.mulProject(mat);
        return this;
    }

    @Override
    public Position3d mul(Matrix3fc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Position3d mul(Matrix3dc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Position3d mul(Matrix3dc mat, Vector3d dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Vector3f mul(Matrix3dc mat, Vector3f dest) {
        return super.mul(mat, dest);
    }

    @Override
    public Position3d mul(Matrix3fc mat, Vector3d dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Position3d mul(Matrix3x2dc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Position3d mul(Matrix3x2dc mat, Vector3d dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Position3d mul(Matrix3x2fc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Position3d mul(Matrix3x2fc mat, Vector3d dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Position3d mulTranspose(Matrix3dc mat) {
        super.mulTranspose(mat);
        return this;
    }

    @Override
    public Position3d mulTranspose(Matrix3dc mat, Vector3d dest) {
        super.mulTranspose(mat, dest);
        return this;
    }

    @Override
    public Position3d mulTranspose(Matrix3fc mat) {
        super.mulTranspose(mat);
        return this;
    }

    @Override
    public Position3d mulTranspose(Matrix3fc mat, Vector3d dest) {
        super.mulTranspose(mat, dest);
        return this;
    }

    @Override
    public Position3d mulPosition(Matrix4fc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Position3d mulPosition(Matrix4dc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Position3d mulPosition(Matrix4x3dc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Position3d mulPosition(Matrix4x3fc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Position3d mulPosition(Matrix4dc mat, Vector3d dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Position3d mulPosition(Matrix4fc mat, Vector3d dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Position3d mulPosition(Matrix4x3dc mat, Vector3d dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Position3d mulPosition(Matrix4x3fc mat, Vector3d dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Position3d mulTransposePosition(Matrix4dc mat) {
        super.mulTransposePosition(mat);
        return this;
    }

    @Override
    public Position3d mulTransposePosition(Matrix4dc mat, Vector3d dest) {
        super.mulTransposePosition(mat, dest);
        return this;
    }

    @Override
    public Position3d mulTransposePosition(Matrix4fc mat) {
        super.mulTransposePosition(mat);
        return this;
    }

    @Override
    public Position3d mulTransposePosition(Matrix4fc mat, Vector3d dest) {
        super.mulTransposePosition(mat, dest);
        return this;
    }

    @Override
    public Position3d mulDirection(Matrix4fc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Position3d mulDirection(Matrix4dc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Position3d mulDirection(Matrix4x3dc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Position3d mulDirection(Matrix4x3fc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Position3d mulDirection(Matrix4dc mat, Vector3d dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Position3d mulDirection(Matrix4fc mat, Vector3d dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Position3d mulDirection(Matrix4x3dc mat, Vector3d dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Position3d mulDirection(Matrix4x3fc mat, Vector3d dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Position3d mulTransposeDirection(Matrix4dc mat) {
        super.mulTransposeDirection(mat);
        return this;
    }

    @Override
    public Position3d mulTransposeDirection(Matrix4dc mat, Vector3d dest) {
        super.mulTransposeDirection(mat, dest);
        return this;
    }

    @Override
    public Position3d mulTransposeDirection(Matrix4fc mat) {
        super.mulTransposeDirection(mat);
        return this;
    }

    @Override
    public Position3d mulTransposeDirection(Matrix4fc mat, Vector3d dest) {
        super.mulTransposeDirection(mat, dest);
        return this;
    }

    @Override
    public Position3d mul(double scalar) {
        super.mul(scalar);
        return this;
    }

    @Override
    public Position3d mul(double scalar, Vector3d dest) {
        super.mul(scalar, dest);
        return this;
    }

    @Override
    public Position3d mul(double x, double y, double z) {
        super.mul(x, y, z);
        return this;
    }

    @Override
    public Position3d mul(double x, double y, double z, Vector3d dest) {
        super.mul(x, y, z, dest);
        return this;
    }

    @Override
    public Position3d rotate(Quaterniondc quat) {
        super.rotate(quat);
        return this;
    }

    @Override
    public Position3d rotate(Quaterniondc quat, Vector3d dest) {
        super.rotate(quat, dest);
        return this;
    }

    @Override
    public Quaterniond rotationTo(Vector3dc toDir, Quaterniond dest) {
        return super.rotationTo(toDir, dest);
    }

    @Override
    public Quaterniond rotationTo(double toDirX, double toDirY, double toDirZ, Quaterniond dest) {
        return super.rotationTo(toDirX, toDirY, toDirZ, dest);
    }

    @Override
    public Position3d rotateAxis(double angle, double x, double y, double z) {
        super.rotateAxis(angle, x, y, z);
        return this;
    }

    @Override
    public Position3d rotateAxis(double angle, double aX, double aY, double aZ, Vector3d dest) {
        super.rotateAxis(angle, aX, aY, aZ, dest);
        return this;
    }

    @Override
    public Position3d rotateX(double angle) {
        super.rotateX(angle);
        return this;
    }

    @Override
    public Position3d rotateX(double angle, Vector3d dest) {
        super.rotateX(angle, dest);
        return this;
    }

    @Override
    public Position3d rotateY(double angle) {
        super.rotateY(angle);
        return this;
    }

    @Override
    public Position3d rotateY(double angle, Vector3d dest) {
        super.rotateY(angle, dest);
        return this;
    }

    @Override
    public Position3d rotateZ(double angle) {
        super.rotateZ(angle);
        return this;
    }

    @Override
    public Position3d rotateZ(double angle, Vector3d dest) {
        super.rotateZ(angle, dest);
        return this;
    }

    @Override
    public Position3d div(double scalar) {
        super.div(scalar);
        return this;
    }

    @Override
    public Position3d div(double scalar, Vector3d dest) {
        super.div(scalar, dest);
        return this;
    }

    @Override
    public Position3d div(double x, double y, double z) {
        super.div(x, y, z);
        return this;
    }

    @Override
    public Position3d div(double x, double y, double z, Vector3d dest) {
        super.div(x, y, z, dest);
        return this;
    }

    @Override
    public Position3d normalize() {
        super.normalize();
        return this;
    }

    @Override
    public Position3d normalize(Vector3d dest) {
        super.normalize(dest);
        return this;
    }

    @Override
    public Position3d normalize(double length) {
        super.normalize(length);
        return this;
    }

    @Override
    public Position3d normalize(double length, Vector3d dest) {
        super.normalize(length, dest);
        return this;
    }

    @Override
    public Position3d cross(Vector3dc v) {
        super.cross(v);
        return this;
    }

    @Override
    public Position3d cross(double x, double y, double z) {
        super.cross(x, y, z);
        return this;
    }

    @Override
    public Position3d cross(Vector3dc v, Vector3d dest) {
        super.cross(v, dest);
        return this;
    }

    @Override
    public Position3d cross(double x, double y, double z, Vector3d dest) {
        super.cross(x, y, z, dest);
        return this;
    }

    @Override
    public Position3d min(Vector3dc v) {
        super.min(v);
        return this;
    }

    @Override
    public Position3d min(Vector3dc v, Vector3d dest) {
        super.min(v, dest);
        return this;
    }

    @Override
    public Position3d max(Vector3dc v) {
        super.max(v);
        return this;
    }

    @Override
    public Position3d max(Vector3dc v, Vector3d dest) {
        super.max(v, dest);
        return this;
    }

    @Override
    public Position3d zero() {
        super.zero();
        return this;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeDouble(x);
        out.writeDouble(y);
        out.writeDouble(z);
        out.writeUTF(this.world.getName());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        x = in.readDouble();
        y = in.readDouble();
        z = in.readDouble();
        world = Server.getInstance().getWorldPool().getWorld(in.readUTF());
    }

    @Override
    public Position3d negate() {
        super.negate();
        return this;
    }

    @Override
    public Position3d negate(Vector3d dest) {
        super.negate(dest);
        return this;
    }

    @Override
    public Position3d absolute() {
        super.absolute();
        return this;
    }

    @Override
    public Position3d absolute(Vector3d dest) {
        super.absolute(dest);
        return this;
    }

    @Override
    public Position3d reflect(Vector3dc normal) {
        super.reflect(normal);
        return this;
    }

    @Override
    public Position3d reflect(double x, double y, double z) {
        super.reflect(x, y, z);
        return this;
    }

    @Override
    public Position3d reflect(Vector3dc normal, Vector3d dest) {
        super.reflect(normal, dest);
        return this;
    }

    @Override
    public Position3d reflect(double x, double y, double z, Vector3d dest) {
        super.reflect(x, y, z, dest);
        return this;
    }

    @Override
    public Position3d half(Vector3dc other) {
        super.half(other);
        return this;
    }

    @Override
    public Position3d half(double x, double y, double z) {
        super.half(x, y, z);
        return this;
    }

    @Override
    public Position3d half(Vector3dc other, Vector3d dest) {
        super.half(other, dest);
        return this;
    }

    @Override
    public Position3d half(double x, double y, double z, Vector3d dest) {
        super.half(x, y, z, dest);
        return this;
    }

    @Override
    public Position3d smoothStep(Vector3dc v, double t, Vector3d dest) {
        super.smoothStep(v, t, dest);
        return this;
    }

    @Override
    public Position3d hermite(Vector3dc t0, Vector3dc v1, Vector3dc t1, double t, Vector3d dest) {
        super.hermite(t0, v1, t1, t, dest);
        return this;
    }

    @Override
    public Position3d lerp(Vector3dc other, double t) {
        super.lerp(other, t);
        return this;
    }

    @Override
    public Position3d lerp(Vector3dc other, double t, Vector3d dest) {
        super.lerp(other, t, dest);
        return this;
    }

    @Override
    public Vector3i get(int mode, Vector3i dest) {
        return super.get(mode, dest);
    }

    @Override
    public Vector3f get(Vector3f dest) {
        return super.get(dest);
    }

    @Override
    public Position3d get(Vector3d dest) {
        super.get(dest);
        return this;
    }

    @Override
    public Position3d orthogonalize(Vector3dc v, Vector3d dest) {
        super.orthogonalize(v, dest);
        return this;
    }

    @Override
    public Position3d orthogonalize(Vector3dc v) {
        super.orthogonalize(v);
        return this;
    }

    @Override
    public Position3d orthogonalizeUnit(Vector3dc v, Vector3d dest) {
        super.orthogonalizeUnit(v, dest);
        return this;
    }

    @Override
    public Position3d orthogonalizeUnit(Vector3dc v) {
        super.orthogonalizeUnit(v);
        return this;
    }

    @Override
    public Position3d floor() {
        super.floor();
        return this;
    }

    @Override
    public Position3d floor(Vector3d dest) {
        super.floor(dest);
        return this;
    }

    @Override
    public Position3d ceil() {
        super.ceil();
        return this;
    }

    @Override
    public Position3d ceil(Vector3d dest) {
        super.ceil(dest);
        return this;
    }

    @Override
    public Position3d round() {
        super.round();
        return this;
    }

    @Override
    public Position3d round(Vector3d dest) {
        super.round(dest);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position3dc that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(world, that.world());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), world);
    }

    @Override
    public String toString() {
        return Runtime.formatNumbers(toString(Options.NUMBER_FORMAT)) + ",world=" + this.world.getName();
    }

    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " world=" + this.world.getName() + ")";
    }
}
