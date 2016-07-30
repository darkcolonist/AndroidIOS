#import <UIKit/UIKit.h>

@protocol EditInfoViewControllerDelegate

-(void)editingInfoWasFinished;

@end


@interface EditInfoViewController : UIViewController <UITextFieldDelegate>

@property (nonatomic, strong) id<EditInfoViewControllerDelegate> delegate;

@property (weak, nonatomic) IBOutlet UITextField *txtFirstname;

@property (weak, nonatomic) IBOutlet UITextField *txtLastname;

@property (weak, nonatomic) IBOutlet UITextField *txtAge;

@property (weak, nonatomic) IBOutlet UITextField *txtSalary;

@property (weak, nonatomic) IBOutlet UITextField *txtPosition;

@property (weak, nonatomic) IBOutlet UITextField *txtDepartment;

@property (weak, nonatomic) IBOutlet UIImageView *profileImageView;

@property (nonatomic) int recordIDToEdit;


- (IBAction)saveInfo:(id)sender;

@end
