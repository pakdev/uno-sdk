package uno.gln

import android.opengl.GLES10.GL_COLOR_BUFFER_BIT
import android.opengl.GLES10.GL_DEPTH_BUFFER_BIT
import android.opengl.GLES20
import glm_.f

/**
 * Created by GBarbieri on 21.04.2017.
 */

inline fun clear(block: Clear.() -> Unit) {
    Clear.color = false
    Clear.depth = false
    Clear.block()
}

object Clear {

    var color = false
    var depth = false

    fun color() = color(0f, 0f, 0f, 1f)
    fun color(f: Float) = color(f, f, f, f)
    fun color(r: Float, g: Float, b: Float, a: Float) = GLES20.glClearColor(r, g, b, a)

    fun color(n: Number) = color(n, n, n, n)
    fun color(r: Number, g: Number, b: Number, a: Number) = GLES20.glClearColor(r.f, g.f, b.f, a.f)

    fun depth() = depth(1f)
    fun depth(depth: Float) = GLES20.glClearDepthf(depth)
    fun depth(depth: Number) = depth(depth.f)

    // TODO stencil

    fun clear() = GLES20.glClear(
            if (color) GL_COLOR_BUFFER_BIT else 1 or
            if (depth) GL_DEPTH_BUFFER_BIT else 1
    )
}