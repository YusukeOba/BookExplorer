package tokyo.oversoftware.bookexplorer.view.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding4.view.clicks
import tokyo.oversoftware.bookexplorer.R
import tokyo.oversoftware.bookexplorer.databinding.ItemBookBinding
import tokyo.oversoftware.bookexplorer.entity.DisplayableBook

class BooksAdapter(private var books: List<DisplayableBook>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * 一軒の描画処理
         *
         * @param book 表示用書籍データ
         */
        fun bindView(book: DisplayableBook) {
            binding.bookTitle.text = book.title
            binding.bookSubTitle.text = book.subTitle
            binding.bookAuthor.text = book.authors.joinToString(" / ")
            if (book.thumbnailUrl == null) {
                binding.bookThumbnail.setImageResource(R.drawable.no_image_logo)
            } else {
                Glide.with(binding.bookThumbnail.context).load(book.thumbnailUrl)
                    .fitCenter()
                    .into(binding.bookThumbnail)
            }

            binding.root.setOnClickListener {
                val uri = Uri.parse(book.link())
                val intent = Intent(Intent.ACTION_VIEW, uri)
                binding.root.context.startActivity(intent);
            }
        }
    }

    /**
     * RecyclerViewの更新
     * @param books 書籍一覧
     */
    fun updateAdapter(books: List<DisplayableBook>) {
        this.books = books
        // TODO: 本当は差分更新にしたいが。。
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        return holder.bindView(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }
}