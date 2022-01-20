package tabacowang.me.etgo.di

import org.koin.dsl.module
import tabacowang.me.etgo.api.repository.DataRepository
import tabacowang.me.etgo.api.repository.DataRepositoryImpl

val repositoryModule = module {
    single<DataRepository> {
        DataRepositoryImpl(get())
    }
}