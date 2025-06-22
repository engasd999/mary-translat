public class MainActivity extends AppCompatActivity {
    private static final int PICK_VIDEO_REQUEST = 1;
    private ActivityMainBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupUI();
        checkPermissions();
    }

    private void setupUI() {
        binding.btnSelectVideo.setOnClickListener(v -> openVideoPicker());
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري معالجة الفيديو...");
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) 
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, 
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 
                101);
        }
    }

    // ... باقي الكود
}