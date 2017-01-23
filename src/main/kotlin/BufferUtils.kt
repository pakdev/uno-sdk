/*
 * Copyright (c) 2009-2012 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.nio.*
import java.util.logging.Level
import java.util.logging.Logger

fun ByteBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun ShortBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun IntBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun LongBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun FloatBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun DoubleBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)
fun CharBuffer.destroy() = BufferUtils.destroyDirectBuffer(this)

class BufferUtils {

    companion object {

        // Oracle JRE / OpenJDK
        val cleanerMethod = loadMethod("sun.nio.ch.DirectBuffer", "cleaner")
        val cleanMethod = loadMethod("sun.misc.Cleaner", "clean")
        val viewedBufferMethod = loadMethod("sun.nio.ch.DirectBuffer", "viewedBuffer")
                ?: loadMethod("sun.nio.ch.DirectBuffer", "attachment")
        // Apache Harmony
        val freeMethod = try {
            ByteBuffer.allocateDirect(1).javaClass.getMethod("free")
        } catch (ex: NoSuchMethodException) {
            null
        } catch (ex: SecurityException) {
            null
        }

        private fun loadMethod(className: String, methodName: String): Method? {
            try {
                val method = Class.forName(className).getMethod(methodName)
                method.isAccessible = true// according to the Java documentation, by default, a reflected object is not accessible
                return method
            } catch (ex: NoSuchMethodException) {
                return null // the method was not found
            } catch (ex: SecurityException) {
                return null // setAccessible not allowed by security policy
            } catch (ex: ClassNotFoundException) {
                return null // the direct buffer implementation was not found
            } catch (t: Throwable) {
                if (t.javaClass.name == "java.lang.reflect.InaccessibleObjectException")
                    return null// the class is in an unexported module
                else
                    throw t
            }
        }

        /**
         * This function explicitly calls the Cleaner method of a direct buffer.

         * @param toBeDestroyed
         * *            The direct buffer that will be "cleaned". Utilizes reflection.
         */
        @JvmStatic fun destroyDirectBuffer(toBeDestroyed: Buffer) {
            try {
                if (freeMethod != null)
                    freeMethod.invoke(toBeDestroyed)
                else {
                    //TODO load the methods only once, store them into a cache (only for Java >= 9)
                    val localCleanerMethod = cleanerMethod ?: loadMethod(toBeDestroyed.javaClass.name, "cleaner")

                    if (localCleanerMethod == null)
                        Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "Buffer cannot be destroyed: {0}", toBeDestroyed)
                    else {
                        val cleaner = localCleanerMethod.invoke(toBeDestroyed)
                        if (cleaner != null) {
                            val localCleanMethod = cleanMethod ?:
                                    if (cleaner is Runnable)    // jdk.internal.ref.Cleaner implements Runnable in Java 9
                                        loadMethod(Runnable::class.java.name, "run")
                                    else    // sun.misc.Cleaner does not implement Runnable in Java < 9
                                        loadMethod(cleaner.javaClass.name, "clean")

                            if (localCleanMethod == null)
                                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE,
                                        "Buffer cannot be destroyed: {0}", toBeDestroyed)
                            else
                                localCleanMethod.invoke(cleaner)

                        } else {
                            val localViewedBufferMethod = viewedBufferMethod ?: loadMethod(toBeDestroyed.javaClass.name, "viewedBuffer")

                            if (localViewedBufferMethod == null)
                                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE,
                                        "Buffer cannot be destroyed: {0}", toBeDestroyed)
                            else {  // Try the alternate approach of getting the viewed buffer first
                                val viewedBuffer = localViewedBufferMethod.invoke(toBeDestroyed)
                                if (viewedBuffer != null)
                                    destroyDirectBuffer(viewedBuffer as Buffer)
                                else
                                    Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE,
                                            "Buffer cannot be destroyed: {0}", toBeDestroyed)
                            }
                        }
                    }
                }
            } catch (ex: IllegalAccessException) {
                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "{0}", ex)
            } catch (ex: IllegalArgumentException) {
                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "{0}", ex)
            } catch (ex: InvocationTargetException) {
                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "{0}", ex)
            } catch (ex: SecurityException) {
                Logger.getLogger(BufferUtils::class.java.name).log(Level.SEVERE, "{0}", ex)
            }
        }
    }
}

