package mx.com.evotae.appxtreme.framework.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import mx.com.evotae.appxtreme.framework.util.commons.managerevents.SingleLiveEvent

open class XTViewModelBase: ViewModel() {
    private var launchLoaderMLD = SingleLiveEvent<Boolean>()
    val launchLoader: LiveData<Boolean>
        get() = launchLoaderMLD

    private var launchErrorMLD = SingleLiveEvent<String>()
    val launchError: LiveData<String>
        get() = launchErrorMLD

    protected fun showLoader(showLoader: Boolean) {
        launchLoaderMLD.postValue(showLoader)
    }

    protected fun showError(message: String) {
        launchErrorMLD.postValue(message)
    }
}