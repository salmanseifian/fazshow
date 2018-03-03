//================================================================================================================================
//
//  Copyright (c) 2015-2017 VisionStar Information Technology (Shanghai) Co., Ltd. All Rights Reserved.
//  EasyAR is the registered trademark or trademark of VisionStar Information Technology (Shanghai) Co., Ltd in China
//  and other countries for the augmented reality technology developed by VisionStar Information Technology (Shanghai) Co., Ltd.
//
//================================================================================================================================

package com.owjmedia.faaz.ar;

import android.opengl.GLES20;
import android.util.Log;

import com.owjmedia.faaz.general.Global;

import java.util.ArrayList;

import cn.easyar.CameraCalibration;
import cn.easyar.CameraDevice;
import cn.easyar.CameraDeviceFocusMode;
import cn.easyar.CameraDeviceType;
import cn.easyar.CameraFrameStreamer;
import cn.easyar.Frame;
import cn.easyar.FunctorOfVoidFromPointerOfTargetAndBool;
import cn.easyar.ImageTarget;
import cn.easyar.ImageTracker;
import cn.easyar.Renderer;
import cn.easyar.StorageType;
import cn.easyar.Target;
import cn.easyar.TargetInstance;
import cn.easyar.TargetStatus;
import cn.easyar.Vec2I;
import cn.easyar.Vec4I;

public class HelloAR {
    private CameraDevice camera;
    private CameraFrameStreamer streamer;
    private ArrayList<ImageTracker> trackers;
    private Renderer videobg_renderer;
    private ArrayList<VideoRenderer> video_renderers;
    private VideoRenderer current_video_renderer;
    private int tracked_target = 0;
    private int active_target = 0;
    private ARVideo video = null;
    private boolean viewport_changed = false;
    private Vec2I view_size = new Vec2I(0, 0);
    private int rotation = 0;
    private Vec4I viewport = new Vec4I(0, 0, 1280, 720);

    public HelloAR() {
        trackers = new ArrayList<ImageTracker>();
    }

    private void loadFromImage(ImageTracker tracker, String path) {
        ImageTarget target = new ImageTarget();
        String jstr = "{\n"
                + "  \"images\" :\n"
                + "  [\n"
                + "    {\n"
                + "      \"image\" : \"" + path + "\",\n"
                + "      \"name\" : \"" + path.substring(0, path.indexOf(".")) + "\"\n"
                + "    }\n"
                + "  ]\n"
                + "}";
        target.setup(jstr, StorageType.Assets | StorageType.Json, "");
        tracker.loadTarget(target, new FunctorOfVoidFromPointerOfTargetAndBool() {
            @Override
            public void invoke(Target target, boolean status) {
                Log.i("HelloAR", String.format("load target (%b): %s (%d)", status, target.name(), target.runtimeID()));
            }
        });
    }

    private void loadAllFromJsonFile(ImageTracker tracker, String path) {
        for (ImageTarget target : ImageTarget.setupAll(path, StorageType.Assets)) {
            tracker.loadTarget(target, new FunctorOfVoidFromPointerOfTargetAndBool() {
                @Override
                public void invoke(Target target, boolean status) {
                    try {
                        Log.i("HelloAR", String.format("load target (%b): %s (%d)", status, target.name(), target.runtimeID()));
                    } catch (Throwable ex) {
                    }
                }
            });
        }
    }

    public boolean initialize() {
        camera = new CameraDevice();
        streamer = new CameraFrameStreamer();
        streamer.attachCamera(camera);

        boolean status = true;
        status &= camera.open(CameraDeviceType.Default);
        camera.setSize(new Vec2I(1280, 720));

        if (!status) {
            return status;
        }
        ImageTracker tracker = new ImageTracker();
        tracker.attachStreamer(streamer);
        loadAllFromJsonFile(tracker, "targets.json");
        loadFromImage(tracker, "namecard.jpg");
        trackers.add(tracker);

        return status;
    }

    public void dispose() {
        if (video != null) {
            video.dispose();
            video = null;
        }
        tracked_target = 0;
        active_target = 0;

        for (ImageTracker tracker : trackers) {
            tracker.dispose();
        }
        trackers.clear();
        video_renderers.clear();
        current_video_renderer = null;
        if (videobg_renderer != null) {
            videobg_renderer.dispose();
            videobg_renderer = null;
        }
        if (streamer != null) {
            streamer.dispose();
            streamer = null;
        }
        if (camera != null) {
            camera.dispose();
            camera = null;
        }
    }

    public boolean start() {
        boolean status = true;
        status &= (camera != null) && camera.start();
        status &= (streamer != null) && streamer.start();
        camera.setFocusMode(CameraDeviceFocusMode.Continousauto);
        for (ImageTracker tracker : trackers) {
            status &= tracker.start();
        }
        return status;
    }

    public boolean stop() {
        boolean status = true;
        for (ImageTracker tracker : trackers) {
            status &= tracker.stop();
        }
        status &= (streamer != null) && streamer.stop();
        status &= (camera != null) && camera.stop();
        return status;
    }

    public void initGL() {
        if (active_target != 0) {
            video.onLost();
            video.dispose();
            video = null;
            tracked_target = 0;
            active_target = 0;
        }
        if (videobg_renderer != null) {
            videobg_renderer.dispose();
        }
        videobg_renderer = new Renderer();
        video_renderers = new ArrayList<VideoRenderer>();
        for (int k = 0; k < 25; k += 1) {
            VideoRenderer video_renderer = new VideoRenderer();
            video_renderer.init();
            video_renderers.add(video_renderer);
        }
        current_video_renderer = null;
    }

    public void resizeGL(int width, int height) {
        view_size = new Vec2I(width, height);
        viewport_changed = true;
    }

    private void updateViewport() {
        CameraCalibration calib = camera != null ? camera.cameraCalibration() : null;
        int rotation = calib != null ? calib.rotation() : 0;
        if (rotation != this.rotation) {
            this.rotation = rotation;
            viewport_changed = true;
        }
        if (viewport_changed) {
            Vec2I size = new Vec2I(1, 1);
            if ((camera != null) && camera.isOpened()) {
                size = camera.size();
            }
            if (rotation == 90 || rotation == 270) {
                size = new Vec2I(size.data[1], size.data[0]);
            }
            float scaleRatio = Math.max((float) view_size.data[0] / (float) size.data[0], (float) view_size.data[1] / (float) size.data[1]);
            Vec2I viewport_size = new Vec2I(Math.round(size.data[0] * scaleRatio), Math.round(size.data[1] * scaleRatio));
            viewport = new Vec4I((view_size.data[0] - viewport_size.data[0]) / 2, (view_size.data[1] - viewport_size.data[1]) / 2, viewport_size.data[0], viewport_size.data[1]);

            if ((camera != null) && camera.isOpened())
                viewport_changed = false;
        }
    }

    public void render() {
        GLES20.glClearColor(1.f, 1.f, 1.f, 1.f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        if (videobg_renderer != null) {
            Vec4I default_viewport = new Vec4I(0, 0, view_size.data[0], view_size.data[1]);
            GLES20.glViewport(default_viewport.data[0], default_viewport.data[1], default_viewport.data[2], default_viewport.data[3]);
            if (videobg_renderer.renderErrorMessage(default_viewport)) {
                return;
            }
        }

        if (streamer == null) {
            return;
        }
        Frame frame = streamer.peek();
        try {
            updateViewport();
            GLES20.glViewport(viewport.data[0], viewport.data[1], viewport.data[2], viewport.data[3]);

            if (videobg_renderer != null) {
                videobg_renderer.render(frame, viewport);
            }

            ArrayList<TargetInstance> targetInstances = frame.targetInstances();
            if (targetInstances.size() > 0) {
                TargetInstance targetInstance = targetInstances.get(0);
                Target target = targetInstance.target();
                int status = targetInstance.status();
                if (status == TargetStatus.Tracked) {
                    int id = target.runtimeID();
                    if (active_target != 0 && active_target != id) {
                        video.onLost();
                        video.dispose();
                        video = null;
                        tracked_target = 0;
                        active_target = 0;
                    }
                    if (tracked_target == 0) {
                        if (video == null && video_renderers.size() > 0) {
                            String target_name = target.name();
                            if (target_name.equals("argame") && video_renderers.get(0).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/2017-12-25_14.09.23_c38ldrE.mp4", video_renderers.get(0).texId());
                                current_video_renderer = video_renderers.get(0);
                            } else if (target_name.equals("01") && video_renderers.get(1).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P001.mp4", video_renderers.get(1).texId());
                                current_video_renderer = video_renderers.get(1);
                            } else if (target_name.equals("02") && video_renderers.get(2).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P02.mp4", video_renderers.get(2).texId());
                                current_video_renderer = video_renderers.get(2);
                            }
                            else if (target_name.equals("03") && video_renderers.get(3).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P03.mp4", video_renderers.get(3).texId());
                                current_video_renderer = video_renderers.get(3);
                            }
                            else if (target_name.equals("04") && video_renderers.get(4).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P04.mp4", video_renderers.get(4).texId());
                                current_video_renderer = video_renderers.get(4);
                            }
                            else if (target_name.equals("05") && video_renderers.get(5).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P05.mp4", video_renderers.get(5).texId());
                                current_video_renderer = video_renderers.get(5);
                            }
                            else if (target_name.equals("06") && video_renderers.get(6).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P06.mp4", video_renderers.get(6).texId());
                                current_video_renderer = video_renderers.get(6);
                            }
                            else if (target_name.equals("07") && video_renderers.get(7).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P07.mp4", video_renderers.get(7).texId());
                                current_video_renderer = video_renderers.get(7);
                            }
                            else if (target_name.equals("08") && video_renderers.get(8).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P08.mp4", video_renderers.get(8).texId());
                                current_video_renderer = video_renderers.get(8);
                            }
                            else if (target_name.equals("09") && video_renderers.get(9).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P09.mp4", video_renderers.get(9).texId());
                                current_video_renderer = video_renderers.get(9);
                            }
                            else if (target_name.equals("10") && video_renderers.get(10).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P10.mp4", video_renderers.get(10).texId());
                                current_video_renderer = video_renderers.get(10);
                            }
                            else if (target_name.equals("11") && video_renderers.get(11).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P11.mp4", video_renderers.get(11).texId());
                                current_video_renderer = video_renderers.get(11);
                            }
                            else if (target_name.equals("12") && video_renderers.get(12).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P12.mp4", video_renderers.get(12).texId());
                                current_video_renderer = video_renderers.get(12);
                            }
                            else if (target_name.equals("13") && video_renderers.get(13).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P13.mp4", video_renderers.get(13).texId());
                                current_video_renderer = video_renderers.get(13);
                            }
                            else if (target_name.equals("14") && video_renderers.get(14).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P14.mp4", video_renderers.get(14).texId());
                                current_video_renderer = video_renderers.get(14);
                            }
                            else if (target_name.equals("15") && video_renderers.get(15).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P15.mp4", video_renderers.get(15).texId());
                                current_video_renderer = video_renderers.get(15);
                            }
                            else if (target_name.equals("16") && video_renderers.get(16).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/P16.mp4", video_renderers.get(16).texId());
                                current_video_renderer = video_renderers.get(16);
                            }
                            else if (target_name.equals("Haran") && video_renderers.get(17).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/Haran.mp4", video_renderers.get(17).texId());
                                current_video_renderer = video_renderers.get(17);
                            }
                            else if (target_name.equals("Refghe") && video_renderers.get(18).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/Refghe.mp4", video_renderers.get(18).texId());
                                current_video_renderer = video_renderers.get(18);
                            }
                            else if (target_name.equals("Saleh") && video_renderers.get(19).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/Saleh.mp4", video_renderers.get(19).texId());
                                current_video_renderer = video_renderers.get(19);
                            }
                            else if (target_name.equals("Box") && video_renderers.get(20).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("http://94.182.227.211/media/ar_item/videos/Box.mp4", video_renderers.get(20).texId());
                                current_video_renderer = video_renderers.get(20);
                            }
                            else if (target_name.equals("namecard") && video_renderers.get(6).texId() != 0) {
                                video = new ARVideo();
                                video.openTransparentVideoFile("transparentvideo.mp4", video_renderers.get(1).texId());
                                current_video_renderer = video_renderers.get(1);
                            } else if (target_name.equals("idback") && video_renderers.get(2).texId() != 0) {
                                video = new ARVideo();
                                video.openStreamingVideo("https://sightpvideo-cdn.sightp.com/sdkvideo/EasyARSDKShow201520.mp4", video_renderers.get(2).texId());
                                current_video_renderer = video_renderers.get(2);
                            }
                        }
                        if (video != null) {
                            video.onFound();
                            tracked_target = id;
                            active_target = id;
                        }
                    }
                    ImageTarget imagetarget = target instanceof ImageTarget ? (ImageTarget) (target) : null;
                    if (imagetarget != null) {
                        if (current_video_renderer != null) {
                            video.update();
                            if (video.isRenderTextureAvailable()) {
                                current_video_renderer.render(camera.projectionGL(0.2f, 500.f), targetInstance.poseGL(), imagetarget.size());
                            }
                        }
                    }
                }
            } else {
                if (tracked_target != 0) {
                    video.onLost();
                    tracked_target = 0;
                }
            }
        } finally {
            frame.dispose();
        }
    }
}
