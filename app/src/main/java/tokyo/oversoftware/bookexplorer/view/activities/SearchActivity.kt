package tokyo.oversoftware.bookexplorer.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tokyo.oversoftware.bookexplorer.databinding.ActivitySearchBinding
import tokyo.oversoftware.bookexplorer.di.DependencyInjection
import tokyo.oversoftware.bookexplorer.entity.DisplayableBook
import tokyo.oversoftware.bookexplorer.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupViewModel()

        viewModel.onSearch("ドラゴンボール")
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            DependencyInjection.searchViewModelFactory()
        ).get(SearchViewModel::class.java)

        viewModel.items.observe(this, renderResponse)
    }

    private val renderResponse = Observer<List<DisplayableBook>> {
        if (it.isNotEmpty()) {
            binding.apiResult.text = it[0].title
        }
    }
}