# 🎟️ Dicoding Events App  
A modern **Kotlin Multiplatform** app for browsing and searching events using **MVI architecture** and **Ktor Client** for networking.  

## ✨ Features  
✅ Browse upcoming events  
✅ Search events with **debounce** support  
✅ View detailed event information  
✅ Smooth **navigation animations**  
✅ Uses **MVI (Model-View-Intent) architecture**  
✅ Built with **Jetpack Compose & Kotlin Coroutines**  

## 📌 Tech Stack  
- **Jetpack Compose** – Modern UI toolkit for declarative UI  
- **Ktor Client** – Lightweight networking library  
- **Hilt (Dagger-Hilt)** – Dependency injection  
- **StateFlow & MVI** – Unidirectional data flow  
- **Navigation Component** – Manages app navigation  
- **Kotlin Coroutines** – Asynchronous programming  

## 🚀 Setup & Installation  
1. Clone this repository:  
   ```bash
   git clone https://github.com/your-username/dicoding-events.git
   cd dicoding-events
   ```  
2. Open the project in **Android Studio (Giraffe or newer)**  
3. Connect an **Android Emulator or Device**  
4. Run the app! 🎉  

## 📸 Screenshots  
| Event List | Event Detail | Search Feature |
|------------|-------------|---------------|
| ![List](![event_list](https://github.com/user-attachments/assets/d7b2725d-7a9a-4d60-9293-dfb3440d351d)
) | ![Detail](![event_detail](https://github.com/user-attachments/assets/07b1d7b4-3227-4a6a-a7f0-19b0a83d4e21)
) | ![Search](![search_feature](https://github.com/user-attachments/assets/7102c276-7c30-4480-9c45-7537d0478ffc)
) |  

## 📜 Architecture  
This app follows the **MVI (Model-View-Intent) architecture**, ensuring a predictable state-driven UI:  
- **View (Composable UI)** → Sends user **Intents**  
- **ViewModel** → Processes intents & updates **StateFlow**  
- **Repository** → Fetches data using **Ktor Client**  

## 📬 API Reference  
This app interacts with the **Dicoding Events API**:  
- **Get All Events:** `GET https://event-api.dicoding.dev/events`  
- **Search Events:** `GET https://event-api.dicoding.dev/events?q={keyword}`  
- **Get Event Detail:** `GET https://event-api.dicoding.dev/events/{id}`  

## 🛠️ Future Enhancements  
- [ ] **Offline support** with Room database  
- [ ] **Dark Mode support**  
- [ ] **User authentication** for event registration  

## 🤝 Contributing  
Contributions are welcome! Fork the repo, create a feature branch, and submit a **Pull Request**.  
