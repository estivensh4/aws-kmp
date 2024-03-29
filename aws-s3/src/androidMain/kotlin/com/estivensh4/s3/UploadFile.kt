/*
 * Copyright 2023 estiven. Use of this source code is governed by the Apache 2.0 license.
 */

package com.estivensh4.s3

import android.content.ContentResolver
import android.net.Uri

actual class UploadFile constructor(
    private val uri: Uri,
    private val contentResolver: ContentResolver,
) {
    actual fun toByteArray(): ByteArray {
        return contentResolver.openInputStream(uri)?.use {
            it.readBytes()
        } ?: error("Couldn't open inputStream $uri")
    }
}

fun Uri.toUploadFile(contentResolver: ContentResolver): UploadFile {
    return UploadFile(this, contentResolver)
}
