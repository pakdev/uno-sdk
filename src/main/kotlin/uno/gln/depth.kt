package uno.gln

import android.opengl.GLES10.*
import android.opengl.GLES20

/**
 * Created by GBarbieri on 21.04.2017.
 */

fun depth(block: ObjectDepth.() -> Unit) = ObjectDepth.block()

object ObjectDepth {

    val never = Func.never
    val less = Func.less
    val equal = Func.equal
    val lEqual = Func.lEqual
    val greater = Func.greater
    val notEqual = Func.notEqual
    val gEqual = Func.gEqual
    val always = Func.always

    var test = false
        set(value) {
            if (value)
                GLES20.glEnable(GL_DEPTH_TEST)
            else
                GLES20.glDisable(GL_DEPTH_TEST)
        }
    var mask = true
        set(value) {
            GLES20.glDepthMask(value)
            field = value
        }
    var func = Func.less
        set(value) {
            GLES20.glDepthFunc(func.i)
            field = value
        }

    var range = 0f .. 1f
        set(value) {
            GLES20.glDepthRangef(value.start, value.endInclusive)
            field = value
        }
//    var clamp = false
//        set(value) {
//            if (value)
//                GLES20.glEnable(GL_DEPTH_CLAMP)
//            else
//                GLES20.glDisable(GL_DEPTH_CLAMP)
//        }

    enum class Func(val i: Int) { never(GL_NEVER), less(GL_LESS), equal(GL_EQUAL), lEqual(GL_LEQUAL),
        greater(GL_GREATER), notEqual(GL_NOTEQUAL), gEqual(GL_GEQUAL), always(GL_ALWAYS)
    }
}