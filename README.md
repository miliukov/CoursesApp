# CoursesApp

A test project for browsing and saving IT courses.

## Tech Stack

- **Kotlin**
- **Jetpack Compose** — UI
- **Hilt** — dependency injection
- **Retrofit + Gson** — networking
- **Room** — local database for favorites
- **Coroutines + Flow** — asynchronous operations
- **Jetpack Navigation Component** — navigation
- **MVVM + Clean Architecture**
- **Multi-module architecture**

## Architecture

The project is split into 6 modules:

```text
:app                — entry point, navigation, bottom bar
:core               — networking (Retrofit), database (Room), domain models, repository, shared UI components
:feature:login      — login screen
:feature:home       — main screen with the course list
:feature:favorites  — favorites screen
:feature:account    — account screen (placeholder)
```

Dependencies go only downward: `feature → core`, `app → feature`. Feature modules do not depend on each other.

Each module follows Clean Architecture:

- **data** — DTOs, entities, mappers, repository implementation
- **domain** — domain models, repository interface, use cases
- **ui** — screen, ViewModel, UI state

## Features

### Login
- Email validation using the `text@text.text` pattern
- Cyrillic characters are blocked in the email field
- The login button is enabled only when all fields are valid
- VK and Odnoklassniki buttons open in the browser

### Home
- Loads courses from the API
- Sorts courses by publication date in descending order
- Adds and removes courses from favorites, with data persisted in Room
- Truncates course descriptions to 2 lines
- Shows a loading indicator

### Favorites
- Displays saved courses from Room
- Updates reactively through Flow, without extra network requests

### Account
- Placeholder screen
