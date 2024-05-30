package sample;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


import com.mgnt.utils.TimeUtils;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.face.EigenFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;

import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;


public class Camera implements Initializable {


    CascadeClassifier faceDetector;
    VideoCapture videoCapture;

    Canvas canvas;
    GraphicsContext g2d;
    AnimationTimer timer;
    @FXML
    Pane pane;
    @FXML
    AnchorPane main_pn;
    int i=0;
    @FXML
    MediaView video;
    @FXML
    ImageView test;
public void fn(){

    ArrayList<Mat> m=FaceRecognize.fn_crop_mat_of_faces();

        Image d=mat2Image(m.get(0));
        ImageView v=new ImageView();
        v.setImage(d);
      main_pn.getChildren().add(v);


}


    public void start() {
//fn();

        Get_user.setID(1);
        Get_user.load_user();
        MediaPlayer Vid = new MediaPlayer(new Media(new File("src/videos/intro.mp4").toURI().toString()));
       video.setMediaPlayer(Vid);
       Vid.setMute(true);
       Vid.setCycleCount(MediaPlayer.INDEFINITE);
       Vid.play();

        initOpenCv();
        canvas = new Canvas(674, 570);
        g2d = canvas.getGraphicsContext2D();
        g2d.setStroke(Color.BLUE);
        Group group = new Group(canvas);
        pane.getChildren().add(group);

        timer = new AnimationTimer() {

            Mat mat = new Mat();

            @Override
            public void handle(long now) {

                videoCapture.read(mat);

                List<Rectangle2D> rectList = detectFaces(mat);
                Mat face=fn_crop_face_from_mat(mat,rectList);
              //  int detected=FaceRecognize.fn_face_recognize(face);
               /* if(detected !=0){
                    Get_user.setID(detected);
                    Get_user.load_user();
                        new Thread(){
                            public void run(){

                                TimeUtils.sleepFor(1, TimeUnit.SECONDS);

                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {

                                        try {
                                            timer.stop();
                                            videoCapture.release();
                                            pane = FXMLLoader.load(getClass().getResource("passenger_home.fxml"));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        Stage stage = new Stage();
                                        Scene scene = new Scene(pane);
                                        stage.setScene(scene);
                                        Image icon = new Image(getClass().getResourceAsStream("/icons/icon_logo.png"));
                                        stage.getIcons().add(icon);
                                        stage.initStyle(StageStyle.UNDECORATED);
                                        stage.show();
                                        stage.centerOnScreen();
                                        main_pn.getScene().getWindow().hide();


                                        javafx.scene.media.AudioClip help = new javafx.scene.media.AudioClip(this.getClass().getResource("/audio/help.mp3").toString());
                                        help.play();

                                    }
                                });
                            }
                        }.start();

                }
              */

                //test.setImage(mat2Image(face));
                Image image = mat2Image(mat);
                String name=new String();
                name="";
                g2d.drawImage(image, 0, 0);

                for (Rectangle2D rect : rectList) {

                    g2d.strokeRect(rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());
                    g2d.strokeText(name,rect.getMinX(),rect.getMinY()-5);
                    g2d.setFont(Font.font("Aerial",30.00));
                    //g2d.drawImage(im,rect.getMinX(),rect.getMinY()-120,120,100);
                }

            }
        };
        timer.start();

    }

    public List<Rectangle2D> detectFaces(Mat mat) {

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale( mat, faceDetections);
       //faceDetector.detectMultiScale( mat, m);
       // Mat ROI = mat.submat();

        List<Rectangle2D> rectList = new ArrayList<>();
        List<Rectangle2D> rectList2 = new ArrayList<>();
        for (Rect rect : faceDetections.toArray()) {

            int x = rect.x;
            int y = rect.y;
            int w = rect.width;
            int h = rect.height;
            rectList.add(new Rectangle2D(x, y, w, h));
        }

if(faceDetections.toArray().length>100){
    if(i==0){
        i=1;
    new Thread(){
        public void run(){

                TimeUtils.sleepFor(1, TimeUnit.SECONDS);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        timer.stop();
                        videoCapture.release();
                        pane = FXMLLoader.load(getClass().getResource("passenger_home.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage stage = new Stage();
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                    Image icon = new Image(getClass().getResourceAsStream("/icons/icon_logo.png"));
                    stage.getIcons().add(icon);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();
                    stage.centerOnScreen();
                    main_pn.getScene().getWindow().hide();


                        javafx.scene.media.AudioClip help = new javafx.scene.media.AudioClip(this.getClass().getResource("/audio/help.mp3").toString());
                        help.play();

                }
            });
        }
    }.start();
    }
}
        return rectList;
    }

    public Mat fn_crop_face_from_mat(Mat mat,List<Rectangle2D> list){
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale( mat, faceDetections);
        Mat cropped_face=new Mat();
        for (Rect rect : faceDetections.toArray()) {
            cropped_face= mat.submat(rect);
        }
        return cropped_face;
    }

    private void initOpenCv() {setLibraryPath();
        videoCapture = new VideoCapture();
        videoCapture.open(0);
        faceDetector = new CascadeClassifier("src/face detection/haarcascade_frontalface.xml");
    }

    public static Image mat2Image(Mat mat) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", mat, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }

    private static void setLibraryPath() {

        try {

            System.setProperty("java.library.path", "lib/x64");
            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        start();

    }
}