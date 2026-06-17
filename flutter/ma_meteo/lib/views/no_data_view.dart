import 'package:flutter/material.dart';

class NoDataView extends StatelessWidget {
  const NoDataView({super.key});


  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return const Center(
      child: Text("Aucune donnée pour le lieu sélectionné"),
    );
  }
}