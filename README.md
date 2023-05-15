
# FizzBuzz Android Application

This is an Android application that provides a modern twist on the classic coding exercise, FizzBuzz. The app is built following the best practices and using the latest technology stack provided by Jetpack.

The application allows users to input two numbers, two words, and a limit, and will generate a FizzBuzz sequence based on these inputs. The results are efficiently displayed using a paginated list, ensuring optimal performance even for large lists.

Key Features:
- **Input Validation**: The application validates user inputs and provides informative error messages when the inputs are invalid.
- **Paginated List**: The results are displayed in a paginated list, ensuring the application remains performant even with large FizzBuzz sequences.
- **Modern Tech Stack**: The application is built with Kotlin and uses several Jetpack libraries, including Compose for the UI, Paging 3 for list display, and Hilt for dependency injection. The application follows the MVVM architectural pattern.
- **Unit Tests**: The application includes unit tests to verify the correctness of the logic.
- **Shared Build Logic**: This project includes a shared-build-logic subproject that serves as a repository for Gradle plugins, enabling distributed logic reuse across all modules.

The FizzBuzz Android application is an excellent demonstration of how to create a modern, robust, and maintainable Android application.

# Android Technical Test Project

This Android technical test project follows the Clean Architecture to ensure a separation of      
concerns and better code maintainability. The project is organized into several modules, divided      
into different layers: presentation, domain, infrastructure, shared, and applications.

## Module Architecture

### Presentation  Modules (Feature + Design System)

Feature modules are organized by screen and are responsible for displaying user interface  
elements.      
They are based on the presentation layer.

- `:presentation:feature:form-screen`: Module for the form screen.
- `:presentation:feature:result-screen`: Module for the result screen.

Presentation modules contain user interface elements and presentation logic.

- `:presentation:design-system`: Module for the design system, including shared components,  
  styles,      
  and resources.

### Domain Modules

Domain modules contain business models, use cases, and repository interfaces. They define the      
business rules and application logic.

- `:domain:models`: Module for domain models, a pure Kotlin library.
- `:domain:use-cases`: Module for use cases.
- `:domain:repositories`: Module for repository interfaces.
- `:domain:services`: Module for Service Domain Logic, contains `NumberTransformer`.

### Infrastructure Modules

Infrastructure modules provide concrete implementations of repository interfaces defined in the      
domain layer, as well as data sources.

- `:infrastructure:repositories`: Module for repository implementations.
- `:infrastructure:data-source`: Module for data sources.

### Shared Modules

Shared modules contain elements reusable in different parts of the project.

- `:shared:testing`: Module for shared testing tools.

### Application Modules

Application modules contain concrete Android applications that use the various layers of the      
architecture.

- `:apps:app`: Module for the main application.
- `:apps:catalog`: Module for the catalog application, to show components from the Design-System  
  library.

## Including Modules

The different modules are included in the project using the `include` function:

```kotlin include( *modulesApplications, *modulesPresentation, *modulesDomain, *modulesInfrastructure, *modulesShared,) ```
# Shared-Build-Logic
The Shared-Build-Logic subproject is a centralized repository for Gradle plugins within our codebase. It is designed to streamline the build configuration process across multiple modules by providing reusable, composable plugins.    
This project simplifies build scripts, eliminates code duplication, and ensures consistency across all modules. It helps us to maintain a cleaner and more manageable build system.    
This modular organization ensures a clean and maintainable architecture for the Android technical test project.

## Key Features
1. **Reusable Gradle Plugins**: With Shared-Build-Logic, we can define custom plugins that encapsulate common build logic. This means that we no longer need to duplicate code in our build scripts, making them more maintainable.
2. **Composable Plugins**: Our shared plugins can be composed, which means we can create complex build scripts by combining simpler ones. This allows us to better structure our build logic and makes it easier to understand and maintain.
3. **Consistent Build Configuration**: By centralizing our build logic in Shared-Build-Logic, we ensure that all our modules are configured consistently. This reduces the risk of bugs caused by inconsistent configuration and makes it easier to update our build logic.
## Usage
To use a plugin from Shared-Build-Logic, simply apply it in your module's `build.gradle.kts` file. Here's an example:
 ```kotlin
plugins {    
id("com.mobilez.my-plugin")}
```  
  
## Helpful section  
  
To start the main application app and not the catalog app, use this command :  
  
```  
./gradlew runDefault  
```  

There's a lot of unit testing in that app check them using the command:  

```
./gradlew test
```

## Contact
If you've had a chance to explore this project and would like to share your thoughts, feedback, or  
suggestions, I'd be more than happy to hear from you! Your perspectives and insights can go a long  
way in helping to improve this project. You can reach me directly  
at [houssem.zaier@gmail.com](mailto:houssem.zaier@gmail.com). Please don't hesitate to get in touch,  
whether you have questions, want to report an issue, or simply want to discuss the project. I'm  
looking forward to hearing from you!
