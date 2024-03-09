package com.example.accessibility_tester.model;

import com.deque.html.axecore.results.CheckedNode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AccessibilityViolation {
    private String id;
    private String description;
    private String help;
    private String helpUrl;
    private String impact;
    private List<String> tags = new ArrayList<>();
    private List<CheckedNode> nodes = new ArrayList<>();
    private String url;
}
