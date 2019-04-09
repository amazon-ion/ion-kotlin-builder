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
        assertIon(expected) { struct(dslFunction = builder) }

    @Test
    fun nullValue() = assertInStructIon(
        expected = "{ field: null }",
        builder = { nullValue("field") })

    @Test
    fun nullWithAnnotations() = assertInStructIon(
        expected = "{ field: a::b::null }",
        builder = { nullValue("field", annotations = annotations) })

    @Test
    fun typedNull() = assertInStructIon(
        expected = "{ field: null.int }",
        builder = { nullValue("field", IonType.INT) })

    @Test
    fun bool() = assertInStructIon(
        expected = "{ field: true }",
        builder = { bool("field", true) })

    @Test
    fun boolWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::true }",
        builder = { bool("field", true, annotations) })

    @Test
    fun int() = assertInStructIon(
        expected = "{ field: 1 }",
        builder = { int("field", 1) })

    @Test
    fun intWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::1 }",
        builder = { int("field", 1, annotations) })

    @Test
    fun bigInt() = assertInStructIon(
        expected = "{ field: 10 }",
        builder = { int("field", BigInteger.TEN) })

    @Test
    fun bigIntWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::10 }",
        builder = { int("field", BigInteger.TEN, annotations) })

    @Test
    fun float() = assertInStructIon(
        expected = "{ field: 1e0 }",
        builder = { float("field", 1.0) })

    @Test
    fun floatWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::1e0 }",
        builder = { float("field", 1.0, annotations) })

    @Test
    fun decimal() = assertInStructIon(
        expected = "{ field: 1. }",
        builder = { decimal("field", BigDecimal.ONE) })

    @Test
    fun decimalWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::1. }",
        builder = { decimal("field", BigDecimal.ONE, annotations) })

    @Test
    fun string() = assertInStructIon(
        expected = "{ field: \"text\" }",
        builder = { string("field", "text") })

    @Test
    fun stringWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::\"text\" }",
        builder = { string("field", "text", annotations) })

    @Test
    fun timestamp() = assertInStructIon(
        expected = "{ field: 2019T }",
        builder = { timestamp("field", Timestamp.valueOf("2019T")) })

    @Test
    fun timestampWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::2019T }",
        builder = { timestamp("field", Timestamp.valueOf("2019T"), annotations) })

    @Test
    fun blob() = assertInStructIon(
        expected = "{ field: {{dGV4dA==}} }",
        builder = { blob("field", "text".toByteArray()) })

    @Test
    fun blobWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{{dGV4dA==}} }",
        builder = { blob("field", "text".toByteArray(), annotations) })

    @Test
    fun blobWithLen() = assertInStructIon(
        expected = "{ field: {{dA==}} }",
        builder = { blob("field", "text".toByteArray(), 0, 1) })

    @Test
    fun blobWithLenAndAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{{dA==}} }",
        builder = { blob("field", "text".toByteArray(), 0, 1, annotations) })

    @Test
    fun clob() = assertInStructIon(
        expected = "{ field: {{ \"text\" }} }",
        builder = { clob("field", "text".toByteArray()) })

    @Test
    fun clobWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{{ \"text\" }} }",
        builder = { clob("field", "text".toByteArray(), annotations) })

    @Test
    fun clobWithLen() = assertInStructIon(
        expected = "{ field: {{ \"t\" }} }",
        builder = { clob("field", "text".toByteArray(), 0, 1) })

    @Test
    fun clobWithLenAndAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{{ \"t\" }} }",
        builder = { clob("field", "text".toByteArray(), 0, 1, annotations) })

    @Test
    fun symbol() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { symbol("field", "symbol") })

    @Test
    fun symbolWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::symbol }",
        builder = { symbol("field", "symbol", annotations) })

    @Test
    fun symbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { symbolToken("field", symbolToken) })

    @Test
    fun symbolTokenWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::symbol }",
        builder = {
            symbolToken(
                "field",
                symbolToken,
                annotations
            )
        })

    @Test
    fun domValue() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { value("field", domSymbol) })

    @Test
    fun readerValue() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { value("field", readerValue("symbol")) })

    @Test
    fun list() = assertInStructIon(
        expected = "{ field: [] }",
        builder = { list("field") {} })

    @Test
    fun listWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::[] }",
        builder = { list("field", annotations) {} })

    @Test
    fun nonEmptyList() = assertInStructIon(
        expected = "{ field: [1] }",
        builder = { list("field") { int(1) } })

    @Test
    fun nonEmptyListWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::[1] }",
        builder = { list("field", annotations) { int(1) } })

    @Test
    fun sexp() = assertInStructIon(
        expected = "{ field: () }",
        builder = { sexp("field") {} })

    @Test
    fun sexpWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::() }",
        builder = { sexp("field", annotations) {} })

    @Test
    fun nonEmptySexp() = assertInStructIon(
        expected = "{ field: (1) }",
        builder = { sexp("field") { int(1) } })

    @Test
    fun nonEmptySexpWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::(1) }",
        builder = { sexp("field", annotations) { int(1) } })

    @Test
    fun struct() = assertInStructIon(
        expected = "{ field: {} }",
        builder = { struct("field") {} })

    @Test
    fun structWithAnnotation() = assertInStructIon(
        expected = "{ field: a::b::{} }",
        builder = { struct("field", annotations) {} })

    // symbol token

    @Test
    fun nullSymbolToken() = assertInStructIon(
        expected = "{ field: null }",
        builder = { nullValue(fieldToken) })

    @Test
    fun nullWithAnnotationsSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::null }",
        builder = { nullValue(fieldToken, annotations = annotations) })

    @Test
    fun typedNullSymbolToken() = assertInStructIon(
        expected = "{ field: null.int }",
        builder = { nullValue(fieldToken, IonType.INT) })

    @Test
    fun boolSymbolToken() = assertInStructIon(
        expected = "{ field: true }",
        builder = { bool(fieldToken, true) })

    @Test
    fun boolWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::true }",
        builder = { bool(fieldToken, true, annotations) })

    @Test
    fun intSymbolToken() = assertInStructIon(
        expected = "{ field: 1 }",
        builder = { int(fieldToken, 1) })

    @Test
    fun intWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::1 }",
        builder = { int(fieldToken, 1, annotations) })

    @Test
    fun bigIntSymbolToken() = assertInStructIon(
        expected = "{ field: 10 }",
        builder = { int(fieldToken, BigInteger.TEN) })

    @Test
    fun bigIntWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::10 }",
        builder = {
            int(
                fieldToken, BigInteger.TEN,
                annotations
            )
        })

    @Test
    fun floatSymbolToken() = assertInStructIon(
        expected = "{ field: 1e0 }",
        builder = { float(fieldToken, 1.0) })

    @Test
    fun floatWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::1e0 }",
        builder = { float(fieldToken, 1.0, annotations) })

    @Test
    fun decimalSymbolToken() = assertInStructIon(
        expected = "{ field: 1. }",
        builder = { decimal(fieldToken, BigDecimal.ONE) })

    @Test
    fun decimalWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::1. }",
        builder = {
            decimal(
                fieldToken, BigDecimal.ONE,
                annotations
            )
        })

    @Test
    fun stringSymbolToken() = assertInStructIon(
        expected = "{ field: \"text\" }",
        builder = { string(fieldToken, "text") })

    @Test
    fun stringWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::\"text\" }",
        builder = { string(fieldToken, "text", annotations) })

    @Test
    fun timestampSymbolToken() = assertInStructIon(
        expected = "{ field: 2019T }",
        builder = { timestamp(fieldToken, Timestamp.valueOf("2019T")) })

    @Test
    fun timestampWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::2019T }",
        builder = {
            timestamp(
                fieldToken, Timestamp.valueOf("2019T"),
                annotations
            )
        })

    @Test
    fun blobSymbolToken() = assertInStructIon(
        expected = "{ field: {{dGV4dA==}} }",
        builder = { blob(fieldToken, "text".toByteArray()) })

    @Test
    fun blobWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{{dGV4dA==}} }",
        builder = {
            blob(
                fieldToken, "text".toByteArray(),
                annotations
            )
        })

    @Test
    fun blobWithLenSymbolToken() = assertInStructIon(
        expected = "{ field: {{dA==}} }",
        builder = { blob(fieldToken, "text".toByteArray(), 0, 1) })

    @Test
    fun blobWithLenAndAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{{dA==}} }",
        builder = {
            blob(
                fieldToken, "text".toByteArray(), 0, 1,
                annotations
            )
        })

    @Test
    fun clobSymbolToken() = assertInStructIon(
        expected = "{ field: {{ \"text\" }} }",
        builder = { clob(fieldToken, "text".toByteArray()) })

    @Test
    fun clobWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{{ \"text\" }} }",
        builder = {
            clob(
                fieldToken, "text".toByteArray(),
                annotations
            )
        })

    @Test
    fun clobWithLenSymbolToken() = assertInStructIon(
        expected = "{ field: {{ \"t\" }} }",
        builder = { clob(fieldToken, "text".toByteArray(), 0, 1) })

    @Test
    fun clobWithLenAndAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{{ \"t\" }} }",
        builder = {
            clob(
                fieldToken, "text".toByteArray(), 0, 1,
                annotations
            )
        })

    @Test
    fun symbolSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { symbol(fieldToken, "symbol") })

    @Test
    fun symbolWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::symbol }",
        builder = {
            symbol(
                fieldToken, "symbol",
                annotations
            )
        })

    @Test
    fun symbolTokenSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { symbolToken(fieldToken, symbolToken) })

    @Test
    fun symbolTokenWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::symbol }",
        builder = {
            symbolToken(
                fieldToken,
                symbolToken,
                annotations
            )
        })

    @Test
    fun domValueSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = { value(fieldToken, domSymbol) })

    @Test
    fun readerValueSymbolToken() = assertInStructIon(
        expected = "{ field: symbol }",
        builder = {
            value(
                fieldToken,
                readerValue("symbol")
            )
        })

    @Test
    fun listSymbolToken() = assertInStructIon(
        expected = "{ field: [] }",
        builder = { list(fieldToken) {} })

    @Test
    fun listWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::[] }",
        builder = { list(fieldToken, annotations) {} })

    @Test
    fun nonEmptyListSymbolToken() = assertInStructIon(
        expected = "{ field: [1] }",
        builder = { list(fieldToken) { int(1) } })

    @Test
    fun nonEmptyListWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::[1] }",
        builder = { list(fieldToken, annotations) { int(1) } })

    @Test
    fun sexpSymbolToken() = assertInStructIon(
        expected = "{ field: () }",
        builder = { sexp(fieldToken) {} })

    @Test
    fun sexpWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::() }",
        builder = { sexp(fieldToken, annotations) {} })

    @Test
    fun nonEmptySexpSymbolToken() = assertInStructIon(
        expected = "{ field: (1) }",
        builder = { sexp(fieldToken) { int(1) } })

    @Test
    fun nonEmptySexpWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::(1) }",
        builder = { sexp(fieldToken, annotations) { int(1) } })

    @Test
    fun structSymbolToken() = assertInStructIon(
        expected = "{ field: {} }",
        builder = { struct(fieldToken) {} })

    @Test
    fun structWithAnnotationSymbolToken() = assertInStructIon(
        expected = "{ field: a::b::{} }",
        builder = { struct(fieldToken, annotations) {} })
}

