//
//  ViewController.h
//  RecipeBook
//
//  Created by Erwin Globio on 2/3/15.
//  Copyright (c) 2015 EGlobio. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController <UITableViewDelegate, UITableViewDataSource>

@property (strong, nonatomic) IBOutlet UITableView *tableView;

@end

