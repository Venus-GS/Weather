package devenus.rodi.weather.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias EventLiveData<T> = LiveData<Event<T>>

class MutableEventLiveData<T> : MutableLiveData<Event<T>>() {

    var event: T?
        get() = value?.peekContent()
        set(value) {
            if (value != null) {
                setValue(Event(value))
            }
        }
}
