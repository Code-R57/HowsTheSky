<h1 align='center'><img src='media/AppIcon.png' width='50' align='center'> How's The Sky</h1>

<p><b>How's The Sky</b> is a Weather App made for Android devices. The app was initially developed as part of the assignment submitted for the Android Development Track of the Summer Group held by [cruX](https://github.com/crux-bphc), The Programming & Computing Club of [BITS Pilani, Hyderabad Campus](https://www.bits-pilani.ac.in/hyderabad/). </p>

<h2>Features</h2>
<ul>
    <li>Search for City</li>
    <li>Stores Recently Searched Cities</li>
    <li>Tap on the Recently Searched City to get Current Weather of that City</li>
    <li>Get More Details (Humidity, Visibility, Wind Speed, etc.) of the City's Weather</li>
    <li>Switch between Dark and Light Mode</li>
</ul>

<h2>Dark Mode UI</h2>
<img src='media/DarkUI.png' align='center'>

<h2>Light Mode UI</h2>
<img src='media/LightUI.png' align='center'>

<h2>Installation</h2>
<ol>
    <li><b>Clone the Project:</b>
        <ol type='i'>
            <li>Run `git clone https://github.com/Code-R57/HowsTheSky.git` in terminal</li>
            <li>Android Studio -> File -> Open</li>
        </ol>
    </li>
    <li><b>Getting API Key:</b>
        <ol type='i'>
            <li>Open [OpenWeather Sign In/Sign Up](https://home.openweathermap.org/users/sign_in)</li>
            <li>Go to [OpenWeather API Keys](https://home.openweathermap.org/api_keys) and copy the API Key Generated
            </li>
        </ol>
    </li>
    <li><b>Adding the API Key in the Project</b>:
        <ol type='i'>
            <li>In the project folder, go to `local.properties` file</li>
            <li>Add the line `OW_KEY={Your API Key}` ( do not add the { } )</li>
        </ol>
    </li>
</ol>

<h2>OpenWeather API</h2>
<p>How's The Sky uses [OpenWeather API](https://openweathermap.org/current) to get the current weather of the city searched. [OpenWeather Weather Conditions](https://openweathermap.org/weather-conditions) is used to get the image from the image id.</p>

<h2>Libraries</h2>
<ul>
    <li>[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - For managing background threads.</li>
    <li>[Retrofit](https://square.github.io/retrofit) - A type-safe HTTP client for Android</li>
    <li>[Glide](https://bumptech.github.io/glide) - Image loading library for Android</li>
    <li>[SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) - Pass data with type safety</li>
    <li>Jetpack
        <ul>
            <li>[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store and manage UI-related data in a lifecycle conscious way</li>
            <li>[LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Observable data holder class</li>
            <li>[Navigation](https://developer.android.com/guide/navigation) - Implements in-app navigation</li>
            <li>[Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite</li>
        </ul>
    </li>
</ul>