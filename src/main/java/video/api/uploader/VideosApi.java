/*
 * api.video Java API client
 * api.video is an API that encodes on the go to facilitate immediate playback, enhancing viewer streaming experiences across multiple devices and platforms. You can stream live or on-demand online videos within minutes.
 *
 * The version of the OpenAPI document: 1
 * Contact: ecosystem@api.video
 *
 * NOTE: This class is auto generated.
 * Do not edit the class manually.
 */

package video.api.uploader;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;

import video.api.uploader.api.models.*;
import video.api.uploader.api.upload.*;
import video.api.uploader.api.*;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideosApi {
    private ApiClient localVarApiClient;

    public VideosApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Constructor for VideosApi production environment where API key is not required.
     */
    public VideosApi() {
        this.localVarApiClient = new ApiClient(Environment.PRODUCTION.basePath);
    }

    /**
     * Constructor for VideosApi with custom API base path where API key is not required.
     * 
     * @param basePath
     *            the api base path. Expected Environment.PRODUCTION.basePath (default) or Environment.SANDBOX.basePath.
     */
    public VideosApi(String basePath) {
        this.localVarApiClient = new ApiClient(basePath);
    }

    /**
     * Constructor for VideosApi with custom API base path
     * 
     * @param apiKey
     *            the api key to use to authenticate to the API
     * @param basePath
     *            the api base path. Expected Environment.PRODUCTION.basePath (default) or Environment.SANDBOX.basePath.
     */
    public VideosApi(String apiKey, String basePath) {
        this.localVarApiClient = new ApiClient(apiKey, basePath);
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for uploadWithUploadToken
     * 
     * @param token
     *            The unique identifier for the token you want to use to upload a video. (required)
     * @param file
     *            The path to the video you want to upload. (required)
     * @param _callback
     *            Callback for upload/download progress
     * 
     * @return Call to execute
     * 
     * @throws ApiException
     *             If fail to serialize the request body object
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    private okhttp3.Call uploadWithUploadTokenCall(String token, File file, final ApiCallback _callback)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/upload";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (file != null) {
            localVarFormParams.put("file", file);
        }

        if (token != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("token", token));
        }

        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = { "multipart/form-data" };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
                localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames,
                _callback);
    }

    private okhttp3.Call uploadWithUploadTokenChunkCall(String token, File file, String videoId, long chunkStart,
            long chunkEnd, long totalBytes, Integer chunksCount, Integer chunkNum,
            UploadProgressListener progressListener, UploadPartProgressListener partProgressListener,
            final ApiCallback _callback, boolean isProgressiveUpload) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/upload";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (file != null) {
            if (isProgressiveUpload) {
                localVarFormParams.put("file", new UploadChunkRequestBody(file, totalBytes, partProgressListener));
            } else {
                localVarFormParams.put("file", new UploadChunkRequestBody(file, chunksCount, chunkNum, totalBytes,
                        chunkStart, chunkEnd + 1, progressListener));
            }
        }

        if (token != null) {
            localVarQueryParams.addAll(localVarApiClient.parameterToPair("token", token));
        }

        localVarHeaderParams.put("Content-Range", "part " + chunkNum + "/" + (chunksCount != null ? chunksCount : "*"));

        if (videoId != null) {
            localVarFormParams.put("videoId", videoId);
        }

        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = { "multipart/form-data" };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] {};
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
                localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call uploadWithUploadTokenValidateBeforeCall(String token, File file, final ApiCallback _callback)
            throws ApiException {

        // verify the required parameter 'token' is set
        if (token == null) {
            throw new ApiException("Missing the required parameter 'token' when calling uploadWithUploadToken");
        }

        // verify the required parameter 'file' is set
        if (file == null) {
            throw new ApiException("Missing the required parameter 'file' when calling uploadWithUploadToken");
        }

        okhttp3.Call localVarCall = uploadWithUploadTokenCall(token, file, _callback);
        return localVarCall;
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call uploadWithUploadTokenChunkValidateBeforeCall(String token, File file, String videoId,
            long chunkStart, long chunkEnd, long totalBytes, Integer chunksCount, Integer chunkNum,
            UploadProgressListener progressListener, UploadPartProgressListener partProgressListener,
            final ApiCallback _callback, boolean isProgressiveUpload) throws ApiException {

        // verify the required parameter 'token' is set
        if (token == null) {
            throw new ApiException("Missing the required parameter 'token' when calling uploadWithUploadToken(Async)");
        }

        // verify the required parameter 'file' is set
        if (file == null) {
            throw new ApiException("Missing the required parameter 'file' when calling uploadWithUploadToken(Async)");
        }

        okhttp3.Call localVarCall = uploadWithUploadTokenChunkCall(token, file, videoId, chunkStart, chunkEnd,
                totalBytes, chunksCount, chunkNum, progressListener, partProgressListener, _callback,
                isProgressiveUpload);
        return localVarCall;

    }

    /**
     * Upload with an upload token
     *
     * This method allows you to send a video using an upload token. Upload tokens are especially useful when the upload
     * is done from the client side. If you want to upload a video from your server-side application, you&#39;d better
     * use the [standard upload method](#upload).
     * 
     * @param token
     *            The unique identifier for the token you want to use to upload a video. (required)
     * @param file
     *            The path to the video you want to upload. (required)
     * 
     * @return Video
     * 
     * @throws ApiException
     *             If fail to call the API, e.g. server error or cannot deserialize the response body
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    public Video uploadWithUploadToken(String token, File file) throws ApiException {
        ApiResponse<Video> localVarResp = uploadWithUploadTokenWithHttpInfo(token, file);
        return localVarResp.getData();
    }

    /**
     * Upload with an upload token
     *
     * This method allows you to send a video using an upload token. Upload tokens are especially useful when the upload
     * is done from the client side. If you want to upload a video from your server-side application, you&#39;d better
     * use the [standard upload method](#upload).
     * 
     * @param token
     *            The unique identifier for the token you want to use to upload a video. (required)
     * @param file
     *            The path to the video you want to upload. (required)
     * @param uploadProgressListener
     *            An upload progress listener
     * 
     * @return Video
     * 
     * @throws ApiException
     *             If fail to call the API, e.g. server error or cannot deserialize the response body
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    public Video uploadWithUploadToken(String token, File file, UploadProgressListener uploadProgressListener)
            throws ApiException {
        ApiResponse<Video> localVarResp = uploadWithUploadTokenWithHttpInfo(token, file, uploadProgressListener);
        return localVarResp.getData();
    }

    /**
     * Upload with an upload token
     *
     * This method allows you to send a video using an upload token. Upload tokens are especially useful when the upload
     * is done from the client side. If you want to upload a video from your server-side application, you&#39;d better
     * use the [standard upload method](#upload).
     * 
     * @param token
     *            The unique identifier for the token you want to use to upload a video. (required)
     * @param file
     *            The path to the video you want to upload. (required)
     * 
     * @return ApiResponse&lt;Video&gt;
     * 
     * @throws ApiException
     *             If fail to call the API, e.g. server error or cannot deserialize the response body
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    public ApiResponse<Video> uploadWithUploadTokenWithHttpInfo(String token, File file) throws ApiException {
        return uploadWithUploadTokenWithHttpInfo(token, file, null);
    }

    public ApiResponse<Video> uploadWithUploadTokenPartWithHttpInfo(String token, File file, String videoId,
            Integer part, boolean isLast, UploadPartProgressListener uploadProgressListener) throws ApiException {
        long fileSize = file.length();
        okhttp3.Call localVarCall = uploadWithUploadTokenChunkValidateBeforeCall(token, file, videoId, 0, fileSize,
                fileSize, isLast ? part : null, part, null, uploadProgressListener, null, true);
        Type localVarReturnType = new TypeToken<Video>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    public class UploadWithUploadTokenProgressiveSession implements IProgressiveUploadSession {
        private Integer part = 1;
        private String videoId;

        private String token;

        public UploadWithUploadTokenProgressiveSession(String token) {
            this.token = token;
        }

        @Override
        public Video uploadPart(File part) throws ApiException {
            return uploadPart(part, false, null);
        }

        @Override
        public Video uploadLastPart(File part) throws ApiException {
            return uploadPart(part, true, null);
        }

        @Override
        public Video uploadPart(File part, UploadPartProgressListener uploadProgressListener) throws ApiException {
            return uploadPart(part, false, uploadProgressListener);
        }

        @Override
        public Video uploadLastPart(File part, UploadPartProgressListener uploadProgressListener) throws ApiException {
            return uploadPart(part, true, uploadProgressListener);
        }

        @Override
        public Video uploadPart(File part, boolean isLastPart, UploadPartProgressListener uploadProgressListener)
                throws ApiException {
            Integer lastPart = this.part;
            this.part++;
            ApiResponse<Video> localVarResp = uploadWithUploadTokenPartWithHttpInfo(this.token, part, this.videoId,
                    lastPart, isLastPart, uploadProgressListener);
            if (this.videoId == null) {
                this.videoId = localVarResp.getData().getVideoId();
            }
            return localVarResp.getData();
        }
    }

    public UploadWithUploadTokenProgressiveSession createUploadWithUploadTokenProgressiveSession(String token) {
        return new UploadWithUploadTokenProgressiveSession(token);
    }

    /**
     * Upload with an upload token
     *
     * This method allows you to send a video using an upload token. Upload tokens are especially useful when the upload
     * is done from the client side. If you want to upload a video from your server-side application, you&#39;d better
     * use the [standard upload method](#upload).
     * 
     * @param token
     *            The unique identifier for the token you want to use to upload a video. (required)
     * @param file
     *            The path to the video you want to upload. (required)
     * @param uploadProgressListener
     *            An upload progress listener
     * 
     * @return ApiResponse&lt;Video&gt;
     * 
     * @throws ApiException
     *             If fail to call the API, e.g. server error or cannot deserialize the response body
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    public ApiResponse<Video> uploadWithUploadTokenWithHttpInfo(String token, File file,
            UploadProgressListener uploadProgressListener) throws ApiException {
        String videoId = null;

        if (file == null) {
            throw new ApiException("Missing the required parameter 'file' when calling upload");
        }
        long totalBytes = file.length();
        long chunkSize = getApiClient().getUploadChunkSize();
        if (totalBytes > chunkSize) {
            int chunkNum = 0;
            ApiResponse<Video> lastRes = null;
            for (long i = 0; i < totalBytes; i += chunkSize) {
                okhttp3.Call localVarCall = uploadWithUploadTokenChunkValidateBeforeCall(token, file, videoId, i,
                        Math.min(i + chunkSize, totalBytes) - 1, totalBytes,
                        (int) Math.ceil((float) totalBytes / chunkSize), chunkNum + 1, uploadProgressListener, null,
                        null, false);
                Type localVarReturnType = new TypeToken<Video>() {
                }.getType();
                lastRes = localVarApiClient.execute(localVarCall, localVarReturnType);
                if (videoId == null) {
                    videoId = lastRes.getData().getVideoId();
                }
                chunkNum++;
            }
            return lastRes;
        } else {
            ApiCallback apiCallback = new ApiCallback() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map responseHeaders) {
                }

                @Override
                public void onSuccess(Object result, int statusCode, Map responseHeaders) {
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
                    if (uploadProgressListener != null) {
                        uploadProgressListener.onProgress(bytesWritten - (contentLength - totalBytes), totalBytes, 1,
                                1);
                    }
                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
                }
            };

            okhttp3.Call localVarCall = uploadWithUploadTokenValidateBeforeCall(token, file, apiCallback);
            Type localVarReturnType = new TypeToken<Video>() {
            }.getType();
            return localVarApiClient.execute(localVarCall, localVarReturnType);
        }

    }

    /**
     * Build call for upload
     * 
     * @param videoId
     *            Enter the videoId you want to use to upload your video. (required)
     * @param file
     *            The path to the video you would like to upload. The path must be local. If you want to use a video
     *            from an online source, you must use the \\\&quot;/videos\\\&quot; endpoint and add the
     *            \\\&quot;source\\\&quot; parameter when you create a new video. (required)
     * @param _callback
     *            Callback for upload/download progress
     * 
     * @return Call to execute
     * 
     * @throws ApiException
     *             If fail to serialize the request body object
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>404</td>
     *                        <td>Not Found</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    private okhttp3.Call uploadCall(String videoId, File file, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/videos/{videoId}/source".replaceAll("\\{" + "videoId" + "\\}",
                localVarApiClient.escapeString(videoId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (file != null) {
            localVarFormParams.put("file", file);
        }

        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = { "multipart/form-data" };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
                localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames,
                _callback);
    }

    private okhttp3.Call uploadChunkCall(String videoId, File file, long chunkStart, long chunkEnd, long totalBytes,
            Integer chunksCount, Integer chunkNum, UploadProgressListener progressListener,
            UploadPartProgressListener partProgressListener, final ApiCallback _callback, boolean isProgressiveUpload)
            throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/videos/{videoId}/source".replaceAll("\\{" + "videoId" + "\\}",
                localVarApiClient.escapeString(videoId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        if (file != null) {
            if (isProgressiveUpload) {
                localVarFormParams.put("file", new UploadChunkRequestBody(file, totalBytes, partProgressListener));
            } else {
                localVarFormParams.put("file", new UploadChunkRequestBody(file, chunksCount, chunkNum, totalBytes,
                        chunkStart, chunkEnd + 1, progressListener));
            }
        }

        localVarHeaderParams.put("Content-Range", "part " + chunkNum + "/" + (chunksCount != null ? chunksCount : "*"));

        final String[] localVarAccepts = { "application/json" };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = { "multipart/form-data" };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "bearerAuth" };
        return localVarApiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams,
                localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames,
                _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call uploadValidateBeforeCall(String videoId, File file, final ApiCallback _callback)
            throws ApiException {

        // verify the required parameter 'videoId' is set
        if (videoId == null) {
            throw new ApiException("Missing the required parameter 'videoId' when calling upload");
        }

        // verify the required parameter 'file' is set
        if (file == null) {
            throw new ApiException("Missing the required parameter 'file' when calling upload");
        }

        okhttp3.Call localVarCall = uploadCall(videoId, file, _callback);
        return localVarCall;
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call uploadChunkValidateBeforeCall(String videoId, File file, long chunkStart, long chunkEnd,
            long totalBytes, Integer chunksCount, Integer chunkNum, UploadProgressListener progressListener,
            UploadPartProgressListener partProgressListener, final ApiCallback _callback, boolean isProgressiveUpload)
            throws ApiException {

        // verify the required parameter 'videoId' is set
        if (videoId == null) {
            throw new ApiException("Missing the required parameter 'videoId' when calling upload(Async)");
        }

        // verify the required parameter 'file' is set
        if (file == null) {
            throw new ApiException("Missing the required parameter 'file' when calling upload(Async)");
        }

        okhttp3.Call localVarCall = uploadChunkCall(videoId, file, chunkStart, chunkEnd, totalBytes, chunksCount,
                chunkNum, progressListener, partProgressListener, _callback, isProgressiveUpload);
        return localVarCall;

    }

    /**
     * Upload a video
     *
     * To upload a video to the videoId you created. You can only upload your video to the videoId once.
     * 
     * 
     * 
     * We offer 2 types of upload:
     * 
     * Regular upload
     * 
     * Progressive upload
     * 
     * The latter allows you to split a video source into X chunks and send those chunks independently (concurrently or
     * sequentially). The 2 main goals for our users are to
     * 
     * allow the upload of video sources &gt; 200 MiB (200 MiB &#x3D; the max. allowed file size for regular upload)
     * 
     * allow to send a video source &quot;progressively&quot;, i.e., before before knowing the total size of the video.
     * 
     * Once all chunks have been sent, they are reaggregated to one source file. The video source is considered as
     * &quot;completely sent&quot; when the &quot;last&quot; chunk is sent (i.e., the chunk that &quot;completes&quot;
     * the upload).
     * 
     * 
     * @param videoId
     *            Enter the videoId you want to use to upload your video. (required)
     * @param file
     *            The path to the video you would like to upload. The path must be local. If you want to use a video
     *            from an online source, you must use the \\\&quot;/videos\\\&quot; endpoint and add the
     *            \\\&quot;source\\\&quot; parameter when you create a new video. (required)
     * 
     * @return Video
     * 
     * @throws ApiException
     *             If fail to call the API, e.g. server error or cannot deserialize the response body
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>404</td>
     *                        <td>Not Found</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    public Video upload(String videoId, File file) throws ApiException {
        ApiResponse<Video> localVarResp = uploadWithHttpInfo(videoId, file);
        return localVarResp.getData();
    }

    /**
     * Upload a video
     *
     * To upload a video to the videoId you created. You can only upload your video to the videoId once.
     * 
     * 
     * 
     * We offer 2 types of upload:
     * 
     * Regular upload
     * 
     * Progressive upload
     * 
     * The latter allows you to split a video source into X chunks and send those chunks independently (concurrently or
     * sequentially). The 2 main goals for our users are to
     * 
     * allow the upload of video sources &gt; 200 MiB (200 MiB &#x3D; the max. allowed file size for regular upload)
     * 
     * allow to send a video source &quot;progressively&quot;, i.e., before before knowing the total size of the video.
     * 
     * Once all chunks have been sent, they are reaggregated to one source file. The video source is considered as
     * &quot;completely sent&quot; when the &quot;last&quot; chunk is sent (i.e., the chunk that &quot;completes&quot;
     * the upload).
     * 
     * 
     * @param videoId
     *            Enter the videoId you want to use to upload your video. (required)
     * @param file
     *            The path to the video you would like to upload. The path must be local. If you want to use a video
     *            from an online source, you must use the \\\&quot;/videos\\\&quot; endpoint and add the
     *            \\\&quot;source\\\&quot; parameter when you create a new video. (required)
     * @param uploadProgressListener
     *            An upload progress listener
     * 
     * @return Video
     * 
     * @throws ApiException
     *             If fail to call the API, e.g. server error or cannot deserialize the response body
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>404</td>
     *                        <td>Not Found</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    public Video upload(String videoId, File file, UploadProgressListener uploadProgressListener) throws ApiException {
        ApiResponse<Video> localVarResp = uploadWithHttpInfo(videoId, file, uploadProgressListener);
        return localVarResp.getData();
    }

    /**
     * Upload a video
     *
     * To upload a video to the videoId you created. You can only upload your video to the videoId once.
     * 
     * 
     * 
     * We offer 2 types of upload:
     * 
     * Regular upload
     * 
     * Progressive upload
     * 
     * The latter allows you to split a video source into X chunks and send those chunks independently (concurrently or
     * sequentially). The 2 main goals for our users are to
     * 
     * allow the upload of video sources &gt; 200 MiB (200 MiB &#x3D; the max. allowed file size for regular upload)
     * 
     * allow to send a video source &quot;progressively&quot;, i.e., before before knowing the total size of the video.
     * 
     * Once all chunks have been sent, they are reaggregated to one source file. The video source is considered as
     * &quot;completely sent&quot; when the &quot;last&quot; chunk is sent (i.e., the chunk that &quot;completes&quot;
     * the upload).
     * 
     * 
     * @param videoId
     *            Enter the videoId you want to use to upload your video. (required)
     * @param file
     *            The path to the video you would like to upload. The path must be local. If you want to use a video
     *            from an online source, you must use the \\\&quot;/videos\\\&quot; endpoint and add the
     *            \\\&quot;source\\\&quot; parameter when you create a new video. (required)
     * 
     * @return ApiResponse&lt;Video&gt;
     * 
     * @throws ApiException
     *             If fail to call the API, e.g. server error or cannot deserialize the response body
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>404</td>
     *                        <td>Not Found</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    public ApiResponse<Video> uploadWithHttpInfo(String videoId, File file) throws ApiException {
        return uploadWithHttpInfo(videoId, file, null);
    }

    public ApiResponse<Video> uploadPartWithHttpInfo(String videoId, File file, Integer part, boolean isLast,
            UploadPartProgressListener uploadProgressListener) throws ApiException {
        long fileSize = file.length();
        okhttp3.Call localVarCall = uploadChunkValidateBeforeCall(videoId, file, 0, fileSize, fileSize,
                isLast ? part : null, part, null, uploadProgressListener, null, true);
        Type localVarReturnType = new TypeToken<Video>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    public class UploadProgressiveSession implements IProgressiveUploadSession {
        private Integer part = 1;

        private String videoId;

        public UploadProgressiveSession(String videoId) {
            this.videoId = videoId;
        }

        @Override
        public Video uploadPart(File part) throws ApiException {
            return uploadPart(part, false, null);
        }

        @Override
        public Video uploadLastPart(File part) throws ApiException {
            return uploadPart(part, true, null);
        }

        @Override
        public Video uploadPart(File part, UploadPartProgressListener uploadProgressListener) throws ApiException {
            return uploadPart(part, false, uploadProgressListener);
        }

        @Override
        public Video uploadLastPart(File part, UploadPartProgressListener uploadProgressListener) throws ApiException {
            return uploadPart(part, true, uploadProgressListener);
        }

        @Override
        public Video uploadPart(File part, boolean isLastPart, UploadPartProgressListener uploadProgressListener)
                throws ApiException {
            Integer lastPart = this.part;
            this.part++;
            ApiResponse<Video> localVarResp = uploadPartWithHttpInfo(this.videoId, part, lastPart, isLastPart,
                    uploadProgressListener);

            return localVarResp.getData();
        }
    }

    public UploadProgressiveSession createUploadProgressiveSession(String videoId) {
        return new UploadProgressiveSession(videoId);
    }

    /**
     * Upload a video
     *
     * To upload a video to the videoId you created. You can only upload your video to the videoId once.
     * 
     * 
     * 
     * We offer 2 types of upload:
     * 
     * Regular upload
     * 
     * Progressive upload
     * 
     * The latter allows you to split a video source into X chunks and send those chunks independently (concurrently or
     * sequentially). The 2 main goals for our users are to
     * 
     * allow the upload of video sources &gt; 200 MiB (200 MiB &#x3D; the max. allowed file size for regular upload)
     * 
     * allow to send a video source &quot;progressively&quot;, i.e., before before knowing the total size of the video.
     * 
     * Once all chunks have been sent, they are reaggregated to one source file. The video source is considered as
     * &quot;completely sent&quot; when the &quot;last&quot; chunk is sent (i.e., the chunk that &quot;completes&quot;
     * the upload).
     * 
     * 
     * @param videoId
     *            Enter the videoId you want to use to upload your video. (required)
     * @param file
     *            The path to the video you would like to upload. The path must be local. If you want to use a video
     *            from an online source, you must use the \\\&quot;/videos\\\&quot; endpoint and add the
     *            \\\&quot;source\\\&quot; parameter when you create a new video. (required)
     * @param uploadProgressListener
     *            An upload progress listener
     * 
     * @return ApiResponse&lt;Video&gt;
     * 
     * @throws ApiException
     *             If fail to call the API, e.g. server error or cannot deserialize the response body
     * 
     * @http.response.details
     *                        <table summary="Response Details" border="1">
     *                        <tr>
     *                        <td>Status Code</td>
     *                        <td>Description</td>
     *                        <td>Response Headers</td>
     *                        </tr>
     *                        <tr>
     *                        <td>201</td>
     *                        <td>Created</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>400</td>
     *                        <td>Bad Request</td>
     *                        <td>-</td>
     *                        </tr>
     *                        <tr>
     *                        <td>404</td>
     *                        <td>Not Found</td>
     *                        <td>-</td>
     *                        </tr>
     *                        </table>
     */
    public ApiResponse<Video> uploadWithHttpInfo(String videoId, File file,
            UploadProgressListener uploadProgressListener) throws ApiException {

        if (file == null) {
            throw new ApiException("Missing the required parameter 'file' when calling upload");
        }
        long totalBytes = file.length();
        long chunkSize = getApiClient().getUploadChunkSize();
        if (totalBytes > chunkSize) {
            int chunkNum = 0;
            ApiResponse<Video> lastRes = null;
            for (long i = 0; i < totalBytes; i += chunkSize) {
                okhttp3.Call localVarCall = uploadChunkValidateBeforeCall(videoId, file, i,
                        Math.min(i + chunkSize, totalBytes) - 1, totalBytes,
                        (int) Math.ceil((float) totalBytes / chunkSize), chunkNum + 1, uploadProgressListener, null,
                        null, false);
                Type localVarReturnType = new TypeToken<Video>() {
                }.getType();
                lastRes = localVarApiClient.execute(localVarCall, localVarReturnType);
                chunkNum++;
            }
            return lastRes;
        } else {
            ApiCallback apiCallback = new ApiCallback() {
                @Override
                public void onFailure(ApiException e, int statusCode, Map responseHeaders) {
                }

                @Override
                public void onSuccess(Object result, int statusCode, Map responseHeaders) {
                }

                @Override
                public void onUploadProgress(long bytesWritten, long contentLength, boolean done) {
                    if (uploadProgressListener != null) {
                        uploadProgressListener.onProgress(bytesWritten - (contentLength - totalBytes), totalBytes, 1,
                                1);
                    }
                }

                @Override
                public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
                }
            };

            okhttp3.Call localVarCall = uploadValidateBeforeCall(videoId, file, apiCallback);
            Type localVarReturnType = new TypeToken<Video>() {
            }.getType();
            return localVarApiClient.execute(localVarCall, localVarReturnType);
        }

    }

}
