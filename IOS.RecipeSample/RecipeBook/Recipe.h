//
//  Recipe.h
//  RecipeBook
//
//  Created by Erwin Globio on 2/3/15.
//  Copyright (c) 2015 EGlobio. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Recipe : NSObject

@property (nonatomic, strong) NSString *name; // name of recipe
@property (nonatomic, strong) NSString *prepTime; // preparation time
@property (nonatomic, strong) NSString *imageFile; // image filename of recipe
@property (nonatomic, strong) NSArray *ingredients; // ingredients


@end
