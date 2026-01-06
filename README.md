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












