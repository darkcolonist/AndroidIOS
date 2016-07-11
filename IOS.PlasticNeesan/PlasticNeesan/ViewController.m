//
//  ViewController.m
//  PlasticNeesan
//
//  Created by Creative on 7/10/16.
//  Copyright © 2016 Creative. All rights reserved.
//

#import "ViewController.h"
#import "CalculatorBrain.h"
@interface ViewController ()
@property (nonatomic) BOOL userIsInTheMiddleOfEnteringANumber;
@property (nonatomic, strong) CalculatorBrain *brain;
@end

@implementation ViewController

@synthesize btnCasio10;
@synthesize btnCentOS;
@synthesize btnCommodore;
@synthesize btnDOS;
@synthesize btnFirefoxOS;
@synthesize btnMIUI;
@synthesize btnSlackware;
@synthesize btnSymbian;

@synthesize display;
@synthesize userIsInTheMiddleOfEnteringANumber;
@synthesize brain = _brain;

//CALCULATOR

-(CalculatorBrain *) brain{
    if(!_brain) _brain = [[CalculatorBrain alloc] init];{
        return _brain;
    }
}

-(IBAction)btnDigitClick:(id)sender{
    NSString *digit = [sender currentTitle];
    if(self.userIsInTheMiddleOfEnteringANumber){
        self.display.text = [self.display.text stringByAppendingString:digit];
    }else{
        self.display.text = digit;
        self.userIsInTheMiddleOfEnteringANumber = YES;
    }
}

-(IBAction)btnOperationClick:(id)sender{
    if(self.userIsInTheMiddleOfEnteringANumber){
        [self btnKyaClick];
    }
    
    NSString *operation = [sender currentTitle];
    double result = [self.brain performOperation:operation];
    self.display.text = [NSString stringWithFormat:@"%g",result];
}

-(IBAction)btnKyaClick{
    [self.brain pushOperand:[self.display.text doubleValue]];
    self.userIsInTheMiddleOfEnteringANumber = NO;
}
/*-----*/

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)showMessage:(id)sender {
    UIAlertView *kahitano = [[UIAlertView alloc] initWithTitle:@"My First App"
                                                       message:@"Hello World!" delegate:nil cancelButtonTitle:@"OK"
                                             otherButtonTitles: nil];
    [kahitano show];
}

- (IBAction)btnOSClick:(id)sender {
    NSString *favoriteOS = [sender titleForState:UIControlStateNormal];
    NSString *alertViewText = [[NSString alloc] initWithFormat:
                               @"Baka-niisan chose %@!!", favoriteOS];
    
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"BAKAAAA!"
                                                    message:alertViewText delegate:nil
                                          cancelButtonTitle:@"yamette!!~"
                                          otherButtonTitles:@"kuso kuso kuso kuso!!~",nil];
    
    [alert show];
}
@end
