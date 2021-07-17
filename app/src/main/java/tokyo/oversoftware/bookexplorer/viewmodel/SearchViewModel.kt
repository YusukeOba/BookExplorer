package tokyo.oversoftware.bookexplorer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import tokyo.oversoftware.bookexplorer.entity.DisplayableBook
import tokyo.oversoftware.bookexplorer.model.repository.BooksRepository
import java.util.concurrent.TimeUnit

class SearchViewModelFactory(private val repository: BooksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository = repository) as T
    }
}

/**
 * 書籍検索用ViewModel
 */
class SearchViewModel(private val repository: BooksRepository) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val _items = MutableLiveData<List<DisplayableBook>>().apply {
        value = emptyList()
    }

    // List表示用のデータ
    val items: LiveData<List<DisplayableBook>> = _items

    private val _isLoading = MutableLiveData<Boolean>()

    // 読み込み中フラグ
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onError = MutableLiveData<Unit>()

    // エラー表示
    val onError: LiveData<Unit> = _onError

    private val _isEmpty = MutableLiveData<Boolean>()

    // リストにデータがない場合
    val isEmpty: LiveData<Boolean> = _isEmpty

    /**
     * 書籍の検索を行う
     *
     * @param keyword
     */
    fun onSearch(keyword: String) {
        Observable.just(keyword) // TODO: RxBindingでEditTextのHotなStreamを利用する
            .subscribeOn(Schedulers.io())
            // 重複した文字は検索品い
            .distinctUntilChanged()
            // 入力されて500msec経過したTextを検索する
            .debounce(500, TimeUnit.MILLISECONDS)
            // 最新のデータのみ処理してその他は破棄
            .switchMapSingle {
                repository.findBooks(it)
            }
            .subscribe({ books ->
                _items.postValue(books.items.map { book -> DisplayableBook(book = book) })
                _isEmpty.postValue(books.items.isEmpty())
                _isLoading.postValue(_isLoading.value)
            }, {
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

