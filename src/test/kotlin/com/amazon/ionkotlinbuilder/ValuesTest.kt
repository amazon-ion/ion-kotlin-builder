package com.amazon.ionkotlinbuilder

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import software.amazon.ion.IonType
import software.amazon.ion.Timestamp
import java.io.ByteArrayOutputStream
import java.math.BigDecimal
import java.math.BigInteger

class ValuesTest {

    @Test
    fun nullValue() = assertIon(
        expected = "null",
        builder = { nullValue() })

    @Test
    fun nullWithAnnotations() = assertIon(
        expected = "a::b::null",
        builder = { nullValue(annotations = annotations) })

    @Test
    fun typedNull() = assertIon(
        expected = "null.int",
        builder = { nullValue(IonType.INT) })

    @Test
    fun bool() = assertIon(
        expected = "true",
        builder = { bool(true) })

    @Test
    fun boolWithAnnotation() = assertIon(
        expected = "a::b::true",
        builder = { bool(true, annotations) })

    @Test
    fun int() = assertIon(
        expected = "1",
        builder = { int(1) })

    @Test
    fun intWithAnnotation() = assertIon(
        expected = "a::b::1",
        builder = { int(1, annotations) })

    @Test
    fun bigInt() = assertIon(
        expected = "10",
        builder = { int(BigInteger.TEN) })

    @Test
    fun bigIntWithAnnotation() = assertIon(
        expected = "a::b::10",
        builder = { int(BigInteger.TEN, annotations) })

    @Test
    fun float() = assertIon(
        expected = "1e0",
        builder = { float(1.0) })

    @Test
    fun floatWithAnnotation() = assertIon(
        expected = "a::b::1e0",
        builder = { float(1.0, annotations) })

    @Test
    fun decimal() = assertIon(
        expected = "1.",
        builder = { decimal(BigDecimal.ONE) })

    @Test
    fun decimalWithAnnotation() = assertIon(
        expected = "a::b::1.",
        builder = { decimal(BigDecimal.ONE, annotations) })

    @Test
    fun string() = assertIon(
        expected = "\"text\"",
        builder = { string("text") })

    @Test
    fun stringWithAnnotation() = assertIon(
        expected = "a::b::\"text\"",
        builder = { string("text", annotations) })

    @Test
    fun timestamp() = assertIon(
        expected = "2019T",
        builder = { timestamp(Timestamp.valueOf("2019T")) })

    @Test
    fun timestampWithAnnotation() = assertIon(
        expected = "a::b::2019T",
        builder = { timestamp(Timestamp.valueOf("2019T"), annotations) })

    @Test
    fun blob() = assertIon(
        expected = "{{dGV4dA==}}",
        builder = { blob("text".toByteArray()) })

    @Test
    fun blobWithAnnotation() = assertIon(
        expected = "a::b::{{dGV4dA==}}",
        builder = { blob("text".toByteArray(), annotations) })

    @Test
    fun blobWithLen() = assertIon(
        expected = "{{dA==}}",
        builder = { blob("text".toByteArray(), 0, 1) })

    @Test
    fun blobWithLenAndAnnotation() = assertIon(
        expected = "a::b::{{dA==}}",
        builder = { blob("text".toByteArray(), 0, 1, annotations) })

    @Test
    fun clob() = assertIon(
        expected = "{{ \"text\" }}",
        builder = { clob("text".toByteArray()) })

    @Test
    fun clobWithAnnotation() = assertIon(
        expected = "a::b::{{ \"text\" }}",
        builder = { clob("text".toByteArray(), annotations) })

    @Test
    fun clobWithLen() = assertIon(
        expected = "{{ \"t\" }}",
        builder = { clob("text".toByteArray(), 0, 1) })

    @Test
    fun clobWithLenAndAnnotation() = assertIon(
        expected = "a::b::{{ \"t\" }}",
        builder = { clob("text".toByteArray(), 0, 1, annotations) })

    @Test
    fun symbol() = assertIon(
        expected = "symbol",
        builder = { symbol("symbol") })

    @Test
    fun symbolWithAnnotation() = assertIon(
        expected = "a::b::symbol",
        builder = { symbol("symbol", annotations) })

    @Test
    fun symbolToken() = assertIon(
        expected = "symbol",
        builder = { symbolToken(symbolToken) })

    @Test
    fun symbolTokenWithAnnotation() = assertIon(
        expected = "a::b::symbol",
        builder = {
            symbolToken(symbolToken, annotations)
        })

    @Test
    fun domValue() = assertIon(
        expected = "symbol",
        builder = { value(domSymbol) })

    @Test
    fun readerValue() = assertIon(
        expected = "symbol",
        builder = { value(readerValue("symbol")) })

    @Test
    fun readerValues() = assertIon(
        expected = "multiple values here",
        builder = { values(reader("multiple values here")) })

    @Test
    fun list() = assertIon(
        expected = "[]",
        builder = { list {} })

    @Test
    fun listWithAnnotation() = assertIon(
        expected = "a::b::[]",
        builder = { list(annotations) {} })

    @Test
    fun nonEmptyList() = assertIon(
        expected = "[1]",
        builder = { list { int(1) } })

    @Test
    fun listWithReadValues() = assertIon(
        expected = "[1, 2, 3, 4, 5, 6]",
        builder = {
            list { values(reader("1 2 3 4 5 6")) }
        })

    @Test
    fun nonEmptyListWithAnnotation() = assertIon(
        expected = "a::b::[1]",
        builder = { list(annotations) { int(1) } })

    @Test
    fun sexp() = assertIon(
        expected = "()",
        builder = { sexp {} })

    @Test
    fun sexpWithAnnotation() = assertIon(
        expected = "a::b::()",
        builder = { sexp(annotations) {} })

    @Test
    fun sexpWithReadValues() = assertIon(
        expected = "(1 2 3 4 5 6)",
        builder = {
            sexp { values(reader("1 2 3 4 5 6")) }
        })

    @Test
    fun nonEmptySexp() = assertIon(
        expected = "(1)",
        builder = { sexp { int(1) } })

    @Test
    fun nonEmptySexpWithAnnotation() = assertIon(
        expected = "a::b::(1)",
        builder = { sexp(annotations) { int(1) } })

    @Test
    fun struct() = assertIon(
        expected = "{}",
        builder = { struct {} })

    @Test
    fun structWithAnnotation() = assertIon(
        expected = "a::b::{}",
        builder = { struct(annotations) {} })

    @Test
    internal fun extensionFunction() {
        val out = ByteArrayOutputStream()
        writerBuilder.build(out).use { w ->
            w.dsl {
                int(10)
            }
        }

        val actual = ion.singleValue(out.toByteArray())
        val expected = ion.singleValue("10")

        assertEquals(expected, actual)
    }
}