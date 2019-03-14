package com.amazon.ionkotlinbuilder

import software.amazon.ion.*
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
    fun writeStruct(annotations: List<String> = listOf(), dslFunction: IonStructWriterDsl.() -> Unit)

    /**
     * Starts a list context.
     *
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the list.
     */
    fun writeList(annotations: List<String> = listOf(), dslFunction: IonWriterDsl.() -> Unit)

    /**
     * Starts a sexp context.
     *
     * @param annotations list of annotations to add in order.
     * @param dslFunction function to build the sexp.
     */
    fun writeSexp(annotations: List<String> = listOf(), dslFunction: IonWriterDsl.() -> Unit)

    /**
     * Writes a DOM value.
     *
     * @param value value to write.
     */
    fun writeValue(value: IonValue)

    /**
     * Writes a value from a reader.
     *
     * @param reader used to read a value from.
     *
     * @see IonWriter.writeValue
     */
    fun writeValue(reader: IonReader)

    /**
     * Writes multiple values from a reader.
     *
     * @param reader used to read a values from.
     *
     * @see IonWriter.writeValues
     */
    fun writeValues(reader: IonReader)

    /**
     * Writes a null value.
     *
     * @param type Ion type for strongly typed nulls. If type is null then writes `null.null`.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeNull
     */
    fun writeNull(type: IonType? = null, annotations: List<String> = listOf())

    /**
     * Writes a bool value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBool
     */
    fun writeBool(value: Boolean, annotations: List<String> = listOf())

    /**
     * Writes an int value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeInt
     */
    fun writeInt(value: Long, annotations: List<String> = listOf())

    /**
     * Writes an int value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeInt
     */
    fun writeInt(value: BigInteger, annotations: List<String> = listOf())

    /**
     * Writes a float value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeFloat
     */
    fun writeFloat(value: Double, annotations: List<String> = listOf())

    /**
     * Writes a decimal value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeDecimal
     */
    fun writeDecimal(value: BigDecimal, annotations: List<String> = listOf())

    /**
     * Writes a String value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeString
     */
    fun writeString(value: String, annotations: List<String> = listOf())

    /**
     * Writes a [Timestamp] int value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeTimestamp
     */
    fun writeTimestamp(value: Timestamp, annotations: List<String> = listOf())

    /**
     * Writes a blob value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeBlob
     */
    fun writeBlob(value: ByteArray, annotations: List<String> = listOf())

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
    fun writeBlob(value: ByteArray, start: Int, len: Int, annotations: List<String> = listOf())

    /**
     * Writes a clob value.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeClob
     */
    fun writeClob(value: ByteArray, annotations: List<String> = listOf())

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
    fun writeClob(value: ByteArray, start: Int, len: Int, annotations: List<String> = listOf())

    /**
     * Writes a symbol from its [SymbolToken].
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see IonWriter.writeSymbolToken
     */
    fun writeSymbolToken(value: SymbolToken, annotations: List<String> = listOf())

    /**
     * Writes a symbol from its text.
     *
     * @param value value to write.
     * @param annotations list of annotations to add in order.
     *
     * @see [IonWriter.writeSymbol]
     */
    fun writeSymbol(value: String, annotations: List<String> = listOf())
}