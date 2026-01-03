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
