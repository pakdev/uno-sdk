package uno.buffer

import glm_.*
import uno.kotlin.Quadruple
import uno.kotlin.Quintuple
import java.nio.*

/**
 * Created by elect on 05/03/17.
 */


fun FloatArray.toFloatBuffer(): FloatBuffer {
    val floatBuffer = floatBufferBig(size * Float.BYTES)
    for (i in 0 until size)
        floatBuffer.put(i, this[i])
    return floatBuffer
}

fun FloatArray.toByteBuffer(): ByteBuffer {
    val byteBuffer = byteBufferBig(size * Float.BYTES)
    for (i in 0 until size)
        byteBuffer.putFloat(i * Float.BYTES, this[i])
    return byteBuffer
}

fun DoubleArray.toDoubleBuffer(): DoubleBuffer {
    val doubleBuffer = doubleBufferBig(size * Double.BYTES)
    for (i in 0 until size)
        doubleBuffer.put(i, this[i])
    return doubleBuffer
}

fun DoubleArray.toByteBuffer(): ByteBuffer {
    val byteBuffer = byteBufferBig(size * Double.BYTES)
    for (i in 0 until size)
        byteBuffer.putDouble(i * Double.BYTES, this[i])
    return byteBuffer
}

fun ByteArray.toByteBuffer(): ByteBuffer {
    val byteBuffer = byteBufferBig(size)
    for (i in 0 until size)
        byteBuffer.put(i, this[i])
    return byteBuffer
}


fun ShortArray.toShortBuffer(): ShortBuffer {
    val shortBuffer = shortBufferBig(size * Short.BYTES)
    for (i in 0 until size)
        shortBuffer.put(i, this[i])
    return shortBuffer
}

fun ShortArray.toByteBuffer(): ByteBuffer {
    val byteBuffer = byteBufferBig(size * Short.BYTES)
    for (i in 0 until size)
        byteBuffer.putShort(i * Short.BYTES, this[i])
    return byteBuffer
}

fun IntArray.toIntBuffer(): IntBuffer {
    val intBuffer = intBufferBig(size * Int.BYTES)
    for (i in 0 until size)
        intBuffer.put(i, this[i])
    return intBuffer
}

fun IntArray.toByteBuffer(): ByteBuffer {
    val byteBuffer = byteBufferBig(size * Int.BYTES)
    for (i in 0 until size)
        byteBuffer.putInt(i * Int.BYTES, this[i])
    return byteBuffer
}

fun LongArray.toLongBuffer(): LongBuffer {
    val longBuffer = longBufferBig(size * Long.BYTES)
    for (i in 0 until size)
        longBuffer.put(i, this[i])
    return longBuffer
}

fun LongArray.toByteBuffer(): ByteBuffer {
    val byteBuffer = byteBufferBig(size * Long.BYTES)
    for (i in 0 until size)
        byteBuffer.putLong(i * Long.BYTES, this[i])
    return byteBuffer
}


fun floatBufferOf(vararg floats: Float): FloatBuffer {
    val count = floats.size
    val floatBuffer = ByteBuffer.allocateDirect(count * Float.BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer()
    for (i in 0 until count)
        floatBuffer.put(i, floats[i])
    return floatBuffer
}

fun floatBufferOf(vararg elements: Number): FloatBuffer {
    val count = elements.size
    val floatBuffer = ByteBuffer.allocateDirect(count * Float.BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer()
    for (i in 0 until count)
        floatBuffer.put(i, elements[i].f)
    return floatBuffer
}

fun doubleBufferOf(vararg doubles: Double): DoubleBuffer {
    val count = doubles.size
    val doubleBuffer = ByteBuffer.allocateDirect(count * Double.BYTES).order(ByteOrder.nativeOrder()).asDoubleBuffer()
    for (i in 0 until count)
        doubleBuffer.put(i, doubles[i])
    return doubleBuffer
}

fun doubleBufferOf(vararg elements: Number): DoubleBuffer {
    val count = elements.size
    val doubleBuffer = ByteBuffer.allocateDirect(count * Double.BYTES).order(ByteOrder.nativeOrder()).asDoubleBuffer()
    for (i in 0 until count)
        doubleBuffer.put(i, elements[i].d)
    return doubleBuffer
}

fun byteBufferOf(vararg bytes: Byte): ByteBuffer {
    val count = bytes.size
    val byteBuffer = ByteBuffer.allocateDirect(count * Byte.BYTES).order(ByteOrder.nativeOrder())
    for (i in 0 until count)
        byteBuffer.put(i, bytes[i])
    return byteBuffer
}

fun byteBufferOf(vararg elements: Number): ByteBuffer {
    val count = elements.size
    val byteBuffer = ByteBuffer.allocateDirect(count * Byte.BYTES).order(ByteOrder.nativeOrder())
    for (i in 0 until count)
        byteBuffer.put(i, elements[i].b)
    return byteBuffer
}

fun shortBufferOf(vararg shorts: Short): ShortBuffer {
    val count = shorts.size
    val shortBuffer = ByteBuffer.allocateDirect(count * Short.BYTES).order(ByteOrder.nativeOrder()).asShortBuffer()
    for (i in 0 until count)
        shortBuffer.put(i, shorts[i])
    return shortBuffer
}

fun shortBufferOf(vararg elements: Number): ShortBuffer {
    val count = elements.size
    val shortBuffer = ByteBuffer.allocateDirect(count * Short.BYTES).order(ByteOrder.nativeOrder()).asShortBuffer()
    for (i in 0 until count)
        shortBuffer.put(i, elements[i].s)
    return shortBuffer
}

fun intBufferOf(vararg ints: Int): IntBuffer {
    val count = ints.size
    val intBuffer = ByteBuffer.allocateDirect(count * Int.BYTES).order(ByteOrder.nativeOrder()).asIntBuffer()
    for (i in 0 until count)
        intBuffer.put(i, ints[i])
    return intBuffer
}

fun intBufferOf(vararg elements: Number): IntBuffer {
    val count = elements.size
    val intBuffer = ByteBuffer.allocateDirect(count * Int.BYTES).order(ByteOrder.nativeOrder()).asIntBuffer()
    for (i in 0 until count)
        intBuffer.put(i, elements[i].i)
    return intBuffer
}

fun longBufferOf(vararg longs: Long): LongBuffer {
    val count = longs.size
    val longBuffer = ByteBuffer.allocateDirect(count * Long.BYTES).order(ByteOrder.nativeOrder()).asLongBuffer()
    for (i in 0 until count)
        longBuffer.put(i, longs[i])
    return longBuffer
}

fun longBufferOf(vararg elements: Number): LongBuffer {
    val count = elements.size
    val longBuffer = ByteBuffer.allocateDirect(count * Long.BYTES).order(ByteOrder.nativeOrder()).asLongBuffer()
    for (i in 0 until count)
        longBuffer.put(i, elements[i].L)
    return longBuffer
}

fun floatBufferBig(size: Int): FloatBuffer = ByteBuffer.allocateDirect(size * Float.BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer()
fun doubleBufferBig(size: Int): DoubleBuffer = ByteBuffer.allocateDirect(size * Double.BYTES).order(ByteOrder.nativeOrder()).asDoubleBuffer()

fun byteBufferBig(size: Int): ByteBuffer = ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder())
fun shortBufferBig(size: Int): ShortBuffer = ByteBuffer.allocateDirect(size * Short.BYTES).order(ByteOrder.nativeOrder()).asShortBuffer()
fun intBufferBig(size: Int): IntBuffer = ByteBuffer.allocateDirect(size * Int.BYTES).order(ByteOrder.nativeOrder()).asIntBuffer()
fun longBufferBig(size: Int): LongBuffer = ByteBuffer.allocateDirect(size * Long.BYTES).order(ByteOrder.nativeOrder()).asLongBuffer()


// i.e: clear color buffer
fun FloatBuffer.put(vararg floats: Float): FloatBuffer {
    floats.forEachIndexed { i, f -> put(i, f) }
    return this
}


fun byteBuffersBig(sizeA: Int, sizeB: Int) = Pair(byteBufferBig(sizeA), byteBufferBig(sizeB))
fun byteBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(byteBufferBig(sizeA), byteBufferBig(sizeB), byteBufferBig(sizeC))
fun byteBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(byteBufferBig(sizeA), byteBufferBig(sizeB), byteBufferBig(sizeC), byteBufferBig(sizeD))
fun byteBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(byteBufferBig(sizeA), byteBufferBig(sizeB), byteBufferBig(sizeC), byteBufferBig(sizeD), byteBufferBig(sizeE))

fun shortBuffersBig(sizeA: Int, sizeB: Int) = Pair(shortBufferBig(sizeA), shortBufferBig(sizeB))
fun shortBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(shortBufferBig(sizeA), shortBufferBig(sizeB), shortBufferBig(sizeC))
fun shortBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(shortBufferBig(sizeA), shortBufferBig(sizeB), shortBufferBig(sizeC), shortBufferBig(sizeD))
fun shortBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(shortBufferBig(sizeA), shortBufferBig(sizeB), shortBufferBig(sizeC), shortBufferBig(sizeD), shortBufferBig(sizeE))

fun intBuffersBig(sizeA: Int, sizeB: Int) = Pair(intBufferBig(sizeA), intBufferBig(sizeB))
fun intBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(intBufferBig(sizeA), intBufferBig(sizeB), intBufferBig(sizeC))
fun intBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(intBufferBig(sizeA), intBufferBig(sizeB), intBufferBig(sizeC), intBufferBig(sizeD))
fun intBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(intBufferBig(sizeA), intBufferBig(sizeB), intBufferBig(sizeC), intBufferBig(sizeD), intBufferBig(sizeE))

fun longBuffersBig(sizeA: Int, sizeB: Int) = Pair(longBufferBig(sizeA), longBufferBig(sizeB))
fun longBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(longBufferBig(sizeA), longBufferBig(sizeB), longBufferBig(sizeC))
fun longBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(longBufferBig(sizeA), longBufferBig(sizeB), longBufferBig(sizeC), longBufferBig(sizeD))
fun longBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(longBufferBig(sizeA), longBufferBig(sizeB), longBufferBig(sizeC), longBufferBig(sizeD), longBufferBig(sizeE))

fun floatBuffersBig(sizeA: Int, sizeB: Int) = Pair(floatBufferBig(sizeA), floatBufferBig(sizeB))
fun floatBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(floatBufferBig(sizeA), floatBufferBig(sizeB), floatBufferBig(sizeC))
fun floatBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(floatBufferBig(sizeA), floatBufferBig(sizeB), floatBufferBig(sizeC), floatBufferBig(sizeD))
fun floatBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(floatBufferBig(sizeA), floatBufferBig(sizeB), floatBufferBig(sizeC), floatBufferBig(sizeD), floatBufferBig(sizeE))

fun doubleBuffersBig(sizeA: Int, sizeB: Int) = Pair(doubleBufferBig(sizeA), doubleBufferBig(sizeB))
fun doubleBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int) = Triple(doubleBufferBig(sizeA), doubleBufferBig(sizeB), doubleBufferBig(sizeC))
fun doubleBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int) = Quadruple(doubleBufferBig(sizeA), doubleBufferBig(sizeB), doubleBufferBig(sizeC), doubleBufferBig(sizeD))
fun doubleBuffersBig(sizeA: Int, sizeB: Int, sizeC: Int, sizeD: Int, sizeE: Int) = Quintuple(doubleBufferBig(sizeA), doubleBufferBig(sizeB), doubleBufferBig(sizeC), doubleBufferBig(sizeD), doubleBufferBig(sizeE))
