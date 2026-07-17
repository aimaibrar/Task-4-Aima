# System Vulnerability Checklist

A simple Java desktop application that helps users identify common security weaknesses in their systems. The application evaluates password strength, software update practices, and unsafe user behavior, then generates an overall risk assessment.

## Project Overview

Cybersecurity threats often arise from weak passwords, outdated software, and risky online habits. This project provides a basic self-assessment tool that allows users to evaluate these areas and understand their security posture through an easy-to-use graphical interface built with Java Swing.

## Features

### Password Strength Analysis

The application evaluates passwords based on:

* Password length
* Uppercase and lowercase letters
* Numbers
* Special characters
* Comparison against a list of commonly leaked passwords

### Software Update Assessment

Users answer questions related to:

* Automatic updates
* Time since the last operating system update
* Application update habits

### Unsafe User Practices Detection

The tool identifies potentially dangerous behaviors, including:

* Reusing passwords across multiple accounts
* Clicking unknown links
* Using public Wi-Fi without a VPN
* Sharing passwords with others
* Leaving devices unlocked

### Final Risk Report

The application combines all assessments and generates:

* A total risk score
* A security verdict:

  * Low Risk
  * Moderate Risk
  * High Risk

## Technologies Used

* Java
* Java Swing
* Object-Oriented Programming (OOP)

## Requirements

* JDK 8 or newer
* IntelliJ IDEA or any Java IDE

## Project Files

| File            | Description                                  |
| --------------- | -------------------------------------------- |
| `task4.java`    | Console-based version of the application     |
| `task4GUI.java` | Graphical user interface version using Swing |

## How to Use

1. Open the **Password** tab and enter a password.
2. Click **Check Password** to analyze its strength.
3. Open the **Software Updates** tab and answer the questions.
4. Open the **User Practices** tab and select the habits that apply.
5. Navigate to the **Report** tab.
6. Click **Generate Final Report** to view your risk score and verdict.

## Risk Score Guide

| Score | Verdict       |
| ----- | ------------- |
| 0–2   | Low Risk      |
| 3–5   | Moderate Risk |
| 6+    | High Risk     |

## Project Objectives

* Promote cybersecurity awareness.
* Help users recognize unsafe digital habits.
* Demonstrate Java programming and GUI development skills.
* Provide a simple security self-assessment tool.

## Future Improvements

* Analyze real system information such as OS patch status and open ports.
* Integrate the Have I Been Pwned API for password breach detection.
* Export reports as PDF or text files.
* Save assessment history for future comparison.

## Disclaimer

This application is intended for educational purposes as part of the DecodeLabs Internship Program. It is a basic self-assessment tool and should not be considered a complete security audit solution.

---

**Developed by Aima Ibrar**
**DecodeLabs Internship Project**
