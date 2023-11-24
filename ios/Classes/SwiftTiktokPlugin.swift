import Flutter
import UIKit
import TikTokOpenSDK

public class SwiftTiktokPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "com.planet.tiktok_plugin", binaryMessenger: registrar.messenger())
    let instance = SwiftTiktokPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
    registrar.addApplicationDelegate(instance)
  }

  public func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [AnyHashable : Any] = [:]) -> Bool {
    TikTokOpenSDKApplicationDelegate.sharedInstance().application(application, didFinishLaunchingWithOptions: launchOptions)
    return true
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    let args = call.arguments as? Dictionary<String, Any>
    if (call.method == "sharePhotos") {
      sharePhotos(result, imagePaths: args!["images"] as? [String] ?? [])
    } else if (call.method == "installTiktok") {
        let isInstalled = TikTokOpenSDKApplicationDelegate.sharedInstance().isAppInstalled()
        result(isInstalled);
    } else {
      result("iOS " + UIDevice.current.systemVersion)
    }
  }

  private func sharePhotos(_ result: @escaping FlutterResult, imagePaths: [String]) {
    TiktokShare.sendComplete(imagePaths) { (isSuccess) in
      result(isSuccess)
    };
  }
}
