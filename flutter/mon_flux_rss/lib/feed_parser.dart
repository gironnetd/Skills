import 'dart:convert';

import 'package:webfeed/domain/rss_feed.dart';

import 'article.dart';
import 'package:http/http.dart' as http;

class FeedParser {

  static var shared = FeedParser();
  final String source = "https://www.francebleu.fr/rss/infos.xml";

  Future<List<Article>?> parseArticles() async {
    final client = http.Client();
    final url = Uri.parse(source);
    final clientResponse = await client.get(url);
    final rssFeed = RssFeed.parse(utf8.decode(clientResponse.bodyBytes));
    final items = rssFeed.items;
    if (items == null) {
      return null;
    } else {
      List<Article> articles = items.map((item) => Article(item: item)).toList();
      return articles;
    }
  }
}