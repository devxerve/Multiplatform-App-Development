Array Parity Generator
🎲 Overview

Java application for generating odd and even number arrays with automatic sizing, statistical analysis, and GCD calculation. 100% Javadoc Compliance - complete automatic documentation.
🚀 Quick Start

bash
# Compile
javac Generator.java

# Run
java Generator

Generate Javadoc:

bash
javadoc -d docs Generator.java

📋 Features

    ✅ Interactive size input with robust validation

    ✅ Auto-sized even array based on odd array minimum

    ✅ Random number generation (odds: 5-50, evens: 1-size)

    ✅ Complete array statistics (min/max per array)

    ✅ Euclidean GCD calculation between even extremes

    ✅ Exception-safe input handling

    ✅ Visual dividers for clear output separation

    ✅ Javadoc Compliance - full method documentation

🏗️ Project Structure

text
Generator.java  (Main + All Logic)
├── Constants: INI_INT, DIVIDER
├── Input: readInt()
├── Generation: numGen(), fillOdds(), fillEvens()
├── Analysis: smallerNumber(), greaterNumber(), gcd()
└── Display: showArray()

📊 Array Specifications
Array Type	Range	Generation Logic
Odds	5-50	Random until odd (do-while loop)
Evens	1-array_size	Random until even (do-while loop)
🎮 User Workflow

text
1. Input array size → odds_size
2. Generate odds array (5-50 range)
3. Auto-size evens → odds minimum value
4. Generate evens array (1-evens_size)
5. Display both arrays + statistics
6. Calculate GCD(evens_min, evens_max)

🔧 Key Components
Constants & Utilities

java
static final int INI_INT = 0;      // Zero initializer
static final String DIVIDER = "\n--------------------";  // Visual separator

Input Validation

java
public static int readInt(Scanner input) {
    // Catches InputMismatchException
    // Returns 0 on invalid input
    // Loops until valid positive integer
}

Smart Array Sizing

java
evens_size = smallerNumber(odds);  // Dynamic sizing

📈 Sample Output

text
Please, type the size of the Array you want to create
5
--------------------
Array containing odd numbers
[0]=17 | [1]=23 | [2]=41 | [3]=7 | [4]=35 | 
--------------------
Array containing even numbers
[0]=2 | [1]=4 | [2]=2 | [3]=6 | [4]=4 | [5]=2 | [6]=2 | 
--------------------
The greatest common divisor of 2 and 6 is: 2

🛡️ Technical Highlights

    Encapsulation: Static utility methods

    Exception Handling: InputMismatchException caught

    Algorithmic: Euclidean GCD implementation

    Memory Efficient: Fixed-size arrays

    Self-documenting: 100% Javadoc coverage

    Robust: Invalid input gracefully handled

📚 Documentation

Full Javadoc Compliance - Run javadoc command for complete HTML API docs.

Every method documented with @param, @return, and algorithm descriptions.
👨‍💻 Author

XervE
Multiplatform App Developer Student | xerve.dev@gmail.com
📅 Release

v1.0 | January 17, 2026

🏆 Production-ready - Javadoc Compliant - Array Generation & Analysis System