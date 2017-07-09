/**
 * Messages
 */
package com.boa.eagls.government.exceptions;

/**
 * Class that holds application information and error messages.
 *  <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class Messages
{

    // Severity definitions
    public final static short HIGH = 1;
    public final static short MEDIUM = 2;
    public final static short LOW = 3;

    // ***** Error messages *****
    // Oracle DB errors
    public final static String ORA_E0000 =
            "Your query could not be completed due to a system error.  Please contact the Bank of America help desk.";
    public final static String ORA_E0001 =
            "Your query could not be completed due to a system error.  Please contact the Bank of America help desk.";
    public final static String ORA_E0002 =
            "Your query could not be completed due to a system error.  Please contact the Bank of America help desk.";
    public final static String ORA_00001 =
            "The information submitted already exists.  Please check your data.";
    public final static String ORA_01403 =
            "No data found for entered information.";
    public final static String ORA_00051 =
            "Time-out occurred while waiting for resource.";
    public final static String ORA_00061 =
            "The transaction was rolled back due to a system error. Please contact the Bank of America help desk.";
    public final static String ORA_01001 =
            "There was an illegal cursor operation.";
    public final static String ORA_01012 =
            "There is no connection to the database. Please contact the Bank of America help desk.";
    public final static String ORA_01017 = "Invalid username/password.";
    public final static String ORA_01422 =
            "There are multiple rows with the same information.  Please contact the Bank of America help desk.";
    public final static String ORA_01476 =
            "There was a division by zero error.";
    public final static String ORA_01722 =
            "There was an invalid number format entered.";
    public final static String ORA_06500 =
            "Internal memory full. Please contact the Bank of America help desk.";
    public final static String ORA_06501 =
            "Internal processing error.  Please contact the Bank of America help desk.";
    public final static String ORA_06502 =
            "There was a format error with the submitted data.";
    public final static String ORA_06504 =
            "Incompatible type between submitted information and database.";
    public final static String ORA_06511 =
            "There is identical process running at this time.";
    public final static String ORA_06530 =
            "Internal processing error.  Please contact the Bank of America help desk.";
    public final static String ORA_06531 =
            "Internal processing error.  Please contact the Bank of America help desk.";
    public final static String ORA_06532 =
            "Internal processing error.  Please contact the Bank of America help desk.";
    public final static String ORA_06533 =
            "Internal processing error.  Please contact the Bank of America help desk.";
    public final static String ORA_20011 =
            "Could not complete user profile request.  Please contact the Bank of America help desk.";
    public final static short ORA_E0000_Severity = HIGH;
    public final static short ORA_E0001_Severity = MEDIUM;
    public final static short ORA_E0002_Severity = LOW;
    public final static short ORA_00001_Severity = HIGH;
    public final static short ORA_01403_Severity = MEDIUM;
    public final static short ORA_00051_Severity = LOW;
    public final static short ORA_00061_Severity = MEDIUM;
    public final static short ORA_01001_Severity = HIGH;
    public final static short ORA_01012_Severity = LOW;
    public final static short ORA_01017_Severity = LOW;
    public final static short ORA_01422_Severity = MEDIUM;
    public final static short ORA_01476_Severity = HIGH;
    public final static short ORA_01722_Severity = HIGH;
    public final static short ORA_06500_Severity = HIGH;
    public final static short ORA_06501_Severity = HIGH;
    public final static short ORA_06502_Severity = HIGH;
    public final static short ORA_06504_Severity = HIGH;
    public final static short ORA_06511_Severity = HIGH;
    public final static short ORA_06530_Severity = HIGH;
    public final static short ORA_06531_Severity = HIGH;
    public final static short ORA_06532_Severity = HIGH;
    public final static short ORA_06533_Severity = HIGH;
    public final static short ORA_20011_Severity = HIGH;

    // PL/SQL errors
    public final static String PLS_E0000 =
            "Your query could not be completed due to a system error.  Please contact the Bank of America help desk.";
    public final static String PLS_E0001 =
            "Your query could not be completed due to a system error.  Please contact the Bank of America help desk.";
    public final static String PLS_E0002 =
            "Your query could not be completed due to a system error.  Please contact the Bank of America help desk.";
    public final static String PLS_E0003 =
            "This transaction has already been queued for transfer.";
    public final static String PLS_E0004 =
            "No Invoice/Statement was found for the period specified.";
    public final static String PLS_E0005 =
            "Transaction(s) has already been certified.";
    public final static String PLS_E0006 =
            "Transaction(s) can not be certified as it is in dispute.";
    public final static String PLS_E0007 =
            "User does not have privilages to change password.";
    public final static String PLS_E0008 =
            "Password must not match the nine previous passwords.";
    public final static String PLS_E0009 =
            "The Current Password value you entered does not match your current password. Please try again or contact your GCSU representative.";
    public final static String PLS_E0010 =
            "The accounting center ID used is invalid or the master accounting code is invalid for the accounting center ID.";
    public final static String PLS_E0011 =
            "Your change has been submitted.  However, there was an error in updating to the Reporting Tool. Please contact your GCSU representative.";
    public final static String PLS_E0012 =
            "Your change has been submitted.  However, there was an error in updating to the Reporting Tool. Please contact your GCSU representative.";
    public final static String PLS_E0013 =
            "The user profile has been submitted to EAGLS.  However, there was an error in updating to the Reporting Tool. Please contact your GCSU representative.";
    public final static String PLS_E0014 =
            "User profile information cannot be submitted. Please contact your GCSU representative.";
    public final static String PLS_E0015 =
            "Authorization Controls have been requested for this individualaccount, and pending approval in queue. User cannot submit new requests.";
    public final static String PLS_E0016 =
            "A transfer individualaccount request for this individualaccount already exists in the queue and has not yet been processed or denied.";
    public final static String PLS_E0017 =
            "An individualaccount can be transferred only within the same HL1.";
    public final static String PLS_E0018 =
            "The hierarchy entered has already been setup through the user profile functionality.";
    public final static short PLS_E0000_Severity = HIGH;
    public final static short PLS_E0001_Severity = MEDIUM;
    public final static short PLS_E0002_Severity = LOW;
    public final static short PLS_E0003_Severity = LOW;
    public final static short PLS_E0004_Severity = LOW;
    public final static short PLS_E0005_Severity = LOW;
    public final static short PLS_E0006_Severity = MEDIUM;
    public final static short PLS_E0007_Severity = HIGH;
    public final static short PLS_E0008_Severity = LOW;
    public final static short PLS_E0009_Severity = LOW;
    public final static short PLS_E0010_Severity = MEDIUM;
    public final static short PLS_E0011_Severity = MEDIUM;
    public final static short PLS_E0012_Severity = MEDIUM;
    public final static short PLS_E0013_Severity = MEDIUM;
    public final static short PLS_E0014_Severity = HIGH;
    public final static short PLS_E0015_Severity = LOW;
    public final static short PLS_E0016_Severity = MEDIUM;
    public final static short PLS_E0017_Severity = MEDIUM;
    public final static short PLS_E0018_Severity = MEDIUM;

    // System errors
    public final static String SYS_E0000 = "Database connection failed.";
    public final static String SYS_E0001 =
            "Connection to provider failed. Please contact the Bank of America help desk.";
    public final static String SYS_E0002 = "Database type mismatch.";
    public final static String SYS_E0003 =
            "The user does not have hierarchical access.";
    public final static String SYS_E0004 =
            "Database query returned a null result set.  Please contact the Bank of America help desk.";
    public final static short SYS_E0000_Severity = HIGH;
    public final static short SYS_E0001_Severity = HIGH;
    public final static short SYS_E0002_Severity = HIGH;
    public final static short SYS_E0003_Severity = HIGH;
    public final static short SYS_E0004_Severity = HIGH;

    // Application errors
    public final static String APP_E0000 =
            "Invalid arguments for database query.  Please contact the Bank of America help desk.";
    public final static String APP_E0001 =
            "The expiration date you selected is invalid.  Please enter an expiration date of less than five years from the current date.";
    public final static String APP_E0002 =
            "Invalid User Role or non-existent functions for User Role.";
    public final static String APP_E0004 =
            "Could not complete requesting card. Please contact the Bank of America help desk.";
    public final static String APP_E0005 =
            "The new central individualaccount number entered is invalid.";
    public final static String APP_E0006 =
            "The new central individualaccount number is not in the same hierarchy as the new hierarchy for the individualaccount being transferred.";
    public final static String APP_E0007 =
            "The Queued Request Item you have selected cannot be assigned to you.";
    public final static short APP_E0000_Severity = HIGH;
    public final static short APP_E0001_Severity = MEDIUM;
    public final static short APP_E0002_Severity = HIGH;
    public final static short APP_E0004_Severity = HIGH;
    public final static short APP_E005_Severity = MEDIUM;
    public final static short APP_E006_Severity = MEDIUM;
    public final static short APP_E007_Severity = MEDIUM;

    // ***** Warning messages *****
    public final static String APP_W0000 =
            "The system can not log you in at this time.  Please check your user ID and password.";
    public final static String APP_W0001 =
            "Your user ID has been locked out of the system due to a security violation.  Please contact the Bank of America help desk.";
    public final static String APP_W0002 =
            "Your session has expired.  Please click below to log into the system again.";
    public final static String APP_W0003 =
            "Your password has expired.  Please click below to change your password.";
    public final static String APP_W0004 =
            "You have selected an invalid password.  Please make sure that your new password contains at least 5 characters, including one alphanumeric character.";
    public final static String APP_W0005 =
            "Your requests have been submitted.  Please wait 24 hrs for synchronization.";
    public final static String APP_W0006 =
            "You do not have authorization for this request.";
    public final static String APP_W0007 =
            "You are not allowed to request a sales draft for this transaction.";
    public final static String APP_W0008 =
            "You have already submitted a request for a sales draft for this transaction.";
    public final static String APP_W0009 = "No data found or invalid data.";
    public final static String APP_W0010 =
            "All of the information you submitted was updated in the system, except the following : ";
    public final static String APP_W0011 =
            "The credit limit you submitted is invalid.";
    public final static String APP_W0012 =
            "The individualaccount you wish to activate is not found or already active.";
    public final static String APP_W0013 =
            "The individualaccount number has already been activated.";
    public final static String APP_W0014 =
            "User ID was not found or invalid for function.";
    public final static String APP_W0015 =
            "No valid roles found for user ID or user ID invalid for function.";
    public final static String APP_W0016 =
            "The individualaccount type is not valid.  Please contact a Bank of America administrator";
    public final static String APP_W0017 =
            "The credit limit of the individualaccount you are attempting to change has been altered by another process. Please try function again to refresh the data.";
    public final static String APP_W0018 =
            "Account information for individualaccount you are attempting to change has been altered by another process. Please try function again to refresh the data.";
    public final static String APP_W0019 =
            "This individualaccount is currently closed, or past due.";
    public final static String APP_W0020 =
            "Current user role does not have access to this individualaccount.";
    public final static String APP_W0021 = "No transactions were found.";
    public final static String APP_W0022 =
            "You cannot order travelers checks for this individualaccount.";
    public final static String APP_W0023 =
            "You cannot order convenience checks for this individualaccount.";
    public final static String APP_W0024 =
            "The accounting center ID that you have chosen does not exist within user's hierarchy.";
    public final static String APP_W0025 =
            "This individualaccount is a non-Card individualaccount.  Cannot request cards.";
    public final static String APP_W0026 =
            "Cannot request card for Central or Diversion Accounts.";
    public final static String APP_W0027 = "Invalid Accounting Center ID.";
    public final static String APP_W0028 =
            "This Accounting Center does not have any accounting code segments setup. Please contact the Bank of America help desk.";
    public final static String APP_W0029 =
            "Accounts cannot be transferred to their current hierarchy level.";
    public final static String APP_W0030 = "No MCCG table names found.";
    public final static String APP_W0031 =
            "Invalid individualaccount type for transfer individualaccount.";
    public final static String APP_W0032 = "This individualaccount is currently closed";
    public final static short APP_W0000_Severity = HIGH;
    public final static short APP_W0001_Severity = HIGH;
    public final static short APP_W0002_Severity = MEDIUM;
    public final static short APP_W0003_Severity = MEDIUM;
    public final static short APP_W0004_Severity = MEDIUM;
    public final static short APP_W0005_Severity = MEDIUM;
    public final static short APP_W0006_Severity = LOW;
    public final static short APP_W0007_Severity = LOW;
    public final static short APP_W0008_Severity = LOW;
    public final static short APP_W0009_Severity = LOW;
    public final static short APP_W0010_Severity = LOW;
    public final static short APP_W0011_Severity = LOW;
    public final static short APP_W0012_Severity = LOW;
    public final static short APP_W0013_Severity = LOW;
    public final static short APP_W0014_Severity = MEDIUM;
    public final static short APP_W0015_Severity = MEDIUM;
    public final static short APP_W0016_Severity = HIGH;
    public final static short APP_W0017_Severity = LOW;
    public final static short APP_W0018_Severity = LOW;
    public final static short APP_W0019_Severity = LOW;
    public final static short APP_W0020_Severity = HIGH;
    public final static short APP_W0021_Severity = LOW;
    public final static short APP_W0022_Severity = LOW;
    public final static short APP_W0023_Severity = LOW;
    public final static short APP_W0024_Severity = MEDIUM;
    public final static short APP_W0025_Severity = LOW;
    public final static short APP_W0026_Severity = LOW;
    public final static short APP_W0027_Severity = LOW;
    public final static short APP_W0028_Severity = HIGH;
    public final static short APP_W0029_Severity = LOW;
    public final static short APP_W0030_Severity = LOW;
    public final static short APP_W0031_Severity = MEDIUM;

    // ***** Informational messages *****
    public final static String APP_I0000 = "No results found.";
    public final static String APP_I0001 =
            "The system was unable to process your submission.  Please contact the Bank of America help desk for more information.";
    public final static String APP_I0002 =
            "Your request was submitted to the provider.  You will be notified within 24 hours if your entry updated successfully.";
    public final static String APP_I0003 =
            "The provider was unable to retrieve your inquiry request, please try again later.";
    public final static short APP_I0000_Severity = LOW;
    public final static short APP_I0001_Severity = MEDIUM;
    public final static short APP_I0002_Severity = LOW;
    public final static short APP_I0003_Severity = LOW;
}
