import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() {
  runApp(const MyApp());
}

class CatImage {
  final String id;
  final String url;

  CatImage({required this.id, required this.url});

  factory CatImage.fromJson(Map<String, dynamic> json) {
    return CatImage(id: json['id'], url: json['url']);
  }
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // TRY THIS: Try running your application with "flutter run". You'll see
        // the application has a blue toolbar. Then, without quitting the app,
        // try changing the seedColor in the colorScheme below to Colors.green
        // and then invoke "hot reload" (save your changes or press the "hot
        // reload" button in a Flutter-supported IDE, or press "r" if you used
        // the command line to start the app).
        //
        // Notice that the counter didn't reset back to zero; the application
        // state is not lost during the reload. To reset the state, use hot
        // restart instead.
        //
        // This works for code too, not just values: Most code changes can be
        // tested with just a hot reload.
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Cats'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<CatImage> catImages = [];
  CatImage? selectedCatImage;

  @override
  void initState() {
    super.initState();
    fetchCatImages();
  }

  Future<void> fetchCatImages() async {
    final response = await http
        .get(Uri.parse('https://api.thecatapi.com/v1/images/search?limit=10'));

    if (response.statusCode == 200) {
      List<dynamic> data = json.decode(response.body);
      List<CatImage> images =
          data.map((json) => CatImage.fromJson(json)).toList();

      setState(() {
        catImages = images;
      });
    } else {
      print('Failed to load images');
    }
  }

  void setSelectedImage(CatImage image) {
    setState(() {
      selectedCatImage = image;
    });
  }

  @override
  Widget build(BuildContext context) {
    // This method is rerun every time setState is called, for instance as done
    // by the _incrementCounter method above.
    //
    // The Flutter framework has been optimized to make rerunning build methods
    // fast, so that you can just rebuild anything that needs updating rather
    // than having to individually change instances of widgets.
    return Scaffold(
      appBar: AppBar(
        // TRY THIS: Try changing the color here to a specific color (to
        // Colors.amber, perhaps?) and trigger a hot reload to see the AppBar
        // change color while the other colors stay the same.
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Container(
              height: 100, // Height of the horizontal list
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemCount: catImages.length,
                itemBuilder: (context, index) {
                  return GestureDetector(
                    onTap: () {
                      setSelectedImage(catImages[index]);
                    },
                    child: Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Image.network(catImages[index].url),
                    ),
                  );
                },
              ),
            ),
            Expanded(
              child: selectedCatImage != null
                  ? Center(
                child: Image.network(
                  selectedCatImage!.url,
                  height: 300,
                ),
              )
                  : Container(),
            ),
          ],
        ),
      ),
    );
  }
}