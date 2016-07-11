//
//  ViewController.h
//  PlasticNeesan
//
//  Created by Creative on 7/10/16.
//  Copyright © 2016 Creative. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

@property (weak, nonatomic) IBOutlet UIButton *btnSymbian;
@property (weak, nonatomic) IBOutlet UIButton *btnMIUI;
@property (weak, nonatomic) IBOutlet UIButton *btnCommodore;
@property (weak, nonatomic) IBOutlet UIButton *btnFirefoxOS;
@property (weak, nonatomic) IBOutlet UIButton *btnDOS;
@property (weak, nonatomic) IBOutlet UIButton *btnCasio10;
@property (weak, nonatomic) IBOutlet UIButton *btnCentOS;
@property (weak, nonatomic) IBOutlet UIButton *btnSlackware;

//@property (weak, nonatomic) IBOutlet UIButton *btnCalc0;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc1;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc2;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc3;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc4;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc5;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc6;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc7;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc8;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalc9;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalcKya;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalcPlus;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalcMinus;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalcMulti;
//@property (weak, nonatomic) IBOutlet UIButton *btnCalcDivide;

@property (weak, nonatomic) IBOutlet UILabel *display;

- (IBAction)showMessage:(id)sender;
- (IBAction)btnOSClick:(id)sender;
- (IBAction)btnDigitClick:(id)sender;
- (IBAction)btnOperationClick:(id)sender;
- (IBAction)btnKyaClick;

@end

