# Interactive Java Quiz

An interactive console quiz application developed in Java. The application allows users to be asked questions, enter their answers, check results, and display the final score. It also offers advanced features such as question management by an administrator.

## 🎯 Features

You will find issues related to these features with the `java` label.

### Core Quiz Engine

- Asks questions stored in a file
- User answer input
- Automatic answer verification
- Displays the final score

### Question Loading / Saving

- Reads questions from a local file (JSON / CSV)
- Saves new questions

### Multiple Choice Questions

- Supports questions with multiple choices
- Not limited to true/false questions
- Intuitive interface for selection

### Administrator Mode

- **Add** new questions
- **Edit** existing questions
- **Delete** obsolete questions
- Dedicated interface for content management

### Advanced Features

- **Timer**: Time limit for each question
- **Scoring System**: Saves players' best scores locally
- **History**: Tracks user performance

## 🚀 Installation and Usage

### Prerequisites

- Java JDK 8 or higher
- A Java IDE (VSCode, IntelliJ IDEA, Eclipse) or a terminal

### Compilation and Execution

```bash
# Navigate to the project folder
cd src/java/quiz

# Execute directly from your Java IDE

# Or

# Compile the project
javac -d bin src/*.java

# Run the application
java -cp bin App
```

## 📁 Project Structure

```
quiz/
├── src/
│   └── App.java          # Main class
├── lib/                  # External libraries (if necessary)
├── data/                 # Question files (JSON/CSV)
├── scores/               # Score saves
└── README.md            # This file
```

## 🎮 How to Play

1. **Start the quiz**: Launch the application
2. **Answer questions**: Type your answer or select a choice
3. **Manage time**: Answer before the timer runs out
4. **Check score**: Your performance is displayed at the end
5. **Admin Mode**: Access administrator mode to manage questions

## 🔧 Administrator Mode

Administrator mode allows you to:

- Create new questions with different answer types
- Modify the content of existing questions
- Delete obsolete or incorrect questions

## 🤝 Contribution

Contributions are welcome! Feel free to participate.

## 📄 License

This project is licensed under the [Apache 2.0 license](https://github.com/Ionfinisher/30-days-of-open-source/blob/main/LICENSE). See the `LICENSE` file for more details.

## 🎓 About

This project is part of the "30 Days Of Open Source" challenge and aims to create a complete and interactive quiz application in Java.
