import 'package:flutter/material.dart';
import 'package:mon_flux_rss/article_detail_page.dart';

import 'article.dart';

class ArticleTile extends StatelessWidget {
  final Article article;

  const ArticleTile({super.key, required this.article});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () {
        final page = ArticleDetailPage(article: article);
        final route = MaterialPageRoute(builder: (context) => page);
        Navigator.of(context).push(route);
      },
      child: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(12),
            child: Row(
              children: [
                const Spacer(),
                Text(
                  article.stringDate(),
                  style: const TextStyle(
                      color: Colors.red,
                      fontSize: 14
                  ),
                )
              ],
            ),
          ),
          Card(
            margin: const EdgeInsets.symmetric(
              horizontal: 16,
              vertical: 8
            ),
            clipBehavior: Clip.antiAlias,
            elevation: 8,
            child: (article.imageUrl != "")
                ? Image.network(
                  article.imageUrl,
                  width: MediaQuery.of(context).size.width,
                  fit: BoxFit.cover,
                  )
                : const SizedBox(height: 0),
          ),
          Text(
            article.title,
            style: Theme.of(context).textTheme.titleMedium,
            textAlign: TextAlign.center,
          ),
          Padding(
              padding: const EdgeInsets.symmetric(
                horizontal: 12
              ),
            child: Text(
                article.description,
              style: const TextStyle(
                fontStyle: FontStyle.italic
              ),
            )
          ),
        ],
      ),
    );
  }
}