package io.github.carlinhoshk.benchmark;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {
    static {
        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Carrega a imagem da pasta de recursos
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sample_image);

        // Converte o Bitmap em uma matriz OpenCV
        Mat originalMat = new Mat();
        Utils.bitmapToMat(originalBitmap, originalMat);

        // Mede o tempo inicial
        long startTime = System.currentTimeMillis();

        // Aplica um filtro de desfoque gaussiano à imagem
        Mat blurredMat = new Mat();
        Imgproc.GaussianBlur(originalMat, blurredMat, new Size(5, 5), 0);

        // Converte a matriz resultante de volta para Bitmap
        Bitmap blurredBitmap = Bitmap.createBitmap(blurredMat.cols(), blurredMat.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(blurredMat, blurredBitmap);

        // Mede o tempo final
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        // Cria um layout linear vertical
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        // Cria a ImageView
        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(blurredBitmap);
        layout.addView(imageView);

        // Exibe o tempo decorrido em um TextView
        TextView textView = new TextView(this);
        textView.setText("Tempo decorrido: " + elapsedTime + " ms");
        layout.addView(textView);

        // Define o layout como conteúdo da atividade
        setContentView(layout);
    }
}