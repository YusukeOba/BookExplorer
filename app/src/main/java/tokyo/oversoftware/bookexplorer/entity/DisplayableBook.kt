package tokyo.oversoftware.bookexplorer.entity

/**
 * 画面表示用に余計な情報を削ぎ落とした書籍データ
 */
class DisplayableBook(private val book: Book) {

    // 書籍のタイトル
    val title: String = book.volumeInfo?.title ?: ""

    // 書籍のサブタイトル
    val subTitle: String = book.volumeInfo?.subtitle ?: ""

    // 著者
    val authors: List<String> = book.volumeInfo?.authors ?: emptyList()

    // サムネイル
    val thumbnailUrl: String? = book.volumeInfo?.imageLinks?.thumbnail

    // リンク
    fun link(): String {
        return book.volumeInfo?.previewLink ?: ""
    }
}