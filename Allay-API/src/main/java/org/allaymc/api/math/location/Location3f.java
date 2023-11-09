package org.allaymc.api.math.location;

import org.allaymc.api.math.position.Position3f;
import org.allaymc.api.world.World;
import com.google.common.base.Objects;
import org.joml.Runtime;
import org.joml.*;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.text.NumberFormat;

/**
 * Allay Project 7/30/2023
 *
 * @author Cool_Loong
 */
public class Location3f extends Position3f implements Location3fc {
    public double pitch;
    public double yaw;
    public double headYaw;

    public Location3f(Location3fc l) {
        super(l);
        this.pitch = l.pitch();
        this.yaw = l.yaw();
        this.headYaw = l.headYaw();
    }

    public Location3f(float x, float y, float z, World world) {
        super(x, y, z, world);
    }

    public Location3f(float d, double pitch, double yaw, double headYaw, World world) {
        super(d, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(float x, float y, float z, double pitch, double yaw, double headYaw, World world) {
        super(x, y, z, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(Vector3fc v, double pitch, double yaw, double headYaw, World world) {
        super(v, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(Vector3ic v, double pitch, double yaw, double headYaw, World world) {
        super(v, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(Vector2fc v, float z, double pitch, double yaw, double headYaw, World world) {
        super(v, z, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(Vector2ic v, float z, double pitch, double yaw, double headYaw, World world) {
        super(v, z, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(float[] xyz, double pitch, double yaw, double headYaw, World world) {
        super(xyz, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(ByteBuffer buffer, double pitch, double yaw, double headYaw, World world) {
        super(buffer, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(int index, ByteBuffer buffer, double pitch, double yaw, double headYaw, World world) {
        super(index, buffer, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(FloatBuffer buffer, double pitch, double yaw, double headYaw, World world) {
        super(buffer, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
    }

    public Location3f(int index, FloatBuffer buffer, double pitch, double yaw, double headYaw, World world) {
        super(index, buffer, world);
        this.pitch = pitch;
        this.yaw = yaw;
        this.headYaw = headYaw;
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
    public double headYaw() {
        return headYaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public void setHeadYaw(double headYaw) {
        this.headYaw = headYaw;
    }

    @Override
    public World world() {
        return super.world();
    }

    @Override
    public void setWorld(World world) {
        super.setWorld(world);
    }

    @Override
    public Location3f set(Vector3ic v) {
        super.set(v);
        return this;
    }

    @Override
    public Location3f set(Vector3fc v) {
        super.set(v);
        return this;
    }

    @Override
    public Location3f set(Vector3dc v) {
        super.set(v);
        return this;
    }

    @Override
    public Location3f set(Vector2fc v, float z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Location3f set(Vector2dc v, float z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Location3f set(Vector2ic v, float z) {
        super.set(v, z);
        return this;
    }

    @Override
    public Location3f set(float d) {
        super.set(d);
        return this;
    }

    @Override
    public Location3f set(float x, float y, float z) {
        super.set(x, y, z);
        return this;
    }

    @Override
    public Location3f set(double d) {
        super.set(d);
        return this;
    }

    @Override
    public Location3f set(double x, double y, double z) {
        super.set(x, y, z);
        return this;
    }

    @Override
    public Location3f set(float[] xyz) {
        super.set(xyz);
        return this;
    }

    @Override
    public Location3f set(ByteBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Location3f set(int index, ByteBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Location3f set(FloatBuffer buffer) {
        super.set(buffer);
        return this;
    }

    @Override
    public Location3f set(int index, FloatBuffer buffer) {
        super.set(index, buffer);
        return this;
    }

    @Override
    public Location3f setFromAddress(long address) {
        super.setFromAddress(address);
        return this;
    }

    @Override
    public Location3f setComponent(int component, float value) throws IllegalArgumentException {
        super.setComponent(component, value);
        return this;
    }

    @Override
    public Location3fc getToAddress(long address) {
        super.getToAddress(address);
        return this;
    }

    @Override
    public Location3f sub(Vector3fc v) {
        super.sub(v);
        return this;
    }

    @Override
    public Location3f sub(Vector3fc v, Vector3f dest) {
        super.sub(v, dest);
        return this;
    }

    @Override
    public Location3f sub(float x, float y, float z) {
        super.sub(x, y, z);
        return this;
    }

    @Override
    public Location3f sub(float x, float y, float z, Vector3f dest) {
        super.sub(x, y, z, dest);
        return this;
    }

    @Override
    public Location3f add(Vector3fc v) {
        super.add(v);
        return this;
    }

    @Override
    public Location3f add(Vector3fc v, Vector3f dest) {
        super.add(v, dest);
        return this;
    }

    @Override
    public Location3f add(float x, float y, float z) {
        super.add(x, y, z);
        return this;
    }

    @Override
    public Location3f add(float x, float y, float z, Vector3f dest) {
        super.add(x, y, z, dest);
        return this;
    }

    @Override
    public Location3f fma(Vector3fc a, Vector3fc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Location3f fma(float a, Vector3fc b) {
        super.fma(a, b);
        return this;
    }

    @Override
    public Location3f fma(Vector3fc a, Vector3fc b, Vector3f dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Location3f fma(float a, Vector3fc b, Vector3f dest) {
        super.fma(a, b, dest);
        return this;
    }

    @Override
    public Location3f mulAdd(Vector3fc a, Vector3fc b) {
        super.mulAdd(a, b);
        return this;
    }

    @Override
    public Location3f mulAdd(float a, Vector3fc b) {
        super.mulAdd(a, b);
        return this;
    }

    @Override
    public Location3f mulAdd(Vector3fc a, Vector3fc b, Vector3f dest) {
        super.mulAdd(a, b, dest);
        return this;
    }

    @Override
    public Location3f mulAdd(float a, Vector3fc b, Vector3f dest) {
        super.mulAdd(a, b, dest);
        return this;
    }

    @Override
    public Location3f mul(float scalar) {
        super.mul(scalar);
        return this;
    }

    @Override
    public Location3f mul(float scalar, Vector3f dest) {
        super.mul(scalar, dest);
        return this;
    }

    @Override
    public Location3f mul(Vector3fc v) {
        super.mul(v);
        return this;
    }

    @Override
    public Location3f mul(Vector3fc v, Vector3f dest) {
        super.mul(v, dest);
        return this;
    }

    @Override
    public Location3f div(Vector3fc v) {
        super.div(v);
        return this;
    }

    @Override
    public Location3f div(Vector3fc v, Vector3f dest) {
        super.div(v, dest);
        return this;
    }

    @Override
    public Location3f mulProject(Matrix4fc mat, Vector3f dest) {
        super.mulProject(mat, dest);
        return this;
    }

    @Override
    public Location3f mulProject(Matrix4fc mat, float w, Vector3f dest) {
        super.mulProject(mat, w, dest);
        return this;
    }

    @Override
    public Location3f mulProject(Matrix4fc mat) {
        super.mulProject(mat);
        return this;
    }

    @Override
    public Location3f mul(Matrix3fc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Location3f mul(Matrix3fc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Location3f mul(Matrix3dc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Location3f mul(Matrix3dc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Location3f mul(Matrix3x2fc mat) {
        super.mul(mat);
        return this;
    }

    @Override
    public Location3f mul(Matrix3x2fc mat, Vector3f dest) {
        super.mul(mat, dest);
        return this;
    }

    @Override
    public Location3f mulTranspose(Matrix3fc mat) {
        super.mulTranspose(mat);
        return this;
    }

    @Override
    public Location3f mulTranspose(Matrix3fc mat, Vector3f dest) {
        super.mulTranspose(mat, dest);
        return this;
    }

    @Override
    public Location3f mulPosition(Matrix4fc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Location3f mulPosition(Matrix4x3fc mat) {
        super.mulPosition(mat);
        return this;
    }

    @Override
    public Location3f mulPosition(Matrix4fc mat, Vector3f dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Location3f mulPosition(Matrix4x3fc mat, Vector3f dest) {
        super.mulPosition(mat, dest);
        return this;
    }

    @Override
    public Location3f mulTransposePosition(Matrix4fc mat) {
        super.mulTransposePosition(mat);
        return this;
    }

    @Override
    public Location3f mulTransposePosition(Matrix4fc mat, Vector3f dest) {
        super.mulTransposePosition(mat, dest);
        return this;
    }

    @Override
    public Location3f mulDirection(Matrix4dc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Location3f mulDirection(Matrix4fc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Location3f mulDirection(Matrix4x3fc mat) {
        super.mulDirection(mat);
        return this;
    }

    @Override
    public Location3f mulDirection(Matrix4dc mat, Vector3f dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Location3f mulDirection(Matrix4fc mat, Vector3f dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Location3f mulDirection(Matrix4x3fc mat, Vector3f dest) {
        super.mulDirection(mat, dest);
        return this;
    }

    @Override
    public Location3f mulTransposeDirection(Matrix4fc mat) {
        super.mulTransposeDirection(mat);
        return this;
    }

    @Override
    public Location3f mulTransposeDirection(Matrix4fc mat, Vector3f dest) {
        super.mulTransposeDirection(mat, dest);
        return this;
    }

    @Override
    public Location3f mul(float x, float y, float z) {
        super.mul(x, y, z);
        return this;
    }

    @Override
    public Location3f mul(float x, float y, float z, Vector3f dest) {
        super.mul(x, y, z, dest);
        return this;
    }

    @Override
    public Location3f div(float scalar) {
        super.div(scalar);
        return this;
    }

    @Override
    public Location3f div(float scalar, Vector3f dest) {
        super.div(scalar, dest);
        return this;
    }

    @Override
    public Location3f div(float x, float y, float z) {
        super.div(x, y, z);
        return this;
    }

    @Override
    public Location3f div(float x, float y, float z, Vector3f dest) {
        super.div(x, y, z, dest);
        return this;
    }

    @Override
    public Location3f rotate(Quaternionfc quat) {
        super.rotate(quat);
        return this;
    }

    @Override
    public Location3f rotate(Quaternionfc quat, Vector3f dest) {
        super.rotate(quat, dest);
        return this;
    }

    @Override
    public Location3f rotateAxis(float angle, float x, float y, float z) {
        super.rotateAxis(angle, x, y, z);
        return this;
    }

    @Override
    public Location3f rotateAxis(float angle, float aX, float aY, float aZ, Vector3f dest) {
        super.rotateAxis(angle, aX, aY, aZ, dest);
        return this;
    }

    @Override
    public Location3f rotateX(float angle) {
        super.rotateX(angle);
        return this;
    }

    @Override
    public Location3f rotateX(float angle, Vector3f dest) {
        super.rotateX(angle, dest);
        return this;
    }

    @Override
    public Location3f rotateY(float angle) {
        super.rotateY(angle);
        return this;
    }

    @Override
    public Location3f rotateY(float angle, Vector3f dest) {
        super.rotateY(angle, dest);
        return this;
    }

    @Override
    public Location3f rotateZ(float angle) {
        super.rotateZ(angle);
        return this;
    }

    @Override
    public Location3f rotateZ(float angle, Vector3f dest) {
        super.rotateZ(angle, dest);
        return this;
    }

    @Override
    public Location3f normalize() {
        super.normalize();
        return this;
    }

    @Override
    public Location3f normalize(Vector3f dest) {
        super.normalize(dest);
        return this;
    }

    @Override
    public Location3f normalize(float length) {
        super.normalize(length);
        return this;
    }

    @Override
    public Location3f normalize(float length, Vector3f dest) {
        super.normalize(length, dest);
        return this;
    }

    @Override
    public Location3f cross(Vector3fc v) {
        super.cross(v);
        return this;
    }

    @Override
    public Location3f cross(float x, float y, float z) {
        super.cross(x, y, z);
        return this;
    }

    @Override
    public Location3f cross(Vector3fc v, Vector3f dest) {
        super.cross(v, dest);
        return this;
    }

    @Override
    public Location3f cross(float x, float y, float z, Vector3f dest) {
        super.cross(x, y, z, dest);
        return this;
    }

    @Override
    public Location3f zero() {
        super.zero();
        return this;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
    }

    @Override
    public Location3f negate() {
        super.negate();
        return this;
    }

    @Override
    public Location3f negate(Vector3f dest) {
        super.negate(dest);
        return this;
    }

    @Override
    public Location3f min(Vector3fc v) {
        super.min(v);
        return this;
    }

    @Override
    public Location3f min(Vector3fc v, Vector3f dest) {
        super.min(v, dest);
        return this;
    }

    @Override
    public Location3f max(Vector3fc v) {
        super.max(v);
        return this;
    }

    @Override
    public Location3f max(Vector3fc v, Vector3f dest) {
        super.max(v, dest);
        return this;
    }

    @Override
    public Location3f orthogonalize(Vector3fc v, Vector3f dest) {
        super.orthogonalize(v, dest);
        return this;
    }

    @Override
    public Location3f orthogonalize(Vector3fc v) {
        super.orthogonalize(v);
        return this;
    }

    @Override
    public Location3f orthogonalizeUnit(Vector3fc v, Vector3f dest) {
        super.orthogonalizeUnit(v, dest);
        return this;
    }

    @Override
    public Location3f orthogonalizeUnit(Vector3fc v) {
        super.orthogonalizeUnit(v);
        return this;
    }

    @Override
    public Location3f floor() {
        super.floor();
        return this;
    }

    @Override
    public Location3f floor(Vector3f dest) {
        super.floor(dest);
        return this;
    }

    @Override
    public Location3f ceil() {
        super.ceil();
        return this;
    }

    @Override
    public Location3f ceil(Vector3f dest) {
        super.ceil(dest);
        return this;
    }

    @Override
    public Location3f round() {
        super.round();
        return this;
    }

    @Override
    public Location3f round(Vector3f dest) {
        super.round(dest);
        return this;
    }

    @Override
    public Location3f absolute() {
        super.absolute();
        return this;
    }

    @Override
    public Location3f absolute(Vector3f dest) {
        super.absolute(dest);
        return this;
    }

    @Override
    public Location3f reflect(Vector3fc normal) {
        super.reflect(normal);
        return this;
    }

    @Override
    public Location3f reflect(float x, float y, float z) {
        super.reflect(x, y, z);
        return this;
    }

    @Override
    public Location3f reflect(Vector3fc normal, Vector3f dest) {
        super.reflect(normal, dest);
        return this;
    }

    @Override
    public Location3f reflect(float x, float y, float z, Vector3f dest) {
        super.reflect(x, y, z, dest);
        return this;
    }

    @Override
    public Location3f half(Vector3fc other) {
        super.half(other);
        return this;
    }

    @Override
    public Location3f half(float x, float y, float z) {
        super.half(x, y, z);
        return this;
    }

    @Override
    public Location3f half(Vector3fc other, Vector3f dest) {
        super.half(other, dest);
        return this;
    }

    @Override
    public Location3f half(float x, float y, float z, Vector3f dest) {
        super.half(x, y, z, dest);
        return this;
    }

    @Override
    public Location3f smoothStep(Vector3fc v, float t, Vector3f dest) {
        super.smoothStep(v, t, dest);
        return this;
    }

    @Override
    public Location3f hermite(Vector3fc t0, Vector3fc v1, Vector3fc t1, float t, Vector3f dest) {
        super.hermite(t0, v1, t1, t, dest);
        return this;
    }

    @Override
    public Location3f lerp(Vector3fc other, float t) {
        super.lerp(other, t);
        return this;
    }

    @Override
    public Location3f lerp(Vector3fc other, float t, Vector3f dest) {
        super.lerp(other, t, dest);
        return this;
    }

    @Override
    public Vector3i get(int mode, Vector3i dest) {
        return super.get(mode, dest);
    }

    @Override
    public Location3f get(Vector3f dest) {
        super.get(dest);
        return this;
    }

    @Override
    public Vector3d get(Vector3d dest) {
        return super.get(dest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location3fc that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equal(world, that.world()) && Objects.equal(pitch, that.pitch()) && Objects.equal(yaw, that.yaw()) && Objects.equal(headYaw, that.headYaw());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), pitch, yaw, headYaw, world);
    }

    @Override
    public String toString() {
        return Runtime.formatNumbers(toString(Options.NUMBER_FORMAT));
    }

    @Override
    public String toString(NumberFormat formatter) {
        return "(" + Runtime.format(x, formatter) + " " + Runtime.format(y, formatter) + " " + Runtime.format(z, formatter) + " pitch=" + this.pitch + " yaw=" + this.yaw + " headYaw=" + this.headYaw + " world=" + this.world.getName() + ")";
    }
}
