
import 'dart:async';

import 'package:flutter/services.dart';

class TiktokPlugin {
  static const MethodChannel _channel = MethodChannel('com.planet.tiktok_plugin');

  static Future<bool?> sharePhotos(List<String> images) async {
    return _channel.invokeMethod('sharePhotos', {'images': images});
  }

  /// 是否客户端安装了tiktok
  static Future<bool?> isInstallTiktok() async {
    return _channel.invokeMethod('installTiktok');
  }
}
