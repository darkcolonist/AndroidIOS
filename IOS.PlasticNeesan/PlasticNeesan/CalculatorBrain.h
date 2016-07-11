//
//  CalculatorBrain.h
//  PlasticNeesan
//
//  Created by Creative on 7/10/16.
//  Copyright © 2016 Creative. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CalculatorBrain : NSObject
-(void) pushOperand: (double) operand;
-(double) performOperation: (NSString *) operation;

@end
