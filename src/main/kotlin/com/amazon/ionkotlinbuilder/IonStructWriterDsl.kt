package com.amazon.ionkotlinbuilder

import software.amazon.ion.*
import java.math.BigDecimal
import java.math.BigInteger

/**
 * DSL interface for values inside a struct context. All `write*` methods are required to provide a field symbol
 */
@IonDslBuilder
interface IonStructWriterDsl {

    /**
     * Writer being used.
     */
    val writer: IonWriter

    /**
     * Starts a struct context.
     *
     * @param field struct value field
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the struct.
     */
    fun writeStruct(field: String, annotations: List<String> = listOf(), dslFunction: IonStructWriterDsl.() -> Unit)

    /**
     * Starts a struct context.
     *
     * @param field struct value field
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the struct.
     */
    fun writeStruct(field: SymbolToken, annotations: List<String> = listOf(), dslFunction: IonStructWriterDsl.() -> Unit)

    /**
     * Starts a list context.
     *
     * @param field struct value field
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the list.
     */
    fun writeList(field: String, annotations: List<String> = listOf(), dslFunction: IonWriterDsl.() -> Unit)

    /**
     * Starts a list context.
     *
     * @param field struct value field
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the list.
     */
    fun writeList(field: SymbolToken, annotations: List<String> = listOf(), dslFunction: IonWriterDsl.() -> Unit)

    /**
     * Starts a sexp context.
     *
     * @param field struct value field
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the sexp.
     */
    fun writeSexp(field: String, annotations: List<String> = listOf(), dslFunction: IonWriterDsl.() -> Unit)

    /**
     * Starts a sexp context.
     *
     * @param field struct value field
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the sexp.
     */
    fun writeSexp(field: SymbolToken, annotations: List<String> = listOf(), dslFunction: IonWriterDsl.() -> Unit)

    /**
     * Writes a DOM value.
     *
     * @param field struct value field
     * @param value value to write
     */
    fun writeValue(field: String, value: IonValue)

    /**
     * Writes a DOM value.
     *
     * @param field struct value field
     * @param value value to write
     */
    fun writeValue(field: SymbolToken, value: IonValue)

    /**
     * Writes a value from a reader.
     *
     * @param field struct value field.
     * @param reader used to read a value from.
     *
     * @see IonWriter.writeValue
     */
    fun writeValue(field: String, reader: IonReader)

    /**
     * Writes a value from a reader.
     *
     * @param field struct value field.
     * @param reader used to read a value from.
     *
     * @see IonWriter.writeValue
     */
    fun writeValue(field: SymbolToken, reader: IonReader)

    // value by type


    /**
     * Writes a null value.
     *
     * @param field struct value field.
     * @param type Ion type for strongly typed nulls. If type is null then writes `null.null`.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeNull
     */
    fun writeNull(field: String, type: IonType? = null, annotations: List<String> = listOf())

    /**
     * Writes a null value.
     *
     * @param field struct value field.
     * @param type Ion type for strongly typed nulls. If type is null then writes `null.null`.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeNull
     */
    fun writeNull(field: SymbolToken, type: IonType? = null, annotations: List<String> = listOf())

    /**
     * Writes a bool value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBool
     */
    fun writeBool(field: String, value: Boolean, annotations: List<String> = listOf())

    /**
     * Writes a bool value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBool
     */
    fun writeBool(field: SymbolToken, value: Boolean, annotations: List<String> = listOf())

    /**
     * Writes an int value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeInt
     */
    fun writeInt(field: String, value: Long, annotations: List<String> = listOf())

    /**
     * Writes an int value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeInt
     */
    fun writeInt(field: SymbolToken, value: Long, annotations: List<String> = listOf())

    /**
     * Writes an int value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeInt
     */
    fun writeInt(field: String, value: BigInteger, annotations: List<String> = listOf())

    /**
     * Writes an int value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeInt
     */
    fun writeInt(field: SymbolToken, value: BigInteger, annotations: List<String> = listOf())

    /**
     * Writes a float value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeFloat
     */
    fun writeFloat(field: String, value: Double, annotations: List<String> = listOf())

    /**
     * Writes a float value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeFloat
     */
    fun writeFloat(field: SymbolToken, value: Double, annotations: List<String> = listOf())

    /**
     * Writes a decimal value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeDecimal
     */
    fun writeDecimal(field: String, value: BigDecimal, annotations: List<String> = listOf())

    /**
     * Writes a decimal value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeDecimal
     */
    fun writeDecimal(field: SymbolToken, value: BigDecimal, annotations: List<String> = listOf())

    /**
     * Writes a String value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeString
     */
    fun writeString(field: String, value: String, annotations: List<String> = listOf())

    /**
     * Writes a String value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeString
     */
    fun writeString(field: SymbolToken, value: String, annotations: List<String> = listOf())

    /**
     * Writes a [Timestamp] int value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeTimestamp
     */
    fun writeTimestamp(field: String, value: Timestamp, annotations: List<String> = listOf())

    /**
     * Writes a [Timestamp] int value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeTimestamp
     */
    fun writeTimestamp(field: SymbolToken, value: Timestamp, annotations: List<String> = listOf())

    /**
     * Writes a blob value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBlob
     */
    fun writeBlob(field: String, value: ByteArray, annotations: List<String> = listOf())

    /**
     * Writes a blob value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBlob
     */
    fun writeBlob(field: SymbolToken, value: ByteArray, annotations: List<String> = listOf())

    /**
     * Writes a blob value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param start offset of the first byte in value to write.
     * @param len number of bytes to write from value.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBlob
     */
    fun writeBlob(field: String, value: ByteArray, start: Int, len: Int, annotations: List<String> = listOf())

    /**
     * Writes a blob value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param start offset of the first byte in value to write.
     * @param len number of bytes to write from value.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBlob
     */
    fun writeBlob(field: SymbolToken, value: ByteArray, start: Int, len: Int, annotations: List<String> = listOf())

    /**
     * Writes a clob value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeClob
     */
    fun writeClob(field: String, value: ByteArray, annotations: List<String> = listOf())

    /**
     * Writes a clob value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeClob
     */
    fun writeClob(field: SymbolToken, value: ByteArray, annotations: List<String> = listOf())

    /**
     * Writes a clob value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     * @param start offset of the first byte in value to write.
     * @param len number of bytes to write from value.
     *
     * @see IonWriter.writeClob
     */
    fun writeClob(field: String, value: ByteArray, start: Int, len: Int, annotations: List<String> = listOf())

    /**
     * Writes a clob value.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     * @param start offset of the first byte in value to write.
     * @param len number of bytes to write from value.
     *
     * @see IonWriter.writeClob
     */
    fun writeClob(field: SymbolToken, value: ByteArray, start: Int, len: Int, annotations: List<String> = listOf())

    /**
     * Writes a symbol from its [SymbolToken].
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeSymbolToken
     */
    fun writeSymbolToken(field: String, value: SymbolToken, annotations: List<String> = listOf())

    /**
     * Writes a symbol from its [SymbolToken].
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeSymbolToken
     */
    fun writeSymbolToken(field: SymbolToken, value: SymbolToken, annotations: List<String> = listOf())

    /**
     * Writes a symbol from its text.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see [IonWriter.writeSymbol]
     */
    fun writeSymbol(field: String, value: String, annotations: List<String> = listOf())

    /**
     * Writes a symbol from its text.
     *
     * @param field struct value field.
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see [IonWriter.writeSymbol]
     */
    fun writeSymbol(field: SymbolToken, value: String, annotations: List<String> = listOf())
}