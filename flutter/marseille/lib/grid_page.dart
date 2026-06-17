import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:marseille/datasource.dart';
import 'package:marseille/navigator_helper.dart';

class GridPage extends StatefulWidget {
  @override
  State<GridPage> createState() => GridState();
}

class GridState extends State<GridPage> {

  @override
  Widget build(BuildContext context) {
    final datas = DataSource().allPlaces();
    final size = MediaQuery.of(context).size;
    return GridView.builder(
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 4),
        itemBuilder: (ctx, i) {
          final place = datas[i];
          return InkWell(
            child: Card(
              elevation: 8,
              child: Container(
                padding: const EdgeInsets.all(5),
                height: size.height * 0.75,
                child: Column(
                  children: [
                    Expanded(
                        child: Image.asset(place.getFolderPath(), fit: BoxFit.cover)
                    ),
                    Text(
                        place.name,
                      style: const TextStyle(
                        color: Colors.blue,
                        fontWeight: FontWeight.bold
                      ),
                    )
                  ],
                ),
              ),
            ),
            onTap: () {
              //Naviguer
              NavigatorHelper().toDetail(context: context, place: place);
            },
          );
        },
        itemCount: datas.length,
    );
  }
}