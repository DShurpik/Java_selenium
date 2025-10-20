# Java_selenium
1. To start test use a command mvn clean test -DsuiteXml="suite name" -Dconfig="property name"
2. Has been created a driver factory for creating a driver
3. Have implemented possibility to change the browser in terminal by using the command -Dbrowser="browser name".
   Default browser is chrome.
   - Example: mvn clean test -DsuiteXml="suite name" -Dconfig="property name" -Dbrowser="firefox"
   - Available browsers: chrome, firefox
4. Have implemented possibility to make an allure report by using the command mvn allure:report or mvn allure:serve
5. Have added a logger, can read logs from the console and from the file target/logs/log.out
6. Have added a retry mechanism for flaky tests
7. Have added a screenshot for failed tests
8. Have added a thread local for driver to make tests run in parallel
9. Have added a config properties file to store test data
10. Have added a ConfigProvider interface to read data from the config properties file
11. Have added a possibility to use different environments by using the command -Denv="environment name".
    Default environment is "dev".
    - Example: mvn clean test -DsuiteXml="suite name" -Dconfig="property name" -Denv="stage"
    - Available environments: dev, test, prod
