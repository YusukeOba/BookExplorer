package tokyo.oversoftware.bookexplorer

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Assert
import org.junit.Test
import tokyo.oversoftware.bookexplorer.entity.Books

class JsonParsingTest {

    @Test
    fun `Stub http response data parsing test`() {
        val stubResponse =
            "{\"kind\":\"books#volumes\",\"totalItems\":743,\"items\":[{\"kind\":\"books#volume\",\"id\":\"SL39GgAACAAJ\",\"etag\":\"Lug76eXB2is\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/SL39GgAACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール\",\"subtitle\":\"孫悟空と仲間たち. 卷1\",\"authors\":[\"鳥山明\"],\"publisher\":\"NOUVELLE ECOLE (EDITIONS)\",\"publishedDate\":\"1985\",\"description\":\"Songoku has a superhuman power living in a mountain. One day, Songoku gets to know about '7 Dragon Balls', which will grant any wishes, and leave to collect them. On the way, Bulma joins and they together start on a journey!\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088518314\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088518312\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":192,\"printType\":\"BOOK\",\"categories\":[\"Comic books, strips, etc\"],\"averageRating\":4,\"ratingsCount\":3,\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=SL39GgAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=SL39GgAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=SL39GgAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=1&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=SL39GgAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB.html?hl=&id=SL39GgAACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=SL39GgAACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Songoku has a superhuman power living in a mountain. One day, Songoku gets to know about &#39;7 Dragon Balls&#39;, which will grant any wishes, and leave to collect them. On the way, Bulma joins and they together start on a journey!\"}},{\"kind\":\"books#volume\",\"id\":\"8ifvjwEACAAJ\",\"etag\":\"+EwJ/fVkIbI\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/8ifvjwEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール超(スーパー)1\",\"authors\":[\"とよたろう\",\"鳥山明\"],\"publishedDate\":\"2016-04\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088806611\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088806617\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":192,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=8ifvjwEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=2&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=8ifvjwEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB%E8%B6%85_%E3%82%B9%E3%83%BC%E3%83%91%E3%83%BC_1.html?hl=&id=8ifvjwEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=8ifvjwEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"t_2wnQEACAAJ\",\"etag\":\"hF5lrLVUlik\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/t_2wnQEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボールSD.\",\"authors\":[\"ナホ・オオイシ\"],\"publishedDate\":\"2013\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"408870648X\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088706481\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":184,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=t_2wnQEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=3&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=t_2wnQEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%ABSD.html?hl=&id=t_2wnQEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=t_2wnQEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"gh-yuAAACAAJ\",\"etag\":\"1+rjhep2g6I\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/gh-yuAAACAAJ\",\"volumeInfo\":{\"title\":\"『ドラゴンボール』に生きる力を学べ!\",\"authors\":[\"平居謙\"],\"publishedDate\":\"2011-02\",\"description\":\"主人公・悟空の第一声、おぼえてる?楽しい人生は、「やあオッス!!」という明るい挨拶から!超ロングセラー漫画『DRAGON BALL』が教えてくれる50のポイント。\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4781700748\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784781700748\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":217,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=gh-yuAAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=gh-yuAAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=gh-yuAAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=4&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=gh-yuAAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB_%E3%81%AB%E7%94%9F%E3%81%8D%E3%82%8B%E5%8A%9B%E3%82%92.html?hl=&id=gh-yuAAACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=gh-yuAAACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"主人公・悟空の第一声、おぼえてる?楽しい人生は、「やあオッス!!」という明るい挨拶から!超ロングセラー漫画『DRAGON BALL』が教えてくれる50のポイント。\"}},{\"kind\":\"books#volume\",\"id\":\"Bl0pNwAACAAJ\",\"etag\":\"Wnod79pfaYs\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/Bl0pNwAACAAJ\",\"volumeInfo\":{\"title\":\"『ドラゴンボール』の秘密\",\"authors\":[\"世田谷ドラゴンボール研究会\"],\"publishedDate\":\"2004-07\",\"description\":\"Z戦士たちの真実に迫る。『ドラゴンボール』研究の決定版。\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4887187769\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784887187764\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":217,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=Bl0pNwAACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=Bl0pNwAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=Bl0pNwAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=5&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=Bl0pNwAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB_%E3%81%AE%E7%A7%98%E5%AF%86.html?hl=&id=Bl0pNwAACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=Bl0pNwAACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"Z戦士たちの真実に迫る。『ドラゴンボール』研究の決定版。\"}},{\"kind\":\"books#volume\",\"id\":\"wi0otAEACAAJ\",\"etag\":\"Rt3pfXWQ+00\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/wi0otAEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール超(スーパー)4\",\"authors\":[\"とよたろう\",\"鳥山明\"],\"publishedDate\":\"2017-11\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088811631\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088811635\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":200,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=wi0otAEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=6&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=wi0otAEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB%E8%B6%85_%E3%82%B9%E3%83%BC%E3%83%91%E3%83%BC_4.html?hl=&id=wi0otAEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=wi0otAEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"wis2AQAAIAAJ\",\"etag\":\"ptX+IOxPC44\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/wis2AQAAIAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール\",\"authors\":[\"鳥山明\"],\"publishedDate\":\"2003\",\"industryIdentifiers\":[{\"type\":\"OTHER\",\"identifier\":\"STANFORD:36105131349677\"}],\"readingModes\":{\"text\":false,\"image\":false},\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"0.4.4.0.preview.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=wis2AQAAIAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=wis2AQAAIAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"ja\",\"previewLink\":\"http://books.google.co.jp/books?id=wis2AQAAIAAJ&q=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=7&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=wis2AQAAIAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB.html?hl=&id=wis2AQAAIAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=wis2AQAAIAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"ありがとふーっ<b>ドラゴンボール</b>っていう玉をさがしにきたらさ飛行機が落っこっちゃって...ふーっ Villism01Vジングル村!あなた」凍ってたのょ!\"}},{\"kind\":\"books#volume\",\"id\":\"74k6DwAAQBAJ\",\"etag\":\"JSQmoNie2oQ\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/74k6DwAAQBAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボールZ アニメコミックス 8 燃えつきろ!! 熱戦・烈戦・超激戦\",\"authors\":[\"鳥山明\"],\"publisher\":\"集英社\",\"publishedDate\":\"2013-10-04\",\"description\":\"【フルカラー版！】ドラゴンボールZ・アニメコミックス第8弾は’93年春休みに劇場公開された長編オリジナル作「燃えつきろ!! 熱戦・烈戦・超激戦」をオールカラーでパーフェクト収録したぞっ!! 最強パワーを誇る伝説の超サイヤ人を相手に孫悟空・孫悟飯・ベジータ・トランクスの4大超サイヤ人が壮絶なバトルを展開!! 超サイヤ人同士のパワフルでスピーディな激突を見逃すなっ!!\",\"industryIdentifiers\":[{\"type\":\"OTHER\",\"identifier\":\"PKEY:83421186100181335501\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":142,\"printType\":\"BOOK\",\"categories\":[\"Comics & Graphic Novels\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"1.1.1.0.preview.3\",\"panelizationSummary\":{\"containsEpubBubbles\":true,\"containsImageBubbles\":true,\"epubBubbleVersion\":\"ca3b80d5032ee6b7_A\",\"imageBubbleVersion\":\"ca3b80d5032ee6b7_A\"},\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=74k6DwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=74k6DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=74k6DwAAQBAJ&printsec=frontcover&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=8&source=gbs_api\",\"infoLink\":\"https://play.google.com/store/books/details?id=74k6DwAAQBAJ&source=gbs_api\",\"canonicalVolumeLink\":\"https://play.google.com/store/books/details?id=74k6DwAAQBAJ\",\"seriesInfo\":{\"kind\":\"books#volume_series_info\",\"bookDisplayNumber\":\"8\",\"volumeSeries\":[{\"seriesId\":\"sYnOGgAAABB-kM\",\"seriesBookType\":\"COLLECTED_EDITION\",\"orderNumber\":8}]}},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":701,\"currencyCode\":\"JPY\"},\"retailPrice\":{\"amount\":701,\"currencyCode\":\"JPY\"},\"buyLink\":\"https://play.google.com/store/books/details?id=74k6DwAAQBAJ&rdid=book-74k6DwAAQBAJ&rdot=1&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":701000000,\"currencyCode\":\"JPY\"},\"retailPrice\":{\"amountInMicros\":701000000,\"currencyCode\":\"JPY\"}}]},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.co.jp/books/download/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%ABZ_%E3%82%A2%E3%83%8B%E3%83%A1%E3%82%B3%E3%83%9F-sample-epub.acsm?id=74k6DwAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.co.jp/books/download/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%ABZ_%E3%82%A2%E3%83%8B%E3%83%A1%E3%82%B3%E3%83%9F-sample-pdf.acsm?id=74k6DwAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://play.google.com/books/reader?id=74k6DwAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"【フルカラー版！】ドラゴンボールZ・アニメコミックス第8弾は’93年春休みに劇場公開された長編オリジナル作「燃えつきろ!! ...\"}},{\"kind\":\"books#volume\",\"id\":\"Q-BPAQAACAAJ\",\"etag\":\"wAgIxv9wJM8\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/Q-BPAQAACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール超(スーパー)2\",\"authors\":[\"とよたろう\",\"鳥山明\"],\"publishedDate\":\"2016-12\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088808673\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088808673\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":216,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=Q-BPAQAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=9&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=Q-BPAQAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB%E8%B6%85_%E3%82%B9%E3%83%BC%E3%83%91%E3%83%BC_2.html?hl=&id=Q-BPAQAACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=Q-BPAQAACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"9hf7ngEACAAJ\",\"etag\":\"2Bmp4ArQixM\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/9hf7ngEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボールZ\",\"subtitle\":\"燃えつきろ!!熱戦・烈戦・超激戦\",\"authors\":[\"鳥山明\"],\"publishedDate\":\"2013-10\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4834232077\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784834232073\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":144,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=9hf7ngEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=10&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=9hf7ngEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%ABZ.html?hl=&id=9hf7ngEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=9hf7ngEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"8d3QzQEACAAJ\",\"etag\":\"CQPiiO/yanU\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/8d3QzQEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール超(スーパー) 13\",\"authors\":[\"とよたろう\",\"鳥山明\"],\"publishedDate\":\"2020-08\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088823915\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088823911\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":192,\"printType\":\"BOOK\",\"categories\":[\"Good and evil\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=8d3QzQEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=11&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=8d3QzQEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB%E8%B6%85_%E3%82%B9%E3%83%BC%E3%83%91%E3%83%BC_13.html?hl=&id=8d3QzQEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=8d3QzQEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"VocV_gAACAAJ\",\"etag\":\"p7cM5ACznGs\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/VocV_gAACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール\",\"subtitle\":\"完全版04。\",\"authors\":[\"鳥山明\"],\"publishedDate\":\"2002-12\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088734475\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088734477\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":219,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=VocV_gAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=12&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=VocV_gAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB.html?hl=&id=VocV_gAACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=VocV_gAACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"yg39ewEACAAJ\",\"etag\":\"SzCLGmrU1h8\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/yg39ewEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール\",\"subtitle\":\"完全版14。\",\"authors\":[\"鳥山明\"],\"publishedDate\":\"2003-06\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088734572\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088734576\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":217,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=yg39ewEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=13&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=yg39ewEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB.html?hl=&id=yg39ewEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=yg39ewEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"QQhUkgEACAAJ\",\"etag\":\"Bb9zkU1UzdA\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/QQhUkgEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール\",\"subtitle\":\"完全版06。\",\"authors\":[\"鳥山明\"],\"publishedDate\":\"2003-02\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088734491\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088734491\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":219,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=QQhUkgEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=14&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=QQhUkgEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB.html?hl=&id=QQhUkgEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=QQhUkgEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"5Vt_ygAACAAJ\",\"etag\":\"BLqzVmwsB30\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/5Vt_ygAACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール\",\"authors\":[\"鳥山明\"],\"publishedDate\":\"2003-07\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088734599\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088734590\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":215,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=5Vt_ygAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=15&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=5Vt_ygAACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB.html?hl=&id=5Vt_ygAACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=5Vt_ygAACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"T6nLywEACAAJ\",\"etag\":\"S1zQyAAVO3A\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/T6nLywEACAAJ\",\"volumeInfo\":{\"title\":\"スーパードラゴンボールヒーローズ 9th ANNIVERSARY SUPER GUIDE\",\"subtitle\":\"Vジャンプブックス/バンダイ公認\",\"authors\":[\"Vジャンプ編集部\"],\"publishedDate\":\"2019-11\",\"description\":\"トランクス:ゼノはどうして超サイヤ人ゴッドになったのかを大特集!トランクス役草尾毅、主題歌Dragon Soul、バトルプリンセスすず&バトルナビゲーターカナタインタビュー!!最新ユニバースミッション11弾のカードデータ&最速攻略情報!!!\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4087797813\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784087797817\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":129,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=T6nLywEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=T6nLywEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=T6nLywEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=16&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=T6nLywEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%82%B9%E3%83%BC%E3%83%91%E3%83%BC%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB%E3%83%92%E3%83%BC.html?hl=&id=T6nLywEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=T6nLywEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"トランクス:ゼノはどうして超サイヤ人ゴッドになったのかを大特集!トランクス役草尾毅、主題歌Dragon Soul、バトルプリンセスすず&amp;バトルナビゲーターカナタインタビュー! ...\"}},{\"kind\":\"books#volume\",\"id\":\"C7qZvwEACAAJ\",\"etag\":\"YjwcPmJOPvs\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/C7qZvwEACAAJ\",\"volumeInfo\":{\"title\":\"劇場版ドラゴンボール超ブロリー\",\"authors\":[\"鳥山明\",\"日下部匡俊\"],\"publishedDate\":\"2018-12\",\"description\":\"「力の大会」が終わり、さらなる高みを目指して修業に明け暮れていた悟空とベジータ。ある日、地獄から舞い戻ってきたフリーザが、謎のサイヤ人を連れて現れる。彼の名は「ブロリー」。3人のサイヤ人の壮絶なド迫力バトルが今、ここに幕を開ける―!!\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4087034682\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784087034684\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":228,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=C7qZvwEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=C7qZvwEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api\"},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=C7qZvwEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=17&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=C7qZvwEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E5%8A%87%E5%A0%B4%E7%89%88%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB%E8%B6%85%E3%83%96%E3%83%AD.html?hl=&id=C7qZvwEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=C7qZvwEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"「力の大会」が終わり、さらなる高みを目指して修業に明け暮れていた悟空とベジータ。ある日、地獄から舞い戻ってきたフリーザが、謎のサイヤ人を連れて現れる。彼の名は「 ...\"}},{\"kind\":\"books#volume\",\"id\":\"hqj0kQEACAAJ\",\"etag\":\"W+PQPmoMRW8\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/hqj0kQEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール\",\"subtitle\":\"完全版08。\",\"authors\":[\"鳥山明\"],\"publishedDate\":\"2003-03\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088734513\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088734514\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":223,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=hqj0kQEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=18&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=hqj0kQEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB.html?hl=&id=hqj0kQEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=hqj0kQEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"jnLlxQEACAAJ\",\"etag\":\"WaO/Rlwprfs\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/jnLlxQEACAAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボール超(スーパー)9\",\"authors\":[\"とよたろう\",\"鳥山明\"],\"publishedDate\":\"2019-04\",\"industryIdentifiers\":[{\"type\":\"ISBN_10\",\"identifier\":\"4088818113\"},{\"type\":\"ISBN_13\",\"identifier\":\"9784088818115\"}],\"readingModes\":{\"text\":false,\"image\":false},\"pageCount\":192,\"printType\":\"BOOK\",\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":false,\"contentVersion\":\"preview-1.0.0\",\"panelizationSummary\":{\"containsEpubBubbles\":false,\"containsImageBubbles\":false},\"language\":\"un\",\"previewLink\":\"http://books.google.co.jp/books?id=jnLlxQEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=19&source=gbs_api\",\"infoLink\":\"http://books.google.co.jp/books?id=jnLlxQEACAAJ&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&source=gbs_api\",\"canonicalVolumeLink\":\"https://books.google.com/books/about/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB%E8%B6%85_%E3%82%B9%E3%83%BC%E3%83%91%E3%83%BC_9.html?hl=&id=jnLlxQEACAAJ\"},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"NOT_FOR_SALE\",\"isEbook\":false},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"NO_PAGES\",\"embeddable\":false,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":false},\"pdf\":{\"isAvailable\":false},\"webReaderLink\":\"http://play.google.com/books/reader?id=jnLlxQEACAAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"NONE\",\"quoteSharingAllowed\":false}},{\"kind\":\"books#volume\",\"id\":\"nm4QEAAAQBAJ\",\"etag\":\"O0Lr62e45Z4\",\"selfLink\":\"https://www.googleapis.com/books/v1/volumes/nm4QEAAAQBAJ\",\"volumeInfo\":{\"title\":\"ドラゴンボールSD 7\",\"authors\":[\"鳥山明\",\"オオイシナホ\"],\"publisher\":\"集英社\",\"publishedDate\":\"2021-02-04\",\"description\":\"【フルカラー版！】殺された仲間を生き返らせるため、新たなドラゴンボールを求めて、ナメック星へ向かったクリリンたち。だが残虐非道なフリーザ軍がドラゴンボールを狙い訪れていた…。ナメック星でのDB（ドラゴンボール）争奪戦の第7巻!!\",\"industryIdentifiers\":[{\"type\":\"OTHER\",\"identifier\":\"PKEY:08882519851831335501\"}],\"readingModes\":{\"text\":true,\"image\":true},\"pageCount\":193,\"printType\":\"BOOK\",\"categories\":[\"Comics & Graphic Novels\"],\"maturityRating\":\"NOT_MATURE\",\"allowAnonLogging\":true,\"contentVersion\":\"1.2.2.0.preview.3\",\"panelizationSummary\":{\"containsEpubBubbles\":true,\"containsImageBubbles\":true,\"epubBubbleVersion\":\"53a6a8da5b37ad3a_A\",\"imageBubbleVersion\":\"53a6a8da5b37ad3a_A\"},\"imageLinks\":{\"smallThumbnail\":\"http://books.google.com/books/content?id=nm4QEAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\",\"thumbnail\":\"http://books.google.com/books/content?id=nm4QEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api\"},\"language\":\"ja\",\"previewLink\":\"http://books.google.co.jp/books?id=nm4QEAAAQBAJ&pg=PA43&dq=%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%AB&hl=&cd=20&source=gbs_api\",\"infoLink\":\"https://play.google.com/store/books/details?id=nm4QEAAAQBAJ&source=gbs_api\",\"canonicalVolumeLink\":\"https://play.google.com/store/books/details?id=nm4QEAAAQBAJ\",\"seriesInfo\":{\"kind\":\"books#volume_series_info\",\"bookDisplayNumber\":\"7\",\"volumeSeries\":[{\"seriesId\":\"WOHyFwAAABCr9M\",\"seriesBookType\":\"COLLECTED_EDITION\",\"orderNumber\":7}]}},\"saleInfo\":{\"country\":\"JP\",\"saleability\":\"FOR_SALE\",\"isEbook\":true,\"listPrice\":{\"amount\":627,\"currencyCode\":\"JPY\"},\"retailPrice\":{\"amount\":627,\"currencyCode\":\"JPY\"},\"buyLink\":\"https://play.google.com/store/books/details?id=nm4QEAAAQBAJ&rdid=book-nm4QEAAAQBAJ&rdot=1&source=gbs_api\",\"offers\":[{\"finskyOfferType\":1,\"listPrice\":{\"amountInMicros\":627000000,\"currencyCode\":\"JPY\"},\"retailPrice\":{\"amountInMicros\":627000000,\"currencyCode\":\"JPY\"}}]},\"accessInfo\":{\"country\":\"JP\",\"viewability\":\"PARTIAL\",\"embeddable\":true,\"publicDomain\":false,\"textToSpeechPermission\":\"ALLOWED\",\"epub\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.co.jp/books/download/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%ABSD_7-sample-epub.acsm?id=nm4QEAAAQBAJ&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"pdf\":{\"isAvailable\":true,\"acsTokenLink\":\"http://books.google.co.jp/books/download/%E3%83%89%E3%83%A9%E3%82%B4%E3%83%B3%E3%83%9C%E3%83%BC%E3%83%ABSD_7-sample-pdf.acsm?id=nm4QEAAAQBAJ&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api\"},\"webReaderLink\":\"http://play.google.com/books/reader?id=nm4QEAAAQBAJ&hl=&printsec=frontcover&source=gbs_api\",\"accessViewStatus\":\"SAMPLE\",\"quoteSharingAllowed\":false},\"searchInfo\":{\"textSnippet\":\"ドドリアさんたしか二人目に殺したナメック星人さんがおもしろいことを言ってましたよね IE あなたたちの<b>ドラゴンボール</b>とやらを集めていろフリーザというものですは&nbsp;...\"}}]}\n"
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val adapter = moshi.adapter(Books::class.java)
        val books = adapter.fromJson(stubResponse)
        Assert.assertEquals(books?.items?.isEmpty(), false)
        Assert.assertEquals(books?.items?.get(0)?.volumeInfo?.title, "ドラゴンボール")
        Assert.assertEquals(books?.items?.get(0)?.volumeInfo?.subtitle, "孫悟空と仲間たち. 卷1")
        Assert.assertEquals(books?.items?.get(0)?.volumeInfo?.authors?.get(0), "鳥山明")
        Assert.assertEquals(books?.items?.size, 20)
    }
}