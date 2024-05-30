package sample;

import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.scene.image.Image;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_face;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import org.bytedeco.opencv.opencv_face.EigenFaceRecognizer;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.opencv.core.*;
import org.opencv.face.FaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import javax.imageio.ImageIO;

import static org.bytedeco.javacpp.opencv_face.*;

public class FaceRecognize {

	public static String Path;

public static int fn_face_recognize(Mat face_test){
	MatOfInt labelsMat=new MatOfInt();
	Integer[] ids=fn_convert_IDs();
	ArrayList<Integer> list=new ArrayList<>();
	for(int i=0;i<ids.length;i++){
		list.add(ids[i]);
	}
	labelsMat.fromList(list);
	 //opencv_face.LBPHFaceRecognizer recognizer = createLBPHFaceRecognizer();
	 //recognizer.train(fn_crop_mat_of_faces(),);
	//opencv_face.LBPHFaceRecognizer efr=createLBPHFaceRecognizer();
	//efr.train(fn_crop_mat_of_faces(),labelsMat);
	int[] outLabel=new int[1];
	double[] outConf=new double[1];
	opencv_core.Mat mat=null;
   //efr.predict(mat,outLabel,outConf);
	if(outConf[0]<60.00){
		return outLabel[0];
	}
	else return 0;
}



	//return list of mat of faces in database
	public static ArrayList<Mat> fn_faces_mat_fromdatabase(){
	ArrayList<Mat> mat=new ArrayList<>();
	Integer[] ids= fn_convert_IDs();
	for(int i=0;i<ids.length;i++)
	{
		mat.add(fn_convert_images(ids[i]));
	}
		return mat;
	}

	//crop face from mat of images from database and return them
	public static ArrayList<Mat> fn_crop_mat_of_faces(){
		ArrayList<Mat> mat=fn_faces_mat_fromdatabase();
		ArrayList<Mat> newmat=new ArrayList<>();
		MatOfRect faceDetections = new MatOfRect();
		CascadeClassifier faceDetector=new CascadeClassifier("src/face detection/haarcascade_frontalface.xml");
		for(int i=0;i<mat.size();i++)
		{
			faceDetector.detectMultiScale( mat.get(i), faceDetections);
			for (Rect rect:faceDetections.toArray()) {
				newmat.add(mat.get(i).submat(rect));
			}

		}
		return newmat;

	}
	private static byte[] readStream(InputStream stream) throws IOException {
		// Copy content of the image to byte-array
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[524288]; // 16384

		while ((nRead = stream.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();
		byte[] temporaryImageInMemory = buffer.toByteArray();
		buffer.close();
		stream.close();
		return temporaryImageInMemory;
	}

	//convert pics in database to mat list for compare
	public static Mat fn_convert_images(int id){
		Mat img=new Mat();
		try {
			Statement st = databaseconnection.test_database();
			ResultSet rs=st.executeQuery("select image from aircare.dbo.Person where ID='"+id +"'");
			while(rs.next()) {
				InputStream is = rs.getBinaryStream("image");
				byte[] temporaryImageInMemory = readStream(is);
				img = Imgcodecs.imdecode(new MatOfByte(temporaryImageInMemory), -1);

			}
		} catch (SQLException | IOException throwables) {
			throwables.printStackTrace();
		}
		return img;
	}

	//get ID of images
	public static Integer[] fn_convert_IDs(){
	// i used integer array instead of an arraylist because it was duplicating values for no reason
	int i=0;
		ResultSet rs=null;
		try {
			Statement st = databaseconnection.test_database();
			rs=st.executeQuery("select ID from aircare.dbo.Person");

			while(rs.next()) {
				i++;
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		Integer[] ids=new Integer[i];
		try {
			Statement st = databaseconnection.test_database();
			rs=st.executeQuery("select ID from aircare.dbo.Person");
           int x=0;
			while(rs.next()&& x<i) {
				ids[x]=rs.getInt("ID");
				x++;
			}



		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

       return ids;
	}


}