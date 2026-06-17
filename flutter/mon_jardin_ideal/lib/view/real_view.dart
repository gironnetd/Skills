import 'package:flutter/material.dart';

class RealView extends StatelessWidget {

  const RealView({super.key});

  @override
  Widget build(BuildContext context) {
    final Size size = MediaQuery.of(context).size;
    return SingleChildScrollView(
      child: Column(
        children: [
          const Text(
            "Nos réalisations",
            style: TextStyle(
              color: Colors.lightGreen,
              fontSize: 24
            ),
          ),
          const SizedBox(height: 18),
          const SizedBox(height: 18,),
          const Divider(),
          const Row(
            children: [
              Text(("Jardins")),
              Spacer()
            ],
          ),
          SizedBox(
            height: 150,
            child: ListView.builder(
              itemBuilder: ((context, index) => Container(
                margin: const EdgeInsets.all(5),
                height: 140,
                width: 140,
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(18),
                  child: Image.asset("images/jardin ${index + 1}.jpeg", fit: BoxFit.cover,),
                ),
              )),
              scrollDirection: Axis.horizontal,
              itemCount: 5,
            ),
          ),
          const Divider(),
          const Row(
            children: [
              Text("Piscines"),
              Spacer()
            ],
          ),
          SizedBox(
            width: size.width,
            height: size.height * 0.75,
            child: ListView.separated(
                itemBuilder: ((c, i) => SizedBox(
                  height: size.height * 0.33,
                  child: Image.asset("images/piscine ${i + 1}.jpeg", fit: BoxFit.cover),
                )),
                separatorBuilder: ((c, i) => const Divider()),
                itemCount: 5
            ),
          ),
          const SizedBox(height: 43,)
        ],
      ),
    );
  }
}