package tabacowang.me.etgo.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addDisposable(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)
