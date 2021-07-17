package tokyo.oversoftware.bookexplorer.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding4.widget.textChanges
import tokyo.oversoftware.bookexplorer.R
import tokyo.oversoftware.bookexplorer.databinding.ActivitySearchBinding
import tokyo.oversoftware.bookexplorer.di.DependencyInjection
import tokyo.oversoftware.bookexplorer.entity.DisplayableBook
import tokyo.oversoftware.bookexplorer.view.adapter.BooksAdapter
import tokyo.oversoftware.bookexplorer.view.fragments.AboutDialogFragment
import tokyo.oversoftware.bookexplorer.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity(), AboutDialogFragment.Callbacks {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        setupViewModel()
        setupUI()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            DependencyInjection.searchViewModelFactory()
        ).get(SearchViewModel::class.java)

        viewModel.onSearch(binding.searchTextField.textChanges())

        viewModel.items.observe(this, renderResponse)
        viewModel.isLoading.observe(this, renderProgressBar)
        viewModel.onError.observe(this, renderError)
    }

    private fun setupUI() {
        adapter = BooksAdapter(viewModel.items.value ?: emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.searchTextField.text =
            Editable.Factory.getInstance().newEditable(getString(R.string.initial_word))

        if (supportFragmentManager.findFragmentByTag(AboutDialogFragment.TAG) == null &&
            !viewModel.aboutDialogDismissed
        ) {
            AboutDialogFragment().show(supportFragmentManager, AboutDialogFragment.TAG)
        }
    }

    /**
     * 読み込み領域の表示
     */
    private val renderProgressBar = Observer<Boolean> { isLoading ->
        if (isLoading) {
            binding.indeterminateBar.visibility = View.VISIBLE
        } else {
            binding.indeterminateBar.visibility = View.GONE
        }
    }

    /**
     * RecyclerViewのデータ反映
     */
    private val renderResponse = Observer<List<DisplayableBook>> {
        adapter.updateAdapter(it)
    }

    /**
     * エラー表示
     */
    private val renderError = Observer<Boolean> { isError ->
        if (isError) {
            binding.textError.visibility = View.VISIBLE
        } else {
            binding.textError.visibility = View.GONE
        }
    }

    /**
     * ダイアログを閉じた
     */
    override fun onDismissAboutDialog() {
        viewModel.dismissAboutDialog()
    }
}