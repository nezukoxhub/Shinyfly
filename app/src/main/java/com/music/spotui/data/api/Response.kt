// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
package com.music.spotui.data.api

sealed class Response<T> {

    class Loading<T> : Response<T>()
    data class Success<T>(val data: T) : Response<T>()
    data class Error<T>(val error : String) : Response<T>()
}