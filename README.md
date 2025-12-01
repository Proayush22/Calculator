# Android Polynomial Calculator

This project is an Android-based custom calculator designed to handle polynomial expressions, exponents, variables, and standard arithmetic. The UI features a clean dark theme with color‑coded function buttons.

---

## 📱 App Screenshots

### **Main Calculator UI**
![Calculator UI](dbad3cbf-d8ea-49e6-abd5-8dd57e84119d.png)

### **App Icon**
![Calculator Icon](bb1a6622-6d5d-4b70-a7f2-30b7d427085e.png)

---

## ✨ Features
- Integer and variable (x) input
- Supports exponent notation such as `^(2)` or `^(n)`
- Arithmetic operations: addition, subtraction, multiplication
- Polynomial multiplication and simplification
- "Ans" button for recalling previous results
- Clear (`AC/CE`) and delete functionality
- Outputs expressions in simplified polynomial form

---

## 🚀 How It Works
The calculator parses input expressions into tokens, identifies coefficients and powers, performs multiplication and combination of like terms, then sorts and formats the result.

Key elements include:
- `updateScreen()` — manages number and variable input
- `updateSign()` — handles operator input
- `calculate()` — core logic for polynomial computation
- `setPower()` — extracts coefficients and degrees from expression tokens

---

## 🧩 Project Structure
```
MainActivity.java
res/
  layout/activity_main.xml
  drawable/
  values/
```

---

## 🛠️ Technologies
- **Java** (Android development)
- **Android XML** for UI layout

---

## 📄 Source Code (MainActivity.java)
A full copy of the `MainActivity.java` implementation used in this project:

```java
[Place your full MainActivity.java code here if desired]
```

---

## 📬 Contact
If you want to expand this project (graphing, calculus tools, better token parsing), feel free to reach out!

