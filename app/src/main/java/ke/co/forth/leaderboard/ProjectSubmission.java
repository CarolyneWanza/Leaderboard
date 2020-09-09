package ke.co.forth.leaderboard;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.isEmpty;

public class ProjectSubmission extends AppCompatActivity {
    Button btnSubmit;
    EditText etFirstName, etLastName, etEmailAddress, etGithubLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        setTitle("");

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
        etGithubLink = (EditText) findViewById(R.id.etGithubLink);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(etFirstName.getText().toString())&&!isEmpty(etLastName.getText().toString())&&!isEmpty(etEmailAddress.getText().toString())
                        &&!isEmpty(etGithubLink.getText().toString())){
                    confirmSubmission(etFirstName.getText().toString(),etLastName.getText().toString(),etEmailAddress.getText().toString(),etGithubLink.getText().toString());
                }
                else{
                        etFirstName.setError("required!");
                        etLastName.setError("required!");
                        etEmailAddress.setError("required!");
                        etGithubLink.setError("required!");
                        Toast.makeText(ProjectSubmission.this, "Please enter all the input fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void confirmSubmission(String fname,String lname,String email,String glink){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProjectSubmission.this);
        View view=getLayoutInflater().inflate(R.layout.dialog,null);
        builder.setView(view);
        // Create the AlertDialog object and return it
        AlertDialog alert=builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        Button btnYes=view.getRootView().findViewById(R.id.btnYes);
        ImageButton imgBtnCancel=view.getRootView().findViewById(R.id.imgBtnCancel);
        imgBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.cancel();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
                new ProjectSubmission.RetrofitPostRequest(fname,lname,email,glink);
            }
        });

        alert.show();
    }

    private void alertSubmissionSuccessful(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.submission_successful,null);
        alertDialog.setView(view);
        AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();
    }

    private void alertSubmissionFailed(){
        AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
        View view=getLayoutInflater().inflate(R.layout.submission_not_successful,null);
        alertDialog.setView(view);
        AlertDialog alert=alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        alert.setCancelable(true);
        alert.show();
    }

    public class RetrofitPostRequest {
        public RetrofitPostRequest(String firstName, String lastName, String email, String githubLink) {
            ApiInterface poster = ApiPost.getRetrofit().create(ApiInterface.class);
            Call<Void> call = poster.sendProject(firstName,lastName,email,githubLink);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        alertSubmissionSuccessful();
                    }
                    else{
                        alertSubmissionFailed();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    alertSubmissionFailed();
                }
            });
        }
    }
}
