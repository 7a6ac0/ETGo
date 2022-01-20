package tabacowang.me.etgo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import tabacowang.me.etgo.di.networkModule
import tabacowang.me.etgo.di.repositoryModule
import tabacowang.me.etgo.di.viewModelModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(networkModule, viewModelModule, repositoryModule)
        }
    }
}