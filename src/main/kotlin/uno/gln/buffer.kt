package uno.gln

import android.opengl.GLES20
import android.opengl.GLES20.GL_ARRAY_BUFFER
import android.opengl.GLES20.GL_ELEMENT_ARRAY_BUFFER
import com.jogamp.opengl.GL2ES3.GL_UNIFORM_BUFFER
import glm_.L
import glm_.mat4x4.Mat4
import glm_.set
import glm_.size
import java.nio.*

/**
 * Created by elect on 18/04/17.
 */


fun glBufferData(target: Int, size: Int, usage: Int) = GLES20.glBufferData(target, size, null, usage);

// ----- Mat4 -----
fun glBufferData(target: Int, mat: Mat4, usage: Int) = GLES20.glBufferData(target, Mat4.size, mat to mat4Buffer, usage)

fun glBufferSubData(target: Int, offset: Int, mat4: Mat4) = GLES20.glBufferSubData(target, offset, Mat4.size, mat4 to mat4Buffer)
fun glBufferSubData(target: Int, mat: Mat4) = GLES20.glBufferSubData(target, 0, Mat4.size, mat to mat4Buffer)

fun glBindBuffer(target: Int) = GLES20.glBindBuffer(target, 0)
fun glBindBuffer(target: Int, buffer: IntBuffer) = GLES20.glBindBuffer(target, buffer[0])

//fun glBindBufferRange(target: Int, index: Int, buffer: IntBuffer, offset: Int, size: Int) =
//        GLES30.glBindBufferRange(target, index, buffer[0], offset.L, size.L)

//fun glBindBufferBase(target: Int, index: Int) = GLES20.glBindBufferBase(target, index, 0)

fun initArrayBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL_ARRAY_BUFFER, block)
}

fun initElementBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL_ELEMENT_ARRAY_BUFFER, block)
}

fun initUniformBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) {
    buffer[0] = initBuffer(GL_UNIFORM_BUFFER, block)
}

fun initUniformBuffers(buffers: IntBuffer, block: Buffers.() -> Unit) {
    Buffers.target = GL_UNIFORM_BUFFER
    GLES20.glGenBuffers(buffers.capacity(), buffers)
    Buffers.buffers = buffers
    Buffers.block()
    GLES20.glBindBuffer(GL_UNIFORM_BUFFER, 0)
}

fun initBuffers(buffers: IntBuffer, block: Buffers.() -> Unit) {
    GLES20.glGenBuffers(buffers.capacity(), buffers)
    Buffers.buffers = buffers
    Buffers.block()
}

fun initBuffer(target: Int, block: Buffer.() -> Unit): Int {
    Buffer.target = target
    GLES20.glGenBuffers(1, intBuffer)
    val name = intBuffer[0]
    Buffer.name = name // bind
    Buffer.block()
    GLES20.glBindBuffer(target, 0)
    return name
}

fun withBuffer(target: Int, buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(target, buffer[0], block)
fun withBuffer(target: Int, buffer: Int, block: Buffer.() -> Unit) {
    Buffer.target = target
    Buffer.name = buffer   // bind
    Buffer.block()
    GLES20.glBindBuffer(target, 0)
}

fun withArrayBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL_ARRAY_BUFFER, buffer[0], block)
fun withArrayBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL_ARRAY_BUFFER, buffer, block)
fun withElementBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer[0], block)
fun withElementBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL_ELEMENT_ARRAY_BUFFER, buffer, block)
fun withUniformBuffer(buffer: IntBuffer, block: Buffer.() -> Unit) = withBuffer(GL_UNIFORM_BUFFER, buffer[0], block)
fun withUniformBuffer(buffer: Int, block: Buffer.() -> Unit) = withBuffer(GL_UNIFORM_BUFFER, buffer, block)

object Buffer {

    var target = 0
    var name = 0
        set(value) {
            GLES20.glBindBuffer(target, value)
            field = value
        }

    fun data(data: ByteBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)
    fun data(data: ShortBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)
    fun data(data: IntBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)
    fun data(data: LongBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)
    fun data(data: FloatBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)

    fun data(data: DoubleBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)

    fun data(size: Int, usage: Int) = GLES20.glBufferData(target, size, null, usage)

    fun subData(offset: Int, data: ByteBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)
    fun subData(offset: Int, data: ShortBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)
    fun subData(offset: Int, data: IntBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)
    fun subData(offset: Int, data: LongBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)
    fun subData(offset: Int, data: FloatBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)

    fun subData(offset: Int, data: DoubleBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)

    fun subData(data: ByteBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)
    fun subData(data: ShortBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)
    fun subData(data: IntBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)
    fun subData(data: LongBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)
    fun subData(data: FloatBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)

    fun subData(data: DoubleBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)


    // ----- Mat4 -----
    fun data(mat: Mat4, usage: Int) = GLES20.glBufferData(target, Mat4.size, mat to mat4Buffer, usage)

    fun subData(offset: Int, mat: Mat4) = GLES20.glBufferSubData(target, offset, Mat4.size, mat to mat4Buffer)
    fun subData(mat: Mat4) = GLES20.glBufferSubData(target, 0, Mat4.size, mat to mat4Buffer)


//    fun bindRange(index: Int, offset: Int, size: Int) = glBindBufferRange(target, index, name, offset.L, size.L)
//    fun bindBase(index: Int) = glBindBufferBase(target, index, 0)

//    fun mapRange(length: Int, access: Int) = mapRange(0, length, access)
//    fun mapRange(offset: Int, length: Int, access: Int): ByteBuffer = glMapBufferRange(target, offset.L, length.L, access)
}

object Buffers {

    lateinit var buffers: IntBuffer
    var target = 0
    var name = 0

    fun data(data: ByteBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)
    fun data(data: ShortBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)
    fun data(data: IntBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)
    fun data(data: LongBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)
    fun data(data: FloatBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)

    fun data(data: DoubleBuffer, usage: Int) = GLES20.glBufferData(target, data.size, data, usage)

    fun data(size: Int, usage: Int) = GLES20.glBufferData(target, size, null, usage)

    fun subData(offset: Int, data: ByteBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)
    fun subData(offset: Int, data: ShortBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)
    fun subData(offset: Int, data: IntBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)
    fun subData(offset: Int, data: LongBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)
    fun subData(offset: Int, data: FloatBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)

    fun subData(offset: Int, data: DoubleBuffer) = GLES20.glBufferSubData(target, offset, data.size, data)

    fun subData(data: ByteBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)
    fun subData(data: ShortBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)
    fun subData(data: IntBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)
    fun subData(data: LongBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)
    fun subData(data: FloatBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)

    fun subData(data: DoubleBuffer) = GLES20.glBufferSubData(target, 0, data.size, data)


    // ----- Mat4 -----
    fun data(mat: Mat4, usage: Int) = GLES20.glBufferData(target, Mat4.size, mat to mat4Buffer, usage)

    fun subData(offset: Int, mat: Mat4) = GLES20.glBufferSubData(target, offset, Mat4.size, mat to mat4Buffer)
    fun subData(mat: Mat4) = GLES20.glBufferSubData(target, 0, Mat4.size, mat to mat4Buffer)


//    fun range(index: Int, offset: Int, size: Int) = glBindBufferRange(target, index, name, offset.L, size.L)
//    fun base(index: Int) = glBindBufferBase(target, index, 0)


    fun at(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = target
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
    }

    fun withArrayAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL_ARRAY_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GLES20.glBindBuffer(GL_ARRAY_BUFFER, 0)
    }

    fun withElementAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL_ELEMENT_ARRAY_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GLES20.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
    }

    fun withUniformAt(bufferIndex: Int, block: Buffer.() -> Unit) {
        Buffer.target = GL_UNIFORM_BUFFER
        Buffer.name = buffers[bufferIndex] // bind
        Buffer.block()
        GLES20.glBindBuffer(GL_UNIFORM_BUFFER, 0)
    }
}