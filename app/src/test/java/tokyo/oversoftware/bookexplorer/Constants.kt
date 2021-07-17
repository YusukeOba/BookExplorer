package tokyo.oversoftware.bookexplorer

import tokyo.oversoftware.bookexplorer.entity.Book
import tokyo.oversoftware.bookexplorer.entity.Books
import tokyo.oversoftware.bookexplorer.entity.ImageLinks
import tokyo.oversoftware.bookexplorer.entity.ReadingModes
import tokyo.oversoftware.bookexplorer.entity.SaleInfo
import tokyo.oversoftware.bookexplorer.entity.VolumeInfo

object Constants {

    val mockBook = Books(
        kind = "books#volumes",
        totalItems = 0,
        items = listOf(
            Book(
                kind = "books#volume",
                id = "exampleId",
                etag = "exampleEtag",
                selfLink = "https://yahoo.co.jp",
                volumeInfo = VolumeInfo(
                    title = "ExampleBook",
                    subtitle = "ExampleSubTitle",
                    authors = listOf(
                        "YusukeOba",
                        "Github"
                    ),
                    publisher = null,
                    publishedDate = "2021/12",
                    description = "Example book!",
                    industryIdentifiers = emptyList(),
                    readingModes = ReadingModes(text = true, image = true),
                    pageCount = 120,
                    printType = "Paper",
                    categories = emptyList(),
                    averageRating = 5,
                    ratingsCount = 100,
                    maturityRating = null,
                    allowAnonLogging = null,
                    contentVersion = null,
                    panelizationSummary = null,
                    imageLinks = ImageLinks(
                        smallThumbnail = "https://picsum.photos/536/354",
                        thumbnail = "https://picsum.photos/536/354"
                    ),
                    language = "Ja",
                    previewLink = "https://yahoo.co.jp",
                    canonicalVolumeLink = null
                ),
                saleInfo = SaleInfo(
                    country = "JP",
                    saleability = null,
                    isEbook = null
                ),
                accessInfo = null
            )
        )
    )
}