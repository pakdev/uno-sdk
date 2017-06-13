package uno.glsl

import android.opengl.GLES20
import android.opengl.GLES20.*
import uno.gln.glGetProgram
import uno.gln.glGetShader

/**
 * Created by GBarbieri on 24.01.2017.
 */

/**

 * @author GBarbieri
 */
open class Program {

    @JvmField val name: Int
    val uniforms = HashMap<String, Int>()

//    constructor(gl: GL3, shadersRoot: String, shadersSrc: String) : this(gl, shadersRoot, shadersSrc, shadersSrc)
//
//    constructor(
//            gl: GL3,
//            shadersRoot: String,
//            shadersSrc: String,
//            replaceVertOld: Array<String>,
//            replaceVertNew: Array<String>,
//            replaceFragOld: Array<String>,
//            replaceFragNew: Array<String>)
//
//            : this(gl, shadersRoot, shadersSrc, shadersSrc, replaceVertOld, replaceVertNew, replaceFragOld, replaceFragNew)
//
//
//    @JvmOverloads constructor(
//            gl: GL3,
//            shadersRoot: String,
//            vertSrc: String,
//            fragSrc: String,
//            replaceVertOld: Array<String>? = null,
//            replaceVertNew: Array<String>? = null,
//            replaceFragOld: Array<String>? = null,
//            replaceFragNew: Array<String>? = null) {
//
//        val vertShader = ShaderCode.create(gl, GL_VERTEX_SHADER, javaClass, shadersRoot, null, vertSrc, "vert", null, true)
//        val fragShader = ShaderCode.create(gl, GL_FRAGMENT_SHADER, javaClass, shadersRoot, null, fragSrc, "frag", null, true)
//
//        if (replaceVertOld != null && replaceVertNew != null)
//            repeat(replaceVertOld.size, { vertShader.replaceInShaderSource(replaceVertOld[it], replaceVertNew[it]) })
//
//        if (replaceFragOld != null && replaceFragNew != null)
//            repeat(replaceFragOld.size, { fragShader.replaceInShaderSource(replaceFragOld[it], replaceFragNew[it]) })
//
//        val shaderProgram = ShaderProgram()
//
//        shaderProgram.add(vertShader)
//        shaderProgram.add(fragShader)
//
//        shaderProgram.link(gl, System.err)
//
//        vertShader.destroy(gl)
//        fragShader.destroy(gl)
//
//        name = shaderProgram.program()
//    }
//
//    constructor(gl: GL3, vertUri: Uri, fragUri: Uri) {
//
//        val vertShader = ShaderCode.create(gl, GL_VERTEX_SHADER, 1, arrayOf(vertUri), false)
//        val fragShader = ShaderCode.create(gl, GL_FRAGMENT_SHADER, 1, arrayOf(fragUri), false)
//
//        val shaderProgram = ShaderProgram()
//
//        shaderProgram.add(vertShader)
//        shaderProgram.add(fragShader)
//
//        shaderProgram.link(gl, System.err)
//
//        vertShader.destroy(gl)
//        fragShader.destroy(gl)
//
//        name = shaderProgram.program()
//    }
//
//    constructor(gl: GL3, context: Class<*>, shaders: Array<String>, uniforms: Array<String> = emptyArray()) {
//
//        val shaderProgram = ShaderProgram()
//
//        val shaderCodes = shaders.map { shaderCodeOf(gl, context, it) }.onEach { shaderProgram.add(gl, it, System.err) }
//
//        shaderProgram.link(gl, System.err)
//
//        name = shaderProgram.program()
//
//        shaderCodes.forEach { it.destroy(gl) }
//
//        uniforms.forEach {
//            val i = gl.glGetUniformLocation(name, it)
//            if (i != -1)
//                this.uniforms[it] = i
//            else
//                System.err.print("unable to find $it uniform location!")
//        }
//    }
//
//    constructor(gl: GL3, context: Class<*>, vararg strings: String) {
//
//        val shaderProgram = ShaderProgram()
//
//        val root =
//                if (strings[0].isShader())
//                    ""
//                else
//                    if (strings[0].endsWith('/'))
//                        strings[0]
//                    else
//                        strings[0] + '/'
//
//        val (shaders, uniforms) = strings.drop(if (root.isEmpty()) 0 else 1).partition { it.isShader() }
//
//        val shaderCodes = shaders.map { shaderCodeOf(gl, context, root + it) }.onEach { shaderProgram.add(gl, it, System.err) }
//
//        shaderProgram.link(gl, System.err)
//
//        shaderCodes.forEach {
//            for (i in 0 until it.shader().capacity()) {
//                gl.glDetachShader(shaderProgram.program(), it.shader()[i])
//                gl.glDeleteShader(it.shader()[i])
//            }
//        }
//
//        name = shaderProgram.program()
//
//        uniforms.forEach {
//            val i = gl.glGetUniformLocation(name, it)
//            if (i != -1)
//                this.uniforms[it] = i
//            else
//                println("unable to find '$it' uniform location!")
//        }
//    }

    // for Learn OpenGL

    //    constructor(context: Class<*>, vararg strings: String) {
//
//        name = GL20.glCreateProgram()
//
//        val root =
//                if (strings[0].isShader())
//                    ""
//                else {
//                    var r = strings[0]
//                    if(r[0] != '/')
//                        r = "/$r"
//                    if(!r.endsWith('/'))
//                        r = "$r/"
//                    r
//                }
//
//        val (shaders, uniforms) = strings.drop(if (root.isEmpty()) 0 else 1).partition { it.isShader() }
//
//        val shaderNames = shaders.map { createShader(context, root + it) }.onEach { GL20.glAttachShader(name, it) }
//
//        GL20.glLinkProgram(name)
//
//        val status = GL20.glGetProgrami(name, GL_LINK_STATUS)
//        if (status == GL2ES2.GL_FALSE) {
//
//            val strInfoLog = GL20.glGetProgramInfoLog(name)
//
//            System.err.println("Linker failure: $strInfoLog")
//        }
//
//        shaderNames.forEach {
//            GL20.glDetachShader(name, it)
//            GL20.glDeleteShader(it)
//        }
//
//        uniforms.forEach {
//            val i = GL20.glGetUniformLocation(name, it)
//            if (i != -1)
//                this.uniforms[it] = i
//            else
//                println("unable to find '$it' uniform location!")
//        }
//    }
//
    constructor(vertexSrc: String, fragmentSrc: String, vararg attrs: Pair<String, Int>) {

        name = GLES20.glCreateProgram()

        val vertex = createShader(vertexSrc, GL_VERTEX_SHADER)
        val fragment = createShader(fragmentSrc, GL_FRAGMENT_SHADER)

        attrs.forEach { GLES20.glBindAttribLocation(name, it.second, it.first) }

        GLES20.glLinkProgram(name)

        if (glGetProgram(name, GL_LINK_STATUS) == GL_FALSE) {

            val strInfoLog = GLES20.glGetProgramInfoLog(name)

            System.err.println("Linker failure: $strInfoLog")
        }

        GLES20.glDetachShader(name, vertex)
        GLES20.glDetachShader(name, fragment)
        GLES20.glDeleteShader(vertex)
        GLES20.glDeleteShader(fragment)
//
//        uniforms.forEach {
//            val i = GL20.glGetUniformLocation(name, it)
//            if (i != -1)
//                this.uniforms[it] = i
//            else
//                println("unable to find '$it' uniform location!")
//        }
    }

    //
//    operator fun get(s: String): Int = uniforms[s]!!
//
//    internal fun String.isShader() = contains(".vert") || contains(".tesc") || contains(".tese") || contains(".geom") || contains(".frag") || contains(".comp")
//
//
//    fun createShader(context: Class<*>, path: String): Int {
//
//        val shader = GL20.glCreateShader(path.type)
//
//        val url = context::class.java.getResource(path)
//        val lines = File(url.toURI()).readLines()
//
//        var source = ""
//        lines.forEach {
//            if (it.startsWith("#include "))
//                source += parseInclude(context, path.substringBeforeLast('/'), it.substring("#include ".length).trim())
//            else
//                source += it
//            source += '\n'
//        }
//
//        GL20.glShaderSource(shader, source)
//
//        GL20.glCompileShader(shader)
//
//        val status = GL20.glGetShaderi(shader, GL_COMPILE_STATUS)
//        if (status == GL_FALSE) {
//
//            val strInfoLog = GL20.glGetShaderInfoLog(shader)
//
//            System.err.println("Compiler failure in ${path.substringAfterLast('/')} shader: $strInfoLog")
//        }
//
//        return shader
//    }
//
//    fun parseInclude(context: Class<*>, root: String, shader: String): String {
//        if (shader.startsWith('"') && shader.endsWith('"'))
//            shader.substring(1, shader.length - 1)
//        val url = context::class.java.getResource("$root/$shader")
//        return File(url.toURI()).readText() + "\n"
//    }
//
    fun createShader(src: String, type: Int): Int {

        val shader = GLES20.glCreateShader(type)

        GLES20.glShaderSource(shader, src)

        GLES20.glCompileShader(shader)

        if (glGetShader(shader, GL_COMPILE_STATUS) == GL_FALSE) {

            val strInfoLog = GLES20.glGetShaderInfoLog(shader)

            val strType = if (type == GL_VERTEX_SHADER) "vertex" else "fragment"
            System.err.println("Compiler failure in $strType shader: $strInfoLog")
        }

        return shader
    }
//
//    fun parseInclude(root: String, shader: String): String {
//        if (shader.startsWith('"') && shader.endsWith('"'))
//            shader.substring(1, shader.length - 1)
//        val url = ClassLoader.getSystemResource("$root/$shader")
//        return File(url.toURI()).readText() + "\n"
//    }
//
//    fun createProgram(shaderList: List<Int>): Int {
//
//        val program = GL20.glCreateProgram()
//
//        shaderList.forEach { GL20.glAttachShader(program, it) }
//
//        GL20.glLinkProgram(program)
//
//        val status = GL20.glGetProgrami(program, GL2ES2.GL_LINK_STATUS)
//        if (status == GL2ES2.GL_FALSE) {
//
//            val strInfoLog = GL20.glGetProgramInfoLog(program)
//
//            System.err.println("Linker failure: $strInfoLog")
//        }
//
//        shaderList.forEach {
//            GL20.glDetachShader(program, it)
//            GL20.glDeleteShader(it)
//        }
//
//        return program
//    }
//
//    private val String.type
//        get() = when (substringAfterLast('.')) {
//            "vert" -> GL_VERTEX_SHADER
//            "tesc" -> com.jogamp.opengl.GL3ES3.GL_TESS_CONTROL_SHADER
//            "tese" -> com.jogamp.opengl.GL3ES3.GL_TESS_EVALUATION_SHADER
//            "geom" -> com.jogamp.opengl.GL3ES3.GL_GEOMETRY_SHADER
//            "frag" -> com.jogamp.opengl.GL3ES3.GL_FRAGMENT_SHADER
//            "comp" -> com.jogamp.opengl.GL3ES3.GL_COMPUTE_SHADER
//            else -> throw Error("invalid shader extension")
//        }
}
