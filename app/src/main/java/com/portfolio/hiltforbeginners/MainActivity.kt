package com.portfolio.hiltforbeginners

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    /**
     * This part demonstrates on how to do field injection. This is the actual place where you
     * inject the various dependencies into the client so that they can be used to full-fill their
     * function
     */
    @Inject lateinit var allNames: SomeClass
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvTestName = findViewById<TextView>(R.id.tv_test_name)
        tvTestName.text =  allNames.getSecondName1() + allNames.getSecondName2()
    }
}

/**
 * This part demonstrates on how to do constructor injection using android hilt and also how you can
 * inject two implementations of the same interface using special hilt annotations( @Qualifier and
 * @Retention)
 */
class SomeClass @Inject constructor(@Impl1 private val someInterfaceImpl1: SomeInterface,
                                    @Impl2 private val someInterfaceImpl2: SomeInterface){
    fun getSecondName1(): String {
        return "One ${someInterfaceImpl1.getAThing()} "
    }

    fun getSecondName2(): String {
        return someInterfaceImpl2.getAThing()
    }
}

class SomeInterfaceImpl1 @Inject constructor(): SomeInterface{
    override fun getAThing(): String {
        return "Two"
    }
}

class SomeInterfaceImpl2 @Inject constructor(): SomeInterface{
    override fun getAThing(): String {
        return "Three"
    }
}

interface SomeInterface {
    fun getAThing(): String
}

/**
 * There are two ways to inject an interface
 * 1. Is using the @Binds annotation that is long than the second method and it does not work
 * in all circumstances.
 * 2. The second ethos is to use the @provides annotaion that works in all circumstances and
 * is easier to implement than the first method.
 */

/**
 * First method that uses @Bind annotation
 * NB: uncomment the following part and comment the second method because the two modules
 * do the same functionality and hence you can use one module at a time.
 */
//@InstallIn(ApplicationComponent::class)
//@Module
//abstract class MyModule{
//    @Singleton
//    @Binds
//    abstract fun bindSomeDependency(someImpl: SomeInterfaceImpl): SomeInterface
//}

/**
 * Second method that uses @Bind annotation
 */



@InstallIn(ApplicationComponent::class)
@Module
 class MyModule{
    @Impl1
    @Singleton
    @Provides
    fun provideSomeInterface1(): SomeInterface{
        return SomeInterfaceImpl1()
    }

    @Impl2
    @Singleton
    @Provides
    fun provideSomeInterface2(): SomeInterface{
        return SomeInterfaceImpl2()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2