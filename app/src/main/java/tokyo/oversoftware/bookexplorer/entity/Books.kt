package tokyo.oversoftware.bookexplorer.entity

/**
 * Google Books APIで返却されるモデルを定義する
 * ここで定義したデータを元にMoshiがRAW JSON <-> Class へ変換を行う
 */
data class Books(
    val kind: String,
    val totalItems: Int,
    val items: List<Book>?
)

data class Book(
    val kind: String?,
    val id: String?,
    val etag: String?,
    val selfLink: String?,
    val volumeInfo: VolumeInfo?,
    val saleInfo: SaleInfo?,
    val accessInfo: AccessInfo?
)

data class VolumeInfo(
    val title: String?,
    val subtitle: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val industryIdentifiers: List<IndustryIdentifier>?,
    val readingModes: ReadingModes?,
    val pageCount: Int?,
    val printType: String?,
    val categories: List<String>?,
    val averageRating: Double?,
    val ratingsCount: Int?,
    val maturityRating: String?,
    val allowAnonLogging: Boolean?,
    val contentVersion: String?,
    val panelizationSummary: PanelizationSummary?,
    val imageLinks: ImageLinks?,
    val language: String?,
    val previewLink: String?,
    val canonicalVolumeLink: String?,
)

data class IndustryIdentifier(
    val type: String?,
    val identifier: String?
)

data class ReadingModes(
    val text: Boolean?,
    val image: Boolean?
)

data class PanelizationSummary(
    val containsEpubBubbles: Boolean?,
    val containsImageBubbles: Boolean?
)

data class ImageLinks(
    val smallThumbnail: String?,
    val thumbnail: String?
)

data class SaleInfo(
    val country: String?,
    val saleability: String?,
    val isEbook: Boolean?
)

data class AccessInfo(
    val country: String?,
    val viewability: String?,
    val embeddable: Boolean?,
    val publicDomain: Boolean?,
    val textToSpeechPermission: String?,
    val epub: Availability?,
    val pdf: Availability?,
    val webReaderLink: String?,
    val accessViewStatus: String?,
    val quoteSharingAllowed: Boolean?,
    val searchInfo: SearchInfo?
)

data class Availability(
    val isAvailable: Boolean?
)

data class SearchInfo(
    val textSnippet: String?
)