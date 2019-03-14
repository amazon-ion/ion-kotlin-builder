package com.amazon.ionkotlinbuilder

import software.amazon.ion.*
import java.math.BigDecimal
import java.math.BigInteger

class DefaultIonWriterDsl(override val writer: IonWriter) : IonWriterDsl,
    IonStructWriterDsl {

    override fun writeStruct(annotations: List<String>, dslFunction: IonStructWriterDsl.() -> Unit) {
        writeWithAnnotations(annotations) {
            writer.stepIn(IonType.STRUCT)
            this.dslFunction()
            writer.stepOut()
        }
    }

    override fun writeList(annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writeSequence(IonType.LIST, annotations, dslFunction)
    }

    override fun writeSexp(annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writeSequence(IonType.SEXP, annotations, dslFunction)
    }

    override fun writeValue(value: IonValue) {
        value.writeTo(writer)
    }

    override fun writeValue(reader: IonReader) {
        writer.writeValue(reader)
    }

    override fun writeValues(reader: IonReader) {
        writer.writeValues(reader)
    }

    override fun writeNull(type: IonType?, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            if (type == null) {
                writer.writeNull()
            } else {
                writer.writeNull(type)
            }
        }
    }

    override fun writeBool(value: Boolean, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeBool(value)
        }
    }

    override fun writeInt(value: Long, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeInt(value)
        }
    }

    override fun writeInt(value: BigInteger, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeInt(value)
        }
    }

    override fun writeFloat(value: Double, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeFloat(value)
        }
    }

    override fun writeDecimal(value: BigDecimal, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeDecimal(value)
        }
    }

    override fun writeString(value: String, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeString(value)
        }
    }

    override fun writeTimestamp(value: Timestamp, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeTimestamp(value)
        }
    }

    override fun writeBlob(value: ByteArray, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeBlob(value)
        }
    }

    override fun writeBlob(value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeBlob(value, start, len)
        }
    }

    override fun writeClob(value: ByteArray, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeClob(value)
        }
    }

    override fun writeClob(value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeClob(value, start, len)
        }
    }

    override fun writeSymbolToken(value: SymbolToken, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeSymbolToken(value)
        }
    }

    override fun writeSymbol(value: String, annotations: List<String>) {
        writeWithAnnotations(annotations) {
            writer.writeSymbol(value)
        }
    }

    // Struct Context

    override fun writeStruct(field: String, annotations: List<String>, dslFunction: IonStructWriterDsl.() -> Unit) {
        writer.setFieldName(field)
        writeStruct(annotations, dslFunction)
    }

    override fun writeStruct(
        field: SymbolToken,
        annotations: List<String>,
        dslFunction: IonStructWriterDsl.() -> Unit
    ) {
        writer.setFieldNameSymbol(field)
        writeStruct(annotations, dslFunction)
    }

    override fun writeList(field: String, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writer.setFieldName(field)
        writeList(annotations, dslFunction)
    }

    override fun writeList(field: SymbolToken, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writer.setFieldNameSymbol(field)
        writeList(annotations, dslFunction)
    }

    override fun writeSexp(field: String, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writer.setFieldName(field)
        writeSexp(annotations, dslFunction)
    }

    override fun writeSexp(field: SymbolToken, annotations: List<String>, dslFunction: IonWriterDsl.() -> Unit) {
        writer.setFieldNameSymbol(field)
        writeSexp(annotations, dslFunction)
    }

    override fun writeValue(field: String, value: IonValue) {
        writer.setFieldName(field)
        writeValue(value)
    }

    override fun writeValue(field: SymbolToken, value: IonValue) {
        writer.setFieldNameSymbol(field)
        writeValue(value)
    }

    override fun writeValue(field: String, reader: IonReader) {
        writer.setFieldName(field)
        writeValue(reader)
    }

    override fun writeValue(field: SymbolToken, reader: IonReader) {
        writer.setFieldNameSymbol(field)
        writeValue(reader)
    }

    override fun writeNull(field: String, type: IonType?, annotations: List<String>) {
        writer.setFieldName(field)
        writeNull(type, annotations)
    }

    override fun writeNull(field: SymbolToken, type: IonType?, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeNull(type, annotations)
    }

    override fun writeBool(field: String, value: Boolean, annotations: List<String>) {
        writer.setFieldName(field)
        writeBool(value, annotations)
    }

    override fun writeBool(field: SymbolToken, value: Boolean, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeBool(value, annotations)
    }

    override fun writeInt(field: String, value: Long, annotations: List<String>) {
        writer.setFieldName(field)
        writeInt(value, annotations)
    }

    override fun writeInt(field: SymbolToken, value: Long, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeInt(value, annotations)
    }

    override fun writeInt(field: String, value: BigInteger, annotations: List<String>) {
        writer.setFieldName(field)
        writeInt(value, annotations)
    }

    override fun writeInt(field: SymbolToken, value: BigInteger, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeInt(value, annotations)
    }

    override fun writeFloat(field: String, value: Double, annotations: List<String>) {
        writer.setFieldName(field)
        writeFloat(value, annotations)
    }

    override fun writeFloat(field: SymbolToken, value: Double, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeFloat(value, annotations)
    }

    override fun writeDecimal(field: String, value: BigDecimal, annotations: List<String>) {
        writer.setFieldName(field)
        writeDecimal(value, annotations)
    }

    override fun writeDecimal(field: SymbolToken, value: BigDecimal, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeDecimal(value, annotations)
    }

    override fun writeString(field: String, value: String, annotations: List<String>) {
        writer.setFieldName(field)
        writeString(value, annotations)
    }

    override fun writeString(field: SymbolToken, value: String, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeString(value, annotations)
    }

    override fun writeTimestamp(field: String, value: Timestamp, annotations: List<String>) {
        writer.setFieldName(field)
        writeTimestamp(value, annotations)
    }

    override fun writeTimestamp(field: SymbolToken, value: Timestamp, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeTimestamp(value, annotations)
    }

    override fun writeBlob(field: String, value: ByteArray, annotations: List<String>) {
        writer.setFieldName(field)
        writeBlob(value, annotations)
    }

    override fun writeBlob(field: SymbolToken, value: ByteArray, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeBlob(value, annotations)
    }

    override fun writeBlob(field: String, value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writer.setFieldName(field)
        writeBlob(value, start, len, annotations)
    }

    override fun writeBlob(field: SymbolToken, value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeBlob(value, start, len, annotations)
    }

    override fun writeClob(field: String, value: ByteArray, annotations: List<String>) {
        writer.setFieldName(field)
        writeClob(value, annotations)
    }

    override fun writeClob(field: SymbolToken, value: ByteArray, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeClob(value, annotations)
    }

    override fun writeSymbolToken(field: String, value: SymbolToken, annotations: List<String>) {
        writer.setFieldName(field)
        writeSymbolToken(value, annotations)
    }

    override fun writeSymbolToken(field: SymbolToken, value: SymbolToken, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeSymbolToken(value, annotations)
    }

    override fun writeClob(field: String, value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writer.setFieldName(field)
        writeClob(value, start, len, annotations)
    }

    override fun writeClob(field: SymbolToken, value: ByteArray, start: Int, len: Int, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeClob(value, start, len, annotations)
    }

    override fun writeSymbol(field: String, value: String, annotations: List<String>) {
        writer.setFieldName(field)
        writeSymbol(value, annotations)
    }

    override fun writeSymbol(field: SymbolToken, value: String, annotations: List<String>) {
        writer.setFieldNameSymbol(field)
        writeSymbol(value, annotations)
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