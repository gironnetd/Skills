import 'package:flutter/material.dart';
import 'package:marseille/place.dart';

class DetailPage extends StatelessWidget {
  Place place;
  DetailPage({required this.place});

  @override
  Widget build(BuildContext context) {
    final orientation = MediaQuery.of(context).orientation;
    final size = MediaQuery.of(context).size;
    return Scaffold(
      appBar: AppBar(
        title: Text(place.name),
      ),
      body: SingleChildScrollView(
        child: (isPortrait(orientation))
            ? Column(children: children(size, orientation),)
            : Row(children: children(size, orientation),),
      )


    );
  }
  
  List<Widget> children(Size size, Orientation orientation) {
    final height = size.height;
    final width = size.width;
    return [
      Image.asset(place.getFolderPath(),
      height: (isPortrait(orientation) ? height / 2.5 : height),
        width: (isPortrait(orientation) ? width : width / 3),
        fit: BoxFit.cover
      ),

      (isPortrait(orientation))
        ? Padding(
          padding: const EdgeInsets.all(15),
        child: Text(place.desc),
      )
          : Expanded(
          child: Padding(
            padding: const EdgeInsets.all(8),
            child: Text(place.desc),
          )
      )
    ];
  }
  
  bool isPortrait(Orientation orientation) {
    return orientation == Orientation.portrait;
  }
}