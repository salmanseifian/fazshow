package com.owjmedia.faaz.general.networking;

import com.owjmedia.faaz.authenticate.model.AuthenticationRequest;
import com.owjmedia.faaz.authenticate.model.AuthenticationResponse;
import com.owjmedia.faaz.authenticate.model.ConfirmationRequest;
import com.owjmedia.faaz.authenticate.model.ConfirmationResponse;
import com.owjmedia.faaz.galleries.model.GalleriesResponse;
import com.owjmedia.faaz.gallerydetail.model.GalleryDetailResponse;
import com.owjmedia.faaz.newsdetail.model.NewsDetailResponse;
import com.owjmedia.faaz.news.model.NewsResponse;
import com.owjmedia.faaz.profile.model.ProfileResponse;
import com.owjmedia.faaz.profile.model.UpdateProfileRequest;
import com.owjmedia.faaz.votedetail.model.VoteDetailRequest;
import com.owjmedia.faaz.votedetail.model.VoteDetailResponse;
import com.owjmedia.faaz.vote.model.VotingResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by salman on 11/7/17.
 */

public interface ApiInterface {

    @POST("v1/users/auth/")
    Call<AuthenticationResponse> sendPhoneNumber(@Body AuthenticationRequest authenticationRequest);

    @POST("v1/users/auth/confirm/")
    Call<ConfirmationResponse> confirmPhoneNumber(@Body ConfirmationRequest confirmationRequest);

    @PUT("v1/users/auth/")
    Call<ResponseBody> updateProfile(@Header("Authorization") String accessToken, @Body UpdateProfileRequest updateProfileRequest);

    @GET("v1/users/auth/")
    Call<ProfileResponse> getProfile(@Header("Authorization") String accessToken);

    @GET("v1/polls/")
    Call<List<VotingResponse>> getVotings(@Query("poll_type") String pollType);

    @GET("v1/polls/{poll_id}")
    Call<VoteDetailResponse> getCandidates(@Header("Authorization") String accessToken, @Path("poll_id") String pollId);

    @POST("v1/polls/{poll_id}/voting/")
    Call<ResponseBody> vote(@Header("Authorization") String accessToken, @Path("poll_id") String pollId, @Body VoteDetailRequest voteDetailRequest);

    @GET("v1/news/")
    Call<NewsResponse> getNews();

    @GET("v1/news/{news_id}/")
    Call<NewsDetailResponse> getNewsDetail(@Header("Authorization") String accessToken, @Path("news_id") String newsId);

    @POST("v1/news/{news_id}/like/")
    Call<ResponseBody> like(@Header("Authorization") String accessToken, @Path("news_id") String newsId);

    @GET("v1/image_galleries/")
    Call<List<GalleriesResponse>> getImageGalleries();

    @GET("v1/video_galleries/")
    Call<List<GalleriesResponse>> getVideoGalleries();

    @GET("v1/image_galleries/{gallery_id}/")
    Call<GalleryDetailResponse> getImageGalleryDetail(@Path("gallery_id") String galleryId);

    @GET("v1/video_galleries/{gallery_id}/")
    Call<GalleryDetailResponse> getVideoGalleryDetail(@Path("gallery_id") String galleryId);

}
