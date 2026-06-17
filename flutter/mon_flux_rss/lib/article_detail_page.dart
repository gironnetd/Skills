import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

import 'article.dart';

class ArticleDetailPage extends StatelessWidget {
  final Article article;

  const ArticleDetailPage({super.key, required this.article});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Mon RSS"),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            const SizedBox(height: 16),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                Text(
                    "Publié le: ${article.date}",
                  style: const TextStyle(
                    fontSize: 14,
                    color: Colors.red
                  ),
                )
              ],
            ),
            const SizedBox(height: 16),
            Text(
                article.title,
              style: Theme.of(context).textTheme.titleMedium,
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 16),
            (article.imageUrl != "")
                ? Image.network(
                  article.imageUrl,
                  width: MediaQuery.of(context).size.width,
                  fit: BoxFit.cover,
                )
                : const SizedBox(height: 0),
            Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: 12,
                  vertical: 16
                ),
              child: Text(
                  article.description,
                textAlign: TextAlign.center,
              ),
            ),
            TextButton(
                onPressed: () {
                  _goToWebsite().then((result) {
                    if (!result) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        const SnackBar(
                            content: Text("Oups, un problème est survenu")
                        )
                      );
                    }
                  });
                },
                child: const Text("Vers l'article")
            )
          ],
        ),
      ),
    );
  }

  Future<bool> _goToWebsite() async {
    final Uri url = Uri.parse(article.link);
    if (await canLaunchUrl(url)) {
      await launchUrl(url);
      return true;
    } else {
      return false;
    }
  }

}