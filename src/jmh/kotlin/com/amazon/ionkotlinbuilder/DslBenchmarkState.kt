package com.amazon.ionkotlinbuilder

import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import com.amazon.ion.system.IonTextWriterBuilder
import java.io.OutputStream

@State(Scope.Thread)
open class DslBenchmarkState {
    private val devNullOS = object : OutputStream() {
        override fun write(b: Int) {}
    }

    val textWriter = IonTextWriterBuilder.standard().build(devNullOS)!!
    val binaryWriter = IonTextWriterBuilder.standard().build(devNullOS)!!
}