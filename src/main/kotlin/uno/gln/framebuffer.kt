package uno.gln

import android.opengl.GLES20
import android.opengl.GLES20.GL_FRAMEBUFFER
import android.opengl.GLES20.GL_TEXTURE_2D
import com.jogamp.opengl.GL2ES1.GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING
import glm_.vec2.Vec2i
import java.nio.IntBuffer

/**
 * Created by elect on 18/04/17.
 */


//fun glRenderbufferStorageMultisample(target: Int, samples: Int, internalformat: Int, size: Vec2i) =
//        GLES20.glRenderbufferStorageMultisample(target, samples, internalformat, size.x, size.y)

fun glBindRenderbuffer(target: Int) = GLES20.glBindRenderbuffer(target, 0)
fun glBindRenderbuffer(target: Int, buffer: IntBuffer) = GLES20.glBindRenderbuffer(target, buffer[0])

fun glFramebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: IntBuffer) =
        GLES20.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer[0])


fun glBindFramebuffer(target: Int, framebuffer: IntBuffer) = GLES20.glBindFramebuffer(target, framebuffer[0])
fun glBindFramebuffer(target: Int) = GLES20.glBindFramebuffer(target, 0)


fun glFramebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int) =
        GLES20.glFramebufferTexture2D(target, attachment, textarget, texture, 0)


fun initFramebuffer(framebuffer: IntBuffer, block: Framebuffer.() -> Unit) {
    GLES20.glGenFramebuffers(framebuffer.capacity(), framebuffer)
    Framebuffer.name = framebuffer[0]   // bind
    Framebuffer.block()
    GLES20.glBindFramebuffer(GL_FRAMEBUFFER, 0)
}


object Framebuffer {

    var name = 0
        set(value) {
            GLES20.glBindFramebuffer(GL_FRAMEBUFFER, value)
            field = value
        }

    fun texture2D(target: Int, attachment: Int, texture: Int, level: Int = 0) =
            GLES20.glFramebufferTexture2D(target, attachment, GL_TEXTURE_2D, texture, level)

    val Int.colorEncoding
        get(): Int {
            GLES20.glGetFramebufferAttachmentParameteriv(GL_FRAMEBUFFER, this, GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING, intBuffer)
            return intBuffer[0]
        }
}