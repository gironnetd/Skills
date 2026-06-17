import 'package:flutter/material.dart';

class DrawerHeaderView extends StatelessWidget {
  const DrawerHeaderView({super.key});


  @override
  Widget build(BuildContext context) {
    return  DrawerHeader(
        child: InkWell(
          onTap: (() => Navigator.pop(context)),
          child: Card(
              color: Colors.green.shade100,
              child: const Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  Icon(Icons.pool, color: Colors.green,),
                  Text("Mon jardin idéal", style: TextStyle(color: Colors.green))
                ],
              )
          ),
        )
    );
  }
}