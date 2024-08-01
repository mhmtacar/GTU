import '../shared/slide_menu.dart';
import 'package:flutter/material.dart';

import 'body.dart';

class SettingsScreen extends StatelessWidget {
  const SettingsScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Ayarlar"),
        centerTitle: true,
        foregroundColor: Colors.blue,
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      body: const SettingsBody(),
      drawer: SlideMenu(),
    );
  }
}
