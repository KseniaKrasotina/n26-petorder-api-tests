# N26 App Exploratory Testing Charters

## Testing Objectives

- Explore the app’s core functionality (adding, editing, and deleting transactions).
- Discover potential issues in calculations (balance, categorization charts).
- Investigate the usability of the user interface.
- Assess app performance under different conditions (battery, connection, device rotation, multiple apps running, calls)
- Identify bugs in functionality and UI
- Security vulnerabilities - weak authentication, or data leaks.
- Verify Android UI guidelines and standards.

## Exploratory Testing Charters

Each charter defines a goal for an exploratory testing session.

|    | **Charter**                                      | **Priority** |
|----|--------------------------------------------------|--------------|
| 1  | Data Protection and Security                     | High         |
| 2  | Financial Transactions(adding, editing, balance) | High         |
| 3  | Synchronization                                  | High         |
| 4  | Error Handling                                   | High         |
| 5  | Payment Methods                                  | High         |
| 6  | Categories                                       | Medium       |
| 7  | Data Visualization                               | Medium       |
| 8  | Search and Filters                               | Medium       |
| 9  | App Behavior During Offline & Interrupted States | Medium       |
| 10 | UI/UX and Android Guidelines Compliance          | Medium       |
| 11 | Multi-Currency Support (depends on audience)     | Medium       |
| 12 | Calculator                                       | Low          |

**Reason for prioritization:** Focus on core functionality and security first.

### 1. Data Protection and Security (Priority: High)
**Explore:** The app's security mechanisms  
**With:** Password, Face ID, and Touch ID options  
**To discover:** Vulnerabilities in the security system and data access  
**Reason for priority:** For a financial app, ensuring user data security is critical.

**Findings:**
- Bug found: There is no authentication functionality. Anyone, who get the device is able to open an app, see all transactions without any authentification 

### 2. Financial Transactions (Priority: High)
**Explore:** The functionality of adding income and expenses  
**With:** Various transaction types and categories  
**To discover:** Potential errors in data entry and their representation in reports  
**Reason for priority:** This is the core functionality of the app.

**Findings:**
- Adding incomes and outcomes work as expected, transactions are counted in a balance correctly
- Bug found: Unclear indication of transaction on a “New Expense” Screen
- Low priority bug found (not a big impact, but annoying): Unexpected resorting of categories happens when adding a new transaction on the main screen
- Decimals and Float work as expected

### 3. Synchronization (Priority: High)
**Explore:** The data synchronization mechanism between devices  
**With:** Google Drive and Dropbox  
**To discover:** Issues with data privacy and integrity during syncing  
**Findings:** No subscription

**Reason for priority:** Accurate data synchronization is critical for a multi-device financial app. Any sync issues could lead to data inconsistencies or loss.

### 4. Error Handling (Priority: High)
**Explore:** The app’s error management and user feedback systems  
**With:** Intentionally triggering error conditions, tools such as Charles, Android Studio  
**To discover:** Unclear error messages, improper error handling, or system crashes  
**Findings:** 
- Tiny issue found: works fine when pass too long int as an Income/Expence value, thought there is no any warning when user exeeds the input limitation
- Works as expected when pass 0 as an income/expence value and try to add an empty transaction

**Reason for priority:** Proper error handling is important for user trust and providing clear guidance when issues occur. In the case of non-informative error handling, errors could lead to user confusion and financial mistakes.

### 5. Payment Method (Accounts) (Priority: High)
**Explore:** The various payment methods supported by the app  
**With:** Different types of payment options (cash, credit cards, bank transfers, etc.)  
**To discover:** Any issues with adding, editing, or using different payment methods, and their impact on financial tracking

**Findings:**
- Payment method switching from Cash to Payment card works fine
- Selected payment method is displayed well on the main screen
- A new payment method can be added
- Bug found: When adding a new payment method, there is a Name input on a "New account" Screen with the "Name" Placehoder. The design of the placeholed has an inconsistance with the placeholed of the amount of money on a "New income" Screen 
- Bug found: After adding a new Bitcoin Account, the Bitcoin currency displayed incorrectly, as $ instead of ₿
- Nice to have: Conversion rate

**Reason for priority:** Diverse payment options are important for a financial app, having a big impact on user experience and financial tracking.

### 6. Categories (Priority: Medium)
**Explore:** The category system for income and expenses  
**With:** Organizing categories  
**To discover:** Issues with category organization

**Findings:**
- Unexpected resorting of categories happens when adding a new transaction
- Subscription is needed to explore the whole functionality

**Reason for priority:** Proper categorization is important for accurate financial analysis, but issues here are less critical than those in core transactions or security.

### 7. Data Visualization (Priority: Medium)
**Explore:** Expense distribution charts and values  
**With:** Different time periods, categories, currencies  
**To discover:** Discrepancies between visual representation and actual data

**Findings:**
- Unexpected resorting of categories happens when adding a new transaction
- Calendar works as wxpected
- Dates can be swiped as expected on a Main Screen
- Bug found: When set an interval of Dates in a Calendar from 1 Jan to 5 Feb and swipe the Main Screen between dates fast - app craches on Google Pixel 9 Pro with the "Invalid item positin 9" error. Screenshot

**Reason for priority:** A clear and accurate visual experience of financial data is key for users to understand their financial situation, though not as critical as security.

### 8. Search and Filters (Priority: Medium)
**Explore:** Search functionality and filtering options  
**With:** Various search criteria and filter combinations (calendar, sorting, etc.)  
**To discover:** Any limitations or unexpected behaviors

**Findings:**
- Tiny issue found: It's not clear what I can use the search for. A placeholder with an example could help
- Bug found: Icons in the app bar in a "Search results" Screen are confusing and it's not clear what they represent
- Keyboard opens as expected
- Bug found: Search gives empty results with the message “No records could be found” for categories where no income/outcome was added. The message could be more detailed in this case, e.g., “No record was added, please search another category.”

**Reason for priority:** Proper search and filtering are important for users to navigate and analyze their financial situation, but not as critical as core functionality.

### 9.  App Behavior During Offline & Interrupted States (Priority: Medium)
**Explore:** How app handles situations, such as when the user get a call, switches between the screen etc
**With:**  Transactions during any interruptions, craches
**To discover:** How app handles any interruptions

**Reason for priority:** App should prived seamless experience during offline states and interruptions

### 10. UI/UX and Android Guidelines Compliance (Priority: Medium)
**Explore:** The app’s user interface and experience  
**With:** Android design guidelines and best practices  
**To discover:** Any discrepancies from Android standards or usability issues

**Findings:**
- The Overflow menu and Search icon are positioned in accordance with Android guidelines
- The core functionality buttons, such as the button for adding a transaction, are placed at the bottom of the screen. This follows Android guidelines too

**Reason for priority:** While a user-friendly interface is important, it’s less critical than core functionality and data security. Minor discrepancies from Android guidelines won’t severely impact the app’s primary functions.

### 11. Multi-Currency Support (Priority: Medium)
**Explore:** The app's support for multiple currencies  
**With:** Creating transactions in different currencies and currency conversion  
**To discover:** Inaccuracies in calculations and balance display

**Reason for priority:** Depends on the target audience. For a primarily local app, it might be a lower priority. For an international app, it would be higher.

### 12. Calculation Functionality (Priority: Low)
**Explore:** The in-app calculator for adding expenses and income  
**With:** Various mathematical operations  
**To discover:** Calculation errors or usability issues

**Findings:**
- The calculator in the app functions correctly
- Issue found: There is no visual feedback indicating the operation being performed. When the user clicks on the '+' or '-' buttons, the operation is not displayed anywhere on the screen.

**Reason for priority:** Not a core function. Users can use their device’s calculator as an alternative if needed.

| **ID** | **Title**                                                  | **Description**                                                                                                                                                                                                                                                                                                                                                                      | **Steps to Reproduce**                                                                                                                                                                                                                                                                                                                                             | **Expected Result**                                                           | **Actual Result**                                                                                    | **Priority** |
|--------|------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------|--------------|
| 1      | No authentication functionality                            | Anyone, who get the device is able to open an app, see all transactions without any authentification                                                                                                                                                                                                                                                                                 | 1. Open app                                                                                                                                                                                                                                                                                                                                                        | App requires to enter a password/Fingeprint/Face ID                           | App  is opened on a Main Screen with the sensitive information                                       | High         |
| 2      | Unclear indication of transaction on a “New Income” Screen | On the “New income” screen, it's unclear whether the user is adding a new income or an expense. The button for adding income on the main screen is green, and for adding expenses, it's red. However, on the “New income” or “New expense” screen, the input field for the value is always green, making it difficult for the user to distinguish between the two transaction types. | 1. Open "New expence" Screen. 2. Observe the color of the value input field. 3. Compare it with the main screen where income and expense buttons are differentiated by color (green for income, red for expense). 4. Notice that the value input field on both the “New income” and “New expense” screens is green, even though the user may be adding an expense. | The color of the input field should match the type of transaction being added | The value input field is always green, regardless of whether the user is adding income or an expense | Medium       |
| 3      | Crash happens on fast swiping between the interval dates   | When selecting a date range from 1st January to 5th February in the Calendar and quickly swiping between dates on the Main Screen, the app crashes on a Google Pixel 9 Pro with the error message “Invalid item position 9.”                                                                                                                                                         | 1. Set the date range from 1st January to 5th February in the Calendar. 2. Swipe quickly between the dates on the Main Screen. 3. The app crashes, showing the error “Invalid item position 9.” 4. Device Google Pixel 9 Pro, Android 15, SDK 35, Release 15                                                                                                       | The app should allow the user to swipe between dates without crashing         | The app crashes when swiping quickly between dates                                                   | High         |
