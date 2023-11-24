#import "TiktokShare.h"
#import <TikTokOpenSDK/TikTokOpenSDKShare.h>

#if __has_include(<tiktok_plugin/tiktok_plugin-Swift.h>)
#import <tiktok_plugin/tiktok_plugin-Swift.h>
#else
#import "tiktok_plugin-Swift.h"
#endif

@implementation TiktokShare
+(void)sendShareComplete:(NSArray *)localIdentifiers responseBlock:(TikTokShareCompletionBlock)completion{
    TikTokOpenSDKShareRequest *request =  [[TikTokOpenSDKShareRequest alloc] init];
    request.mediaType = 0;
    request.hashtag = @"Zervoapp";
    request.landedPageType = 1;
    request.localIdentifiers = localIdentifiers;
    NSLog(@"localIdentifiers ---%@",localIdentifiers);
    [request sendShareRequestWithCompletionBlock:^(TikTokOpenSDKShareResponse * _Nonnull Response) {
        NSLog(@"tiktok share response: %@", Response.errString);
        completion(Response.isSucceed);
    }];
}
@end
