package tabacowang.me.etgo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tabacowang.me.etgo.api.model.GoogleResponse
import tabacowang.me.etgo.api.repository.DataRepository
import tabacowang.me.etgo.util.Resource
import tabacowang.me.etgo.util.addDisposable

class MainViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    private var compositeDisposable = CompositeDisposable()

    private val _data = MutableLiveData<Resource<GoogleResponse>>()
    val data: LiveData<Resource<GoogleResponse>>
        get() = _data

    fun getData(keyword: String) {
        _data.postValue(Resource.Loading)

        dataRepository.getData(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _data.postValue(Resource.Success(it))
            }, {
                _data.postValue(Resource.Error(it))
            })
            .addDisposable(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}