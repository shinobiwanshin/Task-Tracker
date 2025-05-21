# ✅ Task Tracker CLI — Spring Boot

A simple **command-line tool** built using **Java + Spring Boot** to help you track your tasks and manage your to-do list. Tasks are saved locally in a JSON file (`task.json`) — no external dependencies or database required.

---

## 🚀 Features

- ✅ Add, update, delete tasks
- 🟡 Mark tasks as in-progress
- 🟢 Mark tasks as done
- 📄 List all tasks or by status
- 💾 Data saved locally in `task.json`
- 🔒 No external libraries or frameworks used

---

## 📦 Build & Run

### 🧱 Prerequisites

- Java 17+
- Maven

### 🛠️ Build the Project

```bash
./mvnw clean package
```
### 🧪 Run Commands 
```bash
# Add a new task
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar add "Buy groceries"

# Update a task by ID
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar update 1 "Buy groceries and cook dinner"

# Delete a task by ID
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar delete 1

# Mark a task as in progress
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar mark-in-progress 1

# Mark a task as done
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar mark-done 1

# List all tasks
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list

# List tasks by status
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list todo
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list in-progress
java -jar target/tasktracker-0.0.1-SNAPSHOT.jar list done

```
