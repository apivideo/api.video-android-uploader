# Changelog
All changes to this project will be documented in this file.

## [1.2.2] - 2023-04-20
- Add upload token and videoId in WorkManager tags

## [1.2.1] - 2023-04-04
- Add custom tag for WorkManager
- Fix tag for progressive upload in WorkManager
- Worker now returns the file in case developer want to delete it after upload.
- Use api.video theme and icon for examples

## [1.2.0] - 2023-02-28
- Introduce WorkManager dedicated API

## [1.1.2] - 2022-11-16
- Fix the UploadService notification level
- In example, add support for Android 13

## [1.1.1] - 2022-08-30
- Improve the upload Service

## [1.1.0] - 2022-08-22
- Add an upload Service

## [1.0.0] - 2022-07-05
- Add SDK origin header

## [0.2.7] - 2022-05-17
- Publicized chunk maxChunkSize
- Add a progressive upload API to upload a specific part

## [0.2.6] - 2022-04-21
- Fix `video.publishedAt` type

## [0.2.5] - 2022-03-17
- Fix metadata query param in GET /videos

## [0.2.4] - 2022-03-10
- Add Origin identification headers

## [0.2.3] - 2022-01-24
- Add applicationName parameter (to allow user agent extension)

## [0.2.2] - 2022-01-06
- Increase chunked and progressive upload speed
- Update min Sdk version to Android API 24

## [0.2.1] - 2021-12-13
- Add an interface for progressive upload session

## [0.2.0] - 2021-12-06
- Add `name` attribute in player themes

## [0.1.1] - 2021-11-19
- Simplify VideosApi() constructor
- Publish AAR instead of JAR
