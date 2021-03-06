package shr;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.ClarifaiImage;
import clarifai2.dto.model.Model;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import clarifai2.dto.prediction.Prediction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class DetectImage {
    public static String detectImage(BufferedImage b) throws IOException{
        final ClarifaiClient client = new ClarifaiBuilder("eec01429deb44fb591371f69d9fd6ee2").buildSync();
        byte[] imageInByte = BImageToByte(b);

        Model<Concept> generalModel = client.getDefaultModels().generalModel();

        // client.predict("{model_id}").withInputs(ClarifaiInput.forImage("https://samples.clarifai.com/puppy.jpeg")).executeSync();

        List<ClarifaiOutput<Prediction>> predictionResults =
                client.predict("CarHack") // You can also do client.getModelByID("id") to get custom models
                        .withInputs(
                                ClarifaiInput.forImage(ClarifaiImage.of(imageInByte))
                        )
                        .executeSync().get();

        for (ClarifaiOutput<Prediction> oc: predictionResults) {
            double max = 0;
            String result = "";
            List<Prediction> predictionData = oc.data();
            for(Prediction p:predictionData) {
                Concept c = p.asConcept();
                if (c.value() > max) {
                    System.out.println(c.name() + ": " + c.value());
                    max = c.value();
                    result = c.name();
                }
            }
            if (max > 0.8) {
                return result;
            }
        }
        return "";
    }

    // converts image representation to bytes
    private static byte[] BImageToByte(BufferedImage b) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(b, "jpg", baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }
}
