import 'package:flutter/material.dart';
import 'package:mon_jardin_ideal/model/menu_item.dart';
import 'package:mon_jardin_ideal/view/drawer_header_view.dart';

class DrawerView extends StatelessWidget {
  final List<MenuItem> items;
  final Function(Widget) selection;

  const DrawerView({super.key, required this.items, required this.selection});

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: ListView.separated(
          itemBuilder: (context, index) {
            return (index == 0)
                ? const DrawerHeaderView()
                : ListTile(
              title: Text(items[index - 1].name),
              onTap: () {
                selection(items[index - 1].view);
                Navigator.pop(context);
              },
            );
          },
          separatorBuilder: ((context, index) => (index == 0) ? const SizedBox() : const Divider()),
          itemCount: items.length + 1
      ),
    );
  }
}