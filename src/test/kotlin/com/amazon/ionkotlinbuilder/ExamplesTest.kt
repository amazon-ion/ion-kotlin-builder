package com.amazon.ionkotlinbuilder

import org.junit.jupiter.api.Test
import software.amazon.ion.IonType
import software.amazon.ion.Timestamp
import java.math.BigDecimal


class ExamplesTest {

    @Test
    fun readmeExample() = assertIon(
        expected = """
            1
            "text"
            a_symbol
            [
                1.,
                { foo: "bar", t: a::b::2019T }
            ]
        """,
        expectedIonJava = {
            val dg = ion.newDatagram()

            ion.newWriter(dg).use { w ->
                w.writeInt(1)
                w.writeString("text")
                w.writeSymbol("a_symbol")

                w.stepIn(IonType.LIST)

                w.writeDecimal(BigDecimal.ONE)

                w.stepIn(IonType.STRUCT)

                w.setFieldName("foo")
                w.writeString("bar")

                w.setFieldName("t")
                w.setTypeAnnotations("a", "b")
                w.writeTimestamp(Timestamp.valueOf("2019T"))

                // struct
                w.stepOut()

                // list
                w.stepOut()

            }

            dg
        },
        builder = {
            int(1)
            string("text")
            symbol("a_symbol")
            list {
                decimal(BigDecimal.ONE)
                struct {
                    string(field = "foo", value = "bar")
                    timestamp(
                        field = "t",
                        value = Timestamp.valueOf("2019T"),
                        annotations = listOf("a", "b")
                    )
                }
            }
        }
    )
}
