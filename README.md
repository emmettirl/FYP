# NoteReader

**NoteReader** is a cross-platform application for reading documents and taking page-linked notes in a single, unified interface.

This project was developed as my final year project for the BSc(Hons) in Software Development at Munster Technological University (MTU).
Development will continue as part of the Student Inc accelerator program at Rubicon. 

## Technologies

- Kotlin Multiplatform
- PDF.js, Epub.js, LibreOffice and Calibre CLI


## Getting Started

> Setup instructions coming soon. NoteReader is currently under active development.


---


This is a Kotlin Multiplatform project targeting Android, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…