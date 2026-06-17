import 'package:webfeed/domain/rss_item.dart';
import 'package:intl/intl.dart';

class Article {

  String? _title;
  String? _description;
  String? _link;
  DateTime? _pubDate;
  String? _imageUrl;

  String get title => _title ?? "";
  String get description => _description ?? "";
  DateTime get date => _pubDate ?? DateTime.now();
  String get imageUrl => _imageUrl ?? "";
  String get link => _link ?? "";

  Article({required RssItem item}) {
    _title = item.title;
    _description = item.description;
    _pubDate = item.pubDate;
    _link = item.link;
    _imageUrl = item.enclosure?.url;
  }

  String stringDate() {
    DateFormat dateFormat = DateFormat.yMMMMEEEEd('fr_FR');
    String string = dateFormat.format(date);
    return string;
  }
}