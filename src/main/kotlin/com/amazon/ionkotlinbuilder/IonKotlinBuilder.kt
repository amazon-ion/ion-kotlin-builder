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

import com.amazon.ion.IonWriter

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
