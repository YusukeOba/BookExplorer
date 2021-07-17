package tokyo.oversoftware.bookexplorer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
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

    private val _isLoading = MutableLiveData<Boolean>(true)

    // 読み込み中フラグ
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onError = MutableLiveData<Boolean>(false)

    // エラー表示
    val onError: LiveData<Boolean> = _onError

    private var _aboutDialogDismissed: Boolean = false

    // ダイアログを閉じたか
    val aboutDialogDismissed get() = _aboutDialogDismissed

    /**
     * 書籍の検索を行う
     *
     * @param keyword
     */
    fun onSearch(searchKeyword: Observable<CharSequence>) {
        // TODO: 今回は時間がないのでやめておくが、onErrorになるとStreamが途切れてしまうので、
        //  Tuple<Books, Throwable>のような形でRepositoryが返したほうが良いかも
        searchKeyword
            .map { it.toString() }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .filter { it.isNotEmpty() }
            // 入力されて700msec経過したTextを検索する
            .debounce(700, TimeUnit.MILLISECONDS)
            .doOnNext {
                _onError.postValue(false)
                _items.postValue(emptyList())
                _isLoading.postValue(true)
            }
            // 最新のデータのみ処理してその他は破棄
            .switchMapSingle {
                repository.findBooks(it.toString())
            }
            .subscribe(
                { books ->
                    _items.postValue(books.items?.map { book -> DisplayableBook(book = book) })
                    _isLoading.postValue(false)
                },
                {
                    _isLoading.postValue(false)
                    _onError.postValue(true)
                }
            )
            .addTo(compositeDisposable)
    }

    fun dismissAboutDialog() {
        _aboutDialogDismissed = true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

