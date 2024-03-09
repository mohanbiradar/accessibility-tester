package com.example.accessibility_tester.service;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.example.accessibility_tester.model.AccessibilityViolation;
import com.example.accessibility_tester.model.TestResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessibilityTester {

    private WebDriver driver;

    public AccessibilityTester() {
        // Initialize WebDriver

    }

    public List<TestResult> runWCAGTests(String url) {
        return runTests(url);
    }


    private List<TestResult> runTests(String url) {
        List<TestResult> testResults = new ArrayList<>();
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        try {
            // Open the web page
            driver.get(url);
            // Perform accessibility checks using Axe Java
            AxeBuilder builder = new AxeBuilder();
//            builder.withTags(Collections.singletonList(guideline));
            Results axeResult = builder.analyze(driver);
            ObjectMapper objectMapper = new ObjectMapper();
            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/data.json");
            String data = objectMapper.writeValueAsString(axeResult);
            fileWriter.write(data);
            System.out.println(axeResult);
            // Process the result and determine if there are any violations
            TestResult testResult = new TestResult();
            testResult.setUrl(url);
            testResult.setViolations(parseViolations(axeResult));
            testResults.add(testResult);
        } catch (WebDriverException e) {

            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // Close the WebDriver session
            driver.quit();
        }
        return testResults;
    }

    private List<AccessibilityViolation> parseViolations(Results axeResult) {
        List<AccessibilityViolation> violations = new ArrayList<>();
        for (Rule violation : axeResult.getViolations()) {
            AccessibilityViolation accessibilityViolation = new AccessibilityViolation();
            accessibilityViolation.setId(violation.getId());
            accessibilityViolation.setUrl(violation.getUrl());
            accessibilityViolation.setHelpUrl(violation.getHelpUrl());
            accessibilityViolation.setHelp(violation.getHelp());
            accessibilityViolation.setTags(violation.getTags());
            accessibilityViolation.setDescription(violation.getDescription());
            accessibilityViolation.setNodes(violation.getNodes());
            accessibilityViolation.setImpact(violation.getImpact());
            violations.add(accessibilityViolation);
        }
        return violations;
    }
}


