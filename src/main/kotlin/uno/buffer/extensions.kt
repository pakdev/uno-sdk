package uno.buffer

import glm_.BYTES
import org.lwjgl.system.MemoryUtil
import java.nio.*

fun FloatArray.toFloatBuf(): FloatBuffer {
    val res = MemoryUtil.memAllocFloat(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun FloatArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Float.BYTES)
    for (i in 0 until size) res.putFloat(i * Float.BYTES, this[i])
    return res
}

fun DoubleArray.toDoubleBuf(): DoubleBuffer {
    val res = MemoryUtil.memAllocDouble(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun DoubleArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Double.BYTES)
    for (i in 0 until size) res.putDouble(i * Double.BYTES, this[i])
    return res
}

fun ByteArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun ShortArray.toShortBuf(): ShortBuffer {
    val res = MemoryUtil.memAllocShort(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun ShortArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Short.BYTES)
    for (i in 0 until size) res.putShort(i * Short.BYTES, this[i])
    return res
}

fun IntArray.toIntBuf(): IntBuffer {
    val res = MemoryUtil.memAllocInt(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun IntArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Int.BYTES)
    for (i in 0 until size) res.putInt(i * Int.BYTES, this[i])
    return res
}

fun LongArray.toLongBuffer(): LongBuffer {
    val res = MemoryUtil.memAllocLong(size)
    for (i in 0 until size) res.put(i, this[i])
    return res
}

fun LongArray.toBuf(): ByteBuffer {
    val res = MemoryUtil.memAlloc(size * Long.BYTES)
    for (i in 0 until size) res.putLong(i * Long.BYTES, this[i])
    return res
}