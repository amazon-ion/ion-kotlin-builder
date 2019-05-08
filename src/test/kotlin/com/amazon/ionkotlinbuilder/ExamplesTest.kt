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

import org.junit.jupiter.api.Test
import com.amazon.ion.IonType
import com.amazon.ion.Timestamp
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
