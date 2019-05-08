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

import com.amazon.ion.*
import java.math.BigDecimal
import java.math.BigInteger

/**
 * DSL interface for values outside of a struct context.
 */
@IonDslBuilder
interface IonWriterDsl {

    /**
     * Writer being used.
     */
    val writer: IonWriter

    /**
     * Starts a struct context.
     *
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the struct.
     */
    fun struct(annotations: List<String> = listOf(), dslFunction: IonStructWriterDsl.() -> Unit)

    /**
     * Starts a list context.
     *
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the list.
     */
    fun list(annotations: List<String> = listOf(), dslFunction: IonWriterDsl.() -> Unit)

    /**
     * Starts a sexp context.
     *
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the sexp.
     */
    fun sexp(annotations: List<String> = listOf(), dslFunction: IonWriterDsl.() -> Unit)

    /**
     * Writes a DOM value.
     *
     * @param value value to write.
     */
    fun value(value: IonValue)

    /**
     * Writes a value from a reader.
     *
     * @param reader used to read a value from.
     *
     * @see IonWriter.writeValue
     */
    fun value(reader: IonReader)

    /**
     * Writes multiple values from a reader.
     *
     * @param reader used to read a values from.
     *
     * @see IonWriter.writeValues
     */
    fun values(reader: IonReader)

    /**
     * Writes a null value.
     *
     * @param type Ion type for strongly typed nulls. If type is null then writes `null.null`.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeNull
     */
    fun nullValue(type: IonType? = null, annotations: List<String> = listOf())

    /**
     * Writes a bool value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBool
     */
    fun bool(value: Boolean, annotations: List<String> = listOf())

    /**
     * Writes an int value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeInt
     */
    fun int(value: Long, annotations: List<String> = listOf())

    /**
     * Writes an int value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeInt
     */
    fun int(value: BigInteger, annotations: List<String> = listOf())

    /**
     * Writes a float value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeFloat
     */
    fun float(value: Double, annotations: List<String> = listOf())

    /**
     * Writes a decimal value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeDecimal
     */
    fun decimal(value: BigDecimal, annotations: List<String> = listOf())

    /**
     * Writes a String value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeString
     */
    fun string(value: String, annotations: List<String> = listOf())

    /**
     * Writes a [Timestamp] int value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeTimestamp
     */
    fun timestamp(value: Timestamp, annotations: List<String> = listOf())

    /**
     * Writes a blob value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBlob
     */
    fun blob(value: ByteArray, annotations: List<String> = listOf())

    /**
     * Writes a blob value.
     *
     * @param value value to write.
     * @param start offset of the first byte in value to write.
     * @param len number of bytes to write from value.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBlob
     */
    fun blob(value: ByteArray, start: Int, len: Int, annotations: List<String> = listOf())

    /**
     * Writes a clob value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeClob
     */
    fun clob(value: ByteArray, annotations: List<String> = listOf())

    /**
     * Writes a clob value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     * @param start offset of the first byte in value to write.
     * @param len number of bytes to write from value.
     *
     * @see IonWriter.writeClob
     */
    fun clob(value: ByteArray, start: Int, len: Int, annotations: List<String> = listOf())

    /**
     * Writes a symbol from its [SymbolToken].
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeSymbolToken
     */
    fun symbolToken(value: SymbolToken, annotations: List<String> = listOf())

    /**
     * Writes a symbol from its text.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see [IonWriter.writeSymbol]
     */
    fun symbol(value: String, annotations: List<String> = listOf())
}