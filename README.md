## Ion Kotlin builder

This library provides Kotlin Type-Safe Builders for manually writing
Ion documents. It helps avoiding common ion-java streaming api runtime
errors such as forgetting to step out of a container or not setting
a filed name for a struct element.

## Examples

```kotlin

// Ion Document:
/*
1
"text"
a_symbol
[
    1.,
    { foo: "bar", t: a::b::2019T }
]
*/

// with builder
val outBuilder = ByteArrayOutputStream()

IonTextWriterBuilder.standard().build(outBuilder).use { writer ->

    // writeIonWith() is the entry point for the builder.
    // Also available as an extension function: IonWriter.dsl()
    writeIonWith(writer) {
        int(1)
        string("text")
        symbol("a_symbol")
        list {
            decimal(BigDecimal.ONE)
            struct {
                string(field = "foo", value = "bar")
                timestamp(field = "t",
                          value = Timestamp.valueOf("2019T"),
                          annotations = listOf("a", "b"))
            }
        }
    }
}

// with plain ion-java streaming API

val outIonJava = ByteArrayOutputStream()
IonTextWriterBuilder.standard().build(outIonJava).use { w ->
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
```

## Performance

There is small performance penalty in using the builders instead of directly
using the Streaming API as the builder adds a level of indirection to calls to
the streaming API. Bellow are the results for a microbenchmark comparing
the two, code in `DslBenchmark`.

to run execute: `./gradlew jmh --no-daemon`.

```
Throughput, higher is better

Benchmark                                    Mode  Cnt         Score         Error  Units
DslBenchmark.multipleValuesBinaryDsl        thrpt   10   1141172.250 ±  122609.229  ops/s
DslBenchmark.multipleValuesBinaryStreaming  thrpt   10   1487947.880 ±  125854.681  ops/s
DslBenchmark.multipleValuesTextDsl          thrpt   10   1300583.629 ±  136815.481  ops/s
DslBenchmark.multipleValuesTextStreaming    thrpt   10   1358832.587 ±  377357.250  ops/s
DslBenchmark.singleScalarBinaryDsl          thrpt   10  11508758.807 ± 2432243.881  ops/s
DslBenchmark.singleScalarBinaryStreaming    thrpt   10  18911878.323 ±  622044.132  ops/s
DslBenchmark.singleScalarTextDsl            thrpt   10  12702660.292 ± 1522103.747  ops/s
DslBenchmark.singleScalarTextStreaming      thrpt   10  19155615.372 ±  712453.902  ops/s
```

## License

This library is licensed under the Apache 2.0 License. 
