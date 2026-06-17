import 'package:flutter/material.dart';
import 'package:mon_jardin_ideal/model/menu_item.dart';
import 'package:mon_jardin_ideal/view/drawer.dart';
import 'package:mon_jardin_ideal/view/home_view.dart';
import 'package:mon_jardin_ideal/view/real_view.dart';

class AppController extends StatefulWidget {
  final String title;

  const AppController({super.key, required this.title});

  @override
  State<StatefulWidget> createState() {
    return AppControllerState();
  }

}

class AppControllerState extends State<AppController> {

  Widget body = const HomeView();

  final List<MenuItem> menu = [
    MenuItem(name: "Accueil", view: const HomeView()),
    MenuItem(name: "Réalisations", view: const RealView())
  ];

  void selected(Widget newView) => setState(() => body = newView);


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        centerTitle: true,
      ),
      drawer: DrawerView(items: menu, selection: selected),
      body: body,
    );
  }
}