import 'package:flutter/material.dart';
import 'package:quizz/widgets/custom_text.dart';
import 'page_quizz.dart';

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, this.title}) : super(key: key);
  final String? title;

  @override
  _MyHomePageState createState() => new _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text(widget.title!),
      ),
      body: new Center(
        child: new Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            new Card(
              elevation: 10.0,
              child: new Container(
                height: MediaQuery.of(context).size.width * 0.8,
                width: MediaQuery.of(context).size.width * 0.8,
                child: new Image.asset("quizz assets/cover.jpg",
                  fit: BoxFit.cover,
                ),
              ),
            ),
            new ElevatedButton(
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.blue, // Set the button's background color
              ),
                onPressed: () {
                  Navigator.push(context, new MaterialPageRoute(builder: (BuildContext context) {
                    return new PageQuizz();
                  }));
                },
              child: new CustomText("Commencer le quizz", factor: 1.5),
            )
          ],
        ),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}