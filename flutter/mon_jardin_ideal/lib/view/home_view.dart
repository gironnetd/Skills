import 'package:flutter/material.dart';

class HomeView extends StatelessWidget {
  const HomeView({super.key});

  @override
  Widget build(BuildContext context) {
    Size size = MediaQuery.of(context).size;
    return SafeArea(
        child: Stack(
          alignment: Alignment.center,
          children: [
            SizedBox(
              height: size.height,
              width: size.width,
              child: Image.asset(
                  "images/piscine 4.jpeg",
                fit: BoxFit.cover
              ),
            ),
            Column(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Container(
                  decoration: BoxDecoration(
                      color: Theme.of(context).colorScheme.inversePrimary,
                      borderRadius: BorderRadius.circular(25)
                  ),
                  margin: const EdgeInsets.only(top: 16),
                  padding: const EdgeInsets.symmetric(
                      horizontal: 45,
                      vertical: 12
                  ),
                  child: const Text(
                    "Accueil",
                    style: TextStyle(
                        color: Colors.white,
                        fontSize: 24,
                        fontStyle: FontStyle.italic
                    ),
                  ),
                ),
                Container(
                  decoration: BoxDecoration(
                      color: Theme.of(context).colorScheme.inversePrimary,
                      borderRadius: const BorderRadius.only(
                          topLeft: Radius.circular(32),
                          topRight: Radius.circular(32)
                      )
                  ),
                  padding: const EdgeInsets.all(25),
                  child: const Text(
                      "Une expertise unique en Provence au service de tous les jardins du monde.\nRapprochons nous de la nature",
                    textAlign: TextAlign.center,
                  ),
                )
              ],
            )

          ],
        )
    );
  }
}