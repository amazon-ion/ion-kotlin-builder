package com.amazon.ionkotlinbuilder

import org.openjdk.jmh.annotations.Benchmark
import software.amazon.ion.IonType
import software.amazon.ion.IonWriter
import software.amazon.ion.Timestamp
import java.math.BigDecimal


open class DslBenchmark {

    @Benchmark
    fun singleScalarTextDsl(state: DslBenchmarkState): Any {
        state.textWriter.use { w -> w.dsl { writeInt(1) } }

        return state
    }

    @Benchmark
    fun singleScalarTextStreaming(state: DslBenchmarkState): Any {
        state.textWriter.use { w -> w.writeInt(1) }

        return state
    }

    @Benchmark
    fun singleScalarBinaryDsl(state: DslBenchmarkState): Any {
        state.binaryWriter.use { w -> w.dsl { writeInt(1) } }

        return state
    }

    @Benchmark
    fun singleScalarBinaryStreaming(state: DslBenchmarkState): Any {
        state.binaryWriter.use { w -> w.writeInt(1) }

        return state
    }

    @Benchmark
    fun multipleValuesTextDsl(state: DslBenchmarkState): Any {
        state.textWriter.use { writeMultipleValuesDsl(it) }

        return state
    }

    @Benchmark
    fun multipleValuesTextStreaming(state: DslBenchmarkState): Any {
        state.textWriter.use { writeMultipleValuesStreaming(it) }

        return state
    }

    @Benchmark
    fun multipleValuesBinaryDsl(state: DslBenchmarkState): Any {
        state.binaryWriter.use { writeMultipleValuesDsl(it) }

        return state
    }


    @Benchmark
    fun multipleValuesBinaryStreaming(state: DslBenchmarkState): Any {
        state.binaryWriter.use { writeMultipleValuesStreaming(it) }

        return state
    }

    private fun writeMultipleValuesDsl(w: IonWriter) {
        w.dsl {
            writeInt(1)
            writeString("text")
            writeSymbol("a_symbol")
            writeList {
                writeDecimal(BigDecimal.ONE)
                writeStruct {
                    writeString(field = "foo", value = "bar")
                    writeTimestamp(
                        field = "t",
                        value = Timestamp.valueOf("2019T"),
                        annotations = listOf("a", "b")
                    )
                }
            }
        }
    }

    private fun writeMultipleValuesStreaming(w: IonWriter) {
        w.writeInt(1)
        w.writeString("text")
        w.writeSymbol("a_symbol")

        w.stepIn(IonType.LIST)

        w.writeDecimal(BigDecimal.ONE)

        w.stepIn(IonType.STRUCT)

        w.setFieldName("foo")
        w.writeString("bar")

        w.setFieldName("t")
        w.setTypeAnnotations("a", "b")
        w.writeTimestamp(Timestamp.valueOf("2019T"))

        // struct
        w.stepOut()

        // list
        w.stepOut()
    }

}