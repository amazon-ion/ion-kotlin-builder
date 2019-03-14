package com.amazon.ionkotlinbuilder

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.amazon.ion.IonType
import software.amazon.ion.Timestamp
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.math.BigInteger


class ValuesTest {

    @Test
    fun writeNull() = assertIon(
        expected = "null",
        builder = { writeNull() })

    @Test
    fun writeNullWithAnnotations() = assertIon(
        expected = "a::b::null",
        builder = { writeNull(annotations = annotations) })

    @Test
    fun writeTypedNull() = assertIon(
        expected = "null.int",
        builder = { writeNull(IonType.INT) })

    @Test
    fun writeBool() = assertIon(
        expected = "true",
        builder = { writeBool(true) })

    @Test
    fun writeBoolWithAnnotation() = assertIon(
        expected = "a::b::true",
        builder = { writeBool(true, annotations) })

    @Test
    fun writeInt() = assertIon(
        expected = "1",
        builder = { writeInt(1) })

    @Test
    fun writeIntWithAnnotation() = assertIon(
        expected = "a::b::1",
        builder = { writeInt(1, annotations) })

    @Test
    fun writeBigInt() = assertIon(
        expected = "10",
        builder = { writeInt(BigInteger.TEN) })

    @Test
    fun writeBigIntWithAnnotation() = assertIon(
        expected = "a::b::10",
        builder = { writeInt(BigInteger.TEN, annotations) })

    @Test
    fun writeFloat() = assertIon(
        expected = "1e0",
        builder = { writeFloat(1.0) })

    @Test
    fun writeFloatWithAnnotation() = assertIon(
        expected = "a::b::1e0",
        builder = { writeFloat(1.0, annotations) })

    @Test
    fun writeDecimal() = assertIon(
        expected = "1.",
        builder = { writeDecimal(BigDecimal.ONE) })

    @Test
    fun writeDecimalWithAnnotation() = assertIon(
        expected = "a::b::1.",
        builder = { writeDecimal(BigDecimal.ONE, annotations) })

    @Test
    fun writeString() = assertIon(
        expected = "\"text\"",
        builder = { writeString("text") })

    @Test
    fun writeStringWithAnnotation() = assertIon(
        expected = "a::b::\"text\"",
        builder = { writeString("text", annotations) })

    @Test
    fun writeTimestamp() = assertIon(
        expected = "2019T",
        builder = { writeTimestamp(Timestamp.valueOf("2019T")) })

    @Test
    fun writeTimestampWithAnnotation() = assertIon(
        expected = "a::b::2019T",
        builder = { writeTimestamp(Timestamp.valueOf("2019T"), annotations) })

    @Test
    fun writeBlob() = assertIon(
        expected = "{{dGV4dA==}}",
        builder = { writeBlob("text".toByteArray()) })

    @Test
    fun writeBlobWithAnnotation() = assertIon(
        expected = "a::b::{{dGV4dA==}}",
        builder = { writeBlob("text".toByteArray(), annotations) })

    @Test
    fun writeBlobWithLen() = assertIon(
        expected = "{{dA==}}",
        builder = { writeBlob("text".toByteArray(), 0, 1) })

    @Test
    fun writeBlobWithLenAndAnnotation() = assertIon(
        expected = "a::b::{{dA==}}",
        builder = { writeBlob("text".toByteArray(), 0, 1, annotations) })

    @Test
    fun writeClob() = assertIon(
        expected = "{{ \"text\" }}",
        builder = { writeClob("text".toByteArray()) })

    @Test
    fun writeClobWithAnnotation() = assertIon(
        expected = "a::b::{{ \"text\" }}",
        builder = { writeClob("text".toByteArray(), annotations) })

    @Test
    fun writeClobWithLen() = assertIon(
        expected = "{{ \"t\" }}",
        builder = { writeClob("text".toByteArray(), 0, 1) })

    @Test
    fun writeClobWithLenAndAnnotation() = assertIon(
        expected = "a::b::{{ \"t\" }}",
        builder = { writeClob("text".toByteArray(), 0, 1, annotations) })

    @Test
    fun writeSymbol() = assertIon(
        expected = "symbol",
        builder = { writeSymbol("symbol") })

    @Test
    fun writeSymbolWithAnnotation() = assertIon(
        expected = "a::b::symbol",
        builder = { writeSymbol("symbol", annotations) })

    @Test
    fun writeSymbolToken() = assertIon(
        expected = "symbol",
        builder = { writeSymbolToken(symbolToken) })

    @Test
    fun writeSymbolTokenWithAnnotation() = assertIon(
        expected = "a::b::symbol",
        builder = {
            writeSymbolToken(
                symbolToken,
                annotations
            )
        })

    @Test
    fun writeDomValue() = assertIon(
        expected = "symbol",
        builder = { writeValue(domSymbol) })

    @Test
    fun writeReaderValue() = assertIon(
        expected = "symbol",
        builder = { writeValue(readerValue("symbol")) })

    @Test
    fun writeReaderValues() = assertIon(
        expected = "multiple values here",
        builder = { writeValues(reader("multiple values here")) })

    @Test
    fun writeList() = assertIon(
        expected = "[]",
        builder = { writeList {} })

    @Test
    fun writeListWithAnnotation() = assertIon(
        expected = "a::b::[]",
        builder = { writeList(annotations) {} })

    @Test
    fun writeNonEmptyList() = assertIon(
        expected = "[1]",
        builder = { writeList { writeInt(1) } })

    @Test
    fun writeListWithReadValues() = assertIon(
        expected = "[1, 2, 3, 4, 5, 6]",
        builder = {
            writeList { writeValues(reader("1 2 3 4 5 6")) }
        })

    @Test
    fun writeNonEmptyListWithAnnotation() = assertIon(
        expected = "a::b::[1]",
        builder = { writeList(annotations) { writeInt(1) } })

    @Test
    fun writeSexp() = assertIon(
        expected = "()",
        builder = { writeSexp {} })

    @Test
    fun writeSexpWithAnnotation() = assertIon(
        expected = "a::b::()",
        builder = { writeSexp(annotations) {} })

    @Test
    fun writeSexpWithReadValues() = assertIon(
        expected = "(1 2 3 4 5 6)",
        builder = {
            writeSexp { writeValues(reader("1 2 3 4 5 6")) }
        })

    @Test
    fun writeNonEmptySexp() = assertIon(
        expected = "(1)",
        builder = { writeSexp { writeInt(1) } })

    @Test
    fun writeNonEmptySexpWithAnnotation() = assertIon(
        expected = "a::b::(1)",
        builder = { writeSexp(annotations) { writeInt(1) } })

    @Test
    fun writeStruct() = assertIon(
        expected = "{}",
        builder = { writeStruct {} })

    @Test
    fun writeStructWithAnnotation() = assertIon(
        expected = "a::b::{}",
        builder = { writeStruct(annotations) {} })

    @Test
    internal fun extensionFunction() {
        val out = ByteArrayOutputStream()
        writerBuilder.build(out).use { w ->
            w.dsl {
                writeInt(10)
            }
        }

        val actual = ion.singleValue(out.toByteArray())
        val expected = ion.singleValue("10")

        assertEquals(expected, actual)
    }
}