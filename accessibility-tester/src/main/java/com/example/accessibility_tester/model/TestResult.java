package com.example.accessibility_tester.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {
    private String url;
    private List<AccessibilityViolation> violations;
}
