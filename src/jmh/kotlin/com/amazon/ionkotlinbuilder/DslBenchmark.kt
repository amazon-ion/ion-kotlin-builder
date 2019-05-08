/*
 * Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazon.ionkotlinbuilder

import org.openjdk.jmh.annotations.Benchmark
import com.amazon.ion.IonType
import com.amazon.ion.IonWriter
import com.amazon.ion.Timestamp
import java.math.BigDecimal

open class DslBenchmark {

    @Benchmark
    fun singleScalarTextDsl(state: DslBenchmarkState): Any {
        state.textWriter.use { w -> w.dsl { int(1) } }

        return state
    }

    @Benchmark
    fun singleScalarTextStreaming(state: DslBenchmarkState): Any {
        state.textWriter.use { w -> w.writeInt(1) }

        return state
    }

    @Benchmark
    fun singleScalarBinaryDsl(state: DslBenchmarkState): Any {
        state.binaryWriter.use { w -> w.dsl { int(1) } }

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
            int(1)
            string("text")
            symbol("a_symbol")
            list {
                decimal(BigDecimal.ONE)
                struct {
                    string(field = "foo", value = "bar")
                    timestamp(
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