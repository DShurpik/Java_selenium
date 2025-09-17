# Java_selenium
1. To start test use a command mvn clean test -DsuiteXml="suite name" -Dconfig="property name"
2. Create a driver factory for creating a driver
3. Realize that it's possible to change the browser in terminal by using the command -Dbrowser="browser name".
   Default browser is chrome.
   - Example: mvn clean test -DsuiteXml="suite name" -Dconfig="property name" -Dbrowser="firefox"
   - Available browsers: chrome, firefox
4. Realize possibility to make an allure report by using the command mvn allure:report or mvn allure:serve
5. Add a logger, can read logs from the console and from the file target/logs/log.out
6. Add a retry mechanism for flaky tests
7. Add a screenshot for failed tests