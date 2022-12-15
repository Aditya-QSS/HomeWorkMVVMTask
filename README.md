# HomeWorkMVVMTask

-I created this repository for user login using Jetpack Compose and used Room database to save user.

Libraries Used
--------------
* [Architecture][10] - A collection of libraries that help you design robust, testable, and maintainable apps.
  * [Lifecycles][11] - Create a UI that automatically responds to lifecycle events.
  * [Navigation][12] - Handle everything needed for in-app navigation.
  * [ViewModel][13] - Easily schedule asynchronous tasks for optimal execution.
  * [Coroutines][34] - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
  * [Flow][14] - Works very well with coroutines, provides us with cold streams which can be transformed using well known reactive operators.
* [UI][20] - Details on why and how to use UI Components in your apps - together or separate
  * [Jetpack Compose][21] - A basic unit of composable UI.
* Third party and miscellaneous libraries
  * [Volley][30] for turns your HTTP API into a Java interface
  * [Room][35] provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.
  * [Gson][31] for convert Java Objects into their JSON representation
  * [Hilt][32] for [dependency injection][33]

App Video
--------------

https://user-images.githubusercontent.com/92015923/207777772-f5e1601c-e694-42fa-b50b-1a996d31cd22.mp4


Architecture
--------------
The app uses [MVVM architecture][10] to have a unidirectional flow of data, separation of concern, testability, and a lot more.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

Repository 
-----------
A class that you create, for example using the WordRepository class. You use the Repository for managing multiple data sources.

<img src="https://i.imgur.com/UsNsFfN.png" />

Room Database 
----------------
Database layer on top of SQLite database that takes care of mundane tasks that you used to handle with an SQLiteOpenHelper. Database holder that serves as an access point to the underlying SQLite database. The Room database uses the DAO to issue queries to the SQLite database.

Entity 
-----------
When working with Architecture Components, this is an annotated class that describes a database table.

SQLite database 
------------------
On the device, data is stored in an SQLite database. For simplicity, additional storage options, such as a web server, are omitted. The Room persistence library creates and maintains this database for you.

DAO 
--------
Data access object. A mapping of SQL queries to functions. You used to have to define these painstakingly in your SQLiteOpenHelper class. When you use a DAO, you call the methods, and Room takes care of the rest.

Kotlin
-------
Kotlin is an open-source, statically-typed programming language that supports both object-oriented and functional programming. Kotlin provides similar syntax and concepts from other languages, including C#, Java, and Scala, among many others. Kotlin does not aim to be unique — instead, it draws inspiration from decades of language development. It exists in variants that target the JVM (Kotlin/JVM), JavaScript (Kotlin/JS), and native code (Kotlin/Native).

LiveData
--------------
A data holder class that can be observed. Always holds/caches latest version of data. Notifies its observers when the data has changed. LiveData is lifecycle aware. UI components just observe relevant data and don’t stop or resume observation. LiveData automatically manages all of this since it’s aware of the relevant lifecycle status changes while observing.

Coroutines
----------------
Coroutines are a great new feature of Kotlin which allow you to write asynchronous code in a sequential fashion. … However, like RxJava, coroutines have a number of little subtleties that you end up learning for yourself during development time, or tricks that you pick up from others.


[1]: https://www.balldontlie.io/#introduction
[10]: https://developer.android.com/jetpack/compose/architecture
[11]: https://developer.android.com/jetpack/compose/lifecycle
[12]: https://developer.android.com/jetpack/compose/navigation
[13]: https://developer.android.com/jetpack/compose/state#viewmodel-state
[14]: https://kotlinlang.org/docs/flow.html
[15]: https://developer.android.com/training/data-storage/room
[20]: https://developer.android.com/jetpack/compose/tutorial
[21]: https://developer.android.com/jetpack/compose
[30]: https://google.github.io/volley/
[31]: https://github.com/google/gson
[32]: https://developer.android.com/training/dependency-injection/hilt-android
[33]: https://developer.android.com/training/dependency-injection
[34]: https://developer.android.com/kotlin/coroutines
[35]: https://developer.android.com/training/data-storage/room
