import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:marseille/detail_page.dart';
import 'package:marseille/place.dart';

class NavigatorHelper {

  toDetail({
    required BuildContext context,
    required Place place,
  }) {
    Navigator.of(context).push(
        MaterialPageRoute(
            builder: (context) {
              return DetailPage(place: place);
            })
    );
  }

}