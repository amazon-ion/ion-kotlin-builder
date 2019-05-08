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

class DefaultIonWriterDsl(override val writer: IonWriter) : IonWriterDsl,
    IonStructWriterDsl {

    override fun struct(annotations: List<String>, dslFunction: IonStructWriterDsl.() -> Unit) {
        writeWithAnnotations(annotations) {
            writer.stepIn(IonType.STRUCT)
            this.dslFunction()
            writer.stepOut()
        }
    }

    override fun list(annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writeSequence(IonType.LIST, annotations, dslFunction)
    }

    override fun sexp(annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writeSequence(IonType.SEXP, annotations, dslFunction)
    }

    override fun value(value: IonValue) {
        value.writeTo(writer)
    }

    override fun value(reader: IonReader) {
        writer.writeValue(reader)
    }

    override fun values(reader: IonReader) {
        writer.writeValues(reader)
    }

    override fun nullValue(type: IonType?, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            if (type == null) {
                writer.writeNull()
            } else {
                writer.writeNull(type)
            }
        }
    }

    override fun bool(value: Boolean, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeBool(value)
        }
    }

    override fun int(value: Long, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeInt(value)
        }
    }

    override fun int(value: BigInteger, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeInt(value)
        }
    }

    override fun float(value: Double, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeFloat(value)
        }
    }

    override fun decimal(value: BigDecimal, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeDecimal(value)
        }
    }

    override fun string(value: String, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeString(value)
        }
    }

    override fun timestamp(value: Timestamp, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeTimestamp(value)
        }
    }

    override fun blob(value: ByteArray, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeBlob(value)
        }
    }

    override fun blob(value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeBlob(value, start, len)
        }
    }

    override fun clob(value: ByteArray, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeClob(value)
        }
    }

    override fun clob(value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeClob(value, start, len)
        }
    }

    override fun symbolToken(value: SymbolToken, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeSymbolToken(value)
        }
    }

    override fun symbol(value: String, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeSymbol(value)
        }
    }

    // Struct Context

    override fun struct(field: String, annotations: List<String>, dslFunction: IonStructWriterDsl.() -> Unit) {
        writer.setFieldName(field)
        struct(annotations, dslFunction)
    }

    override fun struct(
        field: SymbolToken,
        annotations: List<String>,
        dslFunction: IonStructWriterDsl.() -> Unit
    ) {
        writer.setFieldNameSymbol(field)
        struct(annotations, dslFunction)
    }

    override fun list(field: String, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writer.setFieldName(field)
        list(annotations, dslFunction)
    }

    override fun list(field: SymbolToken, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writer.setFieldNameSymbol(field)
        list(annotations, dslFunction)
    }

    override fun sexp(field: String, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writer.setFieldName(field)
        sexp(annotations, dslFunction)
    }

    override fun sexp(field: SymbolToken, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writer.setFieldNameSymbol(field)
        sexp(annotations, dslFunction)
    }

    override fun value(field: String, value: IonValue) {
        writer.setFieldName(field)
        value(value)
    }

    override fun value(field: SymbolToken, value: IonValue) {
        writer.setFieldNameSymbol(field)
        value(value)
    }

    override fun value(field: String, reader: IonReader) {
        writer.setFieldName(field)
        value(reader)
    }

    override fun value(field: SymbolToken, reader: IonReader) {
        writer.setFieldNameSymbol(field)
        value(reader)
    }

    override fun nullValue(field: String, type: IonType?, annotations: List<String>) {
        writer.setFieldName(field)
        nullValue(type, annotations)
    }

    override fun nullValue(field: SymbolToken, type: IonType?, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        nullValue(type, annotations)
    }

    override fun bool(field: String, value: Boolean, annotations: List<String>) {
        writer.setFieldName(field)
        bool(value, annotations)
    }

    override fun bool(field: SymbolToken, value: Boolean, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        bool(value, annotations)
    }

    override fun int(field: String, value: Long, annotations: List<String>) {
        writer.setFieldName(field)
        int(value, annotations)
    }

    override fun int(field: SymbolToken, value: Long, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        int(value, annotations)
    }

    override fun int(field: String, value: BigInteger, annotations: List<String>) {
        writer.setFieldName(field)
        int(value, annotations)
    }

    override fun int(field: SymbolToken, value: BigInteger, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        int(value, annotations)
    }

    override fun float(field: String, value: Double, annotations: List<String>) {
        writer.setFieldName(field)
        float(value, annotations)
    }

    override fun float(field: SymbolToken, value: Double, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        float(value, annotations)
    }

    override fun decimal(field: String, value: BigDecimal, annotations: List<String>) {
        writer.setFieldName(field)
        decimal(value, annotations)
    }

    override fun decimal(field: SymbolToken, value: BigDecimal, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        decimal(value, annotations)
    }

    override fun string(field: String, value: String, annotations: List<String>) {
        writer.setFieldName(field)
        string(value, annotations)
    }

    override fun string(field: SymbolToken, value: String, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        string(value, annotations)
    }

    override fun timestamp(field: String, value: Timestamp, annotations: List<String>) {
        writer.setFieldName(field)
        timestamp(value, annotations)
    }

    override fun timestamp(field: SymbolToken, value: Timestamp, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        timestamp(value, annotations)
    }

    override fun blob(field: String, value: ByteArray, annotations: List<String>) {
        writer.setFieldName(field)
        blob(value, annotations)
    }

    override fun blob(field: SymbolToken, value: ByteArray, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        blob(value, annotations)
    }

    override fun blob(field: String, value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writer.setFieldName(field)
        blob(value, start, len, annotations)
    }

    override fun blob(field: SymbolToken, value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        blob(value, start, len, annotations)
    }

    override fun clob(field: String, value: ByteArray, annotations: List<String>) {
        writer.setFieldName(field)
        clob(value, annotations)
    }

    override fun clob(field: SymbolToken, value: ByteArray, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        clob(value, annotations)
    }

    override fun symbolToken(field: String, value: SymbolToken, annotations: List<String>) {
        writer.setFieldName(field)
        symbolToken(value, annotations)
    }

    override fun symbolToken(field: SymbolToken, value: SymbolToken, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        symbolToken(value, annotations)
    }

    override fun clob(field: String, value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writer.setFieldName(field)
        clob(value, start, len, annotations)
    }

    override fun clob(field: SymbolToken, value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        clob(value, start, len, annotations)
    }

    override fun symbol(field: String, value: String, annotations: List<String>) {
        writer.setFieldName(field)
        symbol(value, annotations)
    }

    override fun symbol(field: SymbolToken, value: String, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        symbol(value, annotations)
    }

    private inline fun writeSequence(type: IonType, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) =
        writeWithAnnotations(annotations) {
            when (type) {
                IonType.LIST, IonType.SEXP -> {
                    writer.stepIn(type)
                    this.dslFunction()
                    writer.stepOut()
                }
                else -> throw IllegalArgumentException("$type is not a valid sequence type")
            }
        }

    private inline fun writeWithAnnotations(annotations: List<String>, writeFunction: () -> Unit) {
        if (annotations.isNotEmpty()) {
            writer.setTypeAnnotations(*annotations.toTypedArray())
        }

        writeFunction()
    }
}