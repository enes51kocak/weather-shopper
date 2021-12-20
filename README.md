# weather-shopper-preview

* This project contains the end to end UI test scenario for 'https://weathershopper.pythonanywhere.com/' <br>
* It is created using Cucumber framework with JUnit <br>
* This project can be executed on Chrome, Firefox, Opera and Headless-Chrome however make sure you have the specific browser you want to run on  you local machine<br>
* If you want to change the browser you want to run, go to 'configuration.properties' file and change the browser name to followings;  <br>
      -- chrome<br>
      -- firefox<br>
      -- opera<br>
      -- chrome-headless<br>
* The project can be run via Runner class located at 'src/test/java/runner/Runner.java' or via maven command 'mvn clean verify' <br>
* After execution a cucumber html report and a cucumber json report will be generated at reports folder <br>
