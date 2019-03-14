package com.amazon.ionkotlinbuilder

import org.junit.jupiter.api.Test
import software.amazon.ion.IonSymbol
import software.amazon.ion.IonType
import software.amazon.ion.Timestamp
import java.math.BigDecimal
import java.math.BigInteger

private val fieldToken = (ion.singleValue("field") as IonSymbol).symbolValue()

class StructTests {

    private fun assertInStructIon(expected: String, builder: IonStructWriterDsl.() -> Unit) =
        assertIon(expected) { writeStruct(dslFunction = builder) }

    @Test
    fun writeNull() = assertInStructIon(
        expected = "{ field: null }",
        builder = { writeNull("field") })

    @Test
    fun writeNullWithAnnotations() = assertInStructIon(
        expected = "{ field: a::b::null }",
        builder = { writeNull("field", annotations = annotations) })

    @Test
    fun writeTypedNull() = assertInStructIon(
        expected = "{ field: null.int }",
        builder = { writeNull("field", IonType.INT) })

    @Test
    fun writeBool() = assertInStructIon(
        expected = "{ field: true }",
        builder = { writeBool("field", true) })

    @Test
    fun writeBoolWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::true }",
        builder = { writeBool("field", true, annotations) })

    @Test
    fun writeInt() = assertInStructIon(
        expected = "{ field: 1 }",
        builder = { writeInt("field", 1) })

    @Test
    fun writeIntWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::1 }",
        builder = { writeInt("field", 1, annotations) })

    @Test
    fun writeBigInt() = assertInStructIon(
        expected = "{ field: 10 }",
        builder = { writeInt("field", BigInteger.TEN) })

    @Test
    fun writeBigIntWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::10 }",
        builder = { writeInt("field", BigInteger.TEN, annotations) })

    @Test
    fun writeFloat() = assertInStructIon(
        expected = "{ field: 1e0 }",
        builder = { writeFloat("field", 1.0) })

    @Test
    fun writeFloatWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::1e0 }",
        builder = { writeFloat("field", 1.0, annotations) })

    @Test
    fun writeDecimal() = assertInStructIon(
        expected = "{ field: 1. }",
        builder = { writeDecimal("field", BigDecimal.ONE) })

    @Test
    fun writeDecimalWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::1. }",
        builder = { writeDecimal("field", BigDecimal.ONE, annotations) })

    @Test
    fun writeString() = assertInStructIon(
        expected = "{ field: \"text\" }",
        builder = { writeString("field", "text") })

    @Test
    fun writeStringWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::\"text\" }",
        builder = { writeString("field", "text", annotations) })

    @Test
    fun writeTimestamp() = assertInStructIon(
        expected = "{ field: 2019T }",
        builder = { writeTimestamp("field", Timestamp.valueOf("2019T")) })

    @Test
    fun writeTimestampWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::2019T }",
        builder = { writeTimestamp("field", Timestamp.valueOf("2019T"), annotations) })

    @Test
    fun writeBlob() = assertInStructIon(
        expected = "{ field: {{dGV4dA==}} }",
        builder = { writeBlob("field", "text".toByteArray()) })

    @Test
    fun writeBlobWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{{dGV4dA==}} }",
        builder = { writeBlob("field", "text".toByteArray(), annotations) })

    @Test
    fun writeBlobWithLen() = assertInStructIon(
        expected = "{ field: {{dA==}} }",
        builder = { writeBlob("field", "text".toByteArray(), 0, 1) })

    @Test
    fun writeBlobWithLenAndAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{{dA==}} }",
        builder = { writeBlob("field", "text".toByteArray(), 0, 1, annotations) })

    @Test
    fun writeClob() = assertInStructIon(
        expected = "{ field: {{ \"text\" }} }",
        builder = { writeClob("field", "text".toByteArray()) })

    @Test
    fun writeClobWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{{ \"text\" }} }",
        builder = { writeClob("field", "text".toByteArray(), annotations) })

    @Test
    fun writeClobWithLen() = assertInStructIon(
        expected = "{ field: {{ \"t\" }} }",
        builder = { writeClob("field", "text".toByteArray(), 0, 1) })

    @Test
    fun writeClobWithLenAndAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{{ \"t\" }} }",
        builder = { writeClob("field", "text".toByteArray(), 0, 1, annotations) })

    @Test
    fun writeSymbol() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { writeSymbol("field", "symbol") })

    @Test
    fun writeSymbolWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::symbol }",
        builder = { writeSymbol("field", "symbol", annotations) })

    @Test
    fun writeSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { writeSymbolToken("field", symbolToken) })

    @Test
    fun writeSymbolTokenWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::symbol }",
        builder = { writeSymbolToken("field",
            symbolToken,
            annotations
        ) })

    @Test
    fun writeDomValue() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { writeValue("field", domSymbol) })

    @Test
    fun writeReaderValue() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { writeValue("field", readerValue("symbol")) })

    @Test
    fun writeList() = assertInStructIon(
        expected = "{ field: [] }",
        builder = { writeList("field") {} })

    @Test
    fun writeListWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::[] }",
        builder = { writeList("field", annotations) {} })

    @Test
    fun writeNonEmptyList() = assertInStructIon(
        expected = "{ field: [1] }",
        builder = { writeList("field") { writeInt(1) } })

    @Test
    fun writeNonEmptyListWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::[1] }",
        builder = { writeList("field", annotations) { writeInt(1) } })

    @Test
    fun writeSexp() = assertInStructIon(
        expected = "{ field: () }",
        builder = { writeSexp("field") {} })

    @Test
    fun writeSexpWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::() }",
        builder = { writeSexp("field", annotations) {} })

    @Test
    fun writeNonEmptySexp() = assertInStructIon(
        expected = "{ field: (1) }",
        builder = { writeSexp("field") { writeInt(1) } })

    @Test
    fun writeNonEmptySexpWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::(1) }",
        builder = { writeSexp("field", annotations) { writeInt(1) } })

    @Test
    fun writeStruct() = assertInStructIon(
        expected = "{ field: {} }",
        builder = { writeStruct("field") {} })

    @Test
    fun writeStructWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{} }",
        builder = { writeStruct("field", annotations) {} })

    // symbol token

    @Test
    fun writeNullSymbolToken() = assertInStructIon(
        expected = "{ field: null }",
        builder = { writeNull(fieldToken) })

    @Test
    fun writeNullWithAnnotationsSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::null }",
        builder = { writeNull(fieldToken, annotations = annotations) })

    @Test
    fun writeTypedNullSymbolToken() = assertInStructIon(
        expected = "{ field: null.int }",
        builder = { writeNull(fieldToken, IonType.INT) })

    @Test
    fun writeBoolSymbolToken() = assertInStructIon(
        expected = "{ field: true }",
        builder = { writeBool(fieldToken, true) })

    @Test
    fun writeBoolWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::true }",
        builder = { writeBool(fieldToken, true, annotations) })

    @Test
    fun writeIntSymbolToken() = assertInStructIon(
        expected = "{ field: 1 }",
        builder = { writeInt(fieldToken, 1) })

    @Test
    fun writeIntWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::1 }",
        builder = { writeInt(fieldToken, 1, annotations) })

    @Test
    fun writeBigIntSymbolToken() = assertInStructIon(
        expected = "{ field: 10 }",
        builder = { writeInt(fieldToken, BigInteger.TEN) })

    @Test
    fun writeBigIntWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::10 }",
        builder = { writeInt(
            fieldToken, BigInteger.TEN,
            annotations
        ) })

    @Test
    fun writeFloatSymbolToken() = assertInStructIon(
        expected = "{ field: 1e0 }",
        builder = { writeFloat(fieldToken, 1.0) })

    @Test
    fun writeFloatWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::1e0 }",
        builder = { writeFloat(fieldToken, 1.0, annotations) })

    @Test
    fun writeDecimalSymbolToken() = assertInStructIon(
        expected = "{ field: 1. }",
        builder = { writeDecimal(fieldToken, BigDecimal.ONE) })

    @Test
    fun writeDecimalWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::1. }",
        builder = { writeDecimal(
            fieldToken, BigDecimal.ONE,
            annotations
        ) })

    @Test
    fun writeStringSymbolToken() = assertInStructIon(
        expected = "{ field: \"text\" }",
        builder = { writeString(fieldToken, "text") })

    @Test
    fun writeStringWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::\"text\" }",
        builder = { writeString(fieldToken, "text", annotations) })

    @Test
    fun writeTimestampSymbolToken() = assertInStructIon(
        expected = "{ field: 2019T }",
        builder = { writeTimestamp(fieldToken, Timestamp.valueOf("2019T")) })

    @Test
    fun writeTimestampWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::2019T }",
        builder = { writeTimestamp(
            fieldToken, Timestamp.valueOf("2019T"),
            annotations
        ) })

    @Test
    fun writeBlobSymbolToken() = assertInStructIon(
        expected = "{ field: {{dGV4dA==}} }",
        builder = { writeBlob(fieldToken, "text".toByteArray()) })

    @Test
    fun writeBlobWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{{dGV4dA==}} }",
        builder = { writeBlob(
            fieldToken, "text".toByteArray(),
            annotations
        ) })

    @Test
    fun writeBlobWithLenSymbolToken() = assertInStructIon(
        expected = "{ field: {{dA==}} }",
        builder = { writeBlob(fieldToken, "text".toByteArray(), 0, 1) })

    @Test
    fun writeBlobWithLenAndAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{{dA==}} }",
        builder = { writeBlob(
            fieldToken, "text".toByteArray(), 0, 1,
            annotations
        ) })

    @Test
    fun writeClobSymbolToken() = assertInStructIon(
        expected = "{ field: {{ \"text\" }} }",
        builder = { writeClob(fieldToken, "text".toByteArray()) })

    @Test
    fun writeClobWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{{ \"text\" }} }",
        builder = { writeClob(
            fieldToken, "text".toByteArray(),
            annotations
        ) })

    @Test
    fun writeClobWithLenSymbolToken() = assertInStructIon(
        expected = "{ field: {{ \"t\" }} }",
        builder = { writeClob(fieldToken, "text".toByteArray(), 0, 1) })

    @Test
    fun writeClobWithLenAndAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{{ \"t\" }} }",
        builder = { writeClob(
            fieldToken, "text".toByteArray(), 0, 1,
            annotations
        ) })

    @Test
    fun writeSymbolSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { writeSymbol(fieldToken, "symbol") })

    @Test
    fun writeSymbolWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::symbol }",
        builder = { writeSymbol(
            fieldToken, "symbol",
            annotations
        ) })

    @Test
    fun writeSymbolTokenSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { writeSymbolToken(fieldToken, symbolToken) })

    @Test
    fun writeSymbolTokenWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::symbol }",
        builder = { writeSymbolToken(
            fieldToken,
            symbolToken,
            annotations
        ) })

    @Test
    fun writeDomValueSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { writeValue(fieldToken, domSymbol) })

    @Test
    fun writeReaderValueSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { writeValue(
            fieldToken,
            readerValue("symbol")
        ) })

    @Test
    fun writeListSymbolToken() = assertInStructIon(
        expected = "{ field: [] }",
        builder = { writeList(fieldToken) {} })

    @Test
    fun writeListWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::[] }",
        builder = { writeList(fieldToken, annotations) {} })

    @Test
    fun writeNonEmptyListSymbolToken() = assertInStructIon(
        expected = "{ field: [1] }",
        builder = { writeList(fieldToken) { writeInt(1) } })

    @Test
    fun writeNonEmptyListWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::[1] }",
        builder = { writeList(fieldToken, annotations) { writeInt(1) } })

    @Test
    fun writeSexpSymbolToken() = assertInStructIon(
        expected = "{ field: () }",
        builder = { writeSexp(fieldToken) {} })

    @Test
    fun writeSexpWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::() }",
        builder = { writeSexp(fieldToken, annotations) {} })

    @Test
    fun writeNonEmptySexpSymbolToken() = assertInStructIon(
        expected = "{ field: (1) }",
        builder = { writeSexp(fieldToken) { writeInt(1) } })

    @Test
    fun writeNonEmptySexpWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::(1) }",
        builder = { writeSexp(fieldToken, annotations) { writeInt(1) } })

    @Test
    fun writeStructSymbolToken() = assertInStructIon(
        expected = "{ field: {} }",
        builder = { writeStruct(fieldToken) {} })

    @Test
    fun writeStructWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{} }",
        builder = { writeStruct(fieldToken, annotations) {} })
}

