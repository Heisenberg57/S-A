# Selenium Basics – Day 26

This project demonstrates:
- Selenium WebDriver setup using Java
- TestNG test structure
- Browser launch and validation
- Proper setup and teardown

Tools used:
- Java
- Selenium WebDriver
- TestNG
- Maven

#Automation fundamentals : Locators basics.
---
  
1)Find elements reliably

2)Type text (sendKeys)

3)Click buttons

4)Validate something on the page


Mental Model - 
----

1) Go to a page
   
2) Locate elements
   
3) Interact (Type/click)
   
4) Observe result
   
5) Assert

Important Concepts
--

1. findElement - Finds one element, Throws exception if not found.
   
2. sendKeys - Types like a human.
   
3. submit - submits a form

4. getTitle - Reads UI state for validation
   
5. Assertion - Confirms behaviour, not just action


Automation fundamentals : WAITS
---------------------------

Points to remember :

1. Why Selenium is faster than the browser.
   
2. Why elements exist but are "not ready"
   
3. Difference between implicit wait and explicit wait
   
4. How to fix flakeyness correctly.

The Core Problem -
--

Selenium executes code like this.
driver.findElement(...).click();

But the browser is 

loading

rendering

running JS

animating

fetching data

Selenium is too fast.

So selenium says :

I cant find / click this element yet and this is where wait comes in.

The Worst Solution - 

Thread.sleep(5000);
--

why thread.sleep is bad

Always waits full time(even if element is ready earlier)
Makes tests slow
Fails on slow machines

Sleeps = blind waiting

TYPES OF WAITS
--

Implicit Wait
-

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

Meaning : For every findElement, wait up to 10 seconds.

Implicit waits are 
- easy
- unpredictable
- causes hidden delays
- not recommended in modern frameworks  Many Teams avoid implicit waits


Explicit waits 
--

WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
wait.until(ExpectedConditions.elementToBeClickable(locator));

Meaning :

Wait ONLY until this condition is met.

Precise. Clean. Reliable.


Most Used ExpectedConditions:

elementToBeClickable

visibilityOfElementLocated

presenceOfElementLocated

titleContains

urlContains


Automation fundamentals : Handling Dynamic Elements & Sychronization.
--

An Good automation engineer should be able to handle:

1. Elements that appear after clicking

2. Messages that load with delay

3. Dropdowns that update dynamically

4. The difference present, visible and clickable


What "dynamic" truly means 

A dynamic element is one that :

1) is not immediately available.
2) is added/updated by Javascript
3) changes state after user action

Examples:

1) success / error messages.
2) loading spinners.
3) dropdown options
4) enable/disable buttons.


CASE 1 - Dynamic Message After Login.(Reinforce Good Practise).

wait.until(ExpectedConditions.visibilityOfElement(By.id("flash")));

Why this matters:

1) element exists only after login
   
2) visibility tells you UI is ready
   
3) assertion becomes stable


CASE 2 - Dynamic Content (Appears After Click)

Page : https://the-internet.herokuapp.com/dynamic_loading/1

Behaviour:

1) Click "Start"
 
2) Loader shows
   
3) Text appears after delay


Incorrect conclusion : Element is not found - selenium issue

Correct conclusion : I must wait for the correct condition


We waited for:

visibility, not just presence
actual UI readiness

Key ExpectedConditions (Reinforce)

1) presenceOfElementLocated - element exists in DOM

2) visibilityOfElementLocated - element is visible

3) elementToBeClickable - element can be clicked

4) invisibilityOfElementLocated- loaders disappear


CASE 3 : Enable/Disable Elements

Page : https://the-internet.herokuapp.com/dynamic_controls

Behaviour :

Checkbox removed/added dynamically

Button becomes enabled later


Automation fundamentals : Alerts, Popups & Browser Controls
--

1) JavaScript alerts (alert/confirm/prompt)
2) Accepting / dismissing popups
3) Browser navigation (back/forward/refresh)
4) Understanding context ( DOM vs Alert vs Window)

Core Concept (Very Important)
--

Selenium can only talk to one context at a time.

Contexts include

Web Page (DOM)
Alert popup
Browser window tab


If you dont switch context - Selenium throws error



Part 1 : JavaScript Alerts (Most Common)
--

https://the-internet.herokuapp.com/javascript_alerts


Simple Alert (alert())

Behaviour :

Click button
Alert Appears
Must click OK

In code: 
1)get url
2)find JS alert by xpath and click on it
3) create alert object and define - driver.
4) get alert text
5) click on alert accept
6) get string result text
7) assert it

Key rule
--

If an alert is present:

Selenium cannot interact with the page

You MUST handle the alert first

Otherwise → UnhandledAlertException


Part 2 : Confirm Popup (confirm())

Confirm dialogs have:

OK
Cancel

In code example:

1) get url
2) find the locator for JS confirm and click on it.
3) Create an alert object
4) click on dismissing
5) find locator for result and get text
6) assert the text result


PART 3 — Prompt Popup (prompt())
--

Prompt allows typing text.

1) get url
2) find the locator for prompt alert and click on it.
3) Create an alert object
3) send keys "automation"
4) use accept function
5) find locator for result and get text
6) assert the text result


Part 4 - Browser Navigation (Very Real)
--

Used in :
Back Button flows
Multi-page journeys
Retry Scenarios

steps:
--

1) get url
2) Find locator for Form Authentication and perform click action
3) then navigate to back
4) then navigate forward
5) then navigate refresh
6) Assert the title text


Part 5 : Multiple Windows/Tabs

Page: https://the-internet.herokuapp.com/windows

Steps:

1)Create a String variable for parentWindow.
2)for loop - switch to particular window
3)assert if we are on the selected window
4)switch back to parent window

Automation fundamentals : Page Object Model (POM) Why Frameworks Exist
--

1) Why mixing locators + test logic is a problem
2) What a Page Object actually represents 
3) How POM reduces breakage when UI changes
4) How tests become readable and boring


The real problem POM solves 
--

Problems:

1) Locators are everywhere
2) UI Change = edit 20 files
3) Tests are unreadable
4) High maintenance cost

Frameworks exists to reduce pain, not add complexity

The core idea of POM :
--

Each Page : one class
Each user action : one method

Thats it. Nothing more mystical.


step - 1
--

Page/site we are using for example.

https://the-internet.herokuapp.com/login

This page has

username field
password field 
login button
result message

step -2
--

steps in the actual example:

1) create a loginPage class - representating login page
2) for locators create private variables
3) create a constructor for the class
4) For page actions - write separate method :
	1) enter username 
	2) enter password
	3) click login
	4) get flash messge
	
importants points to remember : 

1. No @Tests here
2. No Assertions
3. Only page behaviour

step 3 : Write a clean Test using the page.
--

1) create a logintest class.
2) create driver object, and a loginpage object
3) write @beforemethod setup()
4) write actual test loginwith validCredentials()
		1) enter username 
		2) enter password
		3) click login
		4) take flash message as string variables
		5) assert that message using AssertTrue
5) write @aftermethod , write teardown() logic


Best parts about this approach:
-

1) Tests now read like english sentences.
2) UI changes are isolated.
3) Tests focus on behaviour, not behaviour


What lives where:
-

Test - What to test
Page - How to interact
Selenium - How browser works


Automation Fundamentals : BaseTest & Driver Management 
--

Key points 
--

- Remove duplicate/teardown code
- Centralize WebDriver Lifecycle
- Make tests thinner and cleaner
- Prepare ground for parallel runs & configs

This is what seniors expect next after DOM.

Right now, every test has this.
- @BeforeMethod and @AfterMethod 

Problems with these:
--

1) Duplicated everywhere
2) error-prone
3) hard to change browser later
4) ugly when test count grows

The core idea indicates that : All tests share the same setup logic > move it to a base class.

Tests will inherit setup instead of re writing it.

Step 1 : BaseTest.java
--

1) create class BaseTest
2) create protected object of WebDriver - driver
3) Write @BeforeMethod for setup
4) Write @AfterMethod for teardown

Important points :
--

protected - child tests can access driver

setup/teardown live once

safety check before quit()

Step 2 : Update your Test to extend BaseTest and keep only @Test part remove @BeforeMethod & @AfterMethod
--

public class LoginTest extends BaseTest {


This will Make things easier - 
No setup code here
No teardown code here
Test focuses ONLY on behaviour

- That is professional automation

Why This design is POWERFUL & BEAUTIFUL
--

1) One place to change the browser
2) Easier Debugging
3) Clean Tests

How this Scales in Real Projects
--

browser choice (config-driven)

implicit waits (if any)

screenshots on failure

logging setup


Automation fundamentals : WaitUtils
--

Problems with adding waits randomely in the script.

1) Duplicated everywhere
2) Inconsistent timeouts
3) Hard to change globally
4) Pages look noisy 

**Core Idea being that** - Waiting is infrastructure , not page logic  --> centralize it.
Pages should say what they want, not how to wait.


STEP 1 - Create WaitUtils
--

path - src/test/java/WaitUtils.java


Steps:

1. Create public class waitutils
2. Create two objects - WebDriver and WebdriverWait
3. Create Constructor WaitUtils that adds a 10 seconds wait
4. Create waitForVisibility , waitForClickability,waitForInvisibility methods with locator parameter & 
waitForTitleContains method with text parameter
5. And return meaningful things - WebElement, boolean
6. Clean Readable API


Step 2 : Inject WaitUtils into Page Objects.
--

Before: LoginPage.java code had wait code added randomely.

After : clean and professional , add wait as utils.

This approach improves a lot of stuff - 

Pages are readable
One timeout to rule them all
Consistent waits


Automation Fundamentals : Screenshots on failure
--

When test fails on:

1) Jenkins
2) CI
3) someone else's machine

You wont see the browser

Your first question will be :

What did UI look like when it failed - Screenshots answer this instantly

Core Idea - Simple
--

When a test fails - automatically captures screenshot - save it

this I should happen:

1) without changing test code
2) without adding try/catch everywhere
Thats where test NG Listeners come into picture



Step 1 - Create Screenshot Utility
--

 - src/test/java/utils/
 
Notes - 

Static Utility - easy reuse
Timestamp - avoids overwrite
Folder auto - created


Step 2 - Create TestNG Listener
--

TestListener.java

Listner gets test instance 
Cast to BaseTest
Access driver
Take screenshot

Step 3 - Register Listener
--
Use @Listener

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {
    // tests
}


Automation Fundamentals : DriverFactory
--

As of now, browser creation is here

driver = new ChromeDriver();

problems:
--

1. Hardcoded browser
2. Cant switch without code change
3. CI / Jenkins cant control it
4. BaseTest becomes bloated over time


Every real framework solves this with a DriverFactory.

BasTest should ask for driver, not decide how its created

Creation Logic Goes to driverfactory.


Step 1 Create DriverFactory.java
--

src/test/java/factory/DriverFactory.java

- Single responsibility : create driver
- no TestNG here
- no BaseTest here
- simple & readable





























