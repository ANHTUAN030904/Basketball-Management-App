# 🏀 Basketball Management App (NBAfx)

A desktop application that provides a **Graphical User Interface (GUI)** for managing basketball teams, players, and tournaments.  
Built using **MVC architecture**, it allows users to create teams, manage players, and simulate a season in an interactive way.

---

## ✨ Features

### 🏠 Main Menu
- **Explore Teams** – navigate to the team management section.  
- **Arrange a New Season** – start a new tournament and manage season rounds.  
- **Exit** – quit the application.  

---

### 👥 Team Management
- **Teams Table**  
  - Displays: Team Name, Number of Players, Average Credit, and Average Age.  
  - Add, manage, or delete teams.  
  - Prevents duplicate team names with error messages.  

- **Manage Team Window**  
  - View and edit players in a team.  
  - Add, update, or delete players.  
  - Edit team names and save changes.  

- **Player Update / Add Window**  
  - Update existing player details (name, credit, age, number).  
  - Add new players with default values.  
  - Input validation ensures proper formatting.  

- **Error Handling**  
  - Dedicated error window with clear messages.  
  - Validates names, numbers, and decimal values.  

---

### 🏀 Players View
- Displays all players across teams in a sortable table.  
- Filters by **name, level, and age** (supports partial matches).  
- Highlights selected players.  

---

### 📅 Season Management
- **Season Round Window** – arrange teams into rounds, create matchups.  
- **Current Round Teams** – view current round matchups (e.g., "Team A vs Team B").  
- **Game Window** – simulate tournament games and display results.  
- **Record Window** – view season history, including winners and losers by round.  

---

## 🛠️ Technical Details
- **Languages**: Java (JavaFX) or Python (Tkinter).  
- **Architecture**: Model–View–Controller (MVC).  
- **UI Layout**: JavaFX FXML / Tkinter GUI.  
- **Data Binding**: Observable properties keep UI synced with model changes.  
- **Styling**: Custom CSS included for consistent UI.  

---

## 🚀 Getting Started

### Clone the Repository
```bash
git clone git@github.com:ANHTUAN030904/Basketball-Management-App.git
cd Basketball-Management-App
