public class Translator {
    private final Context context;

    public Translator(Context context) {
        this.context = context;
    }

    public void translate(String text) {
        // استخدام API مجاني مثل LibreTranslate
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://libretranslate.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        TranslateAPI api = retrofit.create(TranslateAPI.class);
        Call<TranslationResponse> call = api.translate(text, "en", "ar"); // من الإنجليزية للعربية

        call.enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.isSuccessful()) {
                    String translatedText = response.body().getTranslatedText();
                    ((MainActivity)context).updateUI(translatedText);
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                Log.e("Translation", "Error: " + t.getMessage());
            }
        });
    }
}