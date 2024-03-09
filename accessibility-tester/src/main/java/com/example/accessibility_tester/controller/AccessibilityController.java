package com.example.accessibility_tester.controller;

import com.example.accessibility_tester.model.TestResult;
import com.example.accessibility_tester.service.AccessibilityTester;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AccessibilityController {

    private AccessibilityTester accessibilityTester;

    public AccessibilityController() {
        this.accessibilityTester = new AccessibilityTester();
    }

    @GetMapping("/report")
    public String showReport() {
        return "report"; // Thymeleaf template name
    }

    @PostMapping("/testAccessibility")
    public String testAccessibility(
            @RequestParam String url,
            Model model) {
        List<TestResult> testResults = new ArrayList<>();
        testResults.addAll(accessibilityTester.runWCAGTests(url));
        model.addAttribute("testResults", testResults);
        return "report";
    }
}

