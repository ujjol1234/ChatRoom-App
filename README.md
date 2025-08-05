# ChatRoom App 📱💬

A modern real-time chat application built with **Android Jetpack Compose** and **Firebase**, enabling users to create chat rooms and communicate seamlessly across multiple devices.

## 🎥 Demo Video

*Watch the app in action with two phones communicating with each other:*


[![Demo Video](https://img.youtube.com/vi/vP-jHtImXB0/0.jpg)](https://youtu.be/vP-jHtImXB0)


*This section will showcase:*
- User registration and login on both devices
- Creating and joining chat rooms
- Real-time message exchange between two phones
- Live synchronization of messages across devices

## ✨ Features

### 🔐 Authentication
- **User Registration**: Create new accounts with email and password
- **User Login**: Secure authentication using Firebase Auth
- **User Profiles**: First name and last name support

### 💬 Chat Functionality
- **Real-time Messaging**: Instant message delivery using Firebase Firestore
- **Multiple Chat Rooms**: Create and join different chat rooms
- **Room Management**: Add new chat rooms dynamically
- **Message History**: Persistent message storage and retrieval
- **User Identification**: Messages show sender information and timestamp

### 📱 Modern UI/UX
- **Jetpack Compose**: Modern declarative UI framework
- **Material Design 3**: Clean and intuitive interface
- **Responsive Design**: Optimized for different screen sizes
- **Real-time Updates**: Live data synchronization across all connected devices

## 🏗️ Architecture

The app follows **MVVM (Model-View-ViewModel)** architecture pattern with the following components:

### 📂 Project Structure
```
app/src/main/java/com/testdir/chatroomapp/
├── data/
│   ├── Injection.kt          # Dependency injection
│   ├── Result.kt             # Result wrapper for operations
│   └── User.kt               # User data model
├── Screen/
│   ├── ChatRoomListScreen.kt # Room listing and creation
│   ├── ChatScreen.kt         # Individual chat interface
│   ├── LogInScreen.kt        # User login
│   ├── SignInScreen.kt       # User registration
│   └── RoomItem.kt           # Room list item component
├── ui/theme/                 # App theming
├── ViewModels/
│   ├── AuthViewModel.kt      # Authentication logic
│   ├── MessageViewModel.kt   # Message handling
│   └── RoomViewModel.kt      # Room management
├── Repositories/
│   ├── MessageRepository.kt  # Message data operations
│   ├── RoomRepository.kt     # Room data operations
│   └── UserRepository.kt     # User data operations
├── Models/
│   ├── Message.kt            # Message data class
│   └── Room.kt               # Room data class
├── MainActivity.kt           # Entry point
└── Navigation.kt             # App navigation setup
```

### 🔧 Key Components

- **Firebase Authentication**: Secure user management
- **Firebase Firestore**: Real-time database for messages and rooms
- **Jetpack Compose**: Modern UI toolkit
- **Navigation Component**: Seamless screen transitions
- **LiveData & ViewModel**: Reactive data management

## 🚀 Getting Started

### Prerequisites

- **Android Studio** (Arctic Fox or newer)
- **Android SDK** (API level 24+)
- **Google account** for Firebase setup
- **Kotlin** support enabled

### 🔧 Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/ChatRoomApp.git
   cd ChatRoomApp
   ```

2. **Firebase Configuration**
   - Go to [Firebase Console](https://console.firebase.google.com/)
   - Create a new project or use existing one
   - Add an Android app with package name: `com.testdir.chatroomapp`
   - Download `google-services.json` and place it in `app/` directory
   - Enable **Authentication** (Email/Password provider)
   - Enable **Cloud Firestore** database

3. **Open in Android Studio**
   - Open the project in Android Studio
   - Wait for Gradle sync to complete
   - Ensure all dependencies are downloaded

4. **Build and Run**
   - Connect your Android device or start an emulator
   - Click **Run** or press `Ctrl+R` (Windows/Linux) / `Cmd+R` (Mac)

### 📱 Testing Multi-Device Communication

To test real-time communication between devices:

1. **Install on Multiple Devices**
   - Build the APK: `./gradlew assembleDebug`
   - Install on 2+ Android devices or emulators

2. **Create User Accounts**
   - Register different users on each device
   - Log in with respective credentials

3. **Join Same Chat Room**
   - Create a room from one device
   - Join the same room from other devices
   - Start messaging and observe real-time sync

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| **Kotlin** | Primary programming language |
| **Jetpack Compose** | Modern UI framework |
| **Firebase Auth** | User authentication |
| **Cloud Firestore** | Real-time database |
| **Navigation Component** | App navigation |
| **ViewModel & LiveData** | Data management |
| **Material Design 3** | UI components and theming |
| **Coroutines** | Asynchronous programming |

## 📋 App Flow

1. **Launch** → Sign Up/Login Screen
2. **Authentication** → Enter credentials or create account
3. **Chat Room List** → View available rooms or create new ones
4. **Join Room** → Enter selected chat room
5. **Real-time Chat** → Send and receive messages instantly
6. **Multi-device Sync** → Messages appear on all connected devices

## 🔒 Security Features

- **Firebase Authentication**: Secure user management
- **Data Validation**: Input sanitization and validation
- **Firestore Security Rules**: Database access control
- **Encrypted Communication**: All data transmitted securely

## 🎨 UI/UX Highlights

- **Clean Interface**: Minimalist design following Material Design principles
- **Intuitive Navigation**: Easy-to-use navigation between screens
- **Real-time Indicators**: Live message delivery status
- **Responsive Layout**: Adapts to different screen sizes
- **Dark/Light Theme**: Supports system theme preferences

---

**Built with ❤️ using Android Jetpack Compose and Firebase**
