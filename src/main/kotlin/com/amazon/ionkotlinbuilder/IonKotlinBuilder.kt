package com.amazon.ionkotlinbuilder

import software.amazon.ion.IonWriter

/**
 * Writes Ion using the a type safe dslFunction with the given writer
 */
fun writeIonWith(writer: IonWriter, dslFunction: IonWriterDsl.() -> Unit) {
    DefaultIonWriterDsl(writer).dslFunction()
}

/**
 * Extension function for writeIonWith
 */
fun IonWriter.dsl(dslFunction: IonWriterDsl.() -> Unit) =
    writeIonWith(this, dslFunction)
