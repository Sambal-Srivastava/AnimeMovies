# Anime Series

## Tech choices:
- Language: Kotlin
- UI: Jetpack Compose
- Architectural Pattern: MVVM Pattern
- Dependency Injection: Dagger HILT
- Concurrent Programming: Coroutines
- Data holder classes: Live Data and Flow
- Android Youtube Player library that uses IFrame Player API

## Features:
- fetched top anime data API and displayed it in the list
- when any list item is clicked, the user is navigated to the anime details screen
- on the details screen the data of the clicked anime is fetched from the anime details API and rendered on the screen.
- if the trailer is available, then a YouTube player is implemented which plays the trailer or else the poster image of the anime is displayed.

## Limitations:
Genre(s) and Main Cast: Data was not very clear for these fields in the anime details API so they have not been included in the fetched details.
