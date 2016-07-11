//
//  RecipeDetailViewController.h
//  RecipeBook
//
//  Created by Erwin Globio on 2/3/15.
//  Copyright (c) 2015 EGlobio. All rights reserved.
//

#import "Recipe.h"
#import <UIKit/UIKit.h>

@interface RecipeDetailViewController : UIViewController
//@property (strong, nonatomic) IBOutlet UILabel *recipelabel;
//@property (nonatomic, strong) NSString *recipeName;

@property (weak, nonatomic) IBOutlet UIImageView *recipePhoto;

@property (weak, nonatomic) IBOutlet UILabel*prepTimeLabel;

@property (weak, nonatomic) IBOutlet UITextView *ingredientTextView;

@property (nonatomic, strong) Recipe *recipe;

@end
