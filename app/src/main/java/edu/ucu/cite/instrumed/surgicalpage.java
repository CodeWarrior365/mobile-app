package edu.ucu.cite.instrumed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class surgicalpage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle mtoggle=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgicalpage);

        drawerLayout =(DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView=findViewById(R.id.navigationdrawer);

        navigationView.bringToFront();
        mtoggle =new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.home);

        final Button button1=findViewById(R.id.cutting);
        final Button button2=findViewById(R.id.clamping);
        final Button button3=findViewById(R.id.grasping);
        final Button button4=findViewById(R.id.retracting);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(surgicalpage.this,surgicalcutting.class);
                ActivityOptionsCompat optionsCompat =ActivityOptionsCompat.makeSceneTransitionAnimation(surgicalpage.this,button1, ViewCompat.getTransitionName(button1));
                startActivity(a, optionsCompat.toBundle());
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(surgicalpage.this,surgicalclamping.class);
                ActivityOptionsCompat optionsCompat =ActivityOptionsCompat.makeSceneTransitionAnimation(surgicalpage.this,button2, ViewCompat.getTransitionName(button2));
                startActivity(a, optionsCompat.toBundle());
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(surgicalpage.this,surgicalgrasping.class);
                ActivityOptionsCompat optionsCompat =ActivityOptionsCompat.makeSceneTransitionAnimation(surgicalpage.this,button3, ViewCompat.getTransitionName(button3));
                startActivity(a, optionsCompat.toBundle());
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(surgicalpage.this,surgicalretracting.class);
                ActivityOptionsCompat optionsCompat =ActivityOptionsCompat.makeSceneTransitionAnimation(surgicalpage.this,button4, ViewCompat.getTransitionName(button4));
                startActivity(a, optionsCompat.toBundle());
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mtoggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.home:
                Intent intent=new Intent(surgicalpage.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.share:
                try {
                    Intent rateintent =new Intent(Intent.ACTION_SEND);
                    rateintent.setType("text/plain");
                    rateintent.putExtra(Intent.EXTRA_SUBJECT,"ElemixAR");
                    rateintent.putExtra(Intent.EXTRA_TEXT,"http://play.google.com/store/apps/details?id=" + getPackageName());
                    startActivity(Intent.createChooser(rateintent,"Share With"));


                }catch (Exception e) {
                    Toast.makeText(this,"Unable to share this app.",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rate:
                Uri uri =Uri.parse("http://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                Intent i =new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(this,"Enable to open\n"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.about:
                Intent aboutintent=new Intent(surgicalpage.this,About.class);
                startActivity(aboutintent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }


}