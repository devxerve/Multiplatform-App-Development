# Academy Management System

## 🎓 Overview
Java application for managing **IT and Education classrooms** with automatic capacity enforcement and student relocation tracking. **100% Javadoc Compliance** - automatic documentation fully supported.

## 🚀 Quick Start

```bash
# Clone & compile
javac academy/*.java

# Run
java academy.Academy
```

**Generate Javadoc:**

```bash
javadoc -d docs -sourcepath . -subpackages academy
```


## 📋 Features

- ✅ Interactive console menu (4 options)
- ✅ Dual classroom types with specific capacity rules
- ✅ **Automatic student relocation** with real-time feedback
- ✅ Sequential unique IDs (I0001, E0001...)
- ✅ Robust input validation + exception handling
- ✅ Dynamic ArrayList storage
- ✅ **Static counters** for totals tracking
- ✅ **Javadoc Compliance** - full automatic docs


## 🏗️ Project Structure

```
src/
└── academy/
    ├── Academy.java     (Main + Menu Logic)
    ├── It.java          (IT Classrooms)
    └── Education.java   (Education Classrooms)
```


## 📊 Capacity Specifications

| Classroom Type | Surface | Capacity Formula       |
| :------------- | :------ | :--------------------- |
| **IT**         | 24m²    | `students ≤ pcs`       |
| **Education**  | 36m²    | `surface/students > 1` |

## 🎮 User Workflow

```
1. CREATE IT → computers (0=12), students
   ↓ (auto-relocates if students > pcs)
2. CREATE EDUCATION → students
   ↓ (auto-relocates if 36/students ≤ 1)
3. VIEW SUMMARY → All classrooms listed
4. EXIT → "Total relocated students: X"
```


## 🔧 Key Components

### Static Global Tracking

```java
// Per-class totals
 It.counter              // IT classrooms created
 It.relocatedStudents    // IT students relocated
 Education.counter       // Education classrooms  
 Education.relocatedStudents // Education students relocated
```


### Input Validation
- `readInt(Scanner)` → Loops until positive integer
- Handles `InputMismatchException`
- Rejects negatives/zero


## 📈 Sample Output

```
Enter option (1-4):
1) Create IT classroom
Computers: 10 | Students: 15
***Capacity exceeded I0001***
Relocating... Final: 10 students
"The It class I0001 has 10 students, 10 computers, 24m²"

4) Exit → "Students to relocate = 5"
```


## 🛡️ Technical Highlights

- **Encapsulation**: Private fields, public getters
- **Static utilities**: Shared counters across instances
- **Exception-safe**: No crashes on bad input
- **Memory efficient**: Dynamic ArrayLists
- **Self-documenting**: Complete Javadoc coverage


## 📚 Documentation

**Full Javadoc Compliance** - Run `javadoc` command for complete HTML API docs.

**Method-level documentation**, **param/return tags**, **exception handling** all implemented.

## 👨‍💻 Author

**Javier Cervera Rodríguez**
**Multiplatform App Developer Student** | Universidad Francisco de Vitoria
`aviercerverarodriguez@gmail.com`

## 📅 Release

**v1.0** | January 8, 2026

---

**🏆 Production-ready • Javadoc Compliant • Multiplatform App Developer Project**
