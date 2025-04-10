# 🎟️ Dicoding Events App
A modern **Kotlin** app for browsing and searching events using **MVI architecture** and **Ktor Client** for networking.  

## ✨ Features
✅ Browse events  
✅ Search events with **debounce** support  
✅ View detailed event information  
✅ Smooth **navigation animations**  
✅ Offline caching with **Room**  
✅ Uses **MVI (Model-View-Intent) architecture**  
✅ Built with **Jetpack Compose & Kotlin Coroutines**  

## 📌 Tech Stack
- **Jetpack Compose** – Modern UI toolkit for declarative UI  
- **Ktor Client** – Lightweight networking library
- **Room** – Local database for offline support  
- **Hilt (Dagger-Hilt)** – Dependency injection  
- **StateFlow & MVI** – Unidirectional data flow  
- **Type-Safe Navigation** – Manages app navigation  
- **Kotlin Coroutines** – Asynchronous programming  

## 🚀 Setup & Installation  
1. Clone this repository:  
   ```bash
   git clone https://github.com/achmadichzan/Dicoding_Events.git
   cd dicoding-events
   ```  
2. Open the project in **Android Studio (Meerkat or newer)**
3. Remember to add the **BASE_URL** on your **local.properties** (BASE_URL=https://event-api.dicoding.dev/)
4. Connect an **Android Device**  
5. Run the app! 🎉  

## 📸 Screenshots
<table>
  <tr>
    <th>Event List</th>
    <th>Event Detail</th>
    <th>Search Feature</th>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/2f5185f0-9142-4940-aa56-2cf7f44b6098" width="320"/></td>
    <td><img src="https://github.com/user-attachments/assets/65708ae1-f617-417f-b3b2-5921c42f1408" width="320"/></td>
    <td><img src="https://github.com/user-attachments/assets/51e52242-156c-4ba8-b11e-08cdc0bdf516" width="320"/></td>
  </tr>
</table>

## 📜 Architecture
This app follows the **MVI (Model-View-Intent) architecture**, ensuring a predictable state-driven UI:  
- **View (Composable UI)** → Sends user **Intents**  
- **ViewModel** → Processes intents & updates **StateFlow**  
- **Repository** → Fetches data using **Ktor Client**  

## 📬 API Reference
This app interacts with the **Dicoding Events API**:  
- **Base URL** `GET https://event-api.dicoding.dev/`

## 🛠️ Future Enhancements
- [ ] **Loading UI** for smooth loading  
- [ ] **Favorite Event support** with Room database  
- [ ] **Dark Mode support** with Preferences 

## 🤝 Contributing
Contributions are welcome! Fork the repo, create a feature branch, and submit a **Pull Request**.

# Big Thanks to Dicoding Academy 🎉
