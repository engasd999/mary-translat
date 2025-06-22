public class OCRHelper {
    private final TextRecognizer recognizer;
    private final Context context;

    public OCRHelper(Context context) {
        this.context = context;
        this.recognizer = TextRecognition.getClient();
    }

    public void extractTextFromFrames(String framesDir) {
        File dir = new File(framesDir);
        File[] frames = dir.listFiles();

        for (File frame : frames) {
            InputImage image = InputImage.fromFilePath(context, Uri.fromFile(frame));
            recognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    String extractedText = visionText.getText();
                    new Translator(context).translate(extractedText);
                });
        }
    }
}