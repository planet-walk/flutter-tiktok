#import <Foundation/Foundation.h>
#import <TikTokOpenSDK/TikTokOpenSDKShare.h>

typedef void (^TikTokShareCompletionBlock)(bool isSuccess);
NS_ASSUME_NONNULL_BEGIN

@interface TiktokShare : NSObject
+(void)sendShareComplete:(NSArray *)localIdentifiers responseBlock:(TikTokShareCompletionBlock)completion;
@end

NS_ASSUME_NONNULL_END
