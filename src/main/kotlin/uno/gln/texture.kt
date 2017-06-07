package uno.gln

import android.opengl.GLES10.*
import android.opengl.GLES20
import android.opengl.GLES20.GL_MIRRORED_REPEAT
import gli.Texture2d
import gli.gl.Format
import glm_.set
import glm_.vec2.Vec2i
import java.nio.ByteBuffer
import java.nio.IntBuffer

/**
 * Created by elect on 18/04/17.
 */


fun glBindTexture(target: Int, texture: IntBuffer) = GLES20.glBindTexture(target, texture[0])


//fun glTexStorage2D(target: Int, internalFormat: Int, size: Vec2i) = GLES20.glTexStorage2D(target, 1, internalFormat, size.x, size.y)


fun glBindTexture(target: Int) = GLES20.glBindTexture(target, 0)

//inline fun withTexture1d(texture: Int, block: Texture1d.() -> Unit) {
//    GLES20.glBindTexture(GL_TEXTURE_1D, texture)
//    Texture1d.block()
//    GLES20.glBindTexture(GL_TEXTURE_1D, 0)
//}

inline fun withTexture2d(texture: Int, block: ObjectTexture2d.() -> Unit) {
    GLES20.glBindTexture(GL_TEXTURE_2D, texture)
    ObjectTexture2d.block()
    GLES20.glBindTexture(GL_TEXTURE_2D, 0)
}

inline fun withTexture(target: Int, texture: Int, block: Texture.() -> Unit) {
    Texture.target = target
    GLES20.glBindTexture(target, texture)
    Texture.block()
    GLES20.glBindTexture(target, 0)
}

//inline fun withTexture1d(unit: Int, texture: Int, sampler: IntBuffer, block: Texture1d.() -> Unit) {
//    GLES20.glActiveTexture(GL_TEXTURE0 + unit)
//    Texture1d.name = texture  // bind
////    GLES20.glBindSampler(unit, sampler[0])
//    Texture1d.block()
//    GLES20.glBindTexture(GL_TEXTURE_1D, 0)
////    GLES20.glBindSampler(0, sampler[0])
//}

inline fun withTexture2d(unit: Int, texture: Int, sampler: IntBuffer, block: ObjectTexture2d.() -> Unit) =
        withTexture2d(unit, texture, sampler[0], block)

inline fun withTexture2d(unit: Int, texture: Int, sampler: Int, block: ObjectTexture2d.() -> Unit) {
    GLES20.glActiveTexture(GL_TEXTURE0 + unit)
    ObjectTexture2d.name = texture  // bind
//    GL33.glBindSampler(unit, sampler)
    ObjectTexture2d.block()
    GLES20.glBindTexture(GL_TEXTURE_2D, 0)
//    GL33.glBindSampler(0, sampler)
}

inline fun withTexture(unit: Int, target: Int, texture: Int, sampler: Int, block: Texture.() -> Unit) {
    Texture.target = target
    GLES20.glActiveTexture(GL_TEXTURE0 + unit)
    Texture.name = texture  // bind
//    GL33.glBindSampler(unit, sampler)
    Texture.block()
    GLES20.glBindTexture(target, 0)
//    GL33.glBindSampler(0, sampler)
}

//fun initTexture1d(texture: IntBuffer, block: Texture1d.() -> Unit) {
//    texture[0] = initTexture1d(block)
//}
//
//fun initTexture1d(block: Texture1d.() -> Unit): Int {
//    GLES20.glGenTextures(1, intBuffer)
//    val name = intBuffer[0]
//    Texture1d.name = name   // bind
//    Texture1d.block()
//    return name
//}

fun initTexture2d(texture: IntBuffer, block: ObjectTexture2d.() -> Unit) {
    texture[0] = initTexture2d(block)
}

fun initTexture2d(block: ObjectTexture2d.() -> Unit): Int {
    GLES20.glGenTextures(1, intBuffer)
    val name = intBuffer[0]
    ObjectTexture2d.name = name   // bind
    ObjectTexture2d.block()
    return name
}

fun initTexture(target: Int, texture: IntBuffer, block: Texture.() -> Unit) {
    texture[0] = initTexture(target, block)
}

fun initTexture(target: Int, block: Texture.() -> Unit): Int {
    GLES20.glGenTextures(1, intBuffer)
    val name = intBuffer[0]
    Texture.target = target
    Texture.name = name   // bind
    Texture.block()
    return name
}


fun initTextures2d(textures: IntBuffer, block: Textures2d.() -> Unit) {
    GLES20.glGenTextures(textures.capacity(), textures)
    Textures2d.names = textures
    Textures2d.block()
}

fun initTextures(target: Int, textures: IntBuffer, block: Textures.() -> Unit) {
    GLES20.glGenTextures(textures.capacity(), textures)
    Textures.target = target
    Textures.names = textures
    Textures.block()
}

object Texture {

    var target = 0
    var name = 0
        set(value) {
            GLES20.glBindTexture(target, value)
            field = name
        }

//    fun image1d(internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
//            GL11.glTexImage1D(GL_TEXTURE_1D, 0, internalFormat, width, 0, format, type, pixels)
//
//    fun image1d(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
//            GL11.glTexImage1D(GL_TEXTURE_1D, level, internalFormat, width, 0, format, type, pixels)

//    var baseLevel = 0
//        set(value) {
//            GLES20.glTexParameteri(target, GL_TEXTURE_BASE_LEVEL, value)
//            field = value
//        }
//    var maxLevel = 1_000
//        set(value) {
//            GLES20.glTexParameteri(target, GL_TEXTURE_MAX_LEVEL, value)
//            field = value
//        }
//    var levels = 0..1_000
//        set(value) {
//            glTexParameteri(target, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
//            glTexParameteri(target, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
//            field = value
//        }
}

//object Texture1d {
//
//    var name = 0
//        set(value) {
//            glBindTexture(GL_TEXTURE_1D, value)
//            field = name
//        }
//
//    fun image(internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
//            GL11.glTexImage1D(GL_TEXTURE_1D, 0, internalFormat, width, 0, format, type, pixels)
//
//    fun image(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
//            GL11.glTexImage1D(GL_TEXTURE_1D, level, internalFormat, width, 0, format, type, pixels)
//
//    var baseLevel = 0
//        set(value) {
//            glTexParameteri(GL_TEXTURE_1D, GL12.GL_TEXTURE_BASE_LEVEL, value)
//            field = value
//        }
//    var maxLevel = 1_000
//        set(value) {
//            glTexParameteri(GL_TEXTURE_1D, GL12.GL_TEXTURE_MAX_LEVEL, value)
//            field = value
//        }
//    var levels = 0..1_000
//        set(value) {
//            glTexParameteri(GL_TEXTURE_1D, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
//            glTexParameteri(GL_TEXTURE_1D, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
//            field = value
//        }
//}

object ObjectTexture2d {

    var name = 0
        set(value) {
            GLES20.glBindTexture(GL_TEXTURE_2D, value)
            field = name
        }

    fun image(internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GLES20.glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, type, pixels)

    fun image(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GLES20.glTexImage2D(GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, pixels)

//    fun storage(internalFormat: Int, size: Vec2i) = storage(1, internalFormat, size)
//    fun storage(levels: Int, internalFormat: Int, size: Vec2i) =
//            GL42.glTexStorage2D(GL_TEXTURE_2D, levels, internalFormat, size.x, size.y)

//    var baseLevel = 0
//        set(value) {
//            glTexParameteri(GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, value)
//            field = value
//        }
//    var maxLevel = 1_000
//        set(value) {
//            glTexParameteri(GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, value)
//            field = value
//        }
//    var levels = 0..1_000
//        set(value) {
//            glTexParameteri(GL_TEXTURE_1D, GL12.GL_TEXTURE_BASE_LEVEL, value.first)
//            glTexParameteri(GL_TEXTURE_1D, GL12.GL_TEXTURE_MAX_LEVEL, value.endInclusive)
//            field = value
//        }

    val linear = Filer.linear
    val nearest = Filer.nearest

    val nearest_mmNearest = Filer.nearest_mmNearest
    val linear_mmNearest = Filer.linear_mmNearest
    val nearest_mmLinear = Filer.nearest_mmLinear
    val linear_mmLinear = Filer.linear_mmLinear

    val clampToEdge = Wrap.clampToEdge
    val mirroredRepeat = Wrap.mirroredRepeat
    val repeat = Wrap.repeat

    var magFilter = linear
        set(value) {
            GLES20.glTexParameteri(name, GL_TEXTURE_MAG_FILTER, value.i)
            field = value
        }
    var minFilter = nearest_mmLinear
        set(value) {
            GLES20.glTexParameteri(name, GL_TEXTURE_MIN_FILTER, value.i)
            field = value
        }
    //    var maxAnisotropy = 1.0f
//        set(value) {
//            glTexParameteri(name, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, value)
//            field = value
//        }
    var wrapS = repeat
        set(value) {
            GLES20.glTexParameteri(name, GL_TEXTURE_WRAP_S, value.i)
            field = value
        }
    var wrapT = repeat
        set(value) {
            GLES20.glTexParameteri(name, GL_TEXTURE_WRAP_T, value.i)
            field = value
        }

    enum class Filer(val i: Int) {nearest(GL_NEAREST), linear(GL_LINEAR),
        nearest_mmNearest(GL_NEAREST_MIPMAP_NEAREST), linear_mmNearest(GL_LINEAR_MIPMAP_NEAREST),
        nearest_mmLinear(GL_NEAREST_MIPMAP_LINEAR), linear_mmLinear(GL_LINEAR_MIPMAP_LINEAR)
    }

    enum class Wrap(val i: Int) {clampToEdge(GL_CLAMP_TO_EDGE), mirroredRepeat(GL_MIRRORED_REPEAT), repeat(GL_REPEAT) }
}

object Textures {

    var target = 0
    lateinit var names: IntBuffer

//    fun image(level: Int, internalFormat: Int, width: Int, format: Int, type: Int, pixels: ByteBuffer) =
//            GL11.glTexImage1D(target, level, internalFormat, width, 0, format, type, pixels)

    fun image(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GLES20.glTexImage2D(target, level, internalFormat, width, height, 0, format, type, pixels)

//    fun at1d(index: Int, block: Texture1d.() -> Unit) {
//        Texture1d.name = names[index] // bind
//        Texture1d.block()
//    }

    fun at2d(index: Int, block: ObjectTexture2d.() -> Unit) {
        ObjectTexture2d.name = names[index] // bind
        ObjectTexture2d.block()
    }
}

object Textures2d {

    lateinit var names: IntBuffer

    fun image(level: Int, internalFormat: Int, width: Int, height: Int, format: Int, type: Int, pixels: ByteBuffer) =
            GLES20.glTexImage2D(GL_TEXTURE_2D, level, internalFormat, width, height, 0, format, type, pixels)

    fun at(index: Int, block: ObjectTexture2d.() -> Unit) {
        ObjectTexture2d.name = names[index] // bind
        ObjectTexture2d.block()
    }
}