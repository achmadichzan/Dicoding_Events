# 🎟️ Dicoding Events App  
A modern **Kotlin** app for browsing and searching events using **MVI architecture** and **Ktor Client** for networking.  

## ✨ Features  
✅ Browse events  
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
- **Type-Safe Navigation** – Manages app navigation  
- **Kotlin Coroutines** – Asynchronous programming  

## 🚀 Setup & Installation  
1. Clone this repository:  
   ```bash
   git clone [https://github.com/achmadichzan/Dicoding_Events.git]
   cd dicoding-events
   ```  
2. Open the project in **Android Studio (Meerkat or newer)**
3. Remember to add the **BASE_URL** on your **local.properties** (BASE_URL=https://event-api.dicoding.dev/)
4. Connect an **Android Emulator or Device**  
5. Run the app! 🎉  

## 📸 Screenshots  
<table>
  <tr>
    <th>Event List</th>
    <th>Event Detail</th>
    <th>Search Feature</th>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/d7b2725d-7a9a-4d60-9293-dfb3440d351d" width="320"/></td>
    <td><img src="https://github.com/user-attachments/assets/07b1d7b4-3227-4a6a-a7f0-19b0a83d4e21" width="320"/></td>
    <td><img src="https://github.com/user-attachments/assets/7102c276-7c30-4480-9c45-7537d0478ffc" width="320"/></td>
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
- **Get All Events:** `GET https://event-api.dicoding.dev/events`  
- **Search Events:** `GET https://event-api.dicoding.dev/events?q={keyword}`  
- **Get Event Detail:** `GET https://event-api.dicoding.dev/events/{id}`  

## 🛠️ Future Enhancements  
- [ ] **Loading UI** for smooth loading  
- [ ] **Offline support** with Room database  
- [ ] **Dark Mode support** with Preferences 

## 🤝 Contributing  
Contributions are welcome! Fork the repo, create a feature branch, and submit a **Pull Request**.  
