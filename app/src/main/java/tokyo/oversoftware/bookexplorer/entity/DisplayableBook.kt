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
        // ISBNコードがあるか
        val isbn10 = book.volumeInfo?.industryIdentifiers?.find { it.type == "ISBN_10" }?.identifier
        if (isbn10 != null) {
            return "https://www.amazon.co.jp/dp/isbn10"
        }

        return if (book.volumeInfo?.industryIdentifiers?.size == 0) {
            // 出版社がない場合はタイトルで検索する
            "https://www.google.com/search?q=" + book.volumeInfo.title
        } else {
            // ISBNコードがない場合は検索する
            "https://www.google.com/search?q=" + book.volumeInfo?.industryIdentifiers?.get(0)?.identifier
        }
    }
}