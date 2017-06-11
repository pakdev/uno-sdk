package uno.gln

import android.opengl.GLES20
import glm_.mat2x2.Mat2
import glm_.mat3x3.Mat3
import glm_.mat4x4.Mat4
import glm_.vec2.Vec2
import glm_.vec3.Vec3
import glm_.vec4.Vec4
import uno.buffer.byteBufferOf
import uno.buffer.destroy
import uno.glsl.Program

fun glCreatePrograms(ints: IntArray) = repeat(ints.size) { ints[it] = GLES20.glCreateProgram() }

fun initPrograms(ints: IntArray, block: Programs.() -> Unit) {
    repeat(ints.size) { ints[it] = GLES20.glCreateProgram() }
    Programs.names = ints
    Programs.block()
}

inline fun usingProgram(program: Int = 0, block: ProgramUse.() -> Unit) {
    ProgramUse.name = program //glUse
    ProgramUse.block()
    GLES20.glUseProgram(0)
}

inline fun usingProgram(program: Program, block: ProgramUse.() -> Unit) {
    ProgramUse.name = program.name //glUse
    ProgramUse.block()
    GLES20.glUseProgram(0)
}

inline fun withProgram(program: Int = 0, block: ProgramBase.() -> Unit) {
    ProgramBase.name = program
    ProgramBase.block()
}

inline fun withProgram(program: Program, block: ProgramBase.() -> Unit) {
    ProgramBase.name = program.name
    ProgramBase.block()
}

object ProgramUse {

    var name = 0
        set(value) {
            GLES20.glUseProgram(value)
            field = value
        }

    val String.location: Int
        get() = GLES20.glGetUniformLocation(name, this)

//    var String.blockIndex
//        get() = GLES20.glGetUniformBlockIndex(name, this)
//        set(value) = glUniformBlockBinding(name, glGetUniformBlockIndex(name, this), value)

    var String.unit: Int
        get() = location
        set(value) = GLES20.glUniform1i(location, value)


    fun link() = GLES20.glLinkProgram(name)

    infix fun Int.to(location: Int) = GLES20.glUniform1i(location, this)
    infix fun Float.to(location: Int) = GLES20.glUniform1f(location, this)

    infix fun Vec2.to(location: Int) = GLES20.glUniform2fv(location, 1, this to vec2Buffer)
    infix fun Vec3.to(location: Int) = GLES20.glUniform3fv(location, 1, this to vec3Buffer)
    infix fun Vec4.to(location: Int) = GLES20.glUniform4fv(location, 1, this to vec4Buffer)

    infix fun Mat2.to(location: Int) = GLES20.glUniformMatrix2fv(location, 1, false, this to mat2Buffer)
    infix fun Mat3.to(location: Int) = GLES20.glUniformMatrix3fv(location, 1, false, this to mat3Buffer)
    infix fun Mat4.to(location: Int) = GLES20.glUniformMatrix4fv(location, 1, false, this to mat4Buffer)

    infix fun Int.to(uniform: String) = GLES20.glUniform1i(uniform.location, this)
    infix fun Float.to(uniform: String) = GLES20.glUniform1f(uniform.location, this)

    infix fun Vec2.to(uniform: String) = GLES20.glUniform2fv(uniform.location, 1, this to vec2Buffer)
    infix fun Vec3.to(uniform: String) = GLES20.glUniform3fv(uniform.location, 1, this to vec3Buffer)
    infix fun Vec4.to(uniform: String) = GLES20.glUniform4fv(uniform.location, 1, this to vec4Buffer)

    infix fun Mat2.to(uniform: String) = GLES20.glUniformMatrix2fv(uniform.location, 1, false, this to mat2Buffer)
    infix fun Mat3.to(uniform: String) = GLES20.glUniformMatrix3fv(uniform.location, 1, false, this to mat3Buffer)
    infix fun Mat4.to(uniform: String) = GLES20.glUniformMatrix4fv(uniform.location, 1, false, this to mat4Buffer)
}

object ProgramBase {

    var name = 0

    var String.location
        get() = GLES20.glGetUniformLocation(name, this)
        set(value) = GLES20.glBindAttribLocation(name, value, this)

    // read only because no program is used
//    val String.blockIndex
//        get() = glGetUniformBlockIndex(name, this)

    inline fun use(block: ProgramUse.() -> Unit) {
        ProgramUse.name = name
        ProgramUse.block()
        GLES20.glUseProgram(0)
    }

//    infix fun Int.blockBinding(uniformBlockBinding: Int) = glUniformBlockBinding(name, this, uniformBlockBinding)
//    infix fun Int.getBlockIndex(uniformBlockName: String) = GL31.glGetUniformBlockIndex(this, uniformBlockName)

    fun link() = GLES20.glLinkProgram(name)

    operator fun plusAssign(shader: Int) = GLES20.glAttachShader(name, shader)
    infix fun attach(shader: Int) = GLES20.glAttachShader(name, shader)
}

object Programs {

    lateinit var names: IntArray

    fun with(index: Int, block: ProgramBase.() -> Unit) {
        ProgramBase.name = names[index]
        ProgramBase.block()
    }

    fun using(index: Int, block: ProgramUse.() -> Unit) {
        ProgramUse.name = names[index]  // bind
        ProgramUse.block()
        GLES20.glUseProgram(0)
    }
}


fun glUseProgram(program: Program) = GLES20.glUseProgram(program.name)
fun glUseProgram() = GLES20.glUseProgram(0)

fun glDeletePrograms(programs: IntArray) = programs.forEach { GLES20.glDeleteProgram(it) }
fun glDeleteProgram(program: Program) = GLES20.glDeleteProgram(program.name)
fun glDeletePrograms(vararg programs: Program) = programs.forEach { GLES20.glDeleteProgram(it.name) }


fun glGetShader(shader: Int, pname: Int): Int {
    GLES20.glGetShaderiv(shader, pname, intBuffer)
    return intBuffer[0]
}