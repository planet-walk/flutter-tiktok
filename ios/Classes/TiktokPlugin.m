#import "TiktokPlugin.h"
#import <TikTokOpenSDK/TikTokOpenSDKApplicationDelegate.h>

#if __has_include(<tiktok_plugin/tiktok_plugin-Swift.h>)
#import <tiktok_plugin/tiktok_plugin-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "tiktok_plugin-Swift.h"
#endif

@implementation TiktokPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftTiktokPlugin registerWithRegistrar:registrar];
}
@end
