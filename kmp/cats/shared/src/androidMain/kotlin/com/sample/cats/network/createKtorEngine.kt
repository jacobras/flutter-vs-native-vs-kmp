package com.sample.cats.network

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

actual fun createKtorEngine(): HttpClientEngineFactory<*> = Android