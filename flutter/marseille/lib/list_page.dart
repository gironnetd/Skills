import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:marseille/datasource.dart';
import 'package:marseille/navigator_helper.dart';

class ListPage extends StatefulWidget {
  @override
  State<ListPage> createState() => ListState();
}

class ListState extends State<ListPage> {

  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    return ListView.separated(
        itemBuilder: (context, index) {
          final place = DataSource().allPlaces()[index];
          return ListTile(
            title: Text(
                place.name,
              style: const TextStyle(
                color: Colors.blue,
                fontWeight: FontWeight.bold
              ),
            ),
            leading: Text((index + 1).toString()),
            trailing: Image.asset(
                place.getFolderPath(),
                width: width / 3,
              fit: BoxFit.cover,
            ),
            onTap: () {
              //Naviguer
              NavigatorHelper().toDetail(context: context, place: place);
            },
          );
        },
        separatorBuilder: ((BuildContext ctx, int index) => const Divider(color: Colors.blueAccent,)),
        itemCount: DataSource().allPlaces().length
    );
  }
}