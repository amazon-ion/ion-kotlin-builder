package com.amazon.ionkotlinbuilder

import org.junit.jupiter.api.Assertions.assertEquals
import com.amazon.ion.IonSymbol
import com.amazon.ion.IonValue
import com.amazon.ion.system.IonReaderBuilder
import com.amazon.ion.system.IonSystemBuilder
import com.amazon.ion.system.IonTextWriterBuilder
import java.io.ByteArrayOutputStream


internal val ion = IonSystemBuilder.standard().build()
internal val writerBuilder = IonTextWriterBuilder.standard()
internal val readerBuilder = IonReaderBuilder.standard()

internal val annotations = listOf("a", "b")
internal val domSymbol = ion.singleValue("symbol") as IonSymbol
internal val symbolToken = domSymbol.symbolValue()

internal fun reader(text: String) = readerBuilder.build(text)
internal fun readerValue(text: String) = reader(text).also { it.next() }


internal fun assertIon(
    expected: String,
    expectedIonJava: () -> IonValue? = { null },
    builder: IonWriterDsl.() -> Unit
) {

    val out = ByteArrayOutputStream()
    writerBuilder.build(out).use { writer ->
        writeIonWith(
            writer,
            builder
        )
    }

    val actualIon = ion.loader.load(out.toByteArray())
    val expectedIon = ion.loader.load(expected)

    assertEquals(expectedIon, actualIon)

    expectedIonJava()?.let { assertEquals(it, actualIon) }
}