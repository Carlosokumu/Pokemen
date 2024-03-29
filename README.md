# Pokemen


##  Pokemen is bult on jetpack compose by consuming [POKEAPI](https://pokeapi.co/) and creating an infinite list using the  characters.

**Screenshots**

PokemonList(Expanded Toolbar) Screen | PokemonList(Collapsed Toolbar)  | Details Screen | Screen Record
--- | --- | --- | --- |
<img src="https://github.com/Carlosokumu/Pokemen/blob/master/shots/Screenshot_20221002_051214.png" width="280"/> | <img src="https://github.com/Carlosokumu/Pokemen/blob/master/shots/Screenshot_20221002_051346.png" width="280"/> | <img src="https://github.com/Carlosokumu/Pokemen/blob/master/shots/Screenshot_20221003_135413.png" width="280"/> | <img src="https://github.com/Carlosokumu/Pokemen/blob/master/shots/gif.gif" width="280"/>

* Technologies used
    * [Kotlin](https://kotlinlang.org/) - Kotlin is a programming language that can run on JVM. Google has announced Kotlin as one of its officially supported programming languages in Android Studio; and the Android community is migrating at a pace from Java to Kotlin.
    *  [Android KTX](https://developer.android.com/kotlin/ktx.html) - Android KTX is a set of Kotlin extensions that are included with Android Jetpack and other Android libraries. KTX extensions provide concise, idiomatic Kotlin to Jetpack, Android platform, and other APIs.
    * [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    * [Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.
    * [Coil](https://coil-kt.github.io/coil/compose/) - An image loading library for Android backed by Kotlin Coroutines.
    * [Retrofit](https://square.github.io/retrofit/)  -  Retrofit is a REST client for Java/ Kotlin and Android by Square inc under Apache 2.0 license. Its a simple network library that is used for network transactions
    * [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) -The Paging library helps you load and display pages of data from a larger dataset from local storage or over network. This approach allows your app to use both network bandwidth and system resources more efficiently.
    * [Hilt-Dagger](https://developer.android.com/training/dependency-injection/hilt-android) -A dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
    * [Jetpack](https://developer.android.com/jetpack)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) -The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way.
     * [Room](https://developer.android.com/training/data-storage/room) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

* Tests
    * [JUnit](https://junit.org/junit5/)
    * [MockK](https://github.com/mockk/mockk)
    * [Turbine](https://github.com/cashapp/turbine)
    * [Robolectric](https://github.com/robolectric/robolectric)
    
 * Architecture
    * MVVM - Model View View Model       

* Gradle
   * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
