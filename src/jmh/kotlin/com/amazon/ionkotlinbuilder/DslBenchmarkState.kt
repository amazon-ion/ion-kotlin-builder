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