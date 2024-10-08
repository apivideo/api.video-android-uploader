<!--<documentation_excluded>-->
[![badge](https://img.shields.io/twitter/follow/api_video?style=social)](https://twitter.com/intent/follow?screen_name=api_video) &nbsp; [![badge](https://img.shields.io/github/stars/apivideo/api.video-android-uploader?style=social)](https://github.com/apivideo/api.video-android-uploader) &nbsp; [![badge](https://img.shields.io/discourse/topics?server=https%3A%2F%2Fcommunity.api.video)](https://community.api.video)
![](https://github.com/apivideo/.github/blob/main/assets/apivideo_banner.png)
<h1 align="center">api.video Android uploader</h1>

[api.video](https://api.video) is the video infrastructure for product builders. Lightning fast video APIs for integrating, scaling, and managing on-demand & low latency live streaming features in your app.

## Table of contents

- [Project description](#project-description)
- [Getting started](#getting-started)
  - [Requirements](#requirements)
  - [Installation](#installation)
    - [Maven users](#maven-users)
    - [Gradle users](#gradle-users)
    - [Others](#others)
  - [Code sample](#code-sample)
  - [Upload options](#upload-options)
  - [Permissions](#permissions)
- [Documentation](#documentation)
  - [API Endpoints](#api-endpoints)
    - [VideosApi](#videosapi)
  - [Models](#models)
  - [Authorization](#documentation-for-authorization)
    - [API key](#api-key)
    - [Public endpoints](#public-endpoints)
  - [Recommendation](#recommendation)
- [Have you gotten use from this API client?](#have-you-gotten-use-from-this-api-client-)
- [Contribution](#contribution)
<!--</documentation_excluded>-->
<!--<documentation_only>
---
title: Android video uploader
meta: 
  description: The official Android video uploader for api.video. [api.video](https://api.video/) is the video infrastructure for product builders. Lightning fast video APIs for integrating, scaling, and managing on-demand & low latency live streaming features in your app.
---

# api.video Android video uploader

[api.video](https://api.video/) is the video infrastructure for product builders. Lightning fast video APIs for integrating, scaling, and managing on-demand & low latency live streaming features in your app.
</documentation_only>-->

## Project description

api.video's Android video uploader streamlines the coding process. Chunking files is handled for you, as is pagination and refreshing your tokens.

## Getting started

### Requirements

Building the API client library requires:
1. Java 1.8+
2. Maven/Gradle

### Installation

#### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>video.api</groupId>
  <artifactId>android-video-uploader</artifactId>
  <version>1.3.7</version>
  <scope>compile</scope>
</dependency>
```

#### Gradle users

Add this dependency to your project's build file:

```groovy
implementation "video.api:android-video-uploader:1.3.7"
```

#### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/android-video-uploader-1.3.7.jar`
* `target/lib/*.jar`

### Code sample

Please follow the [installation](#installation) instruction and execute the following Kotlin code:

```kotlin
// If you want to upload a video with an upload token (uploadWithUploadToken):
VideosApiStore.initialize()
// if you rather like to use the sandbox environment:
// VideosApiStore.initialize(environment = Environment.SANDBOX)

val myVideoFile = File("my-video.mp4")

val workManager = WorkManager.getInstance(context) // WorkManager comes from package "androidx.work:work-runtime"
workManager.uploadWithUploadToken("MY_UPLOAD_TOKEN", myVideoFile) // Dispatch the upload with the WorkManager
```

### Example

Examples that demonstrate how to use the API is provided in folder `examples/`.

## Upload methods

To upload a video, you have 3 differents methods:
* `WorkManager`: preferred method: Upload with Android WorkManager API. It supports progress notifications, upload in background, queue, reupload after lost connections. Directly use, WorkManager extensions. See [example](https://github.com/apivideo/api.video-android-uploader/blob/main/examples/workmanager) for more details.
* `UploadService`: Upload with an Android Service. It supports progress notifications, upload in background, queue. You have to extend the `UploadService` and register it in your `AndroidManifest.xml`. See [example](https://github.com/apivideo/api.video-android-uploader/blob/main/examples/service) for more details.
* Direct call with `ApiClient`: Do not call API from the main thread, otherwise you will get an `android.os.NetworkOnMainThreadException`. Dispatch API calls with Thread, Executors or Kotlin coroutine to avoid this.

## Permissions

If your video files are located in the media store, you have to add the following permissions in your `AndroidManifest.xml`:

```xml
    <uses-permission android:name="android.permission.INTERNET" />

    <!-- The application requires READ_EXTERNAL_STORAGE or READ_MEDIA_VIDEO to access video to upload them` -->
    <uses-permission
        android:name="android.permission.READ_EXTERNAL_STORAGE"
        android:maxSdkVersion="32" />
    <uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />
``` 

Your application also has to dynamically request the `android.permission.READ_EXTERNAL_STORAGE` permission to upload videos.

If your video files are located in the app-specific storage, you don't need to request any permissions nor add any permissions to your `AndroidManifest.xml`.

### WorkManager

To upload with the `WorkManager`, you also have to add the following lines in your `AndroidManifest.xml`:

```xml
    <!-- The application requires POST_NOTIFICATIONS to display the upload notification -->
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

    <!-- The application requires FOREGROUND_SERVICE_DATA_SYNC for API >= 34 -->
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE_DATA_SYNC" />

    <application>
      ...
        <!-- The application requires to declare a service type for API >= 34 -->
        <service
            android:name="androidx.work.impl.foreground.SystemForegroundService"
            android:foregroundServiceType="dataSync"
            tools:node="merge" />
    </application>
```

### UploadService


To upload with the `UploadService`, you also have to add the following lines in your `AndroidManifest.xml`:

```xml
    <!-- The application requires POST_NOTIFICATIONS to display the upload notification -->
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

    <uses-permission android:name="android.permission.FOREGROUND_SERVICE" />

    <application>
        <!--
          The application requires to declare your service, replace `YourUploaderService` by the package
          of your service or by the package of `UploadService` if you directly use `UploadService`.
        -->
        <service android:name=".YourUploaderService" />
    </application>
```



## Documentation

### API Endpoints

All URIs are relative to *https://ws.api.video*


### VideosApi


#### Retrieve an instance of VideosApi:
```kotlin
val videosApi = VideosApi("YOUR_API_KEY", Environment.PRODUCTION)
```



#### Endpoints

Method | HTTP request | Description
------------- | ------------- | -------------
[**upload**](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/VideosApi.md#upload) | **POST** `/videos/{videoId}/source` | Upload a video
[**uploadWithUploadToken**](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/VideosApi.md#uploadWithUploadToken) | **POST** `/upload` | Upload with an delegated upload token



### Documentation for Models

 - [AccessToken](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/AccessToken.md)
 - [AdditionalBadRequestErrors](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/AdditionalBadRequestErrors.md)
 - [AuthenticatePayload](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/AuthenticatePayload.md)
 - [BadRequest](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/BadRequest.md)
 - [Metadata](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/Metadata.md)
 - [NotFound](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/NotFound.md)
 - [RefreshTokenPayload](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/RefreshTokenPayload.md)
 - [TooManyRequests](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/TooManyRequests.md)
 - [Video](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/Video.md)
 - [VideoAssets](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/VideoAssets.md)
 - [VideoSource](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/VideoSource.md)
 - [VideoSourceLiveStream](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/VideoSourceLiveStream.md)
 - [VideoSourceLiveStreamLink](https://github.com/apivideo/api.video-android-uploader/blob/main/docs/VideoSourceLiveStreamLink.md)


### Rate Limiting

api.video implements rate limiting to ensure fair usage and stability of the service. The API provides the rate limit values in the response headers for any API requests you make. The /auth endpoint is the only route without rate limitation.

In this client, you can access these headers by using the `*WithHttpInfo()` or `*Async` versions of the methods. These methods return the `ApiResponse` that contains the response body and the headers, allowing you to check the `X-RateLimit-Limit`, `X-RateLimit-Remaining`, and `X-RateLimit-Retry-After` headers to understand your current rate limit status.
Read more about these response headers in the [API reference](https://docs.api.video/reference#limitation).

Here is an example of how to use these methods:

When listening to the `WorkInfo` with the `WorkManager`, you can access the headers in the `OutputData` of the `WorkInfo`:
```kotlin
val headers = workInfo.outputData.toHeaders()
Log.i(TAG, "X-RateLimit-Limit: ${headers["x-ratelimit-limit"]!![0]}")
Log.i(TAG, "X-RateLimit-Remaining: ${headers["x-ratelimit-remaining"]!![0]}")
Log.i(TAG, "X-RateLimit-Retry-After: ${headers["x-ratelimit-retry-after"]!![0]}")
```

### Authorization

#### API key

Most endpoints required to be authenticated using the API key mechanism described in our [documentation](https://docs.api.video/reference#authentication).

On Android, you must NOT store your API key in your application code to prevent your API key from being exposed in your source code.
Only the [Public endpoints](#public-endpoints) can be called without authentication.
In the case, you want to call an endpoint that requires authentication, you will have to use a backend server. See [Security best practices](https://docs.api.video/sdks/security) for more details.


#### Public endpoints

Some endpoints don't require authentication. These one can be called with a client instantiated without API key:
```kotlin
val videosApi = VideosApi()
```

### Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.
For direct call with `ApiClient`: Do not call API from the main thread, otherwise you will get a `android.os.NetworkOnMainThreadException`. Dispatch API calls with Thread, Executors or Kotlin coroutine to avoid this. Alternatively, APIs come with an asynchronous counterpart (`createAsync` for `create`) except for the upload endpoint.

### Have you gotten use from this API client?

Please take a moment to leave a star on the client ⭐

This helps other users to find the clients and also helps us understand which clients are most popular. Thank you!

## Contribution

Since this API client is generated from an OpenAPI description, we cannot accept pull requests made directly to the repository. If you want to contribute, you can open a pull request on the repository of our [client generator](https://github.com/apivideo/api-client-generator). Otherwise, you can also simply open an issue detailing your need on this repository.