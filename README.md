# LearningApp
### An Online learning App for High School Students
*Overview*
#### The project is written in Kotlin and follow some of the rules of App archtecture according to Android documentaion and it has One activity with Three Fragments
### Things I learnt:
* Hilt for dependencies Injection
* coroutines
* Using Google Exoplayer 
* Klint for writing cleaner codes
* separation of concerns
### Archtectural Descisions
*Dependency Injection*
* I used Hilt because it:
* Reduced boilerplate
* Decoupled build dependencies
* Simplified configuration
* I separated dependencies with Different Components into different Modules,I used SIGNLETON for the Network and database dependencies because I wanted only insatnce and Used ViewModel Components for my Repository and ViewModel class
**MVVM** made it easier to seperate Concerns
Viewmodels: to observe my fragments state and Events
Jetpack Navigation: for easy navigation and less boiler code compared to using normal Fragment Manager
Gson :for the conversion Of Json objects 
Room :for persist the data
Material Design: for better user Interface
Coroutines for background work

###TODO
* UI and  unit testing
* more code abstration/Robustness
* CI/CD










