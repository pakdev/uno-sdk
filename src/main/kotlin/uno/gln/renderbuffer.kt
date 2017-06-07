package uno.gln

import android.opengl.GLES20
import android.opengl.GLES20.GL_RENDERBUFFER
import glm_.vec2.Vec2i

/**
 * Created by elect on 13/05/17.
 */

fun glRenderbufferStorage(internalFormat: Int, width: Int, height: Int) = GLES20.glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, width, height)
fun glRenderbufferStorage(internalFormat: Int, size: Vec2i) = GLES20.glRenderbufferStorage(GL_RENDERBUFFER, internalFormat, size.x, size.y)
fun glRenderbufferStorage(target:Int, internalFormat: Int, size: Vec2i) = GLES20.glRenderbufferStorage(target, internalFormat, size.x, size.y)