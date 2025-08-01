# 📦 Anna Pegova Business Manager

![Last Commit](https://img.shields.io/github/last-commit/nilmar-p/anna-pegova-business-manager?style=flat-square)
![Top Language](https://img.shields.io/github/languages/top/nilmar-p/anna-pegova-business-manager?style=flat-square)
![License](https://img.shields.io/github/license/nilmar-p/anna-pegova-business-manager?style=flat-square)

A Java Swing desktop application for managing products, customers, and sales with real-time calculation of totals, installments, and financial summaries.  
Data is stored locally using JSON files for portability and simplicity.

##✨ Features
- ✅ **Register, Edit, Delete, and Search** products and customers  
- 📦 **Manage sales** with automatic net value and installment calculations  
- 💳 **Installment tracking panel** with status control and payment registration  
- 📊 **Financial summary dashboard** for pending and completed payments  
- 📁 **Local JSON storage** for all product, customer, and sales data  
- 💾 **Backup/export support** for all data

---

## 🖥️ Tech Stack

- Java™ SE Development Kit (JDK) 24+
- Java Swing for the graphical user interface
- Jackson for JSON serialization/deserialization
- Maven for build automation

---

## 🛠️ Requirements

- ✅ Java 23 or higher installed  
- ⚙️ Java must be available in your system's PATH

---

## ▶️ How to Run

1. Download the `.jar` file from the [Releases](../../releases) section  
2. Run it with:

```bash
java -jar target/anna-pegova-stock-manager-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## ⚙️ Build Instructions (Optional)
To build the project yourself:
1. Ensure Apache Maven is installed
2. Clone the repository

3. Run:
```bash
mvn clean install
```
The generated `.jar` file will be located in `target/anna-pegova-stock-manager-1.0-SNAPSHOT-jar-with-dependencies.jar`

## 📄 License
This project is licensed under the [MIT License](./LICENSE).
